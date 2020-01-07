package communications;

import java.util.Calendar;
import java.util.Date;

import user.User;

/**
 * Message is a text content, written by an author on a date
 */
public class Message {
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
	// *****************************************************************

    /** Identifier of the Message, same as database */
    private int id;
    /** Message creation date */
    private Date date;
    /** Message content */
    private String content;
    /** Message author */
    private User author;

    // *****************************************************************
	// *
	// * CONSTRUCTORS
	// *
	// *****************************************************************

    /**
     * Constructor for standard Message
     * @param author of the Message
     * @param content of the Message
     */
    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = Calendar.getInstance().getTime();
        this.id = -1;
    }

    /**
     * Constructor with all parameters.
     * Create a personalized Message with more options.
     * 
     * ``It's like a "super fat turbo big mac deluxe uber extended" 
     * with an "extremely overkilled galactic cone of fries" (TM).
     * Complicated to order (due to the name) but Tasty !``
     * @param id of the Message
     * @param date of Message creation
     * @param author of the Message
     * @param content of the Message
     */
    public Message(int id, Date date, User author, String content) {
        this.id = id;
        this.date = date;
        this.author = author;
        this.content = content;
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
     * Accessor for the date attribute
     * @return Message creation date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Accessor for the content attribute
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Accessor for the author attribute
     * @return the author
     * @see User
     */
    public User getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Message && ((Message) o).getId() == this.id;
    }
}
