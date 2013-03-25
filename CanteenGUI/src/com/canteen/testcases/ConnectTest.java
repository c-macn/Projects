package com.canteen.testcases;

import junit.framework.TestCase;
import com.canteen.db.DatabaseConnector;

public class ConnectTest extends TestCase 
{
	private DatabaseConnector db1;
	
	protected void setUp() throws Exception
	{
		super.setUp();	
		db1 = new DatabaseConnector();
	}
	
	public void testConnection() throws ClassNotFoundException
	{
		db1.connect();
	}
}
