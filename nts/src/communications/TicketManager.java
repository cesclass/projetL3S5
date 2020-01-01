package communications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import user.User;

public class TicketManager {
    private Map<String, List<Ticket>> tickets;
    private Ticket current;

    public TicketManager() {
        this.tickets = new TreeMap<>();
        current = null;
    }

    public void addTicket(String group, Ticket ticket) {
        if (tickets.containsKey(group)) {
            tickets.get(group).add(ticket);
        } else {
            List<Ticket> ticketList = new ArrayList<>();
            ticketList.add(ticket);

            tickets.put(group, ticketList);
        }
        current = ticket;
    }

    public void selectTicket(Ticket ticket) {
        current = ticket;
    }

    /**
     * @return the tickets
     */
    public Map<String, List<Ticket>> getAllTickets() {
        return tickets;
    }

    /**
     * @return the current
     */
    public Ticket getCurrent() {
        return current;
    }
}
