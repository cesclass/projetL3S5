package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import communications.Message;
import communications.Status;

public class MessageInfosWindow {
    // *****************************************************************
	// *
	// * ATTRIBUTES (SWING)
	// *
	// *****************************************************************
    
    // Container
    private JFrame mainWindow;
    private JPanel mainPanel;
    private JPanel messagePanel;
    private JScrollPane messageScrollPane;
    private JScrollPane statusScrollPane;

    // Label
    private JLabel messageInfosLabel;

    // TextArea
    private JTextArea messageArea;

    // Button
    private JButton okButton;

    // DataStructure
    private JTable statusTable;
    
    // *****************************************************************
	// *
	// * ATTRIBUTE (DATA)
	// *
    // *****************************************************************
    
    /** Main window of client application */
    ClientApplication parent;
    /** Message currently selected */
    private Message message;
    /** Statuses for each users */
    private ArrayList<Status> statuses;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

	/**
     * Constructor for MessageInfosWindow
     * @param parent : Main window of Client application
     * @param message currently selected
     * @param statuses for each users
     */
	public MessageInfosWindow(
            ClientApplication parent, 
            Message message, 
            ArrayList<Status> statuses) 
    {
        this.parent = parent;
        this.message = message;
        this.statuses = statuses;

		buildSWINGElements();
		setProperties();
        setLayout();
        addEventListeners();
        
		mainWindow.pack();
		mainWindow.setVisible(true);
    }
    
    // *****************************************************************
	// *
	// * BUILD
	// *
	// *****************************************************************

	/**
	 * Call all buildSWING methods to build graphic elements
	 * @see MessageInfosWindow#buildSWINGContainers()
	 * @see MessageInfosWindow#buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContents();
		buildSWINGContainers();
	}

	/** Build all SWING containers */
	private void buildSWINGContainers() {
		mainWindow = new JFrame();
        mainPanel = new JPanel();
        messagePanel = new JPanel();
        messageScrollPane = new JScrollPane(messageArea);
        statusScrollPane = new JScrollPane(statusTable);
	}

	/** Build all SWING contents */
	private void buildSWINGContents() {
        // Labels
        messageInfosLabel = new JLabel(
                message.getAuthor() + ", "
                + (new SimpleDateFormat("dd/MM/YYYY HH:mm"))
                        .format(message.getDate()));

        // TextArea
        messageArea = new JTextArea(message.getContent());

        // Button
        okButton = new JButton("OK");

        // Table
        StatusTableModel model = new StatusTableModel(statuses);
        statusTable = new JTable(model);
    }

    // *****************************************************************
	// *
	// * PROPERTIES
	// *
	// *****************************************************************

	/**
	 * Call all setProperties methods
	 * 	to define SWING elements properties
	 * @see MessageInfosWindow#setMainWindowProperties()
     * @see MessageInfosWindow#setMainPanelProperties()
     * @see MessageInfosWindow#setMessageAreaProperties()
     * @see MessageInfosWindow#setStatusTableProperties()
	 */
	private void setProperties() {
        setMainWindowProperties();
        setMainPanelProperties();
        setMessageAreaProperties();
        setStatusTableProperties();
    }
    
    /** Define the mainWindow Properties */
    private void setMainWindowProperties() {
        mainWindow.setContentPane(mainPanel);
        mainWindow.setMinimumSize(new Dimension(500, 400));
        mainWindow.setUndecorated(true);
        mainWindow.setLocationRelativeTo(null);
    }

    /** Define the mainPanel SWING Properties */
    private void setMainPanelProperties() {
        mainPanel.setBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, Color.BLACK));
    }

    /** Define the messageArea Properties */
    private void setMessageAreaProperties() {
		messageArea.setEnabled(false);
		messageArea.setRows(5);
		messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setDisabledTextColor(Color.BLACK);     
    }

    private void setStatusTableProperties() {
        statusTable.setShowGrid(true);

        statusTable.getColumnModel().getColumn(0).setHeaderValue(
                "Utilisateurs");
        statusTable.getColumnModel().getColumn(1).setHeaderValue(
                "Status");

        statusTable.getColumnModel().getColumn(0).setCellRenderer(
                new StatusTableCellRenderer());                
        statusTable.getColumnModel().getColumn(0).setCellRenderer(
                new StatusTableCellRenderer());

        statusTable.setEnabled(false);
    }
    
    // *****************************************************************
	// *
	// * LAYOUT
	// *
	// *****************************************************************
    
    /**
	 * Call all setLayout methods to define SWING layouts
	 * @see MessageInfosWindow#setMainPanelLayout()
     * @see MessageInfosWindow#setMessagePanelLayout()
	 */
	private void setLayout() {
        setMainPanelLayout();
        setMessagePanelLayout();
    }
    
    /** Define the mainPanel SWING Layout */
    private void setMainPanelLayout() {
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.CENTER)
                .addComponent(messagePanel)
                .addComponent(statusScrollPane)
                .addComponent(okButton));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messagePanel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(statusScrollPane));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(okButton));
		layout.setVerticalGroup(vGroup);
    }

    /** Define the messagePanel SWING Layout */
    private void setMessagePanelLayout() {
        GroupLayout layout = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout);

		// Horizontal groups
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageScrollPane)
                .addComponent(messageInfosLabel));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageScrollPane));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageInfosLabel));
		layout.setVerticalGroup(vGroup);
    }

    // *****************************************************************
	// *
	// * EVENTS
	// *
	// *****************************************************************

	/**
	 * Call all addEventListener methods to define event
	 * @see MessageInfosWindow#addEventListenerOkButton()
	 */
	private void addEventListeners() {
		addEventListenerOkButton();
	}

	/**
	 * Add a new Event listener on okButton
	 * It will close this window and enable the main window
	 */
    private void addEventListenerOkButton() {
    	this.okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainWindow().setEnabled(true);
                mainWindow.dispose();
            }
    	});
    }
}