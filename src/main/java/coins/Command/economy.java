package coins.Command;

import coins.coins;
import coins.coinsH2;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import utils.Start;

public class economy implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("anyeconomy.balance")) {
            if(args.length==0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, Start.getPlugin().getMessages().getString("Coins"))));
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("Console")));
                }
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Start.getPlugin().getMessages().getString("No_Permission")));
        }

        return true;
    }
}
