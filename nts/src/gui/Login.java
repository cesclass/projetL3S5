package gui;

import java.awt.event.*;
import java.awt.Font;

import javax.swing.*;

import interfaces.UserInterface;

/** Login window of Client application */
public class Login {
	// *****************************************************************
	// *
	// * CONSTANTS
	// *
	// *****************************************************************

	/** Width of the window */
	public final static int SIZEX = 300;
	/** Height of the window */
	public final static int SIZEY = 185;

	// *****************************************************************
	// *
	// * ATTRIBUTES (SWING)
	// *
	// *****************************************************************

	// Containers
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JPanel loginPanel;
	
	// Labels
	private JLabel titleLabel;
	private JLabel loginLabel;
	private JLabel passwordLabel;

	// (Text/Password)Fields
	private JTextField loginField;
	private JPasswordField passwordField;

	// Button
	private JButton connectButton;

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

	/** Constructor for standard Login window */
	public Login() {
		ui = new UserInterface(this);

		buildSWINGElements();
		setProperties();
		setLayout();
		this.addEventListeners();

		mainWindow.setVisible(true);
	}

	// *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************

	/** 
	 * Successfully connected
	 * It will open a new ClientApplication window and close this window
	 */
	public ClientApplication successConnect() {
		ClientApplication app = new ClientApplication(ui);
		mainWindow.dispose();

		return app;
	}

	/**
	 * Connexion error : bad login
	 * It will open a bad login error and clear fields
	 */
	public void errorBadLogin() {
		JOptionPane.showMessageDialog(mainWindow, 
				"<html><b>login</b>" 
				+ " ou <b>password</b> incorrect.</html>"
				, "Erreur", JOptionPane.ERROR_MESSAGE);
		// Clear fields
		loginField.setText("");
		passwordField.setText("");
	}

	/**
	 * Connexion error : already connected
	 * It will open a bad login error and clear fields
	 */
	public void errorAlreadyConnected() {
		JOptionPane.showMessageDialog(mainWindow, 
				"<html><b>" 
				+ loginField.getText() 
				+ "</b>" 
				+ " est déjà connecté.</html>",
				"Erreur", JOptionPane.ERROR_MESSAGE);
		// Clear fields
		loginField.setText("");
		passwordField.setText("");
	}

	// *****************************************************************
	// *
	// * BUILD
	// *
	// *****************************************************************

	/**
	 * Call all buildSWING methods to build graphic elements
	 * @see Login#buildSWINGContainers()
	 * @see Login#buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContainers();
		buildSWINGContents();
	}

	/** Build all SWING containers */
	private void buildSWINGContainers() {
		mainWindow = new JFrame("Login");
		mainPanel = new JPanel();
		loginPanel = new JPanel();
	}

	/** Build all SWING contents */
	private void buildSWINGContents() {
		// Labels
		titleLabel = new JLabel("neOCampus ticket service");
		loginLabel = new JLabel("login");
		passwordLabel = new JLabel("password");

		// (Text/Password)Fields
		loginField = new JTextField();
		passwordField = new JPasswordField();

		// Button
		connectButton = new JButton("Connexion");
	}

	// *****************************************************************
	// *
	// * PROPERTIES
	// *
	// *****************************************************************

	/**
	 * Call all setProperties methods
	 * 	to define SWING elements properties
	 * @see Login#setMainWindowProperties()
	 * @see Login#setTitleLabelProperties()
	 */
	private void setProperties() {
		setMainWindowProperties();
		setTitleLabelProperties();
	}

	/** Set the main mainWindow properties */
	private void setMainWindowProperties() {
		mainWindow.setContentPane(mainPanel);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Position/Size properties
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setSize(SIZEX, SIZEY);
		mainWindow.setResizable(false);
	}

	/** Set the titleLabel Properties */
	private void setTitleLabelProperties() {
		titleLabel.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 20));
	}

	// *****************************************************************
	// *
	// * LAYOUT
	// *
	// *****************************************************************
	
	/**
	 * Call all setLayout methods to define SWING layouts
	 * @see Login#setMainPanelLayout()
	 * @see Login#setLoginPanelLayout()
	 */
	private void setLayout() {
		setMainPanelLayout();
		setLoginPanelLayout();
	}

	/** Define the mainWindow SWING Properties */
	private void setMainPanelLayout() {
		// Creation Border Layout
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		// Create gaps
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.CENTER)
				.addComponent(titleLabel)
				.addComponent(loginPanel)
				.addComponent(connectButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(titleLabel));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(loginPanel));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(connectButton));
		layout.setVerticalGroup(vGroup);
	}

	/** Define the loginPanel SWING Properties */
	private void setLoginPanelLayout() {
		GroupLayout layout = new GroupLayout(loginPanel);
		loginPanel.setLayout(layout);

		// Create gaps
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.TRAILING)
				.addComponent(loginLabel)
				.addComponent(passwordLabel));
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addComponent(loginField)
				.addComponent(passwordField));
		layout.setHorizontalGroup(hGroup);

		// Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.BASELINE)
				.addComponent(loginLabel)
				.addComponent(loginField));
		vGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.BASELINE)
				.addComponent(passwordLabel)
				.addComponent(passwordField));
		layout.setVerticalGroup(vGroup);
	}

	// *****************************************************************
	// *
	// * EVENTS
	// *
	// *****************************************************************

	/**
	 * Call all addEventListener methods to define event
	 * @see Login#addEventListenerConnectButton()
	 */
	private void addEventListeners() {
		addEventListenerConnectButton();
		addEventListenerMainWindow();
	}

	/**
	 * Add a new Event listener on connectButton
	 * It will check informations and try to connect to server
	 */
	private void addEventListenerConnectButton() {
		this.connectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (loginField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(mainWindow,
							"<html>Veuillez renseigner un <b>loginLabel</b>" + " ET <b>passwordLabel</b>.</html>", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String log = loginField.getText();
					String pwd = String.valueOf(passwordField.getPassword());

					ui.emmitConnect(log, pwd);
				}
			}
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
