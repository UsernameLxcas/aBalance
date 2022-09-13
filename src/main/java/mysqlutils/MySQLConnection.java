package mysqlutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private Connection connection;
    private String host;
    private int puerto;
    private String password;
    private String usuario;
    private String database;

    public  MySQLConnection(String host, int puerto, String database, String usuario, String password) {
        this.host = host;
        this.puerto = puerto;
        this.database = database;
        this.usuario = usuario;
        this.password = password;

        try {
            synchronized(this) {
                if(connection != null && !connection.isClosed()) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Ocurrio un error al conectar con la base de datos"));
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.database,this.usuario,this.password);
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eConectando con la base de datos..."));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConectado con exito!"));

            }
        }catch(SQLException e){

        }catch(ClassNotFoundException e) {

        }
    }

    public Connection getConnection() {
        return connection;
    }


}
