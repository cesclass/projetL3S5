package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import communications.Message;

public class MessagesTableCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) 
    {
        DefaultTableCellRenderer defaultRenderer = 
                new DefaultTableCellRenderer();
        Component c = defaultRenderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (row%2 == 0) {
            if (((Message) value).getStatus() == null) {
                c.setBackground(new Color(150, 150, 150, 100));
            } else {
                switch (((Message) value).getStatus()) {
                    case WAITING:
                        c.setBackground(new Color(150, 0, 0, 100));
                        break;
    
                    case RECEIVED:
                        c.setBackground(new Color(225, 125, 0, 100));
                        break;
    
                    case READ:
                        c.setBackground(new Color(0, 125, 0, 100));
                        break;
    
                    default:
                        break;
                }
            }
        }

        return c;
    }
}