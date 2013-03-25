package com.canteen.frames;

import com.canteen.db.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * class for the registration frame
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public class RegisterFrame extends JFrame implements ActionListener
{
	public JLabel username,password,cardNo,cardType,cardExp;
	public DatabaseConnector db;
	public JButton submit;
	public JPanel panel;
	public String[] choices;
	
	@SuppressWarnings("rawtypes")
	public JComboBox cardTypeList;
	public JTextField usernameField,cardNoField,cardExpField;
	public JPasswordField passwordField;
	
	/**
	 * Constructor for the class
	 */
	public RegisterFrame()
	{
		super("Registration");
		db = new DatabaseConnector();
		createFrame();
	}
	
	/**
	 * Method to create a JFrame
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createFrame()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		choices = new String[] {"MasterCard","Visa","Laser"};
		submit = new JButton("Submit");
		
		cardTypeList = new JComboBox(choices);
		username = new JLabel("Username: ");
		password = new JLabel("Password: ");
		cardNo = new JLabel("CardNo: ");
		cardType = new JLabel("Card type: ");
		cardExp = new JLabel("Exp: ");
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		cardNoField = new JTextField();
		cardExpField = new JTextField();
		
		username.setBounds(10,10,100,20);
		password.setBounds(10,40,100,20);
		cardNo.setBounds(10,70,100,20);
		cardType.setBounds(10,100,100,20);
		cardExp.setBounds(10,180,100,20);
		usernameField.setBounds(80,10,130,20);
		passwordField.setBounds(80,40,130,20);
		cardNoField.setBounds(80,70,130,20);
		cardTypeList.setBounds(80,100,130,20);
		cardExpField.setBounds(80, 180, 130, 20);
		submit.setBounds(100,230,100,20);
		
		submit.addActionListener(this);
		cardTypeList.addActionListener(this);
		
		panel.add(username);
		panel.add(password);
		panel.add(cardNo);
		panel.add(cardType);
		panel.add(cardExp);
		panel.add(usernameField);
		panel.add(passwordField);
		panel.add(cardNoField);
		panel.add(cardTypeList);
		panel.add(cardExpField);
		panel.add(submit);
		
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Method to carry out all logic
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		String name = usernameField.getText();
		String pass = passwordField.getText();
		String cardNo = cardNoField.getText();
		String cardType = (String) cardTypeList.getSelectedItem();
		String cardExp = cardExpField.getText();
				
		if(e.getSource().equals(submit))
		{
			try
			{
				String insertNameandPass1 = "INSERT INTO user VALUES('"+name+"','"+pass+"','3')";
				String insertNameandPass2 = "INSERT INTO payment_info VALUES('"+name+"','"+cardNo+"','"+cardExp+"','"+cardType+"')";
				
				if(name.startsWith("B"))
				{
					db.runUpdateQueries(insertNameandPass2);
					db.runUpdateQueries(insertNameandPass1);
				
					JOptionPane.showMessageDialog(null,"Thank you for Registering click here to return to the login page");
					new LogonFrame();
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid StudentID all student must start with 'B'");
				}
			}
			catch(SQLException ex)
			{
				JOptionPane.showMessageDialog(null, "It appears there has been some false data entered");
			}
		}
	}
}