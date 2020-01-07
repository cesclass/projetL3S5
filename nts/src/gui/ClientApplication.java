package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import interfaces.UserInterface;

public class ClientApplication {
	// *****************************************************************
	// *
	// * ATTRIBUTES (SWING)
	// *
	// *****************************************************************
	
	// Containers
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel headPanel;
	private JPanel welcomePanel;
	private JPanel buttonPanel;
	private JPanel sendPanel;
	private JScrollPane ticketTreeScrollPane;
	private JScrollPane chatScrollPane;
	private JScrollPane textScrollPane;

	// Labels
	private JLabel welcomeLabel;

	// TextArea
	private JTextArea textArea;

	// Buttons
	private JButton newTicketButton;
	private JButton disconectButton;
	private JButton sendButton;

	// DataStructures
	private JTree ticketTree;
	private JTable messageTable;

	// *****************************************************************
	// *
	// * ATTRIBUTES (DATA)
	// *
	// *****************************************************************

	/** UserInterface allows to communicate with the User/application */
	private UserInterface ui;
	/** TicketManager of ui (UserInterface attribute) */
	private TicketManager tm;

	// *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

	/** Constructor for ClientApplication */
	public ClientApplication(UserInterface ui) {
		this.ui = ui;
		this.tm = ui.getTicketManager();

		buildSWINGElements();
		setProperties();
		setLayout();
		this.addEventListeners();

		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	// *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************
	
	/**
	 * Accessor for the mainWindow attribute
	 * @return JFrame mainWindow
	 */
	public JFrame getMainWindow() {
		return mainWindow;
	}

	/** Update Graphic user interface */
	public void majGUI() {
		ticketTree.updateUI();
		messageTable.updateUI();
	}

	// *****************************************************************
	// *
	// * BUILD
	// *
	// *****************************************************************

	/**
	 * Call all buildSWING methods to build graphic elements
	 * @see ClientApplication.buildSWINGContainers()
	 * @see ClientApplication.buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContents();
		buildSWINGContainers();
	}

	/** Build all SWING containers */
	private void buildSWINGContainers() {
		mainWindow = new JFrame();
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		headPanel = new JPanel();
		welcomePanel = new JPanel();
		buttonPanel = new JPanel();
		sendPanel = new JPanel();
		ticketTreeScrollPane = new JScrollPane(ticketTree);
		chatScrollPane = new JScrollPane(messageTable);
		textScrollPane = new JScrollPane(textArea);
	}

	/** Build all SWING contents */
	private void buildSWINGContents() {
		// Labels
		welcomeLabel = new JLabel();

		// TextArea
		textArea = new JTextArea();

		// Buttons
		newTicketButton = new JButton("Nouveau ticket");
		disconectButton = new JButton("Déconnexion");
		sendButton = new JButton("Envoyer");

		// Tree
		TicketTreeModel model = new TicketTreeModel(ui.getTicketManager());
		ticketTree = new JTree(model);

		// Table
		messageTable = new JTable();
	}
	

	// *****************************************************************
	// *
	// * PROPERTIES
	// *
	// *****************************************************************

	/**
	 * Call all setProperties methods
	 * 	to define SWING elements properties
	 * @see ClientApplication#setMainWindowProperties()
	 * @see ClientApplication#setLeftPanelProperties()
	 * @see ClientApplication#setTextAreaProperties()
	 * @see ClientApplication#setSendButtonProperties()
	 * @see ClientApplication#setWelcomeLabelProperties()
	 */
	private void setProperties() {
		setMainWindowProperties();
		setLeftPanelProperties();
		setTextAreaProperties();
		setSendButtonProperties();
		setWelcomeLabelProperties();
	}

	/** Define the mainWindow SWING Properties*/
	private void setMainWindowProperties() {
		mainWindow.setContentPane(mainPanel);
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainWindow.setMinimumSize(new Dimension(800, 500));
		mainWindow.setLocationRelativeTo(null);
	}

	/** Define the leftPanel SWING Properties */
	private void setLeftPanelProperties() {
		// Border
		leftPanel.setBorder(BorderFactory
				.createMatteBorder(0, 0, 0, 1, Color.black));
		// Max size
		leftPanel.setMaximumSize(new Dimension(
				400, 
				(int) mainPanel.getMaximumSize().getHeight()));
	}

	/** Define the textArea SWING Properties */
	private void setTextAreaProperties() {
		textArea.setEnabled(false);
		textArea.setRows(5);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	}

	/** Define the sendButton SWING Properties */
	private void setSendButtonProperties() {
		sendButton.setEnabled(false);
	}
	
	/** Set the WelcomeLabel SWING Properties */
	private void setWelcomeLabelProperties() {
		welcomeLabel.setText(
				"Bonjour, " 
				+ ui.getUser().getFirstName() 
				+ " " 
				+ ui.getUser().getLastName());
	}

	// *****************************************************************
	// *
	// * LAYOUT
	// *
	// *****************************************************************
	/**
	 * Call all setLayout methods to define SWING layouts
	 * @see ClientApplication#setMainPanelLayout()
	 * @see ClientApplication#setLeftPanelLayout()
	 * @see ClientApplication#setHeadPanelLayout()
	 * @see ClientApplication#setWelcomePanelLayout()
	 * @see ClientApplication#setButtonPanelLayout()
	 * @see ClientApplication#setRightPanelLayout()
	 * @see ClientApplication#setSendPanelLayout()
	 */
	private void setLayout() {
		setMainPanelLayout();
		setLeftPanelLayout();
		setHeadPanelLayout();
		setWelcomePanelLayout();
		setButtonPanelLayout();
		setRightPanelLayout();
		setSendPanelLayout();
	}

	/** Define the mainPanel SWING Layout */
	private void setMainPanelLayout() {
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(leftPanel));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(rightPanel));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(leftPanel)
				.addComponent(rightPanel));
		layout.setVerticalGroup(vGroup);
	}

	/** Define the leftPanel SWING Layout*/
	private void setLeftPanelLayout() {
		BorderLayout layout = new BorderLayout();
		leftPanel.setLayout(layout);

		leftPanel.add(headPanel, BorderLayout.PAGE_START);
		leftPanel.add(ticketTreeScrollPane, BorderLayout.CENTER);
	}

	/** Define the headPanel SWING Layout */
	private void setHeadPanelLayout() {
		GroupLayout layout = new GroupLayout(headPanel);
		headPanel.setLayout(layout);

		// Horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.CENTER)
				.addComponent(welcomePanel)
				.addComponent(buttonPanel));
		layout.setHorizontalGroup(hGroup);

		// Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(welcomePanel));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(buttonPanel));
		layout.setVerticalGroup(vGroup);
	}

	/** Define the welcomePanel SWING Layout */
	private void setWelcomePanelLayout() {
		welcomePanel.add(welcomeLabel);
	}

	/** Define the buttonPanel SWING Layout */
	private void setButtonPanelLayout() {
		GroupLayout layout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(layout);

		// Create gaps
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(newTicketButton));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(disconectButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(newTicketButton)
				.addComponent(disconectButton));
		layout.setVerticalGroup(vGroup);
	}

	/** Define the rightPanel SWING Layout */
	private void setRightPanelLayout() {
		BorderLayout layout = new BorderLayout();
		rightPanel.setLayout(layout);

		rightPanel.add(chatScrollPane, BorderLayout.CENTER);
		rightPanel.add(sendPanel, BorderLayout.PAGE_END);
	}

	/** Define the sendPanel SWING Layout */
	private void setSendPanelLayout() {
		GroupLayout layout = new GroupLayout(sendPanel);
		sendPanel.setLayout(layout);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(textScrollPane));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(sendButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(textScrollPane)
				.addComponent(sendButton));
		layout.setVerticalGroup(vGroup);
	}

	// *****************************************************************
	// *
	// * EVENTS
	// *
	// *****************************************************************

	/**
	 * Call all addEventListener methods to define event
	 * @see ClientApplication#addEventListenerNewTicketButton()
	 * @see ClientApplication#addEventListenerTicketTree()
	 * @see ClientApplication#addEventListenerDisconnectButton()
	 * @see ClientApplication#addEventListenerSendButton()
	 */
	private void addEventListeners() {
		addEventListenerNewTicketButton();
		addEventListenerTicketTree();
		addEventListenerDisconnectButton();
		addEventListenerSendButton();
	}

	/**
	 * Add a new Event listener on newTicketButton
	 * It will open a NewTicketWindow and disabled this window
	 */
	private void addEventListenerNewTicketButton() {
		ClientApplication app = this;

		this.newTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NewTicketWindow(app, tm, ui);
				mainWindow.setEnabled(false);
			}
		});
	}

	/**
	 * Add a new Event listener on ticketTree
	 * It will update messageTable to show selected Ticket Messages
	 */
	private void addEventListenerTicketTree() {
		this.ticketTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath selPath = ticketTree.getPathForLocation(
						e.getX(), e.getY());

				if (selPath != null && 
						selPath.getLastPathComponent() instanceof Ticket) 
				{
					// Get actual selected Ticket
					Ticket ticket = (Ticket) selPath.getLastPathComponent();
					tm.selectTicket(ticket);

					// Set messageTable model
					TableModel tableModel = new MessagesTableModel(
							ticket.getMessageManager());
					messageTable.setModel(tableModel);

					// Set messageTable headers to null
					messageTable.getColumnModel().getColumn(0)
							.setHeaderValue(null);

					// Set Rows sizes to fit the text inside
					for (int i = 0; i < messageTable.getRowCount(); i++) {
						int taille = messageTable.getValueAt(i, 0)
								.toString().split("<br>").length;
						messageTable.setRowHeight(i, (taille + 1) * 16);
					}

					// Enable to send messages
					textArea.setEnabled(true);
					sendButton.setEnabled(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	/**
	 * Add a new Event listener on disconnectButton
	 * It will open a Login window and close this window
	 */
	private void addEventListenerDisconnectButton() {
		this.disconectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.dispose();
				new Login();
			}
		});
		
	}
	
	/**
	 * Add a new Event listener on sendButton
	 * It will add a Message to current Ticket and clear textArea
	 */
	private void addEventListenerSendButton() {
		this.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textArea.getText().isEmpty()) {
					// Send message
					ui.sendMessage(textArea.getText());

					// Clear textArea
					textArea.setText("");

					// Fire changes
					AbstractTableModel model = 
							(AbstractTableModel) messageTable.getModel();
					model.fireTableRowsInserted(
						model.getRowCount()-2, 
						model.getRowCount()-1);
					
					// Set Rows sizes to fit the text inside
					for (int i = model.getRowCount()-2; 
							i < model.getRowCount();
							i++) 
					{
						int taille = messageTable.getValueAt(i, 0)
								.toString().split("<br>").length;
						messageTable.setRowHeight(i, (taille + 1) * 16);
					}
				}
			}
		});
	}
}
