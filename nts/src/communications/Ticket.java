package communications;

import java.util.Date;

import user.Group;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private String name;
    private Date date;
    private Group group;
    private MessageManager messageManager;

    /**
     * 
     * @param group
     * @param name
     * @param firstMessage
     */
    public Ticket(Group group, String name, Message firstMessage) {
        this.id = -1;
        this.name = name;
        this.date = firstMessage.getDate();
        this.group = group;
        messageManager = new MessageManager(firstMessage);
    }

    /**
     * 
     * @param id
     * @param name
     * @param date
     * @param group
     */
    public Ticket(int id, String name, Date date, Group group) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.group = group;
    }

    public void addMessage(Message message) {
        messageManager.addMessage(message);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @return the messageManager
     */
    public MessageManager getMessageManager() {
        return messageManager;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Ticket && ((Ticket) o).getId() == this.id;
    }

    @Override
    public int compareTo(Ticket t) {
        return this.id - t.getId();
    }

    @Override
    public String toString() {
        return name;
    }

}
