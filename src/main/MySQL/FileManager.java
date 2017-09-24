package main.MySQL;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxim on 24.09.2017.
 */
public class FileManager {

    public static File getMySQLFile() {
        return new File("plugins/MySQL","mysql.yml");
    }

    public static FileConfiguration getMySQLFileConfig() {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }

    public static void setStandardMySQL() {
        FileConfiguration cfg = getMySQLFileConfig();
        cfg.options().copyDefaults(true);
        cfg.addDefault("username","root");
        cfg.addDefault("password","password");
        cfg.addDefault("database","database");
        cfg.addDefault("host","localhost");
        cfg.addDefault("port","3306");
        try {
            cfg.save(getMySQLFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMySQL() {
        FileConfiguration cfg = getMySQLFileConfig();
        MySQL.username = cfg.getString("username");
        MySQL.password = cfg.getString("password");
        MySQL.database = cfg.getString("database");
        MySQL.host = cfg.getString("host");
        MySQL.port = cfg.getString("port");
    }
}
