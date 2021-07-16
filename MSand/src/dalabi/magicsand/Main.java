package dalabi.magicsand;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import dalabi.magicsand.events.Events;

public class Main extends JavaPlugin {

    public static Main plugin;
    private Material magicSandBlock;
    private ArrayList<Block> blocks = new ArrayList<>();

    public void onEnable() {
        plugin = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ENABLED MAGICSAND PLUGIN");
        getServer().getPluginManager().registerEvents(new Events(this), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        magicSandBlock = Material.getMaterial(plugin.getConfig().getString("Magicsand.Block"));
        placeTask();
    }

    public void onDisable() {
        plugin = null;
        Bukkit.getServer().getConsoleSender().sendMessage("MagicSand has been disabled");
    }

    public void placeMS(Block block) {
        if (block.getType() == Material.AIR) {
            block.setType(Material.SAND);
            placeMS(block.getRelative(0, -1, 0));
        }
    }
    
    public void placeTask() {
        new BukkitRunnable() {
            public void run() {
                for (Block b : blocks) {
                    plugin.placeMS(b);
                }
                blocks.clear();
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    public Material getMagicSandBlock() {
        return magicSandBlock;
    }
    
    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
