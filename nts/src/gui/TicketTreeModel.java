package gui;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import communications.Ticket;
import communications.TicketManager;
import user.Group;

public class TicketTreeModel implements TreeModel {
    private TicketManager tm;

    public TicketTreeModel(TicketManager tm) {
        this.tm = tm;
    }

    @Override
    public Object getRoot() {
        return "Tickets en cours";
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent == getRoot()) {
            return tm.getGroup(index);
        } else {
            return tm.getTicket((Group) parent, index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent == getRoot()) {
            return tm.getNbGroup();
        } else {
            return tm.getNbTicket((Group) parent);
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof Ticket;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return tm.getTicketNum((Group) parent, (Ticket) child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
