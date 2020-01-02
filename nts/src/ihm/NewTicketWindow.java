package ihm;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import user.Group;

public class NewTicketWindow {
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

    // data
    JFrame parent;

    public NewTicketWindow(JFrame parent) {
        this.parent = parent;

        createWindow();
        showWindow();
    }

    /**
     * Create the window
     */
    public void createWindow() {
        buildSWINGElements();

        newTicketFrame.setContentPane(newTicketPanel);

        setProperties();
        setLayout();
        addEventListeners();

        newTicketFrame.pack();
    }

    /**
     * Show the main window
     */
    public void showWindow() {
        newTicketFrame.setVisible(true);
    }

    // ************************************************************************
    // *
    // * BUILD
    // *
    // ************************************************************************

    /**
     * Call all buildSWING methods to build graphic elements
     * 
     * @see NewTicketWindow.buildSWINGContainers()
     * @see NewTicketWindow.buildSWINGContents()
     */
    private void buildSWINGElements() {
        buildSWINGContents();
        buildSWINGContainers();
    }

    /**
     * Build all SWING containers
     */
    private void buildSWINGContainers() {
        newTicketFrame = new JFrame();
        newTicketPanel = new JPanel();
        groupPanel = new JPanel();
        titlePanel = new JPanel();
        messagePanel = new JPanel();
        buttonsPanel = new JPanel();
    }

    /**
     * Build all SWING contents
     */
    private void buildSWINGContents() {
        // Labels
        headerLabel = new JLabel("Nouveau ticket");
        groupLabel = new JLabel("Groupe concerné");
        descritpionLabel = new JLabel("Description");
        descriptionContentLabel = new JLabel();
        titleLabel = new JLabel("Titre du ticket");
        messageLabel = new JLabel("Message");

        // ComboBox
        groupCombo = new JComboBox<>();

        // Text
        titleField = new JTextField();
        messageArea = new JTextArea();

        // Buttons
        sendButton = new JButton("Envoyer");
        cancelButton = new JButton("Annuler");
    }

    // ************************************************************************
    // *
    // * PROPERTIES
    // *
    // ************************************************************************

    /**
     * Call all setProperties methods to define properties
     */
    private void setProperties() {
        setNewTicketFrameProperties();
    }

    private void setNewTicketFrameProperties() {
        newTicketFrame.setMinimumSize(new Dimension(300, 400));
        newTicketFrame.setUndecorated(true);
    }

    // ************************************************************************
    // *
    // * LAYOUT
    // *
    // ************************************************************************

    /**
     * Call all setLayout methods to define layouts
     * 
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

    /**
     * Define the newTicketPanel Layout
     */
    private void setNewTicketPanelLayout() {
        GroupLayout layout = new GroupLayout(newTicketPanel);
        newTicketPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(headerLabel)
                .addComponent(groupPanel).addComponent(titlePanel).addComponent(messagePanel)
                .addComponent(buttonsPanel));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().addComponent(headerLabel));
        vGroup.addGroup(layout.createParallelGroup().addComponent(groupPanel));
        vGroup.addGroup(layout.createParallelGroup().addComponent(titlePanel));
        vGroup.addGroup(layout.createParallelGroup().addComponent(messagePanel));
        vGroup.addGroup(layout.createParallelGroup().addComponent(buttonsPanel));
        layout.setVerticalGroup(vGroup);
    }

    /**
     * Define the groupLPanel Layout
     */
    private void setGroupPanelLayout() {
        GroupLayout layout = new GroupLayout(groupPanel);
        groupPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(groupLabel)
                .addComponent(descritpionLabel));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(groupCombo)
                .addComponent(descriptionContentLabel));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(groupLabel)
                .addComponent(groupCombo));
        vGroup.addGroup(
                layout.createParallelGroup().addComponent(descritpionLabel).addComponent(descriptionContentLabel));
        layout.setVerticalGroup(vGroup);
    }

    /**
     * Define the titlePanel Layout
     */
    private void setTitlePanelLayout() {
        GroupLayout layout = new GroupLayout(titlePanel);
        titlePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(titleLabel)
                .addComponent(titleField));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().addComponent(titleLabel));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titleField));
        layout.setVerticalGroup(vGroup);
    }

    /**
     * Define the messagePanel Layout
     */
    private void setMessagePanelLayout() {
        GroupLayout layout = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(messageLabel)
                .addComponent(messageArea));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().addComponent(messageLabel));
        vGroup.addGroup(layout.createParallelGroup().addComponent(messageArea));
        layout.setVerticalGroup(vGroup);
    }

    /**
     * Define the buttonsPanel Layout
     */
    private void setButtonsPanelLayout() {
        GroupLayout layout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(sendButton));
        hGroup.addGroup(layout.createParallelGroup().addComponent(cancelButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup().addComponent(sendButton).addComponent(cancelButton));
        layout.setVerticalGroup(vGroup);
    }

    // ************************************************************************
    // *
    // * EVENTS
    // *
    // ************************************************************************

    private void addEventListeners() {
        addEventListenerClose();
    }

    /**
     * Add actionPerformed event listener on sendButton
     */
    private void addEventListenerClose() {
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTicketFrame.setEnabled(false);
                newTicketFrame.setVisible(false);
                parent.setEnabled(true);

            }
            
        });
    }
}