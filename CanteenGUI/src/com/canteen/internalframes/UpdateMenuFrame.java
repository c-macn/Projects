package com.canteen.internalframes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.canteen.db.DatabaseConnector;
import java.awt.event.*;
import java.sql.*;

/**
 * Class for the update Menu frame
 * @author Chris
 *
 */
public class UpdateMenuFrame implements ActionListener
{
	public JInternalFrame updateFrame;
	public JTextField itemNameField,itemPriceField; 
	public JTextArea itemDescriptionField;
	public JLabel itemName,itemDescription,itemPrice;
	public JButton submit,delete;
	public String name,description,price;
	public JPanel panel;
	public JScrollPane scroll;
	public DatabaseConnector db = new DatabaseConnector();
	
	/**
	 * Constructor for the class
	 */
	public UpdateMenuFrame()
	{
		createFrame();
	}
	
	/**
	 * Method to create a JInternalFrame
	 * @return credted JInternalFrame
	 */
	public JInternalFrame createFrame()
	{
		updateFrame = new JInternalFrame("UpdateMenu",true,true,true,true);
		panel = new JPanel();
		itemName = new JLabel("Item Name: ");
		itemPrice = new JLabel("Price: ");
		itemDescription = new JLabel("Description: ");
		itemNameField = new JTextField(10);
		itemPriceField = new JTextField(10);
		itemDescriptionField = new JTextArea();
		scroll = new JScrollPane(itemDescriptionField);
		submit = new JButton("Update");
		delete = new JButton("Delete");
		updateFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		itemName.setBounds(0,10,100,10);
		itemNameField.setBounds(100,6,100,20);
		itemPrice.setBounds(0,40,100,10);
		itemPriceField.setBounds(100,36,100,20);
		itemDescription.setBounds(0,70,100,10);
		scroll.setBounds(100,66,100,100);
		submit.setBounds(10,200,100,20);
		delete.setBounds(150,200,100,20);
		submit.addActionListener(this);
		delete.addActionListener(this);
		
		panel.add(itemName);
		panel.add(itemNameField);
		panel.add(itemDescription);
		panel.add(itemPrice);
		panel.add(itemPriceField);
		panel.add(scroll);
		panel.add(submit);
		panel.add(delete);
		updateFrame.setSize(280,300);
		updateFrame.setLocation(650,80);
		updateFrame.setVisible(true);
		
		return updateFrame;
	}
	
	public void updateTable()throws Exception
	{
		viewMenuFrame.menuModel = new DefaultTableModel(db.getAllItems(),viewMenuFrame.heading);
		viewMenuFrame.menu.setModel(viewMenuFrame.menuModel);
		viewMenuFrame.menu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		viewMenuFrame.menu.getColumnModel().getColumn(0).setPreferredWidth(30);
		viewMenuFrame.menu.getColumnModel().getColumn(1).setPreferredWidth(200);
		viewMenuFrame.menu.getColumnModel().getColumn(2).setPreferredWidth(600);
	}
	
	/**
	 * Method to carry out all logic
	 */
	public void actionPerformed(ActionEvent e)
	{
		name = itemNameField.getText();
		price = itemPriceField.getText();
		description = itemDescriptionField.getText();
		
		/**
		 * If submit was pressed add the entered information to the database
		 */
		if(e.getSource().equals(submit))
		{
			try
			{
				String query = "INSERT INTO item(itemName,itemDescription,itemPrice) VALUES('"+name+"'"+","+"'"+description+"'"+",'"+price+"'"+")";
				db.runUpdateQueries(query);
				updateTable();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Data entered may be incorrect Item name must be text"+"\n description must be text"+"\nPrice must be in the x.xx format");
			}
		}
		
		/**
		 * Delete entered information from the databse 
		 */
		if(e.getSource().equals(delete))
		{
			try
			{
				String query = "DELETE FROM item WHERE itemname = '"+name+"'";
				db.runUpdateQueries(query);
				updateTable();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}