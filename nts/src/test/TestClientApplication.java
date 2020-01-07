package test;

import gui.ClientApplication;
import interfaces.UserInterface;
import user.Group;

public class TestClientApplication {
		public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		Group g = new Group("L3-INFO-TDA4", "groupe L3-INFO-TDA4");
		ui.createTicket(g, 
				"changement de salle",
				"Bonjour\n"
				+ "Je vous signale que le cours de Graphe aura lieu "
				+ "en U3-208\n"
				+ "Merci d'en prendre compte");
		ui.getTicketManager().getTicket(g, 0).addMessage(
				new communications.Message(ui.getUser(),
				"Changement de salle annulé.\n" +
				"Le cours aura lieux demain..."));	

		ClientApplication app = new ClientApplication(ui);

		ui.createTicket(new Group("L2-INFO-TDA4", "groupe L2-INFO-TDA4"),
				"changement de salle",
				"Bonjour\n"
				+ "Je vous signale que le cours de Graphe aura lieu "
				+ "en U3-208\n"
				+ "Merci d'en prendre compte");
		
		app.majGUI();
	}
	
}
