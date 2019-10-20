package me.wand555.Halloween;

import org.bukkit.plugin.java.JavaPlugin;

public class Halloween extends JavaPlugin {

	private static Halloween plugin;
	
	public void onEnable() {
		plugin = this;
		loadConfig();
		new CarveOnMoveEvent(plugin);
	}
	
	public void onDisable() {
		
	}
	
	public static Halloween getInstance() {
		return plugin;
	}
	
	private void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.getConfig().addDefault("onNaturalGrowth", true);
		this.getConfig().addDefault("onPumpkinPlaced", true);
		this.getConfig().addDefault("Radius", 5);
		this.saveConfig();
	}
}
