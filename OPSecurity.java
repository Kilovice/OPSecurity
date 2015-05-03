package dev.kilovice.opsecurity.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OPSecurity extends JavaPlugin{

	private static Plugin plugin;
	public static FileConfiguration config;
	protected static Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		plugin = this;
		saveDefaultConfig();
		config = this.getConfig();
		if(OPConfig.enabled)
		{
			OPScheduler.start();
		}
		else{
			log.severe("YOU MUST CONFIGURE THEN ENABLE THIS PLUGIN IN IT'S CONFIG FILE!");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public static Plugin getInstance(){
		return plugin;
	}
}
