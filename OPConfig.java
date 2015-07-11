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
	
	
	public static boolean enabled = OPSecurity.config.getBoolean("config.enabled");
	public static boolean checkOP = OPSecurity.config.getBoolean("config.check-op");
	public static boolean checkPerms = OPSecurity.config.getBoolean("config.check-perms");
	public static List<String> permList = OPSecurity.config.getStringList("config.perm-list");
	public static List<String> whitelist = OPSecurity.config.getStringList("config.white-list");
	public static boolean player_ipBan = OPSecurity.config.getBoolean("config.ban-ip");
	public static boolean player_nameban = OPSecurity.config.getBoolean("config.ban");
	public static List<String> cmdList = OPSecurity.config.getStringList("config.commands");
	public static List<String> permMsgList = OPSecurity.config.getStringList("config.perm-msg");
	public static List<String> bcMsgList = OPSecurity.config.getStringList("config.bc-msg");
	public static long timerDelay = OPSecurity.config.getLong("config.timer-delay");
	public static long timerInterval = OPSecurity.config.getLong("config.timer-interval");
	public static String kickMsg = OPSecurity.config.getString("config.kick-msg");
	public static boolean cmdpw = OPSecurity.config.getBoolean("config.command-password");
	public static boolean player_deop = OPSecurity.config.getBoolean("config.de-op");
	public static String cmdtp = OPSecurity.config.getString("config.type");
	public static String globalpw = OPSecurity.config.getString("config.global-password");
	public static List<String> pwcmds = OPSecurity.config.getStringList("config.protected-commands");
	public static Map<String, ?> plrpws = OPSecurity.config.getConfigurationSection("config.player-password").getValues(false);
	public static boolean metrics = OPSecurity.config.getBoolean("config.metrics");
	public static boolean debug = OPSecurity.config.getBoolean("config.debug");
	public static boolean updater = OPSecurity.config.getBoolean("config.update-check");
	public static boolean checkNull(String path){
		if(OPSecurity.config.getString(path) == null){
			throw new OPConfigException("Invalid Configuration!", path);
		}
		else{
			return false;
		}
	}
}
