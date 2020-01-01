package ihm;

import java.util.List;
import java.util.Map;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import communications.Ticket;

public class TicketTreeModel implements TreeModel {
    private Map<String, List<Ticket>> tickets;

    public TicketTreeModel(Map<String, List<Ticket>> tickets) {
        this.tickets = tickets;
    }

    @Override
    public Object getRoot() {
        return "Tickets en cours";
    }

    @Override
    public Object getChild(Object parent, int index) {
        return tickets.get((String) parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
       return tickets.get((String) parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Ticket;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return tickets.get((String) parent).indexOf((Ticket) child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        // TODO Auto-generated method stub

    }
    
}
