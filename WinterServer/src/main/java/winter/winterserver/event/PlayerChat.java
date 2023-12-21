package winter.winterserver.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public static void OnPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String newmeg = "§6[§f" + player.getName() + "§6]§7:§f " + event.getMessage();
        event.setFormat(ChatColor.translateAlternateColorCodes('&', newmeg));

    }
}
