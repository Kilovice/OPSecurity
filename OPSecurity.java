package dev.kilovice.opsecurity.main;

import java.io.File; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.kilovice.opsecurity.listeners.OPAsyncChatEvent;
import dev.kilovice.opsecurity.listeners.OPPlayerCommandPreprocessEvent;
import dev.kilovice.opsecurity.listeners.OPPlayerQuitEvent;

public class OPSecurity extends JavaPlugin{

	private static Plugin plugin;
	public static FileConfiguration config;
	protected static Logger log = Logger.getLogger("Minecraft");
	public static List<String> pw;
	public static List<String> playersetpw;
	public static HashMap<String, String> tempcmd;
	
	@Override
	public void onEnable(){
		pw = new ArrayList<String>();
		tempcmd = new HashMap<String, String>();
		plugin = this;
		saveDefaultConfig();
		config = this.getConfig();
		registerMessages();
		registerEvents(this, new OPAsyncChatEvent(), new OPPlayerCommandPreprocessEvent(), new OPPlayerQuitEvent());
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
	
	   public static void registerEvents(Plugin plugin, Listener... listeners) { 
	        for (Listener listener : listeners) {
	            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	        }
	    }
	   public static void registerMessages()
	   {
			try{
				File f = new File(OPSecurity.getInstance().getDataFolder().getAbsolutePath(), File.separator + "messages.yml");
				if(!f.exists())
				{
					f.createNewFile();
					FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
					fc.set("messages.prefix", "&8&l[&c&lOP&f&oSecurity&8&l]");
					fc.set("messages.set-password", "&8&lPlease enter your desired password.");
					fc.set("messages.request-player", "&8&lPlease enter your player password.");
					fc.set("messages.request-global", "&8&lPlease enter the global password.");
					fc.set("messages.incorrect-password", "&c&lIncorrect Password! &7&lPlease try again!");
					fc.set("messages.correct-password", "&a&lCorrect Password! &7&lInitiating Command!");
					fc.set("messages.no-permission", "&c&oI'm sorry, but you do not have permission to do this!");
					fc.save(f);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	   }
}
