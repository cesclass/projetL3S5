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
        Statement stmt = null;        
        ResultSet set = null;
        String query =  
                "SELECT * FROM users WHERE login = " +
                data.getLogin().getLogin() + " AND password = " +
                data.getLogin().getPassword();

        try {
             stmt = bdd.createStatement();
             set = stmt.executeQuery(query);
             if(set.next()) {
                res = new ComData(ComType.CONNECT_OK, new User(
                    set.getString("first_name"),
                    set.getString("last_name")
                ));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
