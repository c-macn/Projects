package com.canteen.frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;

import com.canteen.db.DatabaseConnector;

/**
 * Class for the Student Frame
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public class StudentGUI extends JFrame implements ActionListener
{
	public JPanel studentPanel;
	public JScrollPane scrollPane;
	public Vector<String> heading;
	public String[] menuHeadings = {"ID","ItemName","Description","Price"};
	public Object[][] menuItems;
	public Object[] confirmationChoices;
	public JTable menu;
	public JLabel item,cardNum;
	public JTextField itemField,cardNumField;
	public JButton addButton;
	public JMenuBar fileBar;
	public JMenu fileMenu;
	public JMenuItem logoff;
	public Container container;
	public static String itemID;
	public String card;
	public TableModel myModel;
	public static int orderID;
	public DatabaseConnector db;
	
	/**
	 * Constructor for the class
	 */
	public StudentGUI()
	{
		 super(LogonFrame.user+" Page");
		 heading = new Vector<String>();
		 confirmationChoices = new Object[] {"Yes","Cancel"};
		 for(int i = 0; i < menuHeadings.length; i++)
		 {
			 heading.add(menuHeadings[i]);
		 }
		 
		 db = new DatabaseConnector();
		 createFrame();
		 
		 if(StaffGUI.hasOrder)
		 {
			 JOptionPane.showMessageDialog(null, "Your order is ready for collection");
			 StaffGUI.hasOrder = false;
		 }
	}
	
	/**
	 * Method to create the JFrame
	 */
	public void createFrame()
	{
		container = getContentPane();
		addButton = new JButton("Add");
		studentPanel = new JPanel();
		item = new JLabel("Please enter the id of your meal: ");
		cardNum = new JLabel("CardNo: ");
		itemField = new JTextField();
		cardNumField = new JTextField();
		
		try 
		{
			menu = new JTable(db.getAllItems(),heading);
			menu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			menu.getColumnModel().getColumn(0).setPreferredWidth(30);
			menu.getColumnModel().getColumn(1).setPreferredWidth(200);
			menu.getColumnModel().getColumn(2).setPreferredWidth(600);
			menu.getColumnModel().getColumn(3).setPreferredWidth(50);
			
		} catch (Exception e) 
		
		{
			e.printStackTrace();
		}
	
		scrollPane = new JScrollPane(menu);
		studentPanel.setLayout(null);
		studentPanel.setBackground(Color.WHITE);
		addButton.addActionListener(this);
		
		scrollPane.setBounds(0,0,860,200);
		addButton.setBounds(220, 300, 150, 30);
		item.setBounds(50,220,200,10);
		itemField.setBounds(250,218,50,20);
		cardNum.setBounds(50,250,100,20);
		cardNumField.setBounds(250,248,130,20);
		
		container.add(studentPanel);
		studentPanel.add(scrollPane);
		studentPanel.add(item);
		studentPanel.add(addButton);
		studentPanel.add(itemField);
		studentPanel.add(cardNum);
		studentPanel.add(cardNumField);
		
		setSize(880,450);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(createMenu());
		setVisible(true);
	}
	
	/**
	 * Method to create a JMenuBar
	 * @return created JMenuBar
	 */
	public JMenuBar createMenu()
	{
		fileMenu = new JMenu("File");
		fileBar = new JMenuBar();
		logoff = new JMenuItem("Log out");
		
		fileBar.add(fileMenu);
		fileMenu.add(logoff);
		
		logoff.setMnemonic('L');
		logoff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L , ActionEvent.CTRL_MASK));
		
		logoff.addActionListener(this);
		
		return fileBar;
	}
	
	/** Method to add item to the users cart */ 
	public void orderItem()
	{
		itemID = itemField.getText();
		card = cardNumField.getText();
		String query = "SELECT itemID,itemName from item WHERE itemID = '"+itemID+"'";
		String query2 = "SELECT cardNumber FROM payment_info WHERE cardNumber = '"+card+"'";
		String result1 = "";
		String result2 = "";
		
		try
		{
			db.rs = db.st.executeQuery(query);
			
			while(db.rs.next())
			{
				result1 = db.rs.getString(1);
				String itemName = db.rs.getString(2);
				db.rs = db.st.executeQuery(query2);
				
				while(db.rs.next())
				{
					result2 = db.rs.getString(1);
					
					if(itemID.equals(result1) && card.equals(result2))
					{
						int confirm = JOptionPane.showOptionDialog(null,"Are you sure you want to order "+itemName+"?","Order Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, confirmationChoices, confirmationChoices[1]);
						
						if(confirm == JOptionPane.YES_OPTION)
						{
							String insert = "INSERT INTO basket(orderID,userID,itemID) Values('"+orderID+"','"+LogonFrame.user+"','"+itemID+"')";
							db.runUpdateQueries(insert);
							JOptionPane.showMessageDialog(null,itemName+" Added to your order\n"+"Remember all of our meals come with Drinks");
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Data entered is incorrect please ensure your card number is correct and that the item exists");
					}
				}
			}
		}
		catch(SQLException ex)
		{
		}
	}
	
	/**
	 * Method to carray out all logic
	 */
	public void actionPerformed(ActionEvent e)
	{
		/**
		 * add the item the user wants to the databse
		 */
		if(e.getSource().equals(addButton))
		{
			orderItem();
		}
		
		/**
		 * destory this frame and return to the logon screen
		 */
		if(e.getSource().equals(logoff))
		{
			new LogonFrame();
			this.dispose();
		}
	}
}