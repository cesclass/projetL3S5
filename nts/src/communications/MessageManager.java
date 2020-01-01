package communications;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MessageManager implements Iterable<Message> {
    private List<Message> messages;

    public MessageManager(Message message) {
        messages = new ArrayList<>();
        messages.add(message);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
