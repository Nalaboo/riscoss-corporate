/*
   (C) Copyright 2013-2016 The RISCOSS Project Consortium
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

/**
 * @author 	Alberto Siena
**/

package eu.riscoss.client.riskconfs;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import eu.riscoss.client.RiscossJsonClient;
import eu.riscoss.client.SimpleRiskCconf;

public class RCPropertyPage implements IsWidget {
	
	TabPanel tab = new TabPanel();
	
	@Override
	public Widget asWidget() {
		return tab;
	}
	
	SimpleRiskCconf rc;
	
	
	ModelList mlist = new ModelList( new ModelList.Listener() {
		@Override
		public void onModelsSelected( String layer, List<String> models ) {
			rc.setModelList( layer, models );
			saveRC();
		}
	});
	
	
	public RCPropertyPage( SimpleRiskCconf rc ) {
		
		VerticalPanel p = new VerticalPanel();
		HorizontalPanel h = new HorizontalPanel();
		h.add( new Label( "Name: " ) );
		TextBox txt = new TextBox();
		txt.setText( rc.getName() );
		txt.setWidth( "100%" );
		h.add( txt );
		p.add( h );
		p.add( new Label( "Models per layer:" ) );
		
//		Grid grid = new Grid();
//		JSONArray array = 
//				response.isObject().get( "models" ).isArray();
//		grid.resize( array.size(), 2 );
//		for( int i = 0; i < array.size(); i++ ) {
//			grid.setWidget( i, 0, new Label( 
//					array.get( i ).isObject().get( "model" ).isString().stringValue() ) );
//		}
//		p.add( grid );
//		p.add( new Button( "Change", new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				NewModelDialog dialog = new NewModelDialog();
//				dialog.show( currentRC.get( "name" ).isString().stringValue() );
//			}} ) );
		
		p.add( mlist );
		
		try {
			loadModels( rc );
		}
		catch( Exception ex ) {
			Window.alert( ex.getMessage() );
		}
		

		tab.add( p, "Properties" );
		tab.selectTab( 0 );
		tab.setSize( "100%", "100%" );
	}
	
	protected void saveRC() {
		RiscossJsonClient.setRCContent( rc.getName(), rc, new JsonCallback() {
			@Override
			public void onSuccess(Method method, JSONValue response) {
			}
			@Override
			public void onFailure(Method method, Throwable exception) {
				Window.alert( exception.getMessage() );
			}
		});
	}
	
	private void loadModels( SimpleRiskCconf rc ) {
		
		if( rc == null ) return;
		
		List<String> layers = new ArrayList<String>();
		
		for( int i = 0; i < rc.getLayerCount(); i++ ) {
			layers.add( rc.getLayer( i ) );
		}
		
		mlist.init( layers );
		
		for( int i = 0; i < rc.getLayerCount(); i++ ) {
			mlist.setModels( rc.getLayer( i ), rc.getModelList( rc.getLayer( i ) ) );
		}
		
		this.rc = rc;
	}
	
}