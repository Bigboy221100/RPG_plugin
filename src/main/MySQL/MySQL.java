package main.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by maxim on 24.09.2017.
 */
public class MySQL {
    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection con;

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("Mit Datenbank connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Dungeons (DungeonName VARCHAR(100), DungeonID VARCHAR(100), Spawn VARCHAR(100))");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS DungeonMobs (DungeonName VARCHAR(100), Location VARCHAR(100), MobType VARCHAR(100), Wave VARCHAR(100))");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Characters (UUID VARCHAR(100), charname VARCHAR(100), charclass VARCHAR(100), charmoney VARCHAR(100), charlevel VARCHAR(100), charxp VARCHAR (100), charinv VARCHAR(600), currentplaying BOOL)");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BanManager (UUID VARCHAR(100), Period VARCHAR(100), Grund VARCHAR(100))");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS NewsManager (id VARCHAR(100), newsTime VARCHAR(100), news VARCHAR(100))");
                System.out.println("Datenbank erstellt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResultSet(String qry) {
        if (isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
