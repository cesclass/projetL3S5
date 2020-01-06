package communications;

import user.User;

public class Status {
    private User user;
    private StatusType status;

    /**
     * 
     * @param user
     * @param status
     */
    public Status(User user, StatusType status) {
        this.user = user;
        this.status = status;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the status
     */
    public StatusType getStatus() {
        return status;
    }
}