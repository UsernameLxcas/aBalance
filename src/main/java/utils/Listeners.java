package utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import coins.coins;
import coins.coinsH2;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(Start.getPlugin().getDatabase().getString("Storage").equalsIgnoreCase("None")) {
            if (!coinsH2.playerExist(event.getPlayer())) {
                coinsH2.createplayer(event.getPlayer());
            }
        }else if(Start.getPlugin().getDatabase().getString("Storage").equalsIgnoreCase("MySQL")) {
            if(!coins.playerexist(Start.getPlugin().getSQL(), event.getPlayer().getUniqueId())) {
                coins.giveCoinsOnJoin(Start.getPlugin().getSQL(), event.getPlayer().getName(), event.getPlayer().getUniqueId());
            }
        }else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The storage method is not mysql or none"));
        }


        }
    }
