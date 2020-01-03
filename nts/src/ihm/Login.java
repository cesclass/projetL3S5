package ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import communications.TicketManager;

public class Login {
	//Constante
	public final static int SIZEX = 275;
	public final static int SIZEY = 150;
	
	//Creation fenêtre
	JFrame frame = new JFrame("Login");
	
	//Creation bouton et Label
	JButton button = new JButton("Connexion");
	JLabel title = new JLabel("neOCampus ticket service");
	JLabel login = new JLabel("       login");
	JLabel password = new JLabel("password");
	
	//Creation zone de mot de passe et de saisie
	JTextField id = new JTextField();
	JPasswordField mdp = new JPasswordField();

	//Constructeur
	public Login() {
		showWindow(createWindow());
	}


	// ************************************************************************
	// * 
	// *	BUILD
	// * 
	// ************************************************************************
	
	/**
	 * Creation de la fenêtre
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
	 * Creation Panel central
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
	 * Mise en place du layout du panel principal
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
	 * Mise en place du layout du panel de la box
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
	 * Affichage de la fenêtre
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
	 * Creation des évènements
	 */
	private void addEventListeners(){
		
		this.addEventListenerOpenMainWindow();
		
	}
	
	/**
	 * Ouverture fenêtre principale
	 */
    private void addEventListenerOpenMainWindow() {
        this.button.addActionListener(new ActionListener() {
            
			public void actionPerformed(ActionEvent arg0) {
				if(id.getText().isEmpty() || String.valueOf(mdp.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(frame, "<html>Erreur<br><b>login</b> ou <b>password</b> incorrect</html>", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					id.setText("");
					mdp.setText("");
					ClientApplication mainWindow = new ClientApplication(new TicketManager());
					frame.setEnabled(false);
					frame.setVisible(false);
				}
			}
            
        });
    }
	
}
