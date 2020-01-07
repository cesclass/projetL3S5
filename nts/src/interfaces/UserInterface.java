package interfaces;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import user.Group;
import user.User;

/**
 * UserInterface is the interface between users and client applications.
 * It contain all methods users could need to interact with the system.
 * 
 * users could :
 *  - connect themselves,
 *  - create Ticket,
 *  - send Message
 *  - and get all Tickets.
 */
public class UserInterface {
    // *****************************************************************
	// *
	// * ATTRIBUTE
	// *
    // *****************************************************************

    /**
     * Identity of User who is connected to the application.
     */
    private User user;
    /**
     * Contain all Tickets and methods for interacting with.
     */
	private TicketManager ticketManager;
    
    public UserInterface() {
        ticketManager = new TicketManager();
    }

    public void connect(String login, String pwd) {
        user = new User(login, pwd);
    }
    
    public User getUser() 
            throws UserNotConnectedException 
    {
        if (user == null) {
            throw new UserNotConnectedException(
                    "No currently connected user");
        }

		return user;
	}

    public void createTicket(Group group, String name, String firstMessage) 
            throws UserNotConnectedException 
    {
        if (user == null) {
            throw new UserNotConnectedException(
                    "No currently connected user");
        }

        Message message = new Message(this.user, firstMessage);
        Ticket ticket = new Ticket(name, group, message);

        ticketManager.addTicket(ticket);
    }

    public void sendMessage(String content) 
            throws UserNotConnectedException 
    {
        if (user == null) {
            throw new UserNotConnectedException(
                    "No currently connected user");
        }
        
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
