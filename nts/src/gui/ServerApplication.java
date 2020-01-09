package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ServerApplication {
	//Constantes
	public final static int MAX_SIZE_LOGIN = 8;
	public final static int MAX_SIZE_PASSWORD = 32;
	public final static int MAX_SIZE_FIRSTNAME = 32;
	public final static int MAX_SIZE_LASTNAME = 32;
	public final static int MAX_SIZE_NAME = 32;
	public final static int MAX_SIZE_INFO = 128;
	
    // Containers
    private JFrame mainWindow;
    private JPanel mainPanel;
    private JPanel displayPanel;
    private JPanel promptPanel;
    private JScrollPane scrollPane;

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
		this.addEventListeners();

		mainWindow.pack();
    }
    
    /**
	 * Show the main window
	 */
	private void showWindow() {
		mainWindow.setVisible(true);
    }
    
	// *****************************************************************
	// *
	// * BUILD
	// *
	// *****************************************************************

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
		mainWindow = new JFrame("neOCampus ticket service - Administration");
        mainPanel = new JPanel();
        displayPanel = new JPanel();
        promptPanel = new JPanel();
        scrollPane = new JScrollPane(displayArea);
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
    
    // *****************************************************************
	// *
	// * PROPERTIES
	// *
	// *****************************************************************

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
		displayArea.setFont(new Font("monospaced", Font.PLAIN, 12));
	}

	/**
	 * Define the promptPanel Properties
	 */
	private void setPromptPanelProperties() {
		promptPanel.setBorder(BorderFactory
				.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		promptField.setBorder(BorderFactory
				.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		promptField.setFont(new Font("monospaced", Font.PLAIN, 12));
	}
	


	// *****************************************************************
	// *
	// * LAYOUT
	// *
	// *****************************************************************

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
				.addComponent(scrollPane));
		layout.setHorizontalGroup(hGroup);

		// Vertical group
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(scrollPane));
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
	
	// *****************************************************************
	// *
	// * EVENT
	// *
	// *****************************************************************
	
	private void addEventListeners() {
		addEventListenerSendButton();
	}
	
	private void addEventListenerSendButton() {
		this.sendButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(!promptField.getText().isEmpty()) {
					commandsCase();
				}
			}
			
			/**
			 * Check the command use
			 */
			private void commandsCase() {
				String chaine = promptField.getText();
				String tab[] = chaine.split(" ");
				switch(tab[0]) {
					case "users" :
						printUsers();
						break;
					case "adduser" :
						addUser(tab);
						break;
					case "deluser" :
						delUser(tab);
						break;
					case "groups" :
						printGroups();
						break;
					case "addgroup" :
						addGroup(tab);
						break;
					case "delgroup" :
						delGroup(tab);
						break;
					case "groupusers" :
						printGroupUsers(tab);
						break;
					case "usergroups" :
						printUserGroups(tab);
						break;
					case "addusergroup" :
						addUserGroup(tab);
						break;
					case "delusergroup" :
						delUserGroup(tab);
						break;
					default :
						help();
						break;
				}
			}
			
			/**
			 * Affichage liste d'utilisateurs
			 */
			private void printUsers() {
				displayArea.setText(displayArea.getText() + "printUsers : Ok!\n");
				promptField.setText("");
			}
			
			/**
			 * Ajout utilisateur
			 */
			private void addUser(String[] tab) {
				if(tab.length == 6 &&
						tab[1].length() <= MAX_SIZE_LOGIN &&
						tab[2].length() <= MAX_SIZE_PASSWORD &&
						tab[3].length() <= MAX_SIZE_FIRSTNAME &&
						tab[4].length() <= MAX_SIZE_LASTNAME &&
						(tab[5].equals("true") || tab[5].equals("false"))) {
					
					displayArea.setText(displayArea.getText() + "addUser : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Suppression utilisateur
			 */
			private void delUser(String[] tab) {
				if(tab.length == 2 &&
						tab[1].length() <= MAX_SIZE_LOGIN) {
					
					displayArea.setText(displayArea.getText() + "delUser : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Affichage liste de groupe
			 */
			private void printGroups() {
				displayArea.setText(displayArea.getText() + "printGroups : Ok!\n");
				promptField.setText("");
			}
			
			/**
			 * Ajout d'un groupe
			 */
			private void addGroup(String[] tab) {
				if(tab.length >= 4 &&
						tab[1].length() <= MAX_SIZE_NAME &&
						(tab[tab.length-1].equals("true") || tab[tab.length-1].equals("false"))) {
					
					int descriptionSize = 0;
					for(int i = 2 ; i < tab.length-1 ; i++) {
						descriptionSize += tab[i].length();
					}
					if(descriptionSize <= MAX_SIZE_INFO) {
						displayArea.setText(displayArea.getText() + "addGroup : Ok!\n");
						promptField.setText("");
					}else {
						help();
					}
					
				}else {
					help();
				}
			}
			
			/**
			 * Suppression d'un groupe
			 */
			private void delGroup(String[] tab) {
				if(tab.length == 2 && 
						tab[1].length() <= MAX_SIZE_NAME) {
					
					displayArea.setText(displayArea.getText() + "delGroup : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Affichage des utilisateurs d'un groupe
			 */
			private void printGroupUsers(String[] tab) {
				if(tab.length == 2 && 
						tab[1].length() <= MAX_SIZE_NAME) {
					
					displayArea.setText(displayArea.getText() + "printGroupUsers : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Affichage des groupes d'un utilisateur
			 */
			private void printUserGroups(String[] tab) {
				if(tab.length == 2 && 
						tab[1].length() <= MAX_SIZE_LOGIN) {
					
					displayArea.setText(displayArea.getText() + "printUserGroups : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Ajout d'un utilisateur dans un groupe
			 */
			private void addUserGroup(String[] tab) {
				if(tab.length == 3 && 
						tab[1].length() <= MAX_SIZE_LOGIN &&
						tab[2].length() <= MAX_SIZE_NAME) {
					
					displayArea.setText(displayArea.getText() + "addUserGroup : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Suppression d'un utilisateur dans un groupe
			 */
			private void delUserGroup(String[] tab) {
				if(tab.length == 3 && 
						tab[1].length() <= MAX_SIZE_LOGIN &&
						tab[2].length() <= MAX_SIZE_NAME) {
					
					displayArea.setText(displayArea.getText() + "delUserGroup : Ok!\n");
					promptField.setText("");
				}else {
					help();
				}
			}
			
			/**
			 * Affiche les commandes
			 */
			private void help() {
				displayArea.setText(displayArea.getText() +
						"// list commands and usages\n" + 
						"help\n" + 
						"\n" + 
						"// list users\n" + 
						"users\n" + 
						"// add user\n" + 
						"adduser \"login\" \"password\" \"firstname\" \"lastname\" \"istech\"\n" + 
						"// delete user\n" + 
						"deluser \"login\"\n" + 
						"\n" + 
						"// list groups\n" + 
						"groups\n" + 
						"// add group\n" + 
						"addgroup \"name\" \"description\" \"is_tech\"\n" + 
						"// delete group\n" + 
						"delgruop \"name\"\n" + 
						"\n" + 
						"// list users in group\n" + 
						"groupusers \"name\"\n" + 
						"// list groups of user\n" + 
						"usergroups \"login\"\n" + 
						"// add user in group\n" + 
						"addusergroup \"login\" \"name\"\n" + 
						"// del user from group\n" + 
						"delusergroup \"login\" \"name\"\n" + 
						"\n" + 
						"Specs :\n" + 
						"  sizes :\n" + 
						"  - login <= 8\n" + 
						"  - password <= 32\n" + 
						"  - firstname <= 32\n" + 
						"  - lastname <= 32\n" + 
						"  - name <= 32\n" + 
						"  - info <= 128\n" + 
						"  restrictions :\n" + 
						"  - istech = (true | false)" +
						"\n");
				promptField.setText("");
			}
		});
	}
	
}