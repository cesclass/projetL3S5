package communications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
     * @return the current
     */
    public Ticket getCurrent() {
        return current;
    }

	public Object getTicket(String group, int index) {
        return tickets.get(group).get(index);
	}

	public int getNbTicket(String group) {
        return tickets.get(group).size();
		
	}

	public int getTicketNum(String group, Ticket ticket) {
        if (tickets.containsKey(group) && tickets.get(group).contains(ticket)) {
            return tickets.get(group).indexOf(ticket);
        } else {
            return -1;
        }
	}

	public int getNbGroup() {
        return tickets.size();
	}

	public Object getGroup(int index) {
        int i = 0;
        String group = "";

		for (Iterator<String> iter = tickets.keySet().iterator(); i <= index; i++) {
            group = iter.next();
        }

        return group;
	}
}
