package communications;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Every Ticket have a MessageManager to manage its Messages.
 * 
 * Ticket use its MessageManager to :
 *  - add a Message to itself,
 *  - get one of its Messages
 *  - and get the count of its Messages.
 * 
 * MessageManager could also give an Iterator to go through messages.
 */
public class MessageManager implements Iterable<Message> {
    // *****************************************************************
	// *
	// * ATTRIBUTE
	// *
    // *****************************************************************
    
    /** List of Message */
    private List<Message> messages;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

    /**
     * Constructor for standard MessageManager
     * @param message is the first Message of the discussion
     */
    public MessageManager(Message message) {
        messages = new ArrayList<>();
        messages.add(message);
    }

    // *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************

    /**
     * Add a Message to the discussion
     * @param message to add
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Count discussion messages
     * @return Messages count
     */
	public int getMessagesCount() {
		return messages.size();
	}

    /**
     * Give the requested Message
     * @param num is the number of the message
     * @return the Message
     */
	public Message getMessage(int num) {
        return messages.get(num);
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
