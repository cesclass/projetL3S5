package communications;

import user.User;

/**
 * Status is a Message status for a specific User 
 */
public class Status implements Comparable<Status> {
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

    @Override
    public int compareTo(Status s) {
        if (status != s.getStatus()) {
            if (status == StatusType.WAITING
                    || s.getStatus() == StatusType.AUTHOR)
            {
                return 1;
            } else if (s.getStatus() == StatusType.WAITING
                    || status == StatusType.AUTHOR) 
            {
                return -1;
            } else if (status == StatusType.RECEIVED 
                    && s.getStatus() != StatusType.WAITING)
            {
                return 1;
            } else {
                return -1;
            }
        } else {
            return user.compareTo(s.getUser());
        }
    }
}