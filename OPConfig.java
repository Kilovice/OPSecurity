package dev.kilovice.opsecurity.main;

import java.util.List;

public class OPConfig {
	
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
	public static boolean checkNull(String path){
		if(OPSecurity.config.getString(path) == null){
			throw new OPConfigException("Invalid Configuration!", path);
		}
		else{
			return false;
		}
	}
}
