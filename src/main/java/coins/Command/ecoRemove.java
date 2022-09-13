package coins.Command;

import coins.Messages;
import coins.coins;
import coins.coinsH2;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import utils.Start;

import java.sql.Connection;

public class ecoRemove {

    Connection connection = Start.getPlugin().getSQL();

    public static void removeCoins(@Nullable Connection connection, Player player, CommandSender sender, String args[]) {
        int amount = Integer.parseInt(args[2]);
        if (Messages.getStorage().equalsIgnoreCase("None")) {
            if(coinsH2.removeCoins(player, amount)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Removed_Coins").replace("%second_player%", player.getName()).replace("%second_playercoins%", amount+"")));
            }
        } else if (Messages.getStorage().equalsIgnoreCase("MySQL")) {
            coins.removeCoins(connection, player.getName(),amount);
        }
    }

    public static void checkUser(@Nullable Connection connection, Player player, CommandSender sender, String args[]) {
        if(player == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
        }else {
            if(Messages.getStorage().equalsIgnoreCase("MySQL")) {
                removeCoins(connection, player, sender, args);
            }else if(Messages.getStorage().equalsIgnoreCase("None")) {
                removeCoins(null, player, sender, args);
            }
        }
    }
}
