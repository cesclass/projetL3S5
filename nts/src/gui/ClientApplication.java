package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;

import communications.Message;
import communications.Status;
import communications.Ticket;
import interfaces.UserInterface;
import user.Group;

/** Main window of Client application */
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
	// * ATTRIBUTE (DATA)
	// *
	// *****************************************************************

	/** UserInterface allows to communicate with the User/application */
	private UserInterface ui;

	// *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

	/** Constructor for ClientApplication */
	public ClientApplication(UserInterface ui) {
		this.ui = ui;
		ui.pullTickets();

		buildSWINGElements();
		setProperties();
		setLayout();
		addEventListeners();

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

	/** Update ticketTree */
	public void updateTreeUI() {
		ticketTree.updateUI();
	}

	/** Update messageTable */
	public void updateTableUI() {

	}

	/** 
	 * Messages are received from server for a specific Ticket 
	 * It will update messageTable accordingly
	 * @param ticket
	 */
	public void recvMessages(Ticket ticket) {
		// get Ticket
		int index = ui.getTicketManager().getTicketNum(
				ticket.getGroup(),
				ticket);
		ticket = ui.getTicketManager().getTicket(
				ticket.getGroup(), 
				index);

		// Set messageTable model
		TableModel tableModel = new MessagesTableModel(
				ticket.getMessageManager());
		messageTable.setModel(tableModel);

		// Set cell renderer
		messageTable.getColumnModel().getColumn(0)
				.setCellRenderer(new MessagesTableCellRenderer());

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

	/**
	 * Message statuses for each authorized User are received
	 * It will open a new MessageInfosWindow and show them
	 * @param message
	 * @param statuses for each User
	 */
	public void recvStatuses(Message message, List<Status> statuses) {
		new MessageInfosWindow(this, message, statuses);
	}

	/**
	 * Create a newTicketWindow and disable this window
	 * @param groups for Ticket creation
	 */
	public void recvGroups(List<Group> groups) {
		new NewTicketWindow(this, ui, groups);
		mainWindow.setEnabled(false);
	}

	// *****************************************************************
	// *
	// * BUILD
	// *
	// *****************************************************************

	/**
	 * Call all buildSWING methods to build graphic elements
	 * @see ClientApplication#buildSWINGContainers()
	 * @see ClientApplication#buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContents();
		buildSWINGContainers();
	}

	/** Build all SWING containers */
	private void buildSWINGContainers() {
		mainWindow = new JFrame("neOCampus ticket service");
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

		// DataStructures
		TicketTreeModel model = new TicketTreeModel(ui.getTicketManager());
		ticketTree = new JTree(model);
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
	 * @see ClientApplication#setMessageTableProperties()
	 */
	private void setProperties() {
		setMainWindowProperties();
		setLeftPanelProperties();
		setTextAreaProperties();
		setSendButtonProperties();
		setWelcomeLabelProperties();
		setMessageTableProperties();
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
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
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

	/** Set the tableMessage SWING Properties */
	private void setMessageTableProperties() {
		messageTable.setShowGrid(false);
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
	 * @see ClientApplication#addEventListenerMessageTable()
	 * @see ClientApplication#addEventListenerMainWindow()
	 */
	private void addEventListeners() {
		addEventListenerNewTicketButton();
		addEventListenerTicketTree();
		addEventListenerDisconnectButton();
		addEventListenerSendButton();
		addEventListenerMessageTable();
		addEventListenerMainWindow();
	}

	/**
	 * Add a new Event listener on newTicketButton
	 * It will pull groups for Ticket creation
	 */
	private void addEventListenerNewTicketButton() {
		this.newTicketButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ui.pullGroups();
			}
		});
	}

	/**
	 * Add a new Event listener on ticketTree
	 * It will pull Messages from selected Ticket from server
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
					ui.selectTicket(ticket);

					// pull messages from server
					ui.pullMessages(ticket);
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
	 * It will disconnect from: server, open a Login window 
	 * and close this window
	 */
	private void addEventListenerDisconnectButton() {
		this.disconectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ui.disconnect();
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!textArea.getText().isEmpty()) {
					// Send message
					ui.sendMessage(textArea.getText());

					// Clear textArea
					textArea.setText("");
				}
			}
		});
	}

	/**
	 * Add a new Event listener on messageTable
	 * It will open a MessageInfoWindow
	 */
	private void addEventListenerMessageTable() {
		this.messageTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = messageTable.rowAtPoint(e.getPoint());

				if (row%2 == 0) {
					TableModel model = messageTable.getModel();
					Message message = (Message) model.getValueAt(row, 0);

					ui.pullStatus(message);
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
	 * Add a new Event listener on mainWindow
	 * It will disconnect from server and close the application
	 */
	private void addEventListenerMainWindow() {
		mainWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ui.disconnect();
				mainWindow.dispose();
			}
		});
	}
}
