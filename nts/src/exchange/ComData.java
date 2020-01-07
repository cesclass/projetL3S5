package exchange;

import java.util.*;

import communications.*;
import user.*;

public class ComData {

    private ComType type = null;

    private ComLogin login = null;
    private User user = null;

    private List<Group> groups = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();

    /**
     * 
     * @param type
     * @param login
     */
    public ComData(ComType type, ComLogin login) {
        this.type = type;
        this.login = login;
    }

    /**
     * 
     * @param type
     * @param user
     */
    public ComData(ComType type, User user) {
        this.type = type;
        this.user = user;
    }

    /**
     * 
     * @param type
     */
    public ComData(ComType type) {
        this.type = type;
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
     * @return the user
     */
    public User getUser() {
        return user;
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

    @Override
    public String toString() {
        String ts =
            "Type: " + this.type + "\n" +
            "Login: " + this.login + "\n" +
            "groups: " + this.groups.size() + " elements\n" +
            "tickets: " + this.tickets.size() + " elements\n" +
            "messages: " + this.messages.size() + " elements\n" +
            "statuses: " + this.statuses.size() + " elements" ;
        return ts;
    }
}
