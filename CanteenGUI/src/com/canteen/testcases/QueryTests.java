package com.canteen.testcases;

import java.sql.SQLException;
import java.util.Vector;
import junit.framework.TestCase;
import com.canteen.db.DatabaseConnector;

public class QueryTests extends TestCase
{
	private DatabaseConnector db;
	
	public QueryTests()
	{
		db = new DatabaseConnector();
	}
	
	public void testSearchQueries()
	{
		try
		{
			//Happy Flow
			db.runSearchQueries("SELECT * FROM user",1);
			System.out.println("------------ END OF HAPPY FLOW TEST ------------------\n");
			//Wrong Values
			db.runSearchQueries("dfdfg",9890);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void testGetAllItems()
	{
		@SuppressWarnings("rawtypes")
		Vector<Vector> rowData = new Vector<Vector>();
		
		/*---------------------------------
		* 		HAPPY FLOW TEST
		*----------------------------------*/
		try
		{
		
			String query = "SELECT * FROM item";
			db.rs = db.st.executeQuery(query);

			 while(db.rs.next())
			 {
				 Vector<String> row = new Vector<String>();
				 row.addElement(db.rs.getString(1));
				 row.addElement(db.rs.getString(2));
				 row.addElement(db.rs.getString(3));
				 row.addElement(db.rs.getString(4));
				 rowData.addElement(row);
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		/*------------------------------
		 * FAILED EXECUTION TEST
		 *-------------------------------*/
		try
		{
			String query = "S3L3CT * FRoM item";
			db.rs = db.st.executeQuery(query);

			 while(db.rs.next())
			 {
				 Vector<String> row = new Vector<String>();
				 row.addElement("HJKH");
				 row.addElement("");
				 row.addElement("");
				 row.addElement("kjjkjk");
				 rowData.addElement(row);
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}