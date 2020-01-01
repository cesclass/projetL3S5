package ihm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import communications.Message;

public class messagesTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private Message message;

    public messagesTableModel(Message message) {
        this.message = message;
    }

    @Override
    public int getRowCount() {
        // message + author/date
        return 2;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (rowIndex) {
            case 0: // Message
                return message.getContent();
            
            case 1: // Author + date
                String authorName = message.getAuthor().getfirstName()
                        + message.getAuthor().getLastName();
                DateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-mm-dd hh:mm:ss");
                String date = dateFormat.format(message.getDate());
                return authorName + ", " + date;
        }
    }

}