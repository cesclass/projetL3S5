package communications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import user.Group;

/**
 * UserInterface have a TicketManager to manage its Tickets 
 * and sort them by participant group
 * 
 * UserInterface use its TicketManager to :
 *  - add a Ticket,
 *  - select a Ticket,
 *  - get the currently selected Ticket or an existing Ticket
 * (with its group and index) or Group
 *  - get informations about Groups and Tickets (number and count)
 */
public class TicketManager {
    // *****************************************************************
	// *
	// * ATTRIBUTES
	// *
    // *****************************************************************
    
    /**
     * Ticket map, sorted by Group
     */
    private Map<Group, List<Ticket>> tickets;
    /**
     * Currently selected Ticket
     */
    private Ticket current;

    // *****************************************************************
	// *
	// * CONSTRUCTOR
	// *
	// *****************************************************************
    
    /**
     * Constructor for standard TicketManager
     */
    public TicketManager() {
        this.tickets = new TreeMap<>();
        current = null;
    }

    // *****************************************************************
	// *
	// * METHODS
	// *
	// *****************************************************************

    /**
     * Accessor for the current attribute
     * @return the currently selected Ticket
     */
    public Ticket getCurrent() {
        return current;
    }

    /**
     * Add a Ticket to the TicketManager
     * @param ticket to add
     * @see Ticket
     */
    public void addTicket(Ticket ticket) {
        // If ticket group already exist
        if (tickets.containsKey(ticket.getGroup())) {
            tickets.get(ticket.getGroup()).add(ticket);
        } 
        // If not
        else {
            List<Ticket> ticketList = new ArrayList<>();
            ticketList.add(ticket);

            tickets.put(ticket.getGroup(), ticketList);
        }
    }

    /**
     * Change currently selected Ticket to the parameter Ticket
     * @param ticket now currently selected
     * @see Ticket
     */
    public void selectTicket(Ticket ticket) {
        current = ticket;
    }

    /**
     * Return Ticket corresponding to the group and index parameters
     * @param group of the Ticket
     * @param index of the Ticket
     * @return ticket corresponding to parameters
     * @see Group
     * @see Ticket
     */
	public Ticket getTicket(Group group, int index) {
        return tickets.get(group).get(index);
	}

    /**
     * Count Tickets in a group
     * @param group of tickets
     * @return Ticket count
     */
	public int getNbTicket(Group group) {
        return tickets.get(group).size();
		
	}

    /**
     * Return the number of Ticket in a Group
     * @param group of ticket
     * @param ticket
     * @return number of the Ticket
     */
	public int getTicketNum(Group group, Ticket ticket) {
        if (tickets.containsKey(group) && tickets.get(group).contains(ticket)) {
            return tickets.get(group).indexOf(ticket);
        } else {
            return -1;
        }
	}

    /**
     * Count groups in the Map
     * @return Group count
     */
	public int getNbGroup() {
        return tickets.size();
	}

    /**
     * Return the Group number "index" in the Map
     * @param index
     * @return Group
     */
	public Group getGroup(int index) {
        int i = 0;
        Group group = null;

		for (Iterator<Group> iter = tickets.keySet().iterator(); i <= index; i++) {
            group = iter.next();
        }

        return group;
	}

    /**
     * Return all groups of the Map
     * @return Groups Set
     */
	public Set<Group> getAllGroups() {
        return tickets.keySet();
	}
}
