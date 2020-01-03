package ihm;

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
        return mm.getMessagesCount();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mm.getMessage(rowIndex);
    }
}
