package com.canteen.internalframes;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.canteen.db.DatabaseConnector;

/**
 * Class for the viewOrderFrame
 * @author Chris
 *
 */
public class viewOrderFrame 
{
	public JInternalFrame orderFrame;
	public DatabaseConnector db;
	public JScrollPane scroll; 
	public JPanel panel;
	public static JTable orders;
	public static DefaultTableModel orderModel;
	public String[] tableHeadings = {"Staff ID","User ID","Order ID","itemID"};
	public static Vector<String> heading;
	
	@SuppressWarnings("rawtypes")
	public Vector<Vector>data;
	
	/**
	 * constructor for the Class
	 */
	@SuppressWarnings("rawtypes")
	public viewOrderFrame()
	{
		db = new DatabaseConnector();
		data = new Vector<Vector>();
		heading = new Vector<String>();
		
		for(int i = 0; i < tableHeadings.length; i++)
		{
			heading.add(tableHeadings[i]);
		}
		
		createFrame();
	}
	
	/**
	 * Method to create a JInternalFrame
	 * @return created JInternalFrame
	 */
	public JInternalFrame createFrame()
	{	
		orderFrame = new JInternalFrame("Current Orders",true,true,true,true);
		panel = new JPanel();
		orderFrame.getContentPane().add(panel);
		
		try 
		{
			orderModel = new DefaultTableModel(db.getAllOrders(),heading);
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		orders = new JTable();
		orders.setModel(orderModel);
		panel.setLayout(null);
		
		//adding the Table to the scrollPane
		scroll = new JScrollPane(orders);
		scroll.setBounds(0, 00, 580, 200);
		panel.add(scroll);
		
		orderFrame.setSize(600,300);
		orderFrame.setLocation(40,40);
		orderFrame.setVisible(true);
		return orderFrame;
	}
}
