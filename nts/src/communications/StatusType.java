package communications;

/**
 * StatusType define status for Message
 */
public enum StatusType {
	/**
	 * Server did not received the Message
	 * (Or User did not received it, 
	 * 	in case of status for a specific User)
	 */
	WAITING,
	/**
	 * Server just received the Message
	 * (Or User just received it,
	 * 	in case of status for a specific User)
	 */
	RECEIVED,
	/**
	 * All Users read the Message
	 * (Or User read it, in case of status for a specific User)
	 */
	READ,
	/**
	 * Current User is the Message author
	 */
	AUTHOR
}
