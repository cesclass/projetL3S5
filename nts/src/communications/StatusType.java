package communications;

/**
 * StatusType define status for Message
 */
public enum StatusType {
	/**
	 * Server did not received the Message
	 * (Or User did not received it, in case of status for a specific User)
	 */
	WAITING,
	/**
	 * Server just received the Message
	 */
	RECEIVED,
	/**
	 * All Users read the Message
	 * (Or User did not read it, in case of status for a specific User)
	 */
	READ,
	/**
	 * Current User is the Message author
	 */
	AUTHOR
}
