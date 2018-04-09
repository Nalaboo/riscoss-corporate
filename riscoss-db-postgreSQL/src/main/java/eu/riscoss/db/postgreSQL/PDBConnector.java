package eu.riscoss.db.postgreSQL;

import org.apache.commons.codec.binary.Base64;

import eu.riscoss.db.RiscossDBDomain;

public class PDBConnector {
	public static String db_addr = null;
	
	public static void initDatabase( String dbaddr ) {
		db_addr = dbaddr;
	}
	
	/**
	 * Opens the database with username and password, specific for "superuser" access to change domains and users.
	 * @param username
	 * @param password
	 * @return
	 */
	public static RiscossDBDomain openPRiscossDBDomain( String username, String password ) throws Exception {
		try {
			return new PRiscossDBDomain( db_addr, username, password );
		}
		catch( Exception ex ) {
			throw ex; 
		}
	}
	/**
	 * Opens the database with a previously stored token (e.g. from a cookie), specific for "superuser" access to change domains and users.
	 * @param token
	 * @return
	 */
	public static RiscossDBDomain openPRiscossDBDomain( String token ) throws Exception {
		try {
			return new PRiscossDBDomain( db_addr, Base64.decodeBase64( token ) );
		}
		catch( Exception ex ) {
			throw ex; 
		}
	}
	
	public static void closePRiscossDBDomain( RiscossDBDomain db ) {
		if( db == null ) return;
		try {
			db.close();
		}
		catch( Exception ex ) {
			ex.printStackTrace();
		}
		finally {
		}
	}
}
