package communications;

import user.Group;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private String name;
    private Group group;
    private MessageManager messageManager;

    public Ticket(Group group, String name, Message firstMessage) {
        this.id = -1;
        this.name = name;
        this.group = group;
        messageManager = new MessageManager(firstMessage);
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
