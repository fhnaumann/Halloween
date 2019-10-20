package me.wand555.Halloween;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CarveOnMoveEvent implements Listener {
	public CarveOnMoveEvent(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if(!event.getPlayer().hasPermission("Halloween.bypass")) {
			int radius = Halloween.getInstance().getConfig().getInt("Radius");
			if(radius < 0) return;
				
			for(int x=-radius; x<radius; x++) {
				for(int y=-radius; y<radius; y++) {
					for(int z=-radius; z<radius; z++) {
						Location loc = new Location(event.getTo().getWorld(), event.getTo().getBlockX() + x, event.getTo().getBlockY() + y, event.getTo().getBlockZ() + z);
						Block block = event.getTo().getWorld().getBlockAt(loc);
						if(block.getType() == Material.PUMPKIN) {
							block.setType(Material.CARVED_PUMPKIN);
							event.getPlayer().playSound(loc, Sound.BLOCK_PUMPKIN_CARVE, 1, 1);	
						}
					}
				}
			}
		}
		
		
	}
	
	@EventHandler
	public void onPumpkinGrowEvent(BlockGrowEvent event) {
		if(Halloween.getInstance().getConfig().getBoolean("onNaturalGrowth")) {
			if(event.getNewState().getType() == Material.PUMPKIN) {
				event.getNewState().setType(Material.PUMPKIN);
				event.getNewState().getWorld().playSound(event.getNewState().getLocation(), Sound.BLOCK_PUMPKIN_CARVE, 1, 1);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerPlaceEvent(BlockPlaceEvent event) {
		if(Halloween.getInstance().getConfig().getBoolean("onPumpkinPlaced")) {
			if(event.getBlock().getType() == Material.PUMPKIN) {
				event.getBlock().setType(Material.PUMPKIN);
				event.getPlayer().playSound(event.getBlock().getLocation(), Sound.BLOCK_PUMPKIN_CARVE, 1, 1);
			}
		}
		
	}

}
