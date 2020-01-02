package interfaces;

import communications.Message;
import communications.Ticket;
import communications.TicketManager;
import user.User;

public class UserInterface {
    private User user;
    private TicketManager ticketManager;

    public UserInterface() {
        user = new User("crd1789a", "Dylan", "CARON");
        ticketManager = new TicketManager();
    }

    public void createTicket(String group, String name, String firstMessage) {
        Message message = new Message(this.user, firstMessage);
        Ticket ticket = new Ticket(name, message);

        ticketManager.addTicket(group, ticket);
    }

    public void sendMessage(String content) {
        Message message = new Message(this.user, content);
        ticketManager.getCurrent().addMessage(message);
    }

    /**
     * @return the ticketManager
     */
    public TicketManager getTicketManager() {
        return ticketManager;
    }
}
