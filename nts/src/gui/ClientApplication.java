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
import interfaces.UserNotConnectedException;

public class ClientApplication {
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

	// Data
	private TicketManager tm;
	private UserInterface ui;

	public ClientApplication(UserInterface ui) {
		this.ui = ui;
		this.tm = ui.getTicketManager();

		createWindow();
		showWindow();
	}

	public JFrame getMainWindow() {
		return mainWindow;
	}

	/**
	 * Create the window
	 * 
	 * @param tm : gestionnaire de tickets
	 */
	private void createWindow() {
		buildSWINGElements();

		mainWindow.setContentPane(mainPanel);
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setProperties();
		setLayout();

		// Set Event
		this.addEventListeners();

		mainWindow.pack();
	}

	/**
	 * Show the main window
	 */
	private void showWindow() {
		mainWindow.setVisible(true);
	}

	/**
	 * Update Graphic user interface
	 */
	public void majGUI() {
		ticketTree.updateUI();
	}

	// ************************************************************************
	// *
	// * BUILD
	// *
	// ************************************************************************

	/**
	 * Call all buildSWING methods to build graphic elements
	 * 
	 * @param tm : gestionnaire de tickets
	 * @see ClientApplication.buildSWINGContainers()
	 * @see ClientApplication.buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContents();
		buildSWINGContainers();
	}

	/**
	 * Build all SWING containers
	 */
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

	/**
	 * Build all SWING contents
	 */
	private void buildSWINGContents() {
		// Labels
		setWelcome();

		// TextArea
		textArea = new JTextArea();

		// Buttons
		newTicketButton = new JButton("Nouveau ticket");
		disconectButton = new JButton("Déconnexion");
		sendButton = new JButton("Envoyer");

		// Tree
		TicketTreeModel model = new TicketTreeModel(tm);
		ticketTree = new JTree(model);
		messageTable = new JTable();
	}

	// ************************************************************************
	// *
	// * PROPERTIES
	// *
	// ************************************************************************

	/**
	 * Call all setProperties methods to define properties
	 * 
	 * @see ClientApplication.setMainWindowProperties()
	 * @see ClientApplication.setLeftPanelProperties()
	 * @see ClientApplication.setTextAreaProperties()
	 */
	private void setProperties() {
		setMainWindowProperties();
		setLeftPanelProperties();
		setTextAreaProperties();
	}

	/**
	 * Define the mainWindow Properties
	 */
	private void setMainWindowProperties() {
		mainWindow.setMinimumSize(new Dimension(800, 500));
		mainWindow.setLocationRelativeTo(null);
	}

	/**
	 * Define the leftPanel Properties
	 */
	private void setLeftPanelProperties() {
		// Border
		leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		// Max size
		leftPanel.setMaximumSize(new Dimension(400, (int) mainPanel.getMaximumSize().getHeight()));
	}

	private void setTextAreaProperties() {
		textArea.setRows(5);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
	}

	/**
	 * Set the Welcome Label
	 */
	private void setWelcome() {
		welcomeLabel = new JLabel();
		try {
			welcomeLabel.setText("Bonjour, " + ui.getUser().getFirstName() + " " + ui.getUser().getLastName());
		} catch (UserNotConnectedException e) {
			e.printStackTrace();
		}
	}

	// ************************************************************************
	// *
	// * LAYOUT
	// *
	// ************************************************************************

