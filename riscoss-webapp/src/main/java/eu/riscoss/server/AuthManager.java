package eu.riscoss.server;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonPrimitive;
import com.orientechnologies.orient.core.metadata.security.ORole;
import com.orientechnologies.orient.core.metadata.security.OSecurity;
import com.orientechnologies.orient.core.metadata.security.OSecurityRole;
import com.orientechnologies.orient.core.metadata.security.OSecurityUser;
import com.orientechnologies.orient.server.config.OServerParameterConfiguration;
import com.orientechnologies.orient.server.token.OrientTokenHandler;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

import eu.riscoss.db.ODBConnector;
import eu.riscoss.db.RiscossDBDomain;
import eu.riscoss.db.postgreSQL.PDBConnector;
import eu.riscoss.shared.KnownRoles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")
@Info("Authentication and Authorization")
public class AuthManager {
	
	private static final OServerParameterConfiguration[] I_PARAMS = new OServerParameterConfiguration[] { 
		new OServerParameterConfiguration( OrientTokenHandler.SIGN_KEY_PAR, "any key"),
		new OServerParameterConfiguration( OrientTokenHandler.SESSION_LENGHT_PAR, "525600000" ) // ( 1000* 60 * 24 * 365 ) ) = 1 year
	};
	static Boolean isPostgreSQLON = true;
	
	/**
	 * Logs in on the DB
	 * @param username
	 * @param password
	 * @return the new token
	 * @throws Exception
	 */
	@POST @Path("/login")
	@Info("This function authenticates the user and returns a token that can be reused (untile the session expires) to call functions that require authentication")
	public String login( 
			@HeaderParam("username") String username, 
			@HeaderParam("password") String password ) throws Exception {
		
//		System.out.println("#### DB address "+new File(DBConnector.db_addr).getAbsolutePath()+" ####");
		
		/*if(isPostgreSQLON)
		{
			String tokenP = getStringTokenJJWT(username, password );
			return tokenP;
		}
		else
		{*/
			OrientGraphNoTx graph = new OrientGraphFactory( ODBConnector.db_addr, username, password ).getNoTx();
			
			try {
				
				String token = getStringToken( graph );
				
	//			System.out.println( "Login succeeded. Token:" );
	//			System.out.println( token );
	//			System.out.println( EncodingUtil.encrypt( username + "\n" + password ) );
				
				return new JsonPrimitive( token ).toString();
			}
			finally {
				if( graph != null )
					graph.getRawGraph().close();
			}
		//}
	}
	
	@GET @Path("token")
	@Info("This function performs a validity check of the token and return successfully if the token is correct and not expired")
	//TODO: change to POST?!
	public String checkToken( 
			@HeaderParam("token") @Info("The authentication token") 			String token
			) throws Exception {
		
		RiscossDBDomain db = null;
		try {
			if(isPostgreSQLON)
			{
				db = PDBConnector.openPRiscossDBDomain(token);
			}
			else
			{
				db = ODBConnector.openORiscossDBDomain( token );
			}
		}
		catch( Exception ex ) {
			throw ex;
		}
		finally {
			if(!isPostgreSQLON)
			{
				ODBConnector.closeRiscossDBDomain( db );
			}
		}
		return new JsonPrimitive( "Ok" ).toString();
	}
	
	@POST @Path("/register")
	@Info("Registers a new user into the database")
	public String register(
			@HeaderParam("username") String username, 
			@HeaderParam("password") String password ) throws Exception {
	/*	if(isPostgreSQLON)
		{
			RiscossDBDomain db = null;
			db = PDBConnector.openPRiscossDBDomain(username, password);
			db.createUser(username, password);
			return new JsonPrimitive( "Ok" ).toString();
		}
		else
		{
			OrientGraphNoTx graph = new OrientGraphFactory( ODBConnector.db_addr ).getNoTx();
			
			try {
				OSecurity security = graph.getRawGraph().getMetadata().getSecurity();
				
				ORole guest = security.getRole( KnownRoles.Consumer.name() );
				
				if( guest == null ) {
					guest = security.createRole( KnownRoles.Consumer.name(), OSecurityRole.ALLOW_MODES.ALLOW_ALL_BUT );
				}
				
				security.createUser( username, password, guest );
				
				// Already return the login token?
	//			graph.getRawGraph().close();
	//			
	//			graph = new OrientGraphFactory( DBConnector.db_addr, username, password ).getNoTx();
	//			
	//			return new JsonPrimitive( getStringToken( graph ) ).toString();
				
				return new JsonPrimitive( "Ok" ).toString();
			}
			finally {
				if( graph != null )
					graph.getRawGraph().close();
			}*/
			RiscossDBDomain db = null;
			db = PDBConnector.openPRiscossDBDomain(username, password);
			db.createUser(username, password);

			OrientGraphNoTx graph = new OrientGraphFactory( ODBConnector.db_addr ).getNoTx();
			
			try {
				OSecurity security = graph.getRawGraph().getMetadata().getSecurity();
				
				ORole guest = security.getRole( KnownRoles.Consumer.name() );
				
				if( guest == null ) {
					guest = security.createRole( KnownRoles.Consumer.name(), OSecurityRole.ALLOW_MODES.ALLOW_ALL_BUT );
				}
				security.createUser( username, password, guest );
				
				return new JsonPrimitive( "Ok" ).toString();
			}
			finally {
				if( graph != null )
					graph.getRawGraph().close();
			}
		
	}
	
	String getStringTokenJJWT( String username, String password ) throws IOException {
		String jwt = Jwts.builder()
				  .setSubject("users/TzMUocMF4p")
				  .setExpiration(new Date(1300819380))
				  .claim("username", username)
				  .claim("password", password)
				  .signWith(
				    SignatureAlgorithm.HS256,
				    "secret".getBytes("UTF-8")
				  )
				  .compact();
		
		return  jwt;
	}
	
	String getStringToken( OrientBaseGraph graph ) {
		OSecurityUser original = graph.getRawGraph().getUser();
		OrientTokenHandler handler = new OrientTokenHandler();
		handler.config(null, I_PARAMS);
		byte[] token = handler.getSignedWebToken( graph.getRawGraph(), original );
		
		return Base64.encodeBase64String( token );
	}
	
	@GET @Path("/username")
	@Info("Returns the user name corresponding to the given token (if valid)")
	public String getUsername( 
			@HeaderParam("token") @Info("The authentication token")			String token ) {
		
		RiscossDBDomain database = null;
		
		try {
			if(isPostgreSQLON)
			{
				database = PDBConnector.openPRiscossDBDomain(token);
			}
			else
			{
				database = ODBConnector.openORiscossDBDomain( token );
			}
			
			String username = database.getUsername();
			
			return new JsonPrimitive( username ).toString();
		}
		catch( Exception ex ) {
			return new JsonPrimitive( "Error" ).toString();
		}
		finally {
			if(!isPostgreSQLON)
			{
				ODBConnector.closeRiscossDBDomain(database);
			}		
		}
		
	}
}
