package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import interfaces.UserInterface;
import user.Group;

/** New Ticket window of Client application */
public class NewTicketWindow {
    // *****************************************************************
	// *
	// * ATTRIBUTES (SWING)
	// *
    // *****************************************************************
    
    // Containers
    private JFrame newTicketFrame;
    private JPanel newTicketPanel;
    private JPanel groupPanel;
    private JPanel titlePanel;
    private JPanel messagePanel;
    private JPanel buttonsPanel;

    // Labels
    private JLabel headerLabel;
    private JLabel groupLabel;
    private JLabel descritpionLabel;
    private JLabel descriptionContentLabel;
    private JLabel titleLabel;
    private JLabel messageLabel;

    // ComboBox
    private JComboBox<Group> groupCombo;

    // Text
    private JTextField titleField;
    private JTextArea messageArea;

    // Buttons
    private JButton sendButton;
    private JButton cancelButton;

    // *****************************************************************
	// *
	// * ATTRIBUTES (DATA)
	// *
    // *****************************************************************
    
    /** Main window of client application */
    ClientApplication parent;
    /** UserInterface allows to communicate with the User/application */
    UserInterface ui;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

    /**
     * Constructor for NewTicketWindow
     * @param parent Main window of Client application
     * @param ui (UserInterface)
     */
    public NewTicketWindow(ClientApplication parent, UserInterface ui) {
        this.parent = parent;
        this.ui = ui;

        buildSWINGElements();
        setProperties();
        setLayout();
        addEventListeners();

        newTicketFrame.pack();
        newTicketFrame.setVisible(true);
    }

    // *****************************************************************
    // *
    // * BUILD
    // *
    // *****************************************************************

    /**
     * Call all buildSWING methods to build graphic elements
     * @see NewTicketWindow.buildSWINGContainers()
     * @see NewTicketWindow.buildSWINGContents()
     */
    private void buildSWINGElements() {
        buildSWINGContents();
        buildSWINGContainers();
    }

    /** Build all SWING containers */
    private void buildSWINGContainers() {
        newTicketFrame = new JFrame();
        newTicketPanel = new JPanel();
        groupPanel = new JPanel();
        titlePanel = new JPanel();
        messagePanel = new JPanel();
        buttonsPanel = new JPanel();
    }

    /** Build all SWING contents */
    private void buildSWINGContents() {
        // Labels
        headerLabel = new JLabel("Nouveau ticket");
        groupLabel = new JLabel("Groupe concerné");
        descritpionLabel = new JLabel("Description");
        descriptionContentLabel = new JLabel();
        titleLabel = new JLabel("Titre du ticket");
        messageLabel = new JLabel("Message");

        // ComboBox
        groupCombo = new JComboBox<>(new Vector<Group>(ui.getAllGroups()));

        // TextField/Area
        titleField = new JTextField();
        messageArea = new JTextArea();

        // Buttons
        sendButton = new JButton("Envoyer");
        cancelButton = new JButton("Annuler");
    }

    // *****************************************************************
    // *
    // * PROPERTIES
    // *
    // *****************************************************************

    /**
     * Call all setProperties methods
     *  to define SWING properties
     * @see NewTicketWindow#setNewTicketFrameProperties()
     * @see NewTicketWindow#setNewTicketPanelProperties()
     */
    private void setProperties() {
        setNewTicketFrameProperties();
        setNewTicketPanelProperties();
        setNewTicketTextAreaProperties();
    }

    /** Define the newTicketFrame SWING Properties */
    private void setNewTicketFrameProperties() {
        newTicketFrame.setContentPane(newTicketPanel);
        newTicketFrame.setMinimumSize(new Dimension(500, 400));
        newTicketFrame.setUndecorated(true);
        newTicketFrame.setLocationRelativeTo(null);
    }

