package com.canteen.internalframes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.canteen.db.*;

/**
 * Class for the delegate Order Frame
 * @author Chris
 *
 */

public class DelegateOrderFrame implements ActionListener
{
	public JInternalFrame delegateFrame;
	public JTextField orderIDField, staffIDField;
	public JLabel orderID,staffID;
	public JButton submit;
	public static String user,order;
	public DatabaseConnector db;
	public JPanel panel;
	
	/**
	 * Constructor for the class
	 */
	public DelegateOrderFrame()
	{
		db = new DatabaseConnector();
		createFrame();
	}
	
	/**
	 * Method to create a JInternalFrame
	 * @return created InternalFrame
	 */
	public JInternalFrame createFrame()
	{
		delegateFrame = new JInternalFrame("DelegateOrder",true,true,true,true);
		panel = new JPanel();
		orderID = new JLabel("Order ID: ");
		staffID = new JLabel("Staff ID: ");
		orderIDField = new JTextField(10);
		staffIDField = new JTextField(10);
		submit = new JButton("submit");
		delegateFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		orderID.setBounds(0,10,60,10);
		orderIDField.setBounds(60,6,100,20);
		staffID.setBounds(0,40,50,10);
		staffIDField.setBounds(60,36,100,20);
		submit.setBounds(50,80,100,20);
		submit.addActionListener(this);
		
		panel.add(orderID);
		panel.add(orderIDField);
		panel.add(staffID);
		panel.add(staffIDField);
		panel.add(submit);
		delegateFrame.setSize(200,150);
		delegateFrame.setLocation(650,40);
		delegateFrame.setVisible(true);
		
		return delegateFrame;
	}
	
	public void updateTable()throws SQLException
	{
		viewOrderFrame.orderModel = new DefaultTableModel(db.getAllOrders(), viewOrderFrame.heading);
		viewOrderFrame.orders.setModel(viewOrderFrame.orderModel);
	}
	
	/**
	 * Method to carry out all logic
	 */
	public void actionPerformed(ActionEvent e)
	{
		user = staffIDField.getText();
		order = orderIDField.getText();
		String result1 = "";
		String result2 = "";
		String getStaffQuery = "SELECT username FROM user WHERE privillage = '2'";
		String getOrderIDQuery = "SELECT orderID from basket";
		
		if(e.getSource().equals(submit))
		{
			try 
			{
				db.rs = db.st.executeQuery(getOrderIDQuery);
				
				while(db.rs.next())
				{
					result1 = db.rs.getString(1);
					
					if(order.equals(result1))
					{
						db.rs = db.st.executeQuery(getStaffQuery);
						
						while(db.rs.next())
						{
							result2 = db.rs.getString(1);
							
							if(user.equals(result2))
							{
								String insert = "UPDATE basket SET staffID = '"+user+"' WHERE orderID = '"+order+"'";
								db.runUpdateQueries(insert);
								updateTable();
							}
						}
					}
				}
			}
			
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, "I don't think that user or order exists");
			}
		}
	}
}