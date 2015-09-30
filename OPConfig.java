package dev.kilovice.opsecurity.main;

import java.io.File;  
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class OPConfig {
	
	public static FileConfiguration getOPConfig()
	{
		return OPSecurity.config;
	}
	
	public static File getOPConfigFile()
	{
		File f = new File(OPSecurity.getInstance().getDataFolder() + File.separator + "config.yml");
		return f;
	}
	
	
	public static final boolean PLUGIN = OPSecurity.config.getBoolean("config.enabled");
	public static final boolean CHECK_OP = OPSecurity.config.getBoolean("config.check-op");
	public static final boolean CHECK_PERMS = OPSecurity.config.getBoolean("config.check-perms");
	public static final List<String> PERMISSION_LIST = OPSecurity.config.getStringList("config.perm-list");
	public static final List<String> WHITELIST = OPSecurity.config.getStringList("config.white-list");
	public static final boolean PLAYER_BAN_IP = OPSecurity.config.getBoolean("config.ban-ip");
	public static final boolean PLAYER_BAN_NAME = OPSecurity.config.getBoolean("config.ban");
	public static final List<String> COMMAND_LIST = OPSecurity.config.getStringList("config.commands");
	public static final List<String> PERMISSION_MESSAGE_LIST = OPSecurity.config.getStringList("config.perm-msg");
	public static final List<String> BROADCAST_MESSAGE_LIST = OPSecurity.config.getStringList("config.bc-msg");
	public static final long TIMER_DELAY = OPSecurity.config.getLong("config.timer-delay");
	public static final long TIMER_INTERVAL = OPSecurity.config.getLong("config.timer-interval");
	public static final String KICK_MESSAGE = OPSecurity.config.getString("config.kick-msg");
	public static final boolean COMMAND_PASSWORD = OPSecurity.config.getBoolean("config.command-password");
	public static final boolean DEOP_PLAYER = OPSecurity.config.getBoolean("config.de-op");
	public static final String COMMAND_TYPE = OPSecurity.config.getString("config.type");
	public static final String GLOBAL_PASSWORD = OPSecurity.config.getString("config.global-password");
	public static final List<String> PASSWORD_COMMANDS = OPSecurity.config.getStringList("config.protected-commands");
	public static final Map<String, ?> PLAYER_PASSWORD = OPSecurity.config.getConfigurationSection("config.player-password").getValues(false);
	public static final boolean METRICS = OPSecurity.config.getBoolean("config.metrics");
	public static final boolean DEBUG = OPSecurity.config.getBoolean("config.debug");
	public static final boolean UPDATE = OPSecurity.config.getBoolean("config.update-check");
	public static boolean checkNull(String path){
		if(OPSecurity.config.getString(path) == null){
			throw new OPConfigException("Invalid Configuration!", path);
		}
		else{
			return false;
		}
	}
}
