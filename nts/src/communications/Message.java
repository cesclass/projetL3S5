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
	// * CONSTANTS
	// *
    // *****************************************************************
	/** Max character on a line */
	public final static int MAX_CHAR = 70;
	
	
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
    /** Message status */
    private StatusType status;

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
        status = null;
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
     * @param status of the Message
     */
    public Message(int id, Date date, User author, String content,
            StatusType status) 
    {
        this.id = id;
        this.date = date;
        this.author = author;
        this.content = content;
        this.status = status;
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

    /**
     * Accessor for the status attribute
     * @return Message status
     */
    public StatusType getStatus() {
        return status;
    }

    @Override
    public String toString() {
    	String chaine = "<html>";
    	int count = MAX_CHAR;
    	String tab[] = this.content.split("\n");
    	
    	for(String sousChaine : tab) {
    		for(String mot : sousChaine.split(" ")) {
	    		/*
	    		Case when the character size is bigger 
	    		than the maximum number of character on a line
	    		*/
	    		if(mot.length() > MAX_CHAR) {
	    			chaine = splitString(chaine, mot);
	    		}else { //Default behavior
					int currentSize = mot.length();
					
					if(count < currentSize) {
						chaine += "<br>";
						count = MAX_CHAR;
					}
					
					chaine += mot;
					count -= currentSize;
					
					if(count != 0) {
						chaine += " ";
						count--;
					}
	    		}
    		}
    		chaine += "<br>";
    	}
    	chaine += "</html>";
    	return chaine;
    }

	private String splitString(String chaine, String mot) {
		char temp[] = mot.toCharArray();
		int count = MAX_CHAR;
		for(char c : temp) {
			if(count == 1) {
				chaine += "-" + "<br>";
				count = MAX_CHAR;
			}
			chaine += c;
			count--;
		}
		chaine += " ";
		return chaine;
	}

    @Override
    public boolean equals(Object o) {
        return o instanceof Message && ((Message) o).getId() == this.id;
    }
}
