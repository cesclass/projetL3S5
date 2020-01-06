package gui;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import communications.Message;

public class MessagesTableCellRenderer 
        extends Box 
        implements TableCellRenderer {
    private static final long serialVersionUID = 1L;
    

    private JPanel messagePanel;
    private JTextArea messageArea;
    private JLabel infosLabel;
    private int count = 0;
    
    public MessagesTableCellRenderer() {
        super(BoxLayout.X_AXIS);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {

        buildSWINGComponents((Message) value);
        
        
        if(count == 0) {
        	setMessagePanelLayout();
        	count++;
        }

        add(messagePanel);
        add(Box.createHorizontalGlue());
        

        table.setRowHeight(200);
        table.setTableHeader(null);
        return this;
    }

    private void buildSWINGComponents(Message message) {
        messagePanel = new JPanel();
        messageArea = new JTextArea(message.getContent());
        
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);

        String infos = "";
        infos += message.getAuthor() +", ";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        infos += format.format(message.getDate());

        infosLabel = new JLabel(infos);
        
    }

    
    private void setMessagePanelLayout() {
        GroupLayout layout = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageArea)
                .addComponent(infosLabel));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(messageArea));
        vGroup.addGroup(layout.createParallelGroup()        
                .addComponent(infosLabel));
        layout.setVerticalGroup(vGroup);
    }
}