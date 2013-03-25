package com.canteen.frames;

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
import javax.swing.table.DefaultTableModel;

import com.canteen.db.DatabaseConnector;

/**
 * Class for the frame that Staff members will access
 * @author Chris
 */
@SuppressWarnings("serial")
public class StaffGUI extends JFrame implements ActionListener
{
	//Declaring components and objects required for the class
	public JMenuBar fileBar;
	public JTextField orderIDField;
	public JLabel idMessage;
	public JMenu fileMenu;
	public JMenuItem logoff;
	public Container cont;
	public DatabaseConnector db;
	public String[] orderHeadings = {"OrderID","UserID","ItemID"};
	public Object[] confirmChoices ={"Yes","No"};
	public Vector<String>heading;
	public JTable order;
	public JPanel panel;
	public String id;
	public JScrollPane scrollPane;
	public JButton submit;
	public DefaultTableModel myModel;
	public static boolean hasOrder = false;
	
	/**
	 * constructor for the class
	 */
	public StaffGUI()
	{
		super("Staff Page");
		db = new DatabaseConnector();
		heading = new Vector<String>();
		
		for(int i = 0; i < orderHeadings.length; i++)
		{
			heading.add(orderHeadings[i]);
		}
		
		createFrame();
	}
	
	/**
	 * Method to create a JFrame
	 */
	public void createFrame()
	{
		panel = new JPanel();
		panel.setLayout(null);
		cont = getContentPane();
		idMessage = new JLabel("Enter orderID when Complete: ");
		orderIDField = new JTextField();
		submit = new JButton("Confirm Order");
		
		try
		{
			myModel = new DefaultTableModel(db.getStaff(),heading);
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		order = new JTable(myModel);
		scrollPane = new JScrollPane(order);
		
		scrollPane.setBounds(0,0,580,200);
		idMessage.setBounds(10,220,200,20);
		orderIDField.setBounds(200,220,50,20);
		submit.setBounds(150, 260, 150, 20);
		submit.addActionListener(this);
		
		cont.add(panel);
		panel.add(scrollPane);
		panel.add(idMessage);
		panel.add(orderIDField);
		panel.add(submit);
		
		setSize(600,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(createMenu());
		setVisible(true);
	}
	
	/**
	 * Method to create a JMenuBar
	 * @return createdMenuBar
	 */
	protected JMenuBar createMenu()
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
	
	public void updateTable()throws SQLException
	{
		myModel = new DefaultTableModel(db.getStaff(),heading);
		order.setModel(myModel);
	}
	
	/**
	 * Method to carry our the logic in class
	 */
	public void actionPerformed(ActionEvent e)
	{
		id = orderIDField.getText();
		String query = "SELECT orderID FROM basket WHERE staffID = '"+LogonFrame.user+"'";
		String result = "";
		
		/**
		 * Destroys this Frame and create a new JFrame
		 */
		if(e.getSource().equals(logoff))
		{
			new LogonFrame();
			this.dispose();
		}
		
		/**
		 * Executes SQL queries when pressed
		 */
		if(e.getSource().equals(submit))
		{
			try 
			{
				db.rs = db.st.executeQuery(query);
				
				/**
				 * fetches results from the query
				 */
				while(db.rs.next())
				{
					result = db.rs.getString(1);
				
					
					/**
					 * if result String is equal to the correct values in the table 
					 * Delete that entry when submited
					 */
					if(id.equals(result))
					{
						int confirm = JOptionPane.showOptionDialog(null,"Double check the ID you eneterd to ensure this is the correct order" ,"Order Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, confirmChoices, confirmChoices[1]);
						
						if(confirm == JOptionPane.YES_OPTION)
						{
							orderIDField.setText(null);
							String delete = "DELETE FROM basket WHERE orderID = '"+id+"'";
							db.runUpdateQueries(delete);
							updateTable();
							JOptionPane.showMessageDialog(null, "A mail will be sent to notify the Student");
							hasOrder = true;
						}
					}
				}
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}	
		}
	}
}