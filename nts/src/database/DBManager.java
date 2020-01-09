package database;

import java.sql.*;
import java.util.*;

import communications.*;
import exchange.*;
import user.*;

public class DBManager {
    private Connection bdd = null;

    // private final static String URL = "jdbc:mysql://127.0.0.1:3306/nts";
    // private final static String ID = "root";
    // private final static String PWD = "";

    private final static String URL = "jdbc:mysql://cyandev.ovh:3306/nts";
    private final static String ID = "neocampus";
    private final static String PWD = "neocampus";

    private final static String SQL_LOGIN = 
            "SELECT * FROM users "+
            "WHERE users.login = ? "+
            "AND users.password = ? ";
    private final static String SQL_GROUP_LIST = 
            "SELECT * FROM groups ";
    private final static String SQL_GET_GROUP =
            "SELECT * FROM groups "+
            "WHERE groups.id = ? ";
    private final static String SQL_GET_GROUP_BY_NAME =
            "SELECT * FROM groups "+
            "WHERE groups.name = ? ";
    private final static String SQL_TICKET_LIST = 
            "SELECT * FROM tickets "+
            "WHERE tickets.author_id = ? "+
            "OR tickets.group_id IN ( "+
                "SELECT members.group_id FROM members "+
                "WHERE members.user_id = ?) ";
    private final static String SQL_COUNT_UNREAD_MESSAGES = 
            "SELECT COUNT(statuses.message_id) FROM statuses "+
            "WHERE statuses.user_id = ? "+
            "AND statuses.message_id IN ( "+
                "SELECT messages.id FROM messages "+
                "WHERE messages.ticket_id = ? "+
                "AND messages.status != 'READ') "+
            "AND statuses.status IN ('WAITING','RECEIVED') ";
    private final static String SQL_GET_MESSAGES =
            "SELECT * FROM messages "+
            "WHERE messages.ticket_id = ? ";
    private final static String SQL_GET_USER = 
            "SELECT users.first_name, users.last_name FROM users "+
            "WHERE users.id = ? ";
    private final static String SQL_GET_STATUSES = 
            "SELECT * FROM statuses "+
            "WHERE statuses.message_id = ? ";

    /**
     * <pre>
     * int ticketID
     * </pre>
     */
    private final static String SQL_GET_TICKET =
            "SELECT * FROM tickets "+
            "WHERE tickets.id = ? ";

    /**
     * <pre>
     * int groupID
     * </pre>
     */
    private final static String SQL_GET_USERS_FROM_GROUP =
            "SELECT members.user_id FROM members "+
            "WHERE members.group_id = ? ";

    /**
     * <pre>
     * int userID, String oldStatus, int ticketID
     * </pre>
     */
    private final static String SQL_GET_SPECIFIC_MESSAGES = 
            "SELECT statuses.message_id FROM statuses "+
            "WHERE statuses.user_id = ? "+
            "AND statuses.status = ? "+
            "AND statuses.message_id IN ( "+
                "SELECT messages.id FROM messages "+
                "WHERE messages.ticket_id = ?) ";
    
    /**
     * <pre>
     * String newStatus, int messageID, int userID
     * </pre>
     */
    private final static String SQL_UPDATE_STATUS =
            "UPDATE statuses SET statuses.status = ?"+ 
            "WHERE statuses.message_id = ? "+
            "AND statuses.user_id = ? ";

    /**
     * <pre>
     * String newStatus, int messageID, int messageID
     * </pre>
     */
    private final static String SQL_UPDATE_MSG_STATUS_W =
            "UPDATE messages SET messages.status = ? "+
            "WHERE messages.id = ? "+
            "AND (SELECT COUNT(statuses.status) FROM statuses "+
                "WHERE statuses.message_id = ? "+
                "AND statuses.status = 'WAITING' ) = 0 ";

    /**
     * <pre>
     * String newStatus, int messageID, int messageID
     * </pre>
     */
    private final static String SQL_UPDATE_MSG_STATUS_WR =
            "UPDATE messages SET messages.status = ? "+
            "WHERE messages.id = ? "+
            "AND (SELECT COUNT(statuses.status) FROM statuses "+
                "WHERE statuses.message_id = ? "+
                "AND statuses.status IN ('WAITING', 'RECEIVED')) = 0 ";