	/**
	 * Call all setLayout methods to define layouts
	 * 
	 * @see ClientApplication.setMainPanelLayout()
	 * @see ClientApplication.setLeftPanelLayout()
	 * @see ClientApplication.setHeadPanelLayout()
	 * @see ClientApplication.setWelcomePanelLayout()
	 * @see ClientApplication.setButtonPanelLayout()
	 * @see ClientApplication.setRightPanelLayout()
	 * @see ClientApplication.setSendPanelLayout()
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

	/**
	 * Define the mainPanel Layout
	 */
	private void setMainPanelLayout() {
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(leftPanel));
		hGroup.addGroup(layout.createParallelGroup().addComponent(rightPanel));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(leftPanel).addComponent(rightPanel));
		layout.setVerticalGroup(vGroup);
	}

	/**
	 * Define the leftPanel Layout
	 */
	private void setLeftPanelLayout() {
		BorderLayout layout = new BorderLayout();
		leftPanel.setLayout(layout);

		leftPanel.add(headPanel, BorderLayout.PAGE_START);
		leftPanel.add(ticketTreeScrollPane, BorderLayout.CENTER);
	}

	/**
	 * Define the headPanel Layout
	 */
	private void setHeadPanelLayout() {
		GroupLayout layout = new GroupLayout(headPanel);
		headPanel.setLayout(layout);

		// Horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(welcomePanel)
				.addComponent(buttonPanel));
		layout.setHorizontalGroup(hGroup);

		// Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(welcomePanel));
		vGroup.addGroup(layout.createParallelGroup().addComponent(buttonPanel));
		layout.setVerticalGroup(vGroup);
	}

	/**
	 * Define the welcomePanel Layout
	 */
	private void setWelcomePanelLayout() {
		welcomePanel.add(welcomeLabel);
	}

	/**
	 * Define the buttonPanel Layout
	 */
	private void setButtonPanelLayout() {
		GroupLayout layout = new GroupLayout(buttonPanel);
		buttonPanel.setLayout(layout);

		// Create gaps
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(newTicketButton));
		hGroup.addGroup(layout.createParallelGroup().addComponent(disconectButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(newTicketButton).addComponent(disconectButton));
		layout.setVerticalGroup(vGroup);
	}

	/**
	 * Define the rightPanel Layout
	 */
	private void setRightPanelLayout() {
		BorderLayout layout = new BorderLayout();
		rightPanel.setLayout(layout);

		rightPanel.add(chatScrollPane, BorderLayout.CENTER);
		rightPanel.add(sendPanel, BorderLayout.PAGE_END);
	}

	/**
	 * Define the sendPanel Layout
	 */
	private void setSendPanelLayout() {
		GroupLayout layout = new GroupLayout(sendPanel);
		sendPanel.setLayout(layout);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(textScrollPane));
		hGroup.addGroup(layout.createParallelGroup().addComponent(sendButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(textScrollPane).addComponent(sendButton));
		layout.setVerticalGroup(vGroup);
	}

	// ************************************************************************
	// *
	// * EVENTS
	// *
	// ************************************************************************

	private void addEventListeners() {
		this.addEventListenerNewTicketButton();
		addEventListenerTicketTree();
		addEventListenerDisconectButton();
		addEventListenerSendButton();
	}


	private void addEventListenerNewTicketButton() {
		ClientApplication me = this;
		this.newTicketButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new NewTicketWindow(me, tm, ui);
				mainWindow.setEnabled(false);
			}
		});
	}

	private void addEventListenerTicketTree() {
		this.ticketTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				TreePath selPath = ticketTree.getPathForLocation(e.getX(), e.getY());

				if (selPath != null && 
						selPath.getLastPathComponent() instanceof Ticket) {
					Ticket ticket = (Ticket) selPath.getLastPathComponent();
					TableModel tableModel = new MessagesTableModel(
							ticket.getMessageManager());
					messageTable.setModel(tableModel);
					messageTable.getColumnModel().getColumn(0).setHeaderValue(null);

					for (int i = 0; i < messageTable.getRowCount(); i++) {
						int taille = messageTable.getValueAt(i, 0).toString().split("<br>").length;
						messageTable.setRowHeight(i, (taille + 1) * 16);
					}
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
	
	private void addEventListenerDisconectButton() {
		this.disconectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setEnabled(false);
				mainWindow.setVisible(false);
				
				new Login();
			}
		});
		
	}
	
	private void addEventListenerSendButton() {
		this.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textArea.getText().isEmpty()) {
					//Envoi du nouveau message dans le ticket courant
					try {
						ui.getTicketManager().getCurrent().addMessage(new Message(ui.getUser(), textArea.getText()));
					} catch (UserNotConnectedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.setText("");
					AbstractTableModel model = (AbstractTableModel) messageTable.getModel();
					model.fireTableRowsInserted(model.getRowCount()-2, model.getRowCount()-1);
					
					for (int i = model.getRowCount()-2 ; i < messageTable.getRowCount(); i++) {
						int taille = messageTable.getValueAt(i, 0).toString().split("<br>").length;
						messageTable.setRowHeight(i, (taille + 1) * 16);
					}
				}
			}
		});
	}
}
