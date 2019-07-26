package com.server;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.client.GreetingService;
import com.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	String [][] ar= {{"Fri Jun 21","1","name1 surname"},{"Fri Jun 21","2","name2"},{"Sat Jun 22","2","name3"},
    		{"Sun Jun 23","1","name4"},{"Fri Jun 21","3","name3"},{"Sat Jun 22","3","name2"},
    		{"Sat Jun 22","4","name3"},{"Fri Jun 21","4","name4"},{"Sun Jun 23","4","name5"}};
	Connection con=null;
	Statement st=null;
	@Override
	public String[][] getappoint(String date) throws IllegalArgumentException {
		int i=0,j=0;
		String [][] rt=new String[10][3];
		while(i<ar.length) {
			if(ar[i][0].equals(date))
			{
				rt[j][1]=ar[i][1];
			    rt[j][2]=ar[i][2];
			    j++;
		}
			i++;
		}
		return rt;
	}
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/docassisto", "root", "root");
				st=con.createStatement();
		}
		catch(Exception e) {
			
		}
		
	}
	@Override
	public String[][] getappointtd(String s) throws IllegalArgumentException {
		String[][] rt =new String[20][3];
		String temp="\""+s+"\"";
		init();
			ResultSet rs=null;
			try {
				rs = st.executeQuery("select * from appoint where date ="+temp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i=0;
			try {
				while(rs.next())
				{
					rt[i][1]=rs.getString(2);
					rt[i][2]=rs.getString(3);
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rt;
	}
	@Override
	public int gettfee(String s) throws IllegalArgumentException {
		ResultSet rs=null;
		int res=0;
		String temp="\""+s+"\"";
		try {
			rs=st.executeQuery("select sum(fee) from appoint where date="+temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.next();
			res=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public String[] getpatient(int no, String s) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String[] res=new String[11];
		ResultSet rs=null;
		String temp="\""+s+"\"";
		try {
			rs=st.executeQuery("select * from appoint where number="+Integer.toString(no)+" and date="+temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.next();
			ResultSetMetaData rsmd=rs.getMetaData();
			int k=0;
			while(k<11) {
				res[k]=rsmd.getColumnName(k+1)+" - "+rs.getString(k+1);
				k++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
	//create appointment
	public String insert(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8,
			String s9, String s10, String s11) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String ss="No";
		int k=0;
		init();
		PreparedStatement pt1=null;
		try {
			pt1 = con.prepareStatement("insert into appoint values(?,?,?,?,?,?,?,?,?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			pt1.setString(1, s1);
			pt1.setInt(2, Integer.parseInt(s2));
			pt1.setString(3, s3);
			pt1.setInt(4, Integer.parseInt(s4));
			pt1.setString(5, s5);
			pt1.setInt(6, Integer.parseInt(s6));
			pt1.setString(7, s7);
			pt1.setString(8, s8);
			pt1.setString(9, s9);
			pt1.setString(10, s10);
			pt1.setString(11, s11);
			k=pt1.executeUpdate();
			if(k!=0)
			{
				ss="yes";
				k=1;
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ss;
	}
	//login
	public String check(String name, String pass) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		init();
		String s1=  "NO";
		ResultSet rs=null;
		try {
			rs=st.executeQuery("select * from clinic where uname='" +name+"' and pass='" + pass + "'");
			if(rs.next())
			{
				s1=rs.getString(3);
			}
		
		}catch (SQLException e) {
		
		}
		return s1;
	}
	//register of doctor or compounder
	public String insertdc(String s1, String s2, String s3, String s4, String s5, String s6) throws IllegalArgumentException { 
		// TODO Auto-generated method stub
		String ss="No";
		int k=0;
		init();
		PreparedStatement pt1=null;
		try {
			pt1 = con.prepareStatement("insert into clinic values(?,?,?,?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			pt1.setString(1, s1);
			pt1.setString(2,s2);
			pt1.setString(3, s3);
			pt1.setString(4,s4);
			pt1.setString(5, s5);
			pt1.setString(6,s6);
			k=pt1.executeUpdate();
			if(k!=0)
			{
				ss="yes";
				k=1;
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ss;
	}
	//subscription
	public int regclinic(String s1, String s2, String s3, String s4) throws IllegalArgumentException{
		int r=0;
		init();
		PreparedStatement pt1=null;
		try {
			pt1 = con.prepareStatement("insert into clinicname values(?,?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			pt1.setString(1, s1);
			pt1.setString(2,s2);
			pt1.setString(3, s3);
			pt1.setString(4,s4);
			r = pt1.executeUpdate();
		}
		catch(SQLException e) {
			
		}
		return r;
		
	}
	public int validclinic(String s1, String s2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		init();
		int r=0;
		ResultSet rs=null;
		try {
			rs=st.executeQuery("select * from clinicname where cname='" +s1+"' and cpass='" + s2 + "'");
			if(rs.next())
			{
				r=1;
			}
		
		}catch (SQLException e) {
		
		}
		return r;
	}
	
}
