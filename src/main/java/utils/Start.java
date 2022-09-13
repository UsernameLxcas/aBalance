package utils;

import mysqlutils.MySQLConnection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import coins.Command.economy;

public final class Start extends JavaPlugin {
    private static Start plugin;
    private MySQLConnection conexion;
    public FileConfiguration mensajes;
    private File messages;

    public FileConfiguration database;
    private File db;
    @Override
    public void onEnable() {
        this.conexion = new MySQLConnection("localhost",3306,"AnyEconomy","root","1234");
        plugin = this;
        MinecraftCommands();
        Messages();
        Database();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new placeholders().register();
        }
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        File users = new File(Start.getPlugin().getDataFolder() + "/users/", "");
        if(!users.exists()) { users.mkdirs(); }

    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static Start getPlugin() {
        return plugin;
    }

    public void MinecraftCommands() {
        this.getCommand("balance").setExecutor(new economy());
        this.getCommand("bal").setExecutor(new economy());
    }

    public Connection getSQL() {
        return this.conexion.getConnection();
    }

    private void Messages() {
        messages = new File(getDataFolder(), "messages.yml");
        mensajes = new YamlConfiguration();
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);

        }
        mensajes = new YamlConfiguration();
        try {
            mensajes.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getMessages() {
        return this.mensajes;
    }

    private void Database() {
        db = new File(getDataFolder(), "database.yml");
        database = new YamlConfiguration();
        if (!db.exists()) {
            db.getParentFile().mkdirs();
            saveResource("database.yml", false);

        }
        database = new YamlConfiguration();
        try {
            database.load(db);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getDatabase() {
        return this.database;
    }

}
