package interfaces;

import java.util.List;

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
    
    /** 
     * Use serverInterface to request all Messages 
     * for a specific Ticket 
     * @param ticket
     */
    public void pullMessages(Ticket ticket) {
        serverInterface.pullMessages(comLogin, ticket);
    }
    
    /**
     * Use serverInterface to request all Statuses
     * for a specific Message
     * @param message
     */
    public void pullStatus(Message message) {
        serverInterface.pullStatus(comLogin, message);
    }
    
    /**
     * Update statuses for each Message in a specific Ticket
     * with datas from server
     * @param ticket
     * @param messages List with statuses
     */
    public void recvStatuses(Ticket ticket, List<Message> messages) {
        if (messages != null) {
            Ticket current = ticketManager.getCurrent();
            if (ticket.equals(current)) {
                int i;
    
                for (Message m : current.getMessageManager()) {
                    if (messages.contains(m)) {
                        i = messages.indexOf(m);
                        m.setStatus(messages.get(i).getStatus());
                    }
                }
            }
        }
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

        serverInterface.pushTicket(
                comLogin, ticket, message);
    }

    /**
     * Add ticket to ticketManager
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        ticketManager.addTicket(ticket);
    }    

    /**
     * Create a message (from the connected User,
     *  for the currently selected Ticket)
     *  and send it to the server
     * @param content of Message
     */
    public void sendMessage(String content) {
        Message message = new Message(this.user, content);
        serverInterface.pushMessage(
                comLogin, ticketManager.getCurrent(), message);
    }

    /**
     * Add Message in its Ticket, all two received from server
     * @param ticket
     * @param messages to add
     */
    public void recvMessages(Ticket ticket, Message message) {
        int numTicket = ticketManager.getTicketNum(ticket.getGroup(), ticket);
        ticketManager.getTicket(ticket.getGroup(), numTicket)
                .addMessage(message);
    }
    
    /**
     * Add new Ticket and its Message, all two received from server
     * @param ticket
     * @param messages to add
     */
	public void recvTicket(Ticket ticket, Message message) {
        ticket.addMessage(message);
        ticketManager.addTicket(ticket);
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
	public void pullGroups() {
		serverInterface.pullGroups(comLogin);
	}
}
