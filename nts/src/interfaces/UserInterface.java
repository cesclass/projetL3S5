package interfaces;

import java.util.Set;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import exchange.ComData;
import exchange.ComLogin;
import gui.Login;
import user.Group;
import user.User;

/**
 * UserInterface is the interface between User and application.
 * 
 * It allow User to : - connect itself to the application, - create a Ticket, -
 * send a Message
 * 
 * Application could also : - get the current User to interact with him - and
 * get the TicketManager.
 */
public class UserInterface {
    // *****************************************************************
    // *
    // * ATTRIBUTES
    // *
    // *****************************************************************

    /** Currently connected User */
    private User user;
    /** Currently connected user information (for database) */
    private ComLogin comLogin;
    /**
     * TicketManager which contain all Groups and Tickets 
     * and allow to interact with them;
     */
    private TicketManager ticketManager;
    /** ServerInterface which allow to interact with the server */
    private ServerInterface serverInterface;

    private Thread thread;

    // *****************************************************************
    // *
    // * CONSTRUCTOR
    // *
    // *****************************************************************

    public UserInterface(Login app) {
        ticketManager = new TicketManager();
        serverInterface = new ServerInterface(this, app);
        thread = new Thread(serverInterface);
        thread.start();
    }

    // *****************************************************************
    // *
    // * METHODS
    // *
    // *****************************************************************

    /**
     * Accessor for the user attribute
     * 
     * @return the currently connected user
     */
    public User getUser() {
        return user;
    }

    /**
     * Accessor for the ticketManager attribute
     * 
     * @return the ticketManager
     */
    public TicketManager getTicketManager() {
        return ticketManager;
    }

    /**
     * Use serverInterface to emit a connection request
     * @param login of User
     * @param pwd (password) of User
     */
    public void emmitConnect(String login, String pwd) {
        serverInterface.tryConnect(new ComLogin(login, pwd));
    }

    /**
     * Connect User with data from connection request response
     * @param conACK response from server
     */
    public void recvConnect(ComData conACK) {
        user = conACK.getUser();
        comLogin = conACK.getLogin();
    }

    /** Use serverInterface to request all Tickets */
    public void pullTickets() {
        serverInterface.pullTickets(comLogin);
	}

    /** Use serverInterface to disconnect a User */
    public void disconnect() {
        serverInterface.disconnect();
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
     * Add ticket to ticketManager
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
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

    /**
     * Select the Ticket in TicketManager
     * @param ticket to select
     */
	public void selectTicket(Ticket ticket) {
        ticketManager.selectTicket(ticket);
	}

    /**
     * Return all Groups in the TicketManager
     * @return Group Set
     */
	public Set<Group> getAllGroups() {
		return ticketManager.getAllGroups();
	}
}
