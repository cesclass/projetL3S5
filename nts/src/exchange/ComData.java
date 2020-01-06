package exchange;

import java.util.*;

import communications.*;
import user.*;

public class ComData {

    private ComType type = null;
    private ComLogin login = null;
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
    public ComData(ComType comType, ComLogin login) {
        this.type = comType;
        this.login = login;
        this.users = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.statuses = new ArrayList<>();
    }

    /**
     * @return the type
     */
    public ComType getType() {
        return type;
    }

    /**
     * @return the login
     */
    public ComLogin getLogin() {
        return login;
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
