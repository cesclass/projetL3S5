package test;

import ihm.ClientApplication;
import interfaces.UserInterface;

public class TestClientApplication {
	
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.createTicket("L3-INFO-TDA4", "changement de salle",
				"Bonjour\n"
				+ "Je vous signale que le cours de Graphe aura lieu "
				+ "en U3-208\n"
				+ "Merci d'en prendre compte");

		ClientApplication app = new ClientApplication(ui.getTicketManager());

		ui.createTicket("L2-INFO-TDA4", "changement de salle",
				"Bonjour\n"
				+ "Je vous signale que le cours de Graphe aura lieu "
				+ "en U3-208\n"
				+ "Merci d'en prendre compte");
		
		app.majGUI();
	}
	
}