    /** Define the newTicketPanel SWING Properties */
    private void setNewTicketPanelProperties() {
        newTicketPanel.setBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, Color.BLACK));
    }
    
    /** Define the newTicketTextArea SWING Properties */
    private void setNewTicketTextAreaProperties() {
    	messageArea.setFont(new Font("monospaced", Font.PLAIN, 12));
    }

    // *****************************************************************
    // *
    // * LAYOUT
    // *
    // *****************************************************************

    /**
     * Call all setLayout methods to define SWING layouts
     * @see NewTicketWindow.setNewTicketPanelLayout()
     * @see NewTicketWindow.setGroupPanelLayout()
     * @see NewTicketWindow.setTitlePanelLayout()
     * @see NewTicketWindow.setMessagePanelLayout()
     * @see NewTicketWindow.setButtonsPanelLayout()
     */
    private void setLayout() {
        setNewTicketPanelLayout();
        setGroupPanelLayout();
        setTitlePanelLayout();
        setMessagePanelLayout();
        setButtonsPanelLayout();
    }

    /** Define the newTicketPanel SWING Layout */
    private void setNewTicketPanelLayout() {
        GroupLayout layout = new GroupLayout(newTicketPanel);
        newTicketPanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);

        // Horizontal group
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.CENTER)
                .addComponent(headerLabel)
                .addComponent(groupPanel)
                .addComponent(titlePanel)
                .addComponent(messagePanel)
                .addComponent(buttonsPanel));
        layout.setHorizontalGroup(hGroup);

        // Vertical groups
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(headerLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(groupPanel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(titlePanel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messagePanel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(buttonsPanel));
        layout.setVerticalGroup(vGroup);
    }

    /** Define the groupPanel SWING Layout */
    private void setGroupPanelLayout() {
        GroupLayout layout = new GroupLayout(groupPanel);
        groupPanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal groups
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.TRAILING)
                .addComponent(groupLabel)
                .addComponent(descritpionLabel));
        hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
                .addComponent(groupCombo)
                .addComponent(descriptionContentLabel));
        layout.setHorizontalGroup(hGroup);

        // Vertical groups
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.BASELINE)
                .addComponent(groupLabel)
                .addComponent(groupCombo));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(descritpionLabel)
                .addComponent(descriptionContentLabel));
        layout.setVerticalGroup(vGroup);
    }

    /** Define the titlePanel SWING Layout */
    private void setTitlePanelLayout() {
        GroupLayout layout = new GroupLayout(titlePanel);
        titlePanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal group
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel)
                .addComponent(titleField));
        layout.setHorizontalGroup(hGroup);

        // Vertical groups
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleLabel));
        vGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.BASELINE)
                .addComponent(titleField));
        layout.setVerticalGroup(vGroup);
    }

    /** Define the messagePanel Layout */
    private void setMessagePanelLayout() {
        GroupLayout layout = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal group
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(
                GroupLayout.Alignment.LEADING)
                .addComponent(messageLabel)
                .addComponent(messageArea));
        layout.setHorizontalGroup(hGroup);

        // Vertical groups
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageArea));
        layout.setVerticalGroup(vGroup);
    }

    /** Define the buttonsPanel Layout */
    private void setButtonsPanelLayout() {
        GroupLayout layout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(layout);

        // Add padding
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Horizontal groups
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(sendButton));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(cancelButton));
        layout.setHorizontalGroup(hGroup);

        // Vertical group
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(sendButton)
                .addComponent(cancelButton));
        layout.setVerticalGroup(vGroup);
    }

    // *****************************************************************
    // *
    // * EVENTS
    // *
    // *****************************************************************

    /**
	 * Call all addEventListener methods to define event
	 * @see NewTicketWindow#addEventListenerSendButton()
	 * @see NewTicketWindow#addEventListenerCancelButton()
	 */
    private void addEventListeners() {
        addEventListenerSendButton();
        addEventListenerCancelButton();
    }

    /**
	 * Add a new Event listener on sendButton
	 * It will create a new Ticket
	 */
    private void addEventListenerSendButton() {
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String title = titleField.getText();
                String msg = messageArea.getText();

                // if title/message are not empty
            	if(!title.isEmpty() && !msg.isEmpty()) {
            		// Ticket creation
            		ui.createTicket((Group) groupCombo.getSelectedItem(),
            						title, 
            						msg);
            		
            		// Clear title/message fields
            		titleField.setText("");
                    messageArea.setText("");
                    
                    // GUI update (to correctly show the TicketTree)
                    parent.updateUI();

                    parent.getMainWindow().setEnabled(true);
                    newTicketFrame.dispose();
                } else {
                    // Show error message dialog
            		JOptionPane.showMessageDialog(
                            newTicketFrame, 
                            "<html><b>titre</b> ou <b>message</b> vide</html>", 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
            	}
            }
            
        });
    }
    
    /**
	 * Add a new Event listener on cancelButton
	 * It will close this window and enable the main window
	 */
    private void addEventListenerCancelButton() {
    	this.cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainWindow().setEnabled(true);
                newTicketFrame.dispose();
            }
    	});
    }
}