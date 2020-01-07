package test;

import database.DBManager;
import exchange.*;

public class TestDBManager {
    public static void main(String[] args) {
        DBManager bdd = new DBManager();
        ComLogin cl = new ComLogin("scc1255a", "foobar");
        ComData cd = new ComData(ComType.CONNECT_RQ, cl);
        String serial = null;

        serial = Serializer.serialize(cd);
        System.out.println("  in: " + serial);
        
        cd = bdd.login(cd);

        serial = Serializer.serialize(cd);
        System.out.println(" out: " + serial);

    }
}