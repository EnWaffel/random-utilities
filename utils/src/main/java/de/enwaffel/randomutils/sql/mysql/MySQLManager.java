package de.enwaffel.randomutils.sql.mysql;

import java.util.HashMap;

public class MySQLManager {

    private static final HashMap<String, MySQL> databases = new HashMap<>();

    public static MySQL connect(String address, String db, String username, String password) {
        try {
            return new MySQL(address, db, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
