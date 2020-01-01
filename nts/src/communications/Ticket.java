package communications;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private String name;
    private MessageManager messageManager;

    public Ticket(String name, Message firstMessage) {
        this.id = -1;
        this.name = name;
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
