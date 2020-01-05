package exchange;

import java.util.*;

import communications.*;
import user.*;

public class ComData {

    private class Control {
        private ComType type = null;
        private String other = null;

        /**
         * Control constructor
         * @param type
         * @param other
         */
        public Control(ComType type, String other) {
            this.type = type;
            this.other = other;
        }

        /**
         * @return the type
         */
        public ComType getType() {
            return type;
        }

        /**
         * @return the other
         */
        public String getOther() {
            return other;
        }
    }

    private class Identity {
        private String login = null;
        private String password = null;

        /**
         * Identity constructor
         * @param login
         * @param password
         */
        public Identity(String login, String password) {
            this.login = login;
            this.password = password;
        }

        /**
         * @return the login
         */
        public String getLogin() {
            return login;
        }

        /**
         * @return the password
         */
        public String getPassword() {
            return password;
        }
    }

    private class Status {
        private User user;
        private StatusType status;

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

    private Control control = null;
    private Identity identity = null;
    private List<User> users = null;
    private List<Group> groups = null;
    private List<Ticket> tickets = null;
    private List<Message> messages = null;
    private List<Status> statuses = null;

    /**
     * ComData constructor
     * @param control_type
     * @param control_other
     * @param identity_login
     * @param identity_password
     */
    public ComData(ComType control_type, String control_other, 
            String identity_login, String identity_password) 
    {
        this.control = new Control(control_type, control_other);
        this.identity = new Identity(identity_login, identity_password);
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.statuses = new ArrayList<>();
    }

    /**
     * @return the control
     */
    public Control getControl() {
        return control;
    }

    /**
     * @return the identity
     */
    public Identity getIdentity() {
        return identity;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * @return the tickets
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * @return the statuses
     */
    public List<Status> getStatuses() {
        return statuses;
    }
}
