package communications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import user.User;

public class TicketManager {
    private Map<String, List<Ticket>> tickets;

    public TicketManager() {
        this.tickets = new TreeMap<>();
    }

    public void addTicket(String group, Ticket ticket) {
        if (tickets.containsKey(group)) {
            tickets.get(group).add(ticket);
        } else {
            List<Ticket> ticketList = new ArrayList<>();
            ticketList.add(ticket);

            tickets.put(group, ticketList);
        }
    }
}
