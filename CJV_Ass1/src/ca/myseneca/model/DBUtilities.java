/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 */

package ca.myseneca.model;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import oracle.jdbc.OracleCallableStatement;

/**
 * This utilities class provides the functionalities of loading properties file,
 * setting up JDBC connection, retrieve SQLException and SQLWarning.
 * This class is coded by changing the JDBCUtilities class of previous Coffees table example. 
 */

public class DBUtilities {

	public static final String DB_file = "database.properties";
	public static final Properties DB_PROPERTIES;

	// Initialize constant DB_PROPERTIES by reading the DB_file (= "database.properties") file.
	// This approach allows you avoid to instantiate the DBUtilities and ensure the database properties 
	// file will be load only once.
	static {
		DB_PROPERTIES = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(DB_file);
			DB_PROPERTIES.load(fis);
		} catch (FileNotFoundException e) {
			System.err.println("Error: database properties file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error when loading database properties file.");
			e.printStackTrace();
		}
	}

	public static void printWarnings(SQLWarning warning) throws SQLException {
		if (warning != null) {
			System.out.println("\n---Warning---\n");
			while (warning != null) {
				System.out.println("Message: " + warning.getMessage());
				System.out.println("SQLState: " + warning.getSQLState());
				System.out.print("Vendor error code: ");
				System.out.println(warning.getErrorCode());
				System.out.println("");
				warning = warning.getNextWarning();
			}
		}
	}

	public static void printBatchUpdateException(BatchUpdateException b) {
		System.err.println("----BatchUpdateException----");
		System.err.println("SQLState:  " + b.getSQLState());
		System.err.println("Message:  " + b.getMessage());
		System.err.println("Vendor:  " + b.getErrorCode());
		System.err.print("Update counts:  ");
		int[] updateCounts = b.getUpdateCounts();
		for (int i = 0; i < updateCounts.length; i++) {
			System.err.print(updateCounts[i] + "   ");
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {     // FOR(int i=0; i<ex.length(); i++) e = ex(i)
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static Connection getConnection()  {
		
		Connection conn = null;

		String driver = DB_PROPERTIES.getProperty("ORACLE_DB_DRIVER");
		String urlString = DB_PROPERTIES.getProperty("ORACLE_DB_URL");

		Properties connectionProps = new Properties();
		connectionProps.put("user",
				DB_PROPERTIES.getProperty("ORACLE_DB_USERNAME"));
		connectionProps.put("password",
				DB_PROPERTIES.getProperty("ORACLE_DB_PASSWORD"));

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(urlString,
					connectionProps);
		} catch (ClassNotFoundException e) {
			System.err.println("Error: database driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error when connecting database.");
			e.printStackTrace();
		}

		System.out.println("Connected to database");
		return conn;
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	public static void closeStatement(Statement s) {
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	public static void closePreparedStatement(PreparedStatement s) {
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	public static void closeCallableStatement(CallableStatement s) {
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}	
	
	public static void closeOracleCallableStatement(OracleCallableStatement s) {
		try {
			if (s != null) {
				s.close();
				s = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}		
	
	public static void closeConnection(Connection conn) {
		System.out.println("Releasing all open resources ...\n");
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
}
