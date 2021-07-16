package dalabi.magicsand.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import dalabi.magicsand.Main;

public class Events implements Listener {

    Main plugin;

    public Events(Main instance) {
        this.plugin = instance;
    }
    public Events() {
    	
    }

    @EventHandler
    public void PlayerInteract(BlockPlaceEvent event) {
        if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == plugin.getMagicSandBlock()) {
            plugin.placeMS(event.getBlock().getRelative(0, -1, 0));
        }
    }

    @EventHandler
    public void onEntitySpawn(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() == Material.SAND && event.getBlock().getRelative(0, 1, 0).getType() == plugin.getMagicSandBlock()) {
            plugin.getBlocks().add(event.getBlock());
        }
    }
}
