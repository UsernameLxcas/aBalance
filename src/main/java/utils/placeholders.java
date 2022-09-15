package utils;

import coins.coinsH2;
import coins.coins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class placeholders extends PlaceholderExpansion {

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }


    @Override
    public String getAuthor(){
        return "Anytown";
    }

    @Override
    public String getIdentifier(){
        return "anyeconomy";
    }

    @Override
    public String getVersion(){
        return Start.getPlugin().getDescription().getVersion();
    }


    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return 0+"";
        }

        if(identifier.equals("balance")){
            if(Start.getPlugin().getDatabase().getString("Storage").equalsIgnoreCase("None")) {
                return coinsH2.getAmount(player)+"";
            }else if(Start.getPlugin().getDatabase().getString("Storage").equalsIgnoreCase("MySQL")) {
                if(Start.getPlugin().getSQL() == null) {
                    return ChatColor.translateAlternateColorCodes('&', "&cNo databases found");
                }else {
                    return coins.getCoins(Start.getPlugin().getSQL(), player.getName())+"";
                }
            }else {
                Bukkit.shutdown();
            }
        }

        return "Error";
    }
}