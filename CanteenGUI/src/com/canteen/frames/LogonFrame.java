package com.canteen.frames;

import java.awt.*;

import javax.swing.*;

import com.canteen.db.DatabaseConnector;
import java.awt.event.*;
import java.sql.*;

/**
 * Login Frame class
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public class LogonFrame extends JFrame implements ActionListener 
{
	public JTextField usernameField;
	public JPasswordField passwordField;
	public JLabel username, password,imageLabel,registerMessage,invalid;
	public JButton login,exit,register;
	public JPanel panel;
	public DatabaseConnector db = new DatabaseConnector();
	public static String user;
	public String pass;
	public Icon image;
	
	/**
	 * Constructor for the class
	 */
	public LogonFrame()
	{
		super("The Rest Canteen");
		db.connect();
		createFrame();
	}
	
	/**
	 * Method to create a JFrame
	 */
	public void createFrame()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		Font error = new Font("comic Sans",Font.BOLD,12); 
		registerMessage = new JLabel("New student? click here to register");
		invalid = new JLabel("Invalid username or password");
		register = new JButton("Register");
		usernameField = new JTextField(9);
		passwordField = new JPasswordField(9);
		username = new JLabel("Username:");
		password = new JLabel("Password:");
		login = new JButton("Login");
		exit = new JButton("Exit");
		image = new ImageIcon("Images/ITBLOGO.jpg");
		imageLabel = new JLabel();
		imageLabel.setIcon(image);
		getContentPane().add(panel);
		
		imageLabel.setBounds(200,0,400,160);
		invalid.setFont(error);
		invalid.setForeground(Color.RED);
		username.setBounds(80,190,100,10);
		usernameField.setBounds(150,188,100,20);
		password.setBounds(80,230,100,10);
		passwordField.setBounds(150,228,100,20);
		login.setBounds(50, 280, 100, 30);
		exit.setBounds(230,280,100,30);
		registerMessage.setBounds(450,188,220,20);
		register.setBounds(490,230,100,20);
		login.addActionListener(this);
		exit.addActionListener(this);
		register.addActionListener(this);
		
		panel.add(invalid);
		panel.add(imageLabel);
		panel.add(username);
		panel.add(usernameField);
		panel.add(password);
		panel.add(passwordField);
		panel.add(login);
		panel.add(exit);
		panel.add(registerMessage);
		panel.add(register);
		
		setSize(800,380);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Method to carry out all logic
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		boolean isValid = true;
		user = usernameField.getText();
		pass = passwordField.getText();
		String result1 = ""; 
		String result2 = ""; 
		String result3 = "";
		String query = "SELECT * FROM user";
	
		if(e.getSource().equals(exit))
		{
			System.exit(0);
		}
		
		/**
		 * if login is pressed and the correct details have been entered create the corresponding frame for the user
		 */
		if(e.getSource().equals(login))
		{
			try 
			{
				db.rs = db.st.executeQuery(query);
				
				while(db.rs.next())
				{
					
					result1 = db.rs.getString(1);
					result2 = db.rs.getString(2);
					result3 = db.rs.getString(3);
					
					if(user.equals(result1) && pass.equals(result2) && result3.equals("1"))
					{
						new Manager();
						this.dispose();
						isValid = true;
					}
					
					if(user.equals(result1) && pass.equals(result2) && result3.equals("2"))
					{	
						new StaffGUI();
						this.dispose();
						isValid = true;
					}
					
					if(user.equals(result1) && pass.equals(result2) && result3.equals("3"))
					{	
						new StudentGUI();
						this.dispose();
					}
					
					if(user != result1 || pass != result2)
					{
						isValid = false;
					}
					
					usernameField.setText(null);
					passwordField.setText(null);
				}
				
				if(!isValid)
				{
					invalid.setBounds(100,160,200,20);
				}
			} 
			
			catch (SQLException e1)
			{
				JOptionPane.showMessageDialog(null, "There appears to be a problem with the system insufficient or incorrect data entered");
			}
		}
		
		/**
		 * Created register frame and destroys logon frame
		 */
		if(e.getSource().equals(register))
		{
			new RegisterFrame();
			this.dispose();
		}
	}
}