package interfaces;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import user.Group;
import user.User;

/**
 * UserInterface is the interface between User and application.
 * 
 * It allow User to :
 *  - connect itself to the application,
 *  - create a Ticket,
 *  - send a Message
 * 
 * Application could also : 
 *  - get the current User to interact with him
 *  - and get the TicketManager.
 */
public class UserInterface {
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
    // *****************************************************************

    /**
     * Currently connected User
     */
    private User user;
    /**
     * TicketManager which contain all Groups and Tickets
     * and allow to interact with them;
     */
    private TicketManager ticketManager = new TicketManager();
    /**
     * ServerInterface which allow to interact with the server
     */
    // UNCOMMENT THIS // private ServerInterface serverInterface = new ServerInterface();

    // *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************
    
    /**
     * Accessor for the user attribute
     * @return the currently connected user
     */
    public User getUser() {
        return user;
    }

    /**
     * Accessor for the ticketManager attribute
     * @return the ticketManager
     */
    public TicketManager getTicketManager() {
        return ticketManager;
    }

    /**
     * Use serverInterface to connect a User
     * (Not for now)
     * @param login of User
     * @param pwd (password) of User
     */
    public void connect(String login, String pwd) {
        user = new User("Dylan", "CARON");
    }

    /**
     * Create a new Ticket and add it to the TicketManager
     * @param group of Ticket participants
     * @param name of Ticket
     * @param firstMessage of Ticket
     */
    public void createTicket(Group group, String name, String firstMessage) {
        Message message = new Message(this.user, firstMessage);
        Ticket ticket = new Ticket(name, group, message);

        ticketManager.addTicket(ticket);
    }

    /**
     * Create a message (from the connected User) and add it
     * to the currently selected Ticket in TicketManager
     * @param content of Message
     */
    public void sendMessage(String content) {
        Message message = new Message(this.user, content);
        ticketManager.getCurrent().addMessage(message);
    }
}
