package database;

import java.sql.*;

import communications.*;
import exchange.*;
import user.*;

public class DBManager {
    private Connection bdd = null;

    // private static String url = "jdbc:mysql://127.0.0.1:3306/nts";
    // private static String user = "root";
    // private static String password = "";

    private static String url = "jdbc:mysql://cyandev.ovh:3306/nts";
    private static String user = "neocampus";
    private static String password = "neocampus";

    private static String sqlLogin = 
            "SELECT * FROM users "  +
            "WHERE users.login = ? " +
            "AND users.password = ?";
    private static String sqlGroupList = 
            "SELECT * FROM groups";
    private static String sqlGetGroup =
            "SELECT * FROM groups "+
            "WHERE groups.id = ?";
    private static String sqlTicketList = 
            "SELECT * FROM tickets " +
            "WHERE tickets.author_id = ? " +
            "OR tickets.group_id IN (" +
                "SELECT members.group_id FROM members " +
                "WHERE members.user_id = ?)";
    private static String sqlCountUnreadMessages = 
            "SELECT COUNT(statuses.message_id) FROM statuses "+
            "WHERE statuses.user_id = ? "+
            "AND statuses.message_id IN ( "+
                "SELECT messages.id FROM messages "+
                "WHERE messages.ticket_id = ? "+
                "AND messages.status != 'READ') "+
            "AND statuses.status IN ('WAITING','RECEIVED')";
    private static String sqlGetTicket =
            "SELECT * FROM tickets "+
            "WHERE tickets.id = ?";
    private static String sqlGetUser = 
            "SELECT users.first_name, users.last_name FROM users "+
            "WHERE users.id = ?";
    private static String sqlStatuses = 
            "SELECT * FROM statuses "+
            "WHERE statuses.message_id = ?";
    

    public DBManager() {
        try {
            this.bdd = DriverManager.getConnection(url, user, password);
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
            stmt = bdd.prepareStatement(sqlLogin);
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
            stmt = bdd.prepareStatement(sqlGroupList);
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
            stmtT = bdd.prepareStatement(sqlTicketList);
            stmtT.setInt(1, data.getLogin().getId());
            stmtT.setInt(2, data.getLogin().getId());
            setT = stmtT.executeQuery();

            while(setT.next()) {
                stmtG = bdd.prepareStatement(sqlGetGroup);
                stmtG.setInt(1, setT.getInt("group_id"));
                setG = stmtG.executeQuery();
                setG.first();
                
                stmtC = bdd.prepareStatement(sqlCountUnreadMessages);
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
            stmtU = bdd.prepareStatement(sqlGetUser);
            stmtM = bdd.prepareStatement(sqlGetTicket);
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
            stmtU = bdd.prepareStatement(sqlGetUser);
            stmtS = bdd.prepareStatement(sqlStatuses);
            stmtS.setInt(1, data.getMessages().get(0).getId());
            setS = stmtS.executeQuery();

            while (setS.next()) {
                stmtU.setInt(1, setS.getInt("user_id"));
                setU = stmtU.executeQuery();
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
}
