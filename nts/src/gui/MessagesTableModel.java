package gui;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import communications.MessageManager;

public class MessagesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private MessageManager mm;

    public MessagesTableModel(MessageManager mm) {
        this.mm = mm;
    }

    @Override
    public int getRowCount() {
        return mm.getMessagesCount() * 2;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowIndex%2 == 0 ?
                mm.getMessage(rowIndex/2) :
                "<html><p style=\"padding: 5px\">"
                + mm.getMessage((rowIndex-1)/2).getAuthor() + ", "
                + (new SimpleDateFormat("dd/MM/YYYY HH:mm"))
                        .format(mm.getMessage((rowIndex-1)/2).getDate())
                + "</p></html>";
    }
}
