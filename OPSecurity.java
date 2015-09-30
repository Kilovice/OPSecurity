package dev.kilovice.opsecurity.main;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.kilovice.mcstats.Metrics;
import dev.kilovice.opsecurity.commands.OPSecurityCommand;
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
	
	public OPSecurity()
	{
		super();
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		saveDefaultConfig();
		config = this.getConfig();
		checkUpdate(Bukkit.getPlayer((String)null));
		pw = new ArrayList<String>();
		tempcmd = new HashMap<String, String>();
		registerMessages();
		OPDebug.log(this.getClass(), "Registering Events . . .");
		
		new OPAsyncChatEvent(this);
		new OPPlayerCommandPreprocessEvent(this);
		new OPPlayerQuitEvent(this);
		
		new OPSecurityCommand(this);
		
		OPDebug.log(this.getClass(), "Checking if Plugin is Enabled . . .");
		if(OPConfig.PLUGIN)
		{
			OPDebug.log(this.getClass(), "OPSecurity is Enabled!");
			OPDebug.log(this.getClass(), "Starting Scheduler.");
			OPScheduler.start();
			
			if(!OPConfig.checkNull("config.metrics"))
			{
				OPDebug.log(this.getClass(), "Checking if Metrics is Enabled . . .");
				if(!OPConfig.METRICS)
				{
					OPDebug.log(this.getClass(), "Metrics is Enabled!");
				metricsStart();
				}
				else
				{
				OPDebug.log(this.getClass(), "Metrics is Disabled!");	
				}
			}
		}
		else{
			OPDebug.log(this.getClass(), "OPSecurity is Disabled!");
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
	            OPDebug.log("OPSecurity", "Registering Event: '" + listener.getClass().getSimpleName() + "'");
	        }
	    }
	   public static synchronized void registerMessages()
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
					fc.set("messages.kick", "&c&oExcesive Permissions!");
					fc.set("messages.permission-message", "&a&o%player% has been kicked!");
					fc.set("messages.broadcast-message", "&c&o%player% has been kicked!");
					fc.save(f);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	   }
	   public static synchronized void metricsStart()
	   {
		   try {
		        Metrics metrics = new Metrics(OPSecurity.getInstance());
		        metrics.start();
		        metrics.enable();
		    } catch (IOException e) {
		    }
	   }
	   public static synchronized void checkUpdate(Player p)
	   {
		   if(!OPConfig.checkNull("config.update-check"))
		   {
			if(OPConfig.UPDATE)
			{
			OPUpdater o = new OPUpdater(OPSecurity.getInstance());
			o.checkForUpdate();
			log.info("Current Version Found: '" + OPSecurity.getInstance().getDescription().getVersion() + "'");
			log.info("Latest Version Found: '" + o.getLatestVersion() + "'");
		    if(o.updateAvailable())
		    {
		    	if(p == null)
		    	{
		    	log.warning("#############################################################");
		    	log.warning("New Version of OPSecurity Available: '"+ o.getLatestVersion() + "'");
		    	log.warning("#############################################################");
		    }
		    	else
		    	{
		    		if(OPConfig.WHITELIST.contains(p.getName()))
		    		{
		    			p.sendMessage(OPUtils.parseString("&f&l&m[+]=======================[]>"));
		    			String msg = OPUtils.parseString("&c&lOP&f&oUpdater &7v&8[&7&o" + (new OPUpdater(OPSecurity.getInstance()).getLatestVersion()) + "&8] &c&lby Kilovice");
		    			TextComponent m = new TextComponent(msg);
						m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OPUtils.parseString("&b&lClick to view Plugin Page! &c&l[UPDATE AVAILABLE]")).create()));
						m.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/opsecurity.6665/"));
		    			p.spigot().sendMessage(m);
		    			p.sendMessage(OPUtils.parseString("&f&l&m[+]=======================[]>"));
		    		}
		    	}
		    }
			}
		   }
	   }
}
