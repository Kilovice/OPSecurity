package dev.kilovice.opsecurity.main;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class OPMessages {

	public static File f = new File(OPSecurity.getInstance().getDataFolder().getAbsolutePath(), File.separator + "messages.yml");
	public static FileConfiguration MSG_CONFIG = YamlConfiguration.loadConfiguration(f);
	
	public static String prefix = MSG_CONFIG.getString("messages.prefix");
	public static String setpw = prefix + MSG_CONFIG.getString("messages.set-password");
	public static String rqtppw = prefix + MSG_CONFIG.getString("messages.request-player");
	public static String rqtgpw = prefix + MSG_CONFIG.getString("messages.request-global");
	public static String noperm = prefix + MSG_CONFIG.getString("messages.no-permission");
	public static String inctpw = prefix + MSG_CONFIG.getString("messages.incorrect-password");
	public static String ctpw = prefix + MSG_CONFIG.getString("messages.correct-password");
}

