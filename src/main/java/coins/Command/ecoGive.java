package coins.Command;

import coins.Messages;
import coins.coins;
import coins.coinsH2;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import utils.Start;

import java.io.IOException;
import java.sql.Connection;

public class ecoGive {

    Connection connection = Start.getPlugin().getSQL();


    public static void giveBalance(@Nullable Connection connection, OfflinePlayer player, CommandSender sender, String args[]) {
        try {
            int amount = Integer.valueOf(args[2]);
            if (Messages.getStorage().equalsIgnoreCase("None")) {
                int oldcoins = Integer.parseInt(coinsH2.getAmount(player));
                coinsH2.setCoins(player, oldcoins + amount);
            } else if (Messages.getStorage().equalsIgnoreCase("MySQL")) {
                int oldcoins = coins.getCoins(connection, player.getName());
                coins.setCoins(connection, player.getName(), oldcoins + amount);
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Added_Coins").replace("%second_player%", player.getName()).replace("%second_playercoins%", amount + "")));
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("NoValid_Number")));
        }
    }

    public static void checkUser(@Nullable Connection connection, OfflinePlayer player, CommandSender sender, String args[]) {
        if(player == null || player.getName() == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
        }else {
            if(Messages.getStorage().equalsIgnoreCase("MySQL")) {
                if(coins.playerexist(connection, player.getUniqueId()) && player != null) {
                    giveBalance(connection, player, sender, args);
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
                }
            }else if(Messages.getStorage().equalsIgnoreCase("None")) {
                if(coinsH2.playerExist(player)) {
                    giveBalance(null, player, sender, args);
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
                }

            }
        }
    }


}
