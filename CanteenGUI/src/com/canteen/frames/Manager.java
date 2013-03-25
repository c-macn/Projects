package com.canteen.frames;

import javax.swing.*;

import java.awt.event.*;
import com.canteen.internalframes.*;

/**
 * Manager frame class
 * @author Chris
 */
@SuppressWarnings("serial")
public class Manager extends JFrame implements ActionListener
{
	public JDesktopPane panel;
	public JMenu fileMenu;
	public JMenuBar fileBar;
	public JMenuItem viewStaff,viewOrders,viewMenu,delegateOrder,updateMenu,addUser,logoff;
	public ViewStaffFrame sf = new ViewStaffFrame();
	public viewOrderFrame of = new viewOrderFrame();
	public viewMenuFrame mf = new viewMenuFrame();
	public DelegateOrderFrame dof = new DelegateOrderFrame();
	public UpdateMenuFrame umf = new UpdateMenuFrame();
	public AddUser au = new AddUser();
	
	/**
	 * Constructor for the class
	 */
	public Manager()
	{
		super("Manager Frame");
		createFrame();
	}
	
	/**
	 * Method to create Jframe
	 */
	public void createFrame()
	{
		panel = new JDesktopPane();
		setContentPane(panel);	
		setSize(1024,600);
		setJMenuBar(createMenu());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * Method to create a JMenuBar
	 * @return created JMenuBar
	 */
	protected JMenuBar createMenu()
	{
		fileMenu = new JMenu("File");
		fileBar = new JMenuBar();
		viewStaff = new JMenuItem("View Staff");
		viewOrders = new JMenuItem("View Orders");
		viewMenu = new JMenuItem("View Menu");
		delegateOrder = new JMenuItem("Delegate Order");
		updateMenu = new JMenuItem("Update Menu");
		addUser = new JMenuItem("Add Chef");
		logoff = new JMenuItem("Log out");
		
		fileBar.add(fileMenu);
		fileMenu.add(viewStaff);
		fileMenu.add(viewOrders);
		fileMenu.add(viewMenu);
		fileMenu.addSeparator();
		fileMenu.add(delegateOrder);
		fileMenu.add(updateMenu);
		fileMenu.add(addUser);
		fileMenu.addSeparator();
		fileMenu.add(logoff);
		
		viewStaff.setMnemonic('S');
		viewOrders.setMnemonic('O');
		viewMenu.setMnemonic('M');
		delegateOrder.setMnemonic('D');
		updateMenu.setMnemonic('U');
		addUser.setMnemonic('C');
		logoff.setMnemonic('L');
		
		viewStaff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S , ActionEvent.CTRL_MASK));
		viewOrders.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O , ActionEvent.CTRL_MASK));
		viewMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M , ActionEvent.CTRL_MASK));
		delegateOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D , ActionEvent.CTRL_MASK));
		updateMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U , ActionEvent.CTRL_MASK));
		addUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C , ActionEvent.CTRL_MASK));
		logoff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L , ActionEvent.CTRL_MASK));
		
		viewStaff.addActionListener(this);
		viewOrders.addActionListener(this);
		viewMenu.addActionListener(this);
		delegateOrder.addActionListener(this);
		updateMenu.addActionListener(this);
		addUser.addActionListener(this);
		logoff.addActionListener(this);
	
		return fileBar;
	}
	
	/**
	 * Method to carry out all logic
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(viewOrders))
		{
			panel.add(of.createFrame());
		}
		
		if(e.getSource().equals(viewStaff))
		{
			panel.add(sf.createFrame());
		}
		
		if(e.getSource().equals(viewMenu))
		{
			panel.add(mf.createFrame());
		}
		
		if(e.getSource().equals(delegateOrder))
		{
			panel.add(dof.createFrame());
		}
		
		if(e.getSource().equals(updateMenu))
		{
			panel.add(umf.createFrame());
		}
		
		if(e.getSource().equals(addUser))
		{
			panel.add(au.createFrame());
		}
		
		if(e.getSource().equals(logoff))
		{
			new LogonFrame();
			this.dispose();
		}
	}
}