package com.client;



import com.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.*;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Docassisto implements EntryPoint {

	@Override
	public void onModuleLoad() {
		MaterialImage mimg=new MaterialImage();
		mimg.setUrl("https://diginomica.com/sites/default/files/images/2017-01/computer-1149148_1280.jpg");
		mimg.setWidth("100%");
		mimg.setHeight("100%");
		mimg.setOpacity(0.8);
		RootPanel.get("body").add(mimg);
		
		MaterialNavBar mnb=new MaterialNavBar();
		mnb.setWidth("100%");
		mnb.setBackgroundColor(Color.BLUE);
		mnb.setActivates("sidenavDrawer");
		
		MaterialSideNavDrawer msnv=new MaterialSideNavDrawer();
		msnv.setId("sidenavDrawer");
		msnv.setWidth("300");
		MaterialLink ml1=new MaterialLink("Subscribe");
		ml1.setIconType(IconType.BUSINESS_CENTER);
		MaterialLink ml5=new MaterialLink("About us");
		ml5.setIconType(IconType.FACE);
		MaterialLink ml6=new MaterialLink("Contact us");
		ml6.setIconType(IconType.PHONE);
		MaterialLink ml7=new MaterialLink("Help");
		ml7.setIconType(IconType.HELP);
		msnv.add(ml1);
		msnv.add(ml5);
		msnv.add(ml6);
		msnv.add(ml7);
		msnv.setBackgroundColor(Color.WHITE);
		
		MaterialNavBrand mnbr =new MaterialNavBrand("Doc Assisto");
		mnbr.setFloat(Float.LEFT);
		MaterialNavSection mns=new MaterialNavSection();
		mns.setFloat(Float.RIGHT);
		MaterialLink ml2=new MaterialLink("Login");
		MaterialLink ml3=new MaterialLink("Register");
		ml1.setFontSize("20px");
		ml2.setFontSize("20px");
		ml3.setFontSize("20px");
		ml5.setFontSize("20px");
		ml6.setFontSize("20px");
		ml7.setFontSize("20px");
		mnbr.setMarginLeft(20);
		mnbr.setWidth("30%");
		mns.add(ml3);
		mns.add(ml2);
		ml2.setIconType(IconType.ACCOUNT_CIRCLE);
		ml3.setIconType(IconType.REDEEM);
		ml2.setFloat(Float.RIGHT);
		ml3.setFloat(Float.RIGHT);
	 
		mnb.add(mnbr);
		mnb.add(mns);
		mnb.add(msnv);
		RootPanel.get("navbar").add(mnb);
//subscription
		ml1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
		
               disp dsp=new disp();
               RootPanel.get("navbar").clear();
               RootPanel.get("body").clear();
               RootPanel.get("body").add(dsp);
							
			}
		});	
		//login
       ml2.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
		
				MaterialDialog md= new MaterialDialog();
				md.setBackgroundColor(Color.GREY_LIGHTEN_4);
				md.setTextAlign(TextAlign.CENTER);
				md.setSize("36%","60%");
				md.add(new login());
				RootPanel.get().add(md);
				md.open();
						
			}
		});	
       //register
       ml3.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Register rg =new Register();
			MaterialDialog md= new MaterialDialog();
			md.setBackgroundColor(Color.GREY_LIGHTEN_4);
			md.setTextAlign(TextAlign.CENTER);
			md.setSize("80%","100%");
			md.add(rg);
			RootPanel.get().add(md);
			md.open();
		}
	});
	}
}
