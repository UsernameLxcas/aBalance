package coins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import utils.Start;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class coins {

    public static int getCoins(@Nullable Connection connection, String player){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT coins FROM users WHERE player=(?)");
            statement.setString(1, player);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()) {
                int cantidad = resultado.getInt("coins");
                return cantidad;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public void setCoins(@Nullable Connection connection, String player, int amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET coins=(?) WHERE player=(?)");
            statement.setInt(1, amount);
            statement.setString(2, player);
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeCoins(@Nullable Connection connection, String player, int amount) {
        try {
            int coins = getCoins(connection, player);
            int newcoins = coins - amount;
            if(amount>coins) {
                Player player1 = Bukkit.getPlayer(player);
                player1.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("No_Sufficient_Coins")));
            }else {
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET coins=(?) WHERE player=(?)");
                statement.setInt(1, newcoins);
                statement.setString(2, player);
                statement.executeUpdate();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean playerexist(Connection connection, UUID uuid) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE uuid=(?)");
            statement.setString(1, uuid.toString());
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()) {
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void giveCoinsOnJoin(Connection connection,String nombre,UUID uuid) {
        try {
            if(!playerexist(connection, uuid)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUE (?,?,'500')");
                statement.setString(1, uuid.toString());
                statement.setString(2, nombre);
                statement.executeUpdate();
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
