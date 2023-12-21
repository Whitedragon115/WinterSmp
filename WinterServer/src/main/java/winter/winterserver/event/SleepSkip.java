package winter.winterserver.event;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepSkip implements Listener {
    @EventHandler
    public static void onPlayerSleep(PlayerBedEnterEvent event){
        Player player = event.getPlayer();
        TextComponent clickcmd = new TextComponent("§3[Skip Button]");
        clickcmd.setBold(true);
        clickcmd.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/skipnight"));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(ChatColor.AQUA + player.getName() + " §8§l entered the bed, if you also want to skip the night, please click the button below");
            onlinePlayer.spigot().sendMessage(clickcmd);
        }

    }
}
