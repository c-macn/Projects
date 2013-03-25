package com.canteen.frames;

import com.canteen.db.DatabaseConnector;

/**
 * Main class
 * @author Chris
 *
 */
public class Main 
{
	public static void main(String[] args) 
	{	
		new DatabaseConnector();
		new LogonFrame();
	}
}
