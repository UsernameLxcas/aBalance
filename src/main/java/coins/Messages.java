package coins;

import org.bukkit.ChatColor;
import utils.Start;

public class Messages {

    public static String getUsage() {
        return ChatColor.translateAlternateColorCodes('&', "&cIncorrect Usage!\n" +
                "&e/eco give <player> <amount> &b- use this command to give coins to a player!\n" +
                "&e/eco remove <player> <amount> &b- use this command to remove coins from a player\n" +
                "&e/eco get <player> &b- use this command to get a player amount coins\n" +
                "&e/eco reset <player> &b- use this command to reset the balance of a player\n" +
                "&e/eco reload &b- reload configuration and messages\n" +
                "&e/eco storage &b- returns storage method\n");
    }

    public static String getStorage() {
        return Start.getPlugin().getDatabase().getString("Storage");
    }
}