    /**
     * <pre>
     * Date date, String content, int authorID, int ticketID
     * </pre>
     */
    private final static String SQL_NEW_MESSAGE =
            "INSERT INTO messages (date, content, author_id, ticket_id)"+
            "VALUES (? , ? , ? , ? ) ";
    
    /**
     * <pre>
     * Date date, int ticketID
     * </pre>
     */
    private final static String SQL_NEW_MSG_IN_TICKET =
            "UPDATE tickets SET tickets.date = ? "+
            "WHERE tickets.id = ? ";

    /**
     * <pre>
     * int messageID, int userID
     * </pre>
     */
    private final static String SQL_NEW_MSG_IN_STATUS =
            "INSERT INTO statuses (message_id, user_id) "+
            "VALUES ( ? , ? )";
    
    /**
     * <pre>
     * String name, Date date, int authorID, int groupID
     * </pre>
     */
    private final static String SQL_NEW_TICKET =
            "INSERT INTO tickets (name, date, author_id, group_id) "+
            "VALUES ( ? , ? , ? , ? ) ";

    
    // *****************************************************************
            
    public DBManager() {
        try {
            this.bdd = DriverManager.getConnection(URL, ID, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param data
     * @return
     */
    public ComData login(ComData data) {
        ComData res = null;
        PreparedStatement stmt = null;        
        ResultSet set = null;

        try {
            stmt = bdd.prepareStatement(SQL_LOGIN);
            stmt.setString(1, data.getLogin().getLogin());
            stmt.setString(2, data.getLogin().getPassword());
            set = stmt.executeQuery();

            if(set.next()) {
                ComLogin login = new ComLogin(set.getInt("id"));
                User user = new User(
                        set.getString("first_name"),
                        set.getString("last_name")
                );

                res = new ComData(ComType.CONNECT_RP, login, user);
            } else {
                res = new ComData(ComType.ERROR_BAD_LOGIN);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 
     * @return
     */
    public ComData groupList() {
        ComData res = new ComData(ComType.GROUPS_RP);
        PreparedStatement stmt = null;        
        ResultSet set = null;

        try {
            stmt = bdd.prepareStatement(SQL_GROUP_LIST);
            set = stmt.executeQuery();

            while(set.next()) {
                res.getGroups().add( new Group(
                        set.getString("name"),
                        set.getString("info")
                ));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return res;
    }

    /**
     * 
     * @param data
     * @return
     */
    public ComData ticketsList(ComData data) {
        ComData res = new ComData(ComType.TICKET_LIST_RP);
        PreparedStatement stmtT = null;
        PreparedStatement stmtG = null;
        PreparedStatement stmtC = null;
        ResultSet setT = null;
        ResultSet setG = null;
        ResultSet setC = null;

        try {
            stmtT = bdd.prepareStatement(SQL_TICKET_LIST);
            stmtT.setInt(1, data.getLogin().getId());
            stmtT.setInt(2, data.getLogin().getId());
            setT = stmtT.executeQuery();

            while(setT.next()) {
                stmtG = bdd.prepareStatement(SQL_GET_GROUP);
                stmtG.setInt(1, setT.getInt("group_id"));
                setG = stmtG.executeQuery();
                setG.first();
                
                stmtC = bdd.prepareStatement(SQL_COUNT_UNREAD_MESSAGES);
                stmtC.setInt(1, data.getLogin().getId());
                stmtC.setInt(2, setT.getInt("id"));
                setC = stmtC.executeQuery();
                setC.first();

                res.getTickets().add( new Ticket(
                        setT.getInt("id"),
                        setT.getString("name"),
                        new java.util.Date(setT.getDate("date").getTime()), 
                        new Group(
                                setG.getString("name"),
                                setG.getString("info")
                        ),
                        setC.getInt(1)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 
     * @param data
     * @return
     */
    public ComData ticket(ComData data) {
        ComData res = new ComData(ComType.TICKET_RP);
        PreparedStatement stmtM = null;
        PreparedStatement stmtU = null;
        ResultSet setM = null;
        ResultSet setU = null;

        try {
            res.getTickets().add(data.getTickets().get(0));
            stmtU = bdd.prepareStatement(SQL_GET_USER);
            stmtM = bdd.prepareStatement(SQL_GET_MESSAGES);
            stmtM.setInt(1, data.getTickets().get(0).getId());
            setM = stmtM.executeQuery();

            while(setM.next()) {
                stmtU.setInt(1, setM.getInt("author_id"));
                setU = stmtU.executeQuery();
                setU.first();

                res.getMessages().add( new Message(
                    setM.getInt("id"), 
                    new java.util.Date(setM.getDate("date").getTime()), 
                    new User(
                            setU.getString("first_name"), 
                            setU.getString("last_name")
                    ),
                    setM.getString("content"), 
                    StatusType.valueOf(setM.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ComData newTicket(ComData data) {
        Ticket tck = data.getTickets().get(0);
        ComData res = new ComData(ComType.NEW_TICKET_RP, data.getLogin());

        PreparedStatement stmtG = null;
        PreparedStatement stmtT = null;
        ResultSet setG = null;
        ResultSet setT = null;
        int newTicketID = -1;

        try {
            stmtG = bdd.prepareStatement(SQL_GET_GROUP_BY_NAME);
            stmtG.setString(1, tck.getGroup().getName());
            
            setG = stmtG.executeQuery();
            setG.first();

            stmtT = bdd.prepareStatement(SQL_NEW_TICKET, 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stmtT.setString(1, tck.getName());
            stmtT.setDate(2, new java.sql.Date(tck.getDate().getTime()));
            stmtT.setInt(3, data.getLogin().getId());
            stmtT.setInt(4, setG.getInt("id"));
            stmtT.executeUpdate();
            
            setT = stmtT.getGeneratedKeys();
            setT.first();

            newTicketID = (int) setT.getDouble(1);

            res.getTickets().add( new Ticket(
                    newTicketID, 
                    tck.getName(), 
                    tck.getDate(), 
                    tck.getGroup(), 
                    1
            ));
            res.getMessages().add(data.getMessages().get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 
     * @param data
     * @return 
     */
    public ComData newMessage(ComData data) {
        Message msg = data.getMessages().get(0);
        Ticket tck = data.getTickets().get(0);

        ComData res = new ComData(ComType.NEW_MESSAGE_RP);
        PreparedStatement stmtM = null;
        PreparedStatement stmtUT = null;
        PreparedStatement stmtGT = null;
        PreparedStatement stmtU = null;
        PreparedStatement stmtS = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        int newMsgId = -1;
        int tckAuthorID = -1;

        try {
            res.getTickets().add(tck);
            stmtM = bdd.prepareStatement(SQL_NEW_MESSAGE, 
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            stmtM.setDate(1, new java.sql.Date(msg.getDate().getTime()));
            stmtM.setString(2, msg.getContent());
            stmtM.setInt(3, data.getLogin().getId());
            stmtM.setInt(4, tck.getId());
            stmtM.executeUpdate();

            set = stmtM.getGeneratedKeys();
            set.first();
            newMsgId = (int) set.getDouble(1);

            stmtUT = bdd.prepareStatement(SQL_NEW_MSG_IN_TICKET);
            stmtUT.setDate(1, new java.sql.Date(msg.getDate().getTime()));
            stmtUT.setInt(2, tck.getId());
            stmtUT.executeUpdate();

            stmtGT = bdd.prepareStatement(SQL_GET_TICKET);
            stmtGT.setInt(1, tck.getId());
            set = stmtGT.executeQuery();
            set.first();
            tckAuthorID = set.getInt("author_id");

            stmtU = bdd.prepareStatement(SQL_GET_USERS_FROM_GROUP);
            stmtU.setInt(1, set.getInt("group_id"));
            set = stmtU.executeQuery();

            stmtS = bdd.prepareStatement(SQL_NEW_MSG_IN_STATUS);
            stmtS.setInt(1, newMsgId);
            stmtS.setInt(2, tckAuthorID);
            stmtS.executeUpdate();

            while(set.next()) {
                if(set.getInt("user_id") != tckAuthorID) {
                    stmtS.setInt(2, set.getInt("user_id"));
                    stmtS.executeUpdate();
                }
            }

            stmt = bdd.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setString(1, StatusType.AUTHOR.name());
            stmt.setInt(2, newMsgId);
            stmt.setInt(3, data.getLogin().getId());
            
            res.getMessages().add(new Message(
                newMsgId, 
                msg.getDate(), 
                msg.getAuthor(), 
                msg.getContent(), 
                StatusType.WAITING
            ));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 
     * @param data
     * @return
     */
    public ComData statuses(ComData data) {
        ComData res = new ComData(ComType.STATUSES_RP);
        PreparedStatement stmtS = null;
        PreparedStatement stmtU = null;
        ResultSet setS = null;
        ResultSet setU = null;

        try {
            res.getMessages().add(data.getMessages().get(0));
            stmtU = bdd.prepareStatement(SQL_GET_USER);
            stmtS = bdd.prepareStatement(SQL_GET_STATUSES);
            stmtS.setInt(1, data.getMessages().get(0).getId());
            setS = stmtS.executeQuery();

            while (setS.next()) {
                stmtU.setInt(1, setS.getInt("user_id"));
                setU = stmtU.executeQuery();
                setU.first();
                res.getStatuses().add(new Status(
                        new User(
                                setU.getString("first_name"), 
                                setU.getString("last_name")
                        ),
                        StatusType.valueOf(setS.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 
     * @param t
     * @return
     */
    public List<Integer> ticketUsersID(Ticket t) {
        List<Integer> users = new ArrayList<>();
        PreparedStatement stmtT = null;
        PreparedStatement stmtU = null;
        ResultSet setT = null;
        ResultSet setU = null;
        Integer ticketAuthor = null;

        try {
            stmtT = bdd.prepareStatement(SQL_GET_TICKET);
            stmtT.setInt(1, t.getId());
            setT = stmtT.executeQuery();
            setT.first();
            ticketAuthor = new Integer(setT.getInt("author_id"));

            stmtU = bdd.prepareStatement(SQL_GET_USERS_FROM_GROUP);
            stmtU.setInt(1, setT.getInt("group_id"));
            setU = stmtU.executeQuery();

            while(setU.next()) {
                users.add(new Integer(setU.getInt("user_id")));
            }

            if(!users.contains(ticketAuthor)) {
                users.add(ticketAuthor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * 
     * @param ticket
     * @param userID
     * @param oldStatus
     * @param newStatus
     * @return
     */
    public ComData updateMsgStatus(Ticket ticket, Integer userID, 
            StatusType oldStatus, StatusType newStatus) 
    {
        ComData update = new ComData(ComType.UPDATE_STATUS_SRV);
        update.getTickets().add(ticket);

        String SQL_UPDATE_MESSAGE_STATUS = (oldStatus == StatusType.WAITING) ?
            SQL_UPDATE_MSG_STATUS_W : SQL_UPDATE_MSG_STATUS_WR;

        PreparedStatement stmtS = null;
        PreparedStatement stmtUS = null;
        PreparedStatement stmtUM = null;
        ResultSet setS = null;
        int messageID = 0;

        try {
            stmtS = bdd.prepareStatement(SQL_GET_SPECIFIC_MESSAGES);
            stmtS.setInt(1, userID.intValue());
            stmtS.setString(2, oldStatus.name());
            stmtS.setInt(3, ticket.getId());
            setS = stmtS.executeQuery();

            while(setS.next()) {
                messageID = setS.getInt("message_id");

                stmtUS = bdd.prepareStatement(SQL_UPDATE_STATUS);
                stmtUS.setString(1, newStatus.name());
                stmtUS.setInt(2, messageID);
                stmtUS.setInt(3, userID.intValue());

                if(stmtUS.executeUpdate() == 1) {
                    stmtUM = bdd.prepareStatement(SQL_UPDATE_MESSAGE_STATUS);
                    stmtUM.setString(1, newStatus.name());
                    stmtUM.setInt(2, messageID);
                    stmtUM.setInt(3, messageID);

                    if(stmtUM.executeUpdate() == 1) {
                        update.getMessages().add(new Message(
                                messageID, 
                                null, null, null,
                                newStatus
                        ));
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Serializer.serialize(update));
        return update;
    }

    public void userRecvMsg(int userID, int messageID) {
        try {
            PreparedStatement stmt = bdd.prepareStatement(SQL_UPDATE_STATUS);
            stmt.setString(1, StatusType.RECEIVED.name());
            stmt.setInt(2, messageID);
            stmt.setInt(3, userID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
