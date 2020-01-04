package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class ServerApplication {
    // Containers
    private JFrame mainWindow;
    private JPanel mainPanel;
    private JPanel displayPanel;
    private JPanel promptPanel;

    // Label
    private JLabel promptLabel;
    
    // TextArea
    private JTextArea displayArea;
    private JTextField promptField;

    // Button
    private JButton sendButton;

    public ServerApplication() {
		createWindow();
		showWindow();
	}
	
	public JFrame getMainWindow() {
		return mainWindow;
    }
    
    /**
	 * Create the window
	 */
	private void createWindow() {
		buildSWINGElements();

		mainWindow.setContentPane(mainPanel);
		mainWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setProperties();
		setLayout();

		// Set Event
		//this.addEventListeners();

		mainWindow.pack();
    }
    
    /**
	 * Show the main window
	 */
	private void showWindow() {
		mainWindow.setVisible(true);
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
	 * @see ServerApplication.buildSWINGContainers()
	 * @see ServerApplication.buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContents();
		buildSWINGContainers();
	}

	/**
	 * Build all SWING containers
	 */
	private void buildSWINGContainers() {
		mainWindow = new JFrame("Administration - neOCampus ticket service");
        mainPanel = new JPanel();
        displayPanel = new JPanel();
        promptPanel = new JPanel();
	}

	/**
	 * Build all SWING contents
	 */
	private void buildSWINGContents() {
        // Labels
        promptLabel = new JLabel(">_");

		// TextArea
		displayArea = new JTextArea();
		promptField = new JTextField();

		// Button
		sendButton = new JButton("send");

    }
    
    // ************************************************************************
	// *
	// * PROPERTIES
	// *
	// ************************************************************************

	/**
	 * Call all setProperties methods to define properties
	 * 
	 * @see ServerApplication.setMainWindowProperties()
	 * @see ServerApplication.setDisplayAreaProperties()
	 * @see ServerApplication.setPromptPanelProperties()
	 */
	private void setProperties() {
		setMainWindowProperties();
		setDisplayAreaProperties();
		setPromptPanelProperties();
	}

	/**
	 * Define the mainWindow Properties
	 */
	private void setMainWindowProperties() {
		mainWindow.setMinimumSize(new Dimension(800, 500));
		mainWindow.setLocationRelativeTo(null);
	}

	/**
	 * Define the displayArea Properties
	 */
	private void setDisplayAreaProperties() {
		displayArea.setEditable(false);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		displayArea.setBorder(BorderFactory
				.createMatteBorder(1, 1, 1, 1, Color.BLACK));
	}

	/**
	 * Define the promptPanel Properties
	 */
	private void setPromptPanelProperties() {
		promptPanel.setBorder(BorderFactory
				.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		promptField.setBorder(BorderFactory
				.createMatteBorder(1, 1, 1, 1, Color.BLACK));


	}

	// ************************************************************************
	// *
	// * LAYOUT
	// *
	// ************************************************************************

	/**
	 * Call all setLayout methods to define layouts
	 * 
	 * @see ServerApplication.setMainPanelLayout()
	 * @see ServerApplication.setDisplayPanelLayout()
	 * @see ServerApplication.setPromptPanelLayout()
	 */
	private void setLayout() {
		setMainPanelLayout();
		setDisplayPanelLayout();
		setPromptPanelLayout();
	}

	/**
	 * Define the mainPanel Layout
	 */
	private void setMainPanelLayout() {
		BorderLayout layout = new BorderLayout();
		mainPanel.setLayout(layout);

		mainPanel.add(displayPanel, BorderLayout.CENTER);
		mainPanel.add(promptPanel, BorderLayout.PAGE_END);
	}

	/**
	 * Define the displayPanel Layout
	 */
	private void setDisplayPanelLayout() {
		GroupLayout layout = new GroupLayout(displayPanel);
		displayPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// Horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(displayArea));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(displayArea));
		layout.setVerticalGroup(vGroup);
	}

	/**
	 * Define the promptPanel Layout
	 */
	private void setPromptPanelLayout() {
		GroupLayout layout = new GroupLayout(promptPanel);
		promptPanel.setLayout(layout);
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);

		// Horizontal group
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(promptLabel));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(promptField));
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(sendButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.CENTER)
				.addComponent(promptLabel)
				.addComponent(promptField)
				.addComponent(sendButton));
		layout.setVerticalGroup(vGroup);
	}
}