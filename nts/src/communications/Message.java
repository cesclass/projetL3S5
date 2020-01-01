package communications;

import java.util.Calendar;
import java.util.Date;

import user.User;

public class Message {
    private int id;
    private Date date;
    private User author;
    private String content;

    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = Calendar.getInstance().getTime();
        this.id = -1;
    }

    public Message(String messageInfo) {
        // TODO Message constructeur via messageInfo
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the author
     */
    public User getAuthor() {
        return author;
    }
}
