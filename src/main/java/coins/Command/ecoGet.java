package coins.Command;

import coins.coins;
import coins.coinsH2;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import utils.Start;
import coins.Messages;
import java.sql.Connection;

public class ecoGet {

    Connection connection = Start.getPlugin().getSQL();

    public static void getPlayer(@Nullable Connection connection, Player player, CommandSender sender) {
        if (Messages.getStorage().equalsIgnoreCase("None")) {
            int coinsz = Integer.parseInt(coinsH2.getAmount(player));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Coins_Other").replace("%second_player%", player.getName()).replace("%second_playercoins%", coinsz+"")));
        } else if (Messages.getStorage().equalsIgnoreCase("MySQL")) {
            int coinsz = coins.getCoins(connection, player.getName());
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Coins_Other").replace("%second_player%", player.getName()).replace("%second_playercoins%", coinsz+"")));
        }
    }

    public static void checkUser(@Nullable Connection connection, Player player, CommandSender sender, String args[]) {
        if(player == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
        }else {
            if(Messages.getStorage().equalsIgnoreCase("MySQL")) {
                getPlayer(connection, player, sender);
            }else if(Messages.getStorage().equalsIgnoreCase("None")) {
                getPlayer(null, player, sender);
            }
        }
    }

}
