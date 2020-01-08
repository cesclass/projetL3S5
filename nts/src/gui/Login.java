package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interfaces.UserInterface;

public class Login {
    // *****************************************************************
	// *
	// * CONSTANTS
	// *
    // *****************************************************************
	
	/** Width of the window */
	public final static int SIZEX = 275;
	/** Height of the window */
	public final static int SIZEY = 150;
	
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
    // *****************************************************************
	
	// Containers
	private JFrame frame;
	private Box box;

	// Labels
	private JLabel title;
	private JLabel login;
	private JLabel password;

	// TextFields
	private JTextField id;
	private JPasswordField mdp;

	// button
	private JButton button;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************
	
	/** Constructor for standard Login window */
	public Login() {
		showWindow(createWindow());
	}
	
	/**
	 * Creation of the window
	 * @return main frame
	 */
	private JFrame createWindow() {
		buildSWINGElements();
		
		setMainFrame();
		
		setFrameFont();
		
		//On ajoute les évènements
		this.addEventListeners();
		return frame;
	}


	// ************************************************************************
	// * 
	// *	BUILD
	// * 
	// ************************************************************************
	
	/**
	 * Call all buildSWING methods to build graphic elements
	 * 
	 * @see Login.buildSWINGContainers()
	 * @see Login.buildSWINGContents()
	 */
	private void buildSWINGElements() {
		buildSWINGContainers();
		buildSWINGContents();
	}
	
	/** Build all SWING containers */
	private void buildSWINGContainers() {
		frame = new JFrame("Login");
		box = new Box(BoxLayout.Y_AXIS);
	}

	/** Build all SWING contents */
	private void buildSWINGContents() {
		button = new JButton("Connexion");
		title = new JLabel("neOCampus ticket service");
		login = new JLabel("login");
		password = new JLabel("password");
		id = new JTextField();
		mdp = new JPasswordField();
	}
	
	
	// ************************************************************************
	// *
	// * PROPERTIES
	// *
	// ************************************************************************
	
	/** Set the main frame properties */
	private void setMainFrame() {
		//On initialise la taille de la fenêtre
		frame.setSize(SIZEX, SIZEY);
		//Mise en place de l'évènement de fermeture de la fenêtre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Positionnement au centre du titre
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		
		frame = setFrameLayout();
		
	
		
		//Paramétrage position
		frame.setLocationRelativeTo(null);
		
		//On empêche la l'agrandissement et rétrécissement de la fenêtre
		frame.setResizable(false);
	}
	
	/** Set the main frame font properties */
	private void setFrameFont() {
		//Mise en place de la police/taille d'écriture
		title.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 20));
		login.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 15));
		password.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 15));
	}
	
	/**
	 * Creation of the central Box
	 * @return the central Box
	 */
	private Box createCenterBox() {
		//Creation de Box et de Panel
		JPanel logPanel = new JPanel();

		setCenter(box, logPanel);

		return box;
	}
	
	/**
	 * Set the Box properties
	 * @param the box
	 * @param the panel who contain the component for the login
	 */
	private void setCenter(Box root, JPanel logPanel) {
		setLogPanelLayout(logPanel);

		//Creation de glue
		root.add(Box.createVerticalGlue());
		root.add(logPanel);
		root.add(Box.createVerticalGlue());
	}

	// ************************************************************************
	// * 
	// *	Layout
	// * 
	// ************************************************************************

	/**
	 * Set up of the main panel layout
	 * @return the main frame
	 */
	private JFrame setFrameLayout() {
		//Creation Border Layout
		BorderLayout layout = new BorderLayout();
		
		//Creation de panel
		JPanel root = new JPanel(layout);
		Box centerBox = createCenterBox();
		frame.setContentPane(root);
		
		
		//Comportement Border Layout
		root.add(title, BorderLayout.PAGE_START);
		root.add(centerBox, BorderLayout.CENTER);
		root.add(button, BorderLayout.PAGE_END);
		
		return frame;
	}

	/**
	 * Set up of the center Box layout
	 * @param panel who contain the component for the login
	 */
	private void setLogPanelLayout(JPanel logPanel) {
		GroupLayout layout = new GroupLayout(logPanel);
		logPanel.setLayout(layout);
		
		//Comportement Horizontal
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.TRAILING)
				.addComponent(login)
				.addComponent(password));
		hGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addComponent(id)
				.addComponent(mdp));
		layout.setHorizontalGroup(hGroup);
		
		//Comportement Vertical
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.BASELINE)
				.addComponent(login)
				.addComponent(id));
		vGroup.addGroup(layout.createParallelGroup(
				GroupLayout.Alignment.BASELINE)
				.addComponent(password)
				.addComponent(mdp));
		layout.setVerticalGroup(vGroup);
	}
	

	// ************************************************************************
	// * 
	// *	VIEW
	// * 
	// ************************************************************************
	
	/**
	 * Set the window visible
	 * @param the main frame
	 */
	private void showWindow(JFrame frame) {
		frame.setVisible(true);
	}
	
	// ************************************************************************
	// * 
	// *	EVENTS
	// * 
	// ************************************************************************
	
	/** Set up of all the events */
	private void addEventListeners(){
		
		this.addEventListenerOpenMainWindow();
		
	}
	
	/** Add an event on the connection button */
    private void addEventListenerOpenMainWindow() {
        this.button.addActionListener(new ActionListener() {
            
			public void actionPerformed(ActionEvent arg0) {
				if(id.getText().isEmpty() || String.valueOf(mdp.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(frame, "<html>Erreur<br><b>login</b> ou <b>password</b> incorrect</html>", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					String log = id.getText();
					String mdpValue = String.valueOf(mdp.getPassword());
					id.setText("");
					mdp.setText("");
					
					//Creation UserInterface
					UserInterface ui = new UserInterface();
					ui.connect(log, mdpValue);
					
					new ClientApplication(ui);
					frame.setEnabled(false);
					frame.setVisible(false);
				}
			}
            
        });
    }
	
}
