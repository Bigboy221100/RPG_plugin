package main.Chars;

import main.MySQL.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by user on 27.09.2017.
 */
public class Charvoids {
    public Charvoids(){
    }
    public static boolean isUsed(String charname){
        ResultSet rs = MySQL.getResultSet("SELECT * FROM Characters");
        try {
            while(rs.next()) {
                if(rs.getString("charname").equalsIgnoreCase(charname)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean charbelongsto(String p){
        ResultSet rs = MySQL.getResultSet("SELECT * FROM Characters");
        try{
            while(rs.next()){
                if(rs.getString("UUID").equalsIgnoreCase(p)){
                    return true;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
