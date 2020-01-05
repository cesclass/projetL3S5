package communications;

import java.util.Calendar;
import java.util.Date;

import user.User;

public class Message {
    private int id;
    private Date date;
    private String content;
    private User author;


    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = Calendar.getInstance().getTime();
        this.id = -1;
    }

    /**
     * Message constructor with all parameters, all options...
     * It's like a "super fat turbo big mac deluxe uber extended" 
     * with an "overkilled galactic cone of fries" (TM).
     * Complicated to order (due to the name) but Tasty !
     * @param id
     * @param date
     * @param author
     * @param content
     */
    public Message(int id, Date date, User author, String content) {
        this.id = id;
        this.date = date;
        this.author = author;
        this.content = content;
    }

    public Message(String messageInfo) {
        // TODO Message constructeur via messageInfo
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        return o instanceof Message && ((Message) o).getId() == this.id;
    }
}
