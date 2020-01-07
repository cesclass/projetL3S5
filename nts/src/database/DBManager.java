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

    public DBManager() {
        try {
            this.bdd = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ComData login(ComData data) {
        ComData res = null;
        PreparedStatement stmt = null;        
        ResultSet set = null;
        String query = "SELECT * FROM users WHERE login = ? AND password = ?";

        try {
            stmt = bdd.prepareStatement(query);
            stmt.setString(1, data.getLogin().getLogin());
            stmt.setString(2, data.getLogin().getPassword());
            set = stmt.executeQuery();

            if(set.next()) {
                ComLogin login = new ComLogin(set.getInt("id"));
                User user = new User(
                        set.getString("first_name"),
                        set.getString("last_name")
                );

                res = new ComData(ComType.CONNECT_OK, login, user);
            } else {
                res = new ComData(ComType.CONNECT_KO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
