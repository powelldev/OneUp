package com.teambad.oneup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Statement;


public class OneUpDb {
	private String userName ="OneUpDbAdmin";
	private String password = "OneUpMu14";
	private String dbms = "mysql";
	private String serverName = "oneupdb.cx0pyxmofvod.us-west-2.rds.amazonaws.com";
	private String portNumber = "3306";
	private String dbName = "OneUpDb";
	private String select = "Select * from OneUpDb.Challenges;";
	private Connection conn;
	private Statement stmt;
	private List<Challenge> challenges;
	public OneUpDb(){
		this.conn=null;
		this.stmt=null;
		this.challenges = new ArrayList<Challenge>();
	}
	public Statement getConnection() throws SQLException {

	    this.conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        this.conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/",
	                   connectionProps);
	    } else if (this.dbms.equals("derby")) {
	        this.conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + ":" +
	                   this.dbName +
	                   ";create=true",
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return (Statement) this.conn.createStatement();
	}
	@SuppressWarnings("null")
	public ArrayList<Challenge> select() throws SQLException{
		try{
		this.stmt = getConnection(); 
		ResultSet rs;
		rs = stmt.executeQuery(this.select);
		Class.forName("com.mysql.jdbc.Driver");
		
	    //STEP 5: Extract data from result set
	    while(rs.next()){
	       //Retrieve by column name
	       int id  = rs.getInt("id");
	       int uid = rs.getInt("uid");
	       int numOfRatings = rs.getInt("numOfRatings");
	       double lng = rs.getDouble("lng");
	       double lat = rs.getDouble("lat");
	       double rating = rs.getDouble("rating");
	       String picVid = rs.getString("picVid");
	       Challenge c = new Challenge(uid,numOfRatings,id,lat,lng,rating,picVid);
	       challenges.add(c);
	    }
	    rs.close();
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		return (ArrayList<Challenge>) challenges;
	}
	public void insert(double lat, double lng, int uId, String url){
		try{
			this.stmt = getConnection();
		String valuesToInsert= uId + "," + String.valueOf(lat) + "," + String.valueOf(lat) + ","+ "\""+url+"\""; 
		String sql = "INSERT INTO OneUpDb.Challenges(uid, lat, lng, picVid)" 
		+ "VALUES(" + valuesToInsert + ")";
		System.out.println(sql);
		stmt.execute(sql);
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
}
