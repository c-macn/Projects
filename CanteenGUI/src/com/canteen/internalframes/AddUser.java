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

import com.canteen.db.DatabaseConnector;
/**
 * Class for the Add user internal Frame
 * @author Chris 
 *
 */
public class AddUser implements ActionListener
{
	public JInternalFrame addUserFrame;
	public DatabaseConnector db = new DatabaseConnector();
	public JTextField usernameField, passwordField;
	public JLabel username,password;
	public String name,pass;
	public JButton submit,delete;
	public JPanel panel;
	
	/**
	 * Constructor for the class
	 */
	public AddUser()
	{
		createFrame();
	}
	
	/**
	 * Method to create an JInternalFrame
	 * @return the credted JInternalFrame
	 */
	public JInternalFrame createFrame()
	{
		addUserFrame = new JInternalFrame("Add Chef",true,true,true,true);
		panel = new JPanel();
		username = new JLabel("username: ");
		password = new JLabel("password: ");
		usernameField = new JTextField(10);
		passwordField = new JTextField(10);

		submit = new JButton("submit");
		delete = new JButton("Delete");
		addUserFrame.getContentPane().add(panel);
		panel.setLayout(null);
		username.setBounds(0,10,100,20);
		usernameField.setBounds(80,10,100,20);
		password.setBounds(0,40,100,20);
		passwordField.setBounds(80,40,100,20);
		submit.setBounds(10,100,100,20);
		delete.setBounds(150,100,100,20);
		submit.addActionListener(this);
		delete.addActionListener(this);
		
		panel.add(username);
		panel.add(usernameField);
		panel.add(password);
		panel.add(passwordField);
		panel.add(submit);
		panel.add(delete);
		
		addUserFrame.setSize(280,190);
		addUserFrame.setLocation(650,0);
		addUserFrame.setVisible(true);
		
		return addUserFrame;
	}
	
	/**
	 * Method to update a JTable
	 * @throws SQLException
	 */
	public void updateTable()throws SQLException
	{
		ViewStaffFrame.staffModel = new DefaultTableModel(db.viewStaff(), ViewStaffFrame.heading);
		ViewStaffFrame.staff.setModel(ViewStaffFrame.staffModel);
	}

	/**
	 * Method to carry out all the logic
	 */
	public void actionPerformed(ActionEvent e) 
	{
		name = usernameField.getText();
		pass = passwordField.getText();
		
		/**
		 * If the button is pressed insert the data entered by the manager into the databse
		 */
		if(e.getSource().equals(submit))
		{
			try
			{
				String query = "INSERT INTO user VALUES"+"('"+name+"','"+pass+"','2')";
				
				if(name.startsWith("S"))
				{
					db.runUpdateQueries(query);
					updateTable();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Staff username must start with a 'S' followed by 8 characters");
				}
			}
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, "There may be a problem with the entered data"+"\n username should start with a capital S\n");
			}
		}
		
		/**
		 * delete entered information from the database
		 */
		if(e.getSource().equals(delete))
		{
			try
			{
				String deleteQuery = "DELETE FROM user WHERE username = '"+name+"'";
				db.runUpdateQueries(deleteQuery);
				updateTable();
			}
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, "There appears to be a problem with the sytem incorrect or insufficent data entered");
			}
		}
	}
}