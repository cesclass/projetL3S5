package ihm;

import java.awt.*;

import javax.swing.*;

public class ClientApplication {
	// Containers
	private JFrame mainWindow;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel headPanel;
	private JPanel welcomePanel;
	private JPanel buttonPanel;
	private JScrollPane ticketTreeScrollPane;
	private JScrollPane chatScrollPane;
	private JScrollPane textScrollPane;

	// Labels
	private JLabel welcomeLabel = new JLabel("Bonjour, Prénom NOM");

	// Buttons
	private JButton newTicketButton = new JButton("Nouveau ticket");
	private JButton disconectButton = new JButton("Déconnexion");
	private JButton sendButton = new JButton("Envoyer");
	
	public ClientApplication() {
		createWindow();
		showWindow();
	}
	
	/**
	 * Create the window
	 */
	private void createWindow() {
		buildSWINGElements();

		mainWindow.setContentPane(mainPanel);
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setStyle();
		setLayout();
	}

	/**
	 * Call all buildSWING methods to build graphic elements
	 * @see ClientApplication.buildSWINGContainers()
	 * @see ClientApplication.buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContainers();
		buildSWINGContents();
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
		ticketTreeScrollPane = new JScrollPane();
		chatScrollPane = new JScrollPane();
		textScrollPane = new JScrollPane();
	}

	/**
	 * Build all SWING contents
	 */
	private void buildSWINGContents() {
		// Labels
		welcomeLabel = new JLabel("Bonjour, Prénom NOM");

		// Buttons
		newTicketButton = new JButton("Nouveau ticket");
		disconectButton = new JButton("Déconnexion");
		sendButton = new JButton("Envoyer");
	}

	/**
	 * Call all setStyle methods to define style
	 * @see ClientApplication.setMainWindowStyle()
	 * @see ClientApplication.setLeftPanelStyle()
	 */
	private void setStyle() {
		setMainWindowStyle();
		setLeftPanelStyle();
		
		mainWindow.pack();
	}

	/**
	 * Define the mainWindow Style
	 */
	private void setMainWindowStyle() {
		mainWindow.setMinimumSize(new Dimension(700, 400));
	}

	/**
	 * Define the leftPanel Style
	 */
	private void setLeftPanelStyle() {
		// Border
		leftPanel.setBorder(BorderFactory.createMatteBorder(
				0, 0, 0, 1, Color.black));
		// Max size
		leftPanel.setMaximumSize(new Dimension(
				400, (int) mainPanel.getMaximumSize().getHeight()));
	}

	/**
	 * Call all setLayout methods to define layouts
	 * @see ClientApplication.setMainPanelLayout()
	 * @see ClientApplication.setLeftPanelLayout()
	 * @see ClientApplication.setHeadPanelLayout()
	 * @see ClientApplication.setWelcomePanelLayout()
	 * @see ClientApplication.setButtonPanelLayout()
	 */
	private void setLayout() {
		setMainPanelLayout();
		setLeftPanelLayout();
		setHeadPanelLayout();
		setWelcomePanelLayout();
		setButtonPanelLayout();
	}
	
	/**
	 * Define the mainPanel Layout
	 */
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
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.CENTER)
				.addComponent(welcomePanel)
				.addComponent(buttonPanel));
		layout.setHorizontalGroup(hGroup);

		// Vertical groups
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().
				addComponent(welcomePanel));
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(buttonPanel));
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

	/**
	 * Show the main window
	 */
	private void showWindow() {
		mainWindow.setVisible(true);
	}
}
