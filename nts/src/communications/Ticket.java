package communications;

import java.util.Date;

import user.Group;

/**
 * Ticket is a discussion with a name, a last update date,
 * a group of participants and a MessageManager to represent
 * Ticket messages
 */
public class Ticket implements Comparable<Ticket> {
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
    // *****************************************************************
    
    /** The identifier of the Ticket, same as database */
    private int id;
    /** Ticket name (discussion topic)*/
    private String name;
    /** Ticket last update*/
    private Date date;
    /** Ticket participants*/
    private Group group;
    /** Unread Messages count */
    private int unreadCount;
    /** Message manager (represent Ticket messages) */
    private MessageManager messageManager;

    // *****************************************************************
	// *
	// * CONSTRUCTORS
	// *
	// *****************************************************************

    /**
     * Constructor for standard Ticket.
     * @param name of the Ticket
     * @param group of participants
     * @param firstMessage
     */
    public Ticket(String name, Group group, Message firstMessage) {
        this.id = -1;
        this.name = name;
        this.date = firstMessage.getDate();
        this.group = group;
        this.unreadCount = 0;
        messageManager = new MessageManager(firstMessage);
    }

    /**
     * Constructor with more parameters.
     * Create a personalized Ticket with more options.
     * @param id of the Ticket
     * @param name of the Ticket
     * @param date of Ticket last update
     * @param group of participants
     * @param unreadCount of Messages
     */
    public Ticket(int id, String name, Date date, Group group, 
            int unreadCount) 
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.group = group;
        this.unreadCount = unreadCount;
        messageManager = new MessageManager();
    }

    // *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************

    /**
     * Accessor for the id attribute
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Accessor for the name attribute
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the date attribute
     * @return Message last update date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Accessor for the group attribute
     * @return the group
     * @see Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Accessor for the messageManager attribute
     * @return the messageManager
     * @see MessageManager
     */
    public MessageManager getMessageManager() {
        return messageManager;
    }

    /**
     * Add a message to the discussion with the Ticket MessageManager
     * @param message to add
     * @see MessageManager
     */
    public void addMessage(Message message) {
        messageManager.addMessage(message);
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Ticket && ((Ticket) o).getId() == this.id;
    }

    @Override
    public int compareTo(Ticket t) {
        return this.id - t.getId();
    }

    @Override
    public String toString() {
        return name +
                (unreadCount > 0 ?
                " (" + unreadCount + ")" :
                "");
    }

	public void setUnread(int unreadCount) {
        this.unreadCount = unreadCount;
	}

}
