package interfaces;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import user.Group;
import user.User;

public class UserInterface {
    private User user;

	private TicketManager ticketManager;

    public UserInterface() {
        user = new User("Dylan", "CARON");
        ticketManager = new TicketManager();
    }
    
    public UserInterface(User user) {
        this.user = user;
        ticketManager = new TicketManager();
    }
    
    public User getUser() {
		return user;
	}

    public void createTicket(Group group, String name, String firstMessage) {
        Message message = new Message(this.user, firstMessage);
        Ticket ticket = new Ticket(name, group, message);

        ticketManager.addTicket(ticket);
    }

    public void sendMessage(String content) {
        Message message = new Message(this.user, content);
        ticketManager.getCurrent().addMessage(message);
    }

    /**
     * @return the ticketManager
     */
    public TicketManager getTicketManager() {
        return ticketManager;
    }
}
