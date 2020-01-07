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
	// * ATTRIBUTE
	// *
    // *****************************************************************
	
	/**
	 * Constant width of the window
	 */
	public final static int SIZEX = 275;
	
	/**
	 * Constant height of the window
	 */
	public final static int SIZEY = 150;
	
	/**
	 * Main Frame
	 */
	JFrame frame = new JFrame("Login");
	
	/**
	 * Button to connect to the Client Application
	 */
	JButton button = new JButton("Connexion");
	
	/**
	 * Title of the login window
	 */
	JLabel title = new JLabel("neOCampus ticket service");
	
	/**
	 * Label next to the text field to write an id
	 */
	JLabel login = new JLabel("login");
	
	/**
	 * Label next to the text field to write a password
	 */
	JLabel password = new JLabel("password");
	
	/**
	 * Text field to write an id
	 */
	JTextField id = new JTextField();
	
	/**
	 * Text field to write a password
	 */
	JPasswordField mdp = new JPasswordField();

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************
	
	/**
	 * Constructor for standard Login window
	 */
	public Login() {
		showWindow(createWindow());
	}


	// ************************************************************************
	// * 
	// *	BUILD
	// * 
	// ************************************************************************
	
	/**
	 * Creation of the window
	 * @return main frame
	 */
	private JFrame createWindow() {
		//On initialise la taille de la fenêtre
		frame.setSize(SIZEX, SIZEY);
		//Mise en place de l'évènement de fermeture de la fenêtre
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Positionnement au centre du titre
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		
		frame = setFrameLayout();
		
		//Mise en place de la police/taille d'écriture
		title.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 20));
		login.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 15));
		password.setFont(new java.awt.Font(Font.SANS_SERIF, Font.BOLD, 15));
		
		//Paramétrage position
		frame.setLocationRelativeTo(null);
		
		//On empêche la l'agrandissement et rétrécissement de la fenêtre
		frame.setResizable(false);
		
		//On ajoute les évènements
		this.addEventListeners();
		
		return frame;
	}
	
	/**
	 * Creation of the central Box
	 * @return the central Box
	 */
	private Box setCenter() {
		//Creation de Box et de Panel
		Box root = new Box(BoxLayout.Y_AXIS);
		JPanel logPanel = new JPanel();

		setLogPanelLayout(logPanel);

		//Creation de glue
		root.add(Box.createVerticalGlue());
		root.add(logPanel);
		root.add(Box.createVerticalGlue());


		return root;
	}


	// ************************************************************************
	// * 
	// *	Layout
	// * 
	// ************************************************************************

	/**
	 * Set up of the main panel's layout
	 * @return the main frame
	 */
	private JFrame setFrameLayout() {
		//Creation Border Layout
		BorderLayout layout = new BorderLayout();
		
		//Creation de panel
		JPanel root = new JPanel(layout);
		Box centerBox = setCenter();
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
	
	/**
	 * Set up of all the events
	 */
	private void addEventListeners(){
		
		this.addEventListenerOpenMainWindow();
		
	}
	
	/**
	 * Add an event on the connexion button
	 */
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
