package communications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import user.Group;

public class TicketManager {
    private Map<Group, List<Ticket>> tickets;
    private Ticket current;

    public TicketManager() {
        this.tickets = new TreeMap<>();
        current = null;
    }

    public void addTicket(Ticket ticket) {
        if (tickets.containsKey(ticket.getGroup())) {
            tickets.get(ticket.getGroup()).add(ticket);
        } else {
            List<Ticket> ticketList = new ArrayList<>();
            ticketList.add(ticket);

            tickets.put(ticket.getGroup(), ticketList);
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

	public Ticket getTicket(Group group, int index) {
        return tickets.get(group).get(index);
	}

	public int getNbTicket(Group group) {
        return tickets.get(group).size();
		
	}

	public int getTicketNum(Group group, Ticket ticket) {
        if (tickets.containsKey(group) && tickets.get(group).contains(ticket)) {
            return tickets.get(group).indexOf(ticket);
        } else {
            return -1;
        }
	}

	public int getNbGroup() {
        return tickets.size();
	}

	public Group getGroup(int index) {
        int i = 0;
        Group group = null;

		for (Iterator<Group> iter = tickets.keySet().iterator(); i <= index; i++) {
            group = iter.next();
        }

        return group;
	}

	public Set<Group> getAllGroups() {
        return tickets.keySet();
	}
}
