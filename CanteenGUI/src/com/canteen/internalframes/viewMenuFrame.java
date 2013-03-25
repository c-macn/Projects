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
 * Class for the viewMenu InternalFrame
 * @author Chris
 *
 */
public class viewMenuFrame 
{
	public JInternalFrame menuFrame;
	public static JScrollPane scroll; 
	public static JPanel panel;
	public static JTable menu;
	public String[] tableHeadings = {"ID","Name","OrderDescription"};
	public static DefaultTableModel menuModel;
	public static Vector<String>heading;
	public DatabaseConnector db;
	
	/**
	 * Constructor for the class
	 */
	public viewMenuFrame()
	{
		heading = new Vector<String>();
		
		for(int i = 0; i<tableHeadings.length; i++)
		{
			heading.add(tableHeadings[i]);
		}
		
		db = new DatabaseConnector();
		createFrame();
	}
	
	/**
	 * Method to create a JInternalFrame
	 * @return created JInternalFrame
	 */
	public JInternalFrame createFrame()
	{
		menuFrame = new JInternalFrame("Menu",true,true,true,true);
		panel = new JPanel();
	
		try 
		{
			menuModel = new DefaultTableModel(db.getAllItems(),heading);
			menu = new JTable();
			menu.setModel(menuModel);
			menu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			menu.getColumnModel().getColumn(0).setPreferredWidth(30);
			menu.getColumnModel().getColumn(1).setPreferredWidth(200);
			menu.getColumnModel().getColumn(2).setPreferredWidth(600);
			
		
			
			
		} catch (Exception e) 
		
		{
			e.printStackTrace();
		}
		
		scroll = new JScrollPane(menu);
		menuFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//adding the Table to the scrollPane
		scroll = new JScrollPane(menu);
		scroll.setBounds(0, 00, 580, 200);
		panel.add(scroll);
		
		menuFrame.setSize(600,300);
		menuFrame.setLocation(0,0);
		menuFrame.setVisible(true);
		
		return menuFrame;
	}
}
