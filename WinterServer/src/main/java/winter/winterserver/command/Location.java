package winter.winterserver.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Location implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        World.Environment environment = player.getWorld().getEnvironment();

        if (cmd.getName().equalsIgnoreCase("MyLocation")) {
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            TextComponent clickcmd = new TextComponent("§3[Send to chat]");
            clickcmd.setBold(true);
            clickcmd.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/locationtochat"));

            player.sendMessage("§7Your coordinates are\n" + "§8[ §fX: §6" + x + " §fY: §6" + y + " §fZ: §6" + z +" §8]");
            player.spigot().sendMessage(clickcmd);

        }

        if (cmd.getName().equalsIgnoreCase("LocationToChat")) {

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            String worldtype = "";

            if(environment == World.Environment.NORMAL){
                worldtype = "§aOverWorld§7";
            } else if (environment == World.Environment.NETHER) {
                worldtype = "§cNeither§7";
            }else {
                worldtype = "§eEnd§7";
            }

            Bukkit.getServer().broadcastMessage("§7[ §e" + player.getName() + " §7] in "+ worldtype +" coordinates are \n" + "§8[ §fX: §6" + x + " §fY: §6" + y + " §fZ: §6" + z +" §8]");
        }

        if(cmd.getName().equalsIgnoreCase("NeitherLocation")){
            if(environment != World.Environment.NORMAL) {
            player.sendMessage("§You need to stay in §aOverWorld §7to use this command");
            return true;
            }

            double x = (double) player.getLocation().getBlockX() / 8;
            double y = (double) player.getLocation().getBlockY();
            double z = (double) player.getLocation().getBlockZ() / 8;

            player.sendMessage("§7Your §cNeither §7coordinates are\n" + "§8[ §fX: §6" + x + " §fY: §6" + y + " §fZ: §6" + z +" §8]");
        }

        if(cmd.getName().equalsIgnoreCase("OverWorldLocation")){
            if(environment != World.Environment.NETHER) {
                player.sendMessage("§7You need to stay in §cNeither §7to use this command");
                return true;
            }

            int x = player.getLocation().getBlockX() * 8;
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ() * 8;

            player.sendMessage("§7Your §aOverWorld §7coordinates are\n" + "§8[ §fX: §6" + x + " §fY: §6" + y + " §fZ: §6" + z +" §8]");
        }

        if(cmd.getName().equalsIgnoreCase("LastDeadLocation")){
            int x = player.getLastDeathLocation().getBlockX();
            int y = player.getLastDeathLocation().getBlockY();
            int z = player.getLastDeathLocation().getBlockZ();


            player.sendMessage("§7Your §cLast Dead §7coordinates are\n" + "§8[ §fX: §6" + x + " §fY: §6" + y + " §fZ: §6" + z +" §8]");


        }



        return true;
    }
}
