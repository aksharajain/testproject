package com.client;

//import java.sql.Connection;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getappoint(String s, AsyncCallback<String[][]> callback) throws IllegalArgumentException;
	void getappointtd(String s,AsyncCallback<String[][]> callback) throws IllegalArgumentException;
	void gettfee(String s,AsyncCallback<Integer> callback)throws IllegalArgumentException;
	void getpatient(int no,String s,AsyncCallback<String[]> callback)throws IllegalArgumentException;
	void insert(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8, String s9,String s10,String s11, AsyncCallback<String> callback)
		      throws IllegalArgumentException;
	void check(String s1,String s2, AsyncCallback<String> callback) throws IllegalArgumentException;
	void insertdc(String s1,String s2,String s3,String s4,String s5,String s6, AsyncCallback<String> callback)throws IllegalArgumentException;
	void regclinic(String s1,String s2,String s3,String s4, AsyncCallback<Integer> callback)throws IllegalArgumentException;
	void validclinic(String s1,String s2,AsyncCallback<Integer> callback)throws IllegalArgumentException;
}
