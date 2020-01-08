package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.table.AbstractTableModel;

import communications.Status;

public class StatusTableModel extends AbstractTableModel{
    private static final long serialVersionUID = 1L;

    List<Status> statuses;

    public StatusTableModel(ArrayList<Status> statuses) {
        // Sort by Status natural order
        this.statuses = new ArrayList<>(new TreeSet<>(statuses));
    }

    @Override
    public int getRowCount() {
        return statuses.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Status status = statuses.get(rowIndex);
        
        return columnIndex == 0 ?
                status.getUser() :
                status.getStatus();
    }

}