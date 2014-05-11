package com.teambad.oneup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import android.os.StrictMode;
import android.util.Log;

import com.mysql.jdbc.Statement;

public class OneUpDb {
	private String userName = "OneUpDbAdmin";
	private String password = "OneUpMu14";
	private String dbms = "mysql";
	private String serverName = "oneupdb.cx0pyxmofvod.us-west-2.rds.amazonaws.com";
	private String portNumber = "3306";
	private String dbName = "OneUpDb";
	private String select = "Select * from OneUpDb.Challenges;";
	private String selectUser = "Select * from OneUpDb.User;";
	private List<UserId> userIds;
	private Connection conn;
	private Statement stmt;
	private List<Challenge> challenges;

	public OneUpDb() {
		this.conn = null;
		this.stmt = null;
		this.challenges = new ArrayList<Challenge>();
	}

	public Statement getConnection() throws SQLException {

		this.conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

		if (this.dbms.equals("mysql")) {
			this.conn = DriverManager.getConnection("jdbc:" + this.dbms + "://"
					+ this.serverName + ":" + this.portNumber + "/"
					+ this.dbName, connectionProps);
		} else if (this.dbms.equals("derby")) {
			this.conn = DriverManager.getConnection("jdbc:" + this.dbms + ":"
					+ this.dbName + ";create=true", connectionProps);
		}
		return (Statement) this.conn.createStatement();
	}

	public ArrayList<Challenge> select() throws SQLException {
		try {
			this.stmt = getConnection();
			ResultSet rs;
			rs = stmt.executeQuery(this.select);
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int uid = rs.getInt("uid");
				int numOfRatings = rs.getInt("numOfRatings");
				double lng = rs.getDouble("lng");
				double lat = rs.getDouble("lat");
				double rating = rs.getDouble("rating");
				String picVid = rs.getString("picVid");
				Challenge c = new Challenge(uid, numOfRatings, id, lat, lng,
						rating, picVid);
				challenges.add(c);
			}
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return (ArrayList<Challenge>) challenges;
	}

	public void insert(Challenge c) {
		try {
			this.stmt = getConnection();
			String valuesToInsert = c.userid + "," + String.valueOf(c.lat)
					+ "," + String.valueOf(c.lng) + "," + "\"" + c.url + "\"";
			String sql = "INSERT INTO OneUpDb.Challenges(uid, lat, lng, picVid)"
					+ "VALUES(" + valuesToInsert + ");";
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

	public ArrayList<UserId> selectUser() throws SQLException {
		try {
			this.stmt = getConnection();
			ResultSet rs;
			rs = stmt.executeQuery(this.selectUser);
			Class.forName("com.mysql.jdbc.Driver");
			ArrayList<UserId> ids = new ArrayList<UserId>();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("idUser");
				String userName = rs.getString("userName");
				UserId u = new UserId(id, userName);
				ids.add(u);
				Log.e("DOHEFO: JEFS:DEBUG", ids.size() + "");
			}
			rs.close();
			return ids;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return (ArrayList<UserId>) userIds;
	}

	public void update(Challenge c) {
		try {
			this.stmt = getConnection();
			String sql = "UPDATE Challenges SET rating = "
					+ String.valueOf(c.rating) + ", numOfRatings = "
					+ String.valueOf(c.numRatings) + " WHERE id = "
					+ String.valueOf(c.challengeId) + ";";
			stmt.execute(sql);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

}
