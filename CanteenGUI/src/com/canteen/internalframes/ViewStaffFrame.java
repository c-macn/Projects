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
 * Class for the view Staff Frame
 * @author Chris
 *
 */
public class ViewStaffFrame 
{
	public JInternalFrame staffFrame;
	public DatabaseConnector db;
	public JScrollPane scroll; 
	public JPanel panel;
	public static JTable staff;
	public String[] tableHeadings = {"StaffID"};
	public static Vector<String> heading;
	public static DefaultTableModel staffModel;
	
	@SuppressWarnings("rawtypes")
	public Vector<Vector>data;
	
	@SuppressWarnings("rawtypes")
	/**
	 * Contructor for the class
	 */
	public ViewStaffFrame()
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
	 * @return the credted JInternalFrame
	 */
	public JInternalFrame createFrame()
	{
		//creating the components
		staffFrame = new JInternalFrame("Staff Members",true,true,true,true);
		panel = new JPanel();
		panel.setLayout(null);
		staffFrame.getContentPane().add(panel);
		
		try 
		{
			staffModel = new DefaultTableModel(db.viewStaff(),heading);
			staff = new JTable();
			staff.setModel(staffModel);
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		//adding the Table to the scrollPane
		scroll = new JScrollPane(staff);
		scroll.setBounds(0, 0, 200, 300);
		panel.add(scroll);
		
		//setting the visibilty and the size of the frame
		staffFrame.setSize(200,300);
		staffFrame.setLocation(80,80);
		staffFrame.setVisible(true);
		return staffFrame;
	}
}