package com.canteen.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import com.canteen.frames.LogonFrame;

/**
 * Class for connections to the database
 * @author Chris
 *
 */
public class DatabaseConnector
{
	public String dbUrl = "jdbc:mysql://127.0.0.1:3307/canteendb";
	public String username = "root";
	public String password = "usbw";
	public Connection con;
	public Statement st;
	public ResultSet rs = null;
	public ResultSetMetaData md;
	
	/**
	 * Constructor for the class
	 */
	public DatabaseConnector()
	{
		connect();
	}  	
	
	/**
	 * Method to connect to the database
	 */
	public void connect()
	{
		try 
		{
			//creating the connection to the database
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection (dbUrl,username,password);
			st = con.createStatement();
		}	 

		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to run SELECT queries
	 * @param query = the query to be executed
	 * @param col = the index to fetch
	 * @throws SQLException
	 */
	public void runSearchQueries(String query,int col) throws SQLException
	{
		String run;
		rs = st.executeQuery(query);
		
		while(rs.next())
		{
			run = rs.getString(col);
			System.out.println(run);
		}
	}
	
	/**
	 * Method to run INSERT queries
	 * @param query = the query to be executed
	 * @throws SQLException
	 */
	public void runUpdateQueries(String query)throws SQLException
	{
		@SuppressWarnings("unused")
		int update;
		update = st.executeUpdate(query);
		
		System.out.println(query);
	}
	
	
	/**
	 * Method to fetch data from the database and fill the menuTable in the JFrame
	 * @return the filled Vector
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getAllItems()/*3*/throws Exception
	{
	 Vector<Vector> rowData = new Vector<Vector>();
	 String query = "SELECT * FROM item";
	/*2*/ rs = st.executeQuery(query);

	 /*2*/while(rs.next())
	 {
		 /*4*/Vector<String> row = new Vector<String>();
		 /*5*/row.addElement(rs.getString(1));
		 row.addElement(rs.getString(2));
		 row.addElement(rs.getString(3));
		 row.addElement(rs.getString(4));
		 rowData.addElement(row);
	 }
	 /*6*/return rowData;
	}
	
	/**
	 * Method to fetch data from the database and fill the Staff JInternalFrame
	 * @return filled Vector
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getAllStaff() throws SQLException
	{
		Vector<Vector> rowData = new Vector<Vector>();
		String query = "SELECT * FROM basket WHERE staffID != '0'";
		rs = st.executeQuery(query);
		
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.addElement(rs.getString(1));
			row.addElement(rs.getString(2));
			row.addElement(rs.getString(3));
			row.addElement(rs.getString(4));
			rowData.addElement(row);
		}
		return rowData;
	}
	
	/**
	 * Method to fetch data from the database and fill the Staff JFrame
	 * @return filled Vector
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getStaff() throws SQLException
	{
		Vector<Vector> rowData = new Vector<Vector>();
		String query = "SELECT * FROM basket WHERE staffID = '"+LogonFrame.user+"'";
		rs = st.executeQuery(query);
		
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.addElement(rs.getString(2));
			row.addElement(rs.getString(3));
			row.addElement(rs.getString(4));
			rowData.addElement(row);
		}
		return rowData;
	}
	
	/**
	 * Method to fecth data from the database and fill the staff JInternalFrame
	 * @return filled Vector
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> viewStaff() throws SQLException
	{
		Vector<Vector> rowData = new Vector<Vector>();
		String getStaff = "SELECT username FROM user WHERE privillage ='2'";
		rs = st.executeQuery(getStaff);
		
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.addElement(rs.getString(1));
			rowData.addElement(row);
		}
		
		return rowData;
	}
	
	/**
	 * Method to fecth data from the database and fill the order JInternalFrame
	 * @return filled Vector
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public Vector<Vector> getAllOrders() throws SQLException
	{
		Vector<Vector> rowData = new Vector<Vector>();
		String query = "SELECT * FROM basket";
		rs = st.executeQuery(query);
		
		while(rs.next())
		{
			Vector<String> row = new Vector<String>();
			row.addElement(rs.getString(1));
			row.addElement(rs.getString(3));
			row.addElement(rs.getString(2));
			row.addElement(rs.getString(4));
			rowData.addElement(row);
		}
		return rowData;
	}
} 