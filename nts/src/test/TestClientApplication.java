package test;

import gui.ClientApplication;
import interfaces.UserInterface;
import interfaces.UserNotConnectedException;
import user.Group;
import user.User;

public class TestClientApplication {
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		Group g = new Group("L3-INFO-TDA4", "groupe L3-INFO-TDA4");
		ui.connect("crd1789a", "01001011");
		

		try {
			User u = ui.getUser();
			ui.createTicket(g, 
				"changement de salle",
				"Bonjour\n"
				+ "Je vous signale que le cours de Graphe aura lieu "
				+ "en U3-208\n"
				+ "Merci d'en prendre compte");

			ui.getTicketManager().getTicket(g, 0).addMessage(
					new communications.Message(u,
					"Changement de salle annulé.\n" +
					"Le cours aura lieux demain..."));
		} catch (UserNotConnectedException e) {
			e.printStackTrace();
		}	

		ClientApplication app = new ClientApplication(ui);

		try {
			ui.createTicket(new Group("L2-INFO-TDA4", "groupe L2-INFO-TDA4"), 
					"changement de salle",
					"Bonjour\n" 
					+ "Je vous signale que le cours de Graphe aura lieu " 
					+ "en U3-208\n"
					+ "Merci d'en prendre compte");
		} catch (UserNotConnectedException e) {
			e.printStackTrace();
		}
		
		app.majGUI();
	}
	
}
