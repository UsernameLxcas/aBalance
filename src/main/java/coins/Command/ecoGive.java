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

public class ecoGive {

    Connection connection = Start.getPlugin().getSQL();


    public static void giveBalance(@Nullable Connection connection, Player player, CommandSender sender, String args[]) {
        int amount = Integer.parseInt(args[2]);
        if(Messages.getStorage().equalsIgnoreCase("None")) {
            int oldcoins = Integer.parseInt(coinsH2.getAmount(player));
            coinsH2.setCoins(player, oldcoins+amount);
        }else if(Messages.getStorage().equalsIgnoreCase("MySQL")) {
            int oldcoins = coins.getCoins(connection, player.getName());
            coins.setCoins(connection, player.getName(), oldcoins+amount);
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Added_Coins").replace("%second_player%", player.getName()).replace("%second_playercoins%", amount+"")));

    }

    public static void checkUser(@Nullable Connection connection, Player player, CommandSender sender, String args[]) {
        if(player == null) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Not_Player")));
        }else {
            if(Messages.getStorage().equalsIgnoreCase("MySQL")) {
                giveBalance(connection, player, sender, args);
            }else if(Messages.getStorage().equalsIgnoreCase("None")) {
                giveBalance(null, player, sender, args);
            }
        }
    }


}
