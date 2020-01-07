package communications;

import user.User;

/**
 * Status is a Message status for a specific User 
 */
public class Status {
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
	// *****************************************************************
    
    /** User linked to a StatusType */
    private User user;
    /** StatusType linked to a User */
    private StatusType status;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************

    /**
     * Constructor for standard Status
     * @param user
     * @param status
     */
    public Status(User user, StatusType status) {
        this.user = user;
        this.status = status;
    }

    // *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************

    /**
     * Accessor for the user attribute
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Accessor for the status attribute
     * @return the status
     */
    public StatusType getStatus() {
        return status;
    }
}