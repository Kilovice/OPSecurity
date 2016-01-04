package dev.kilovice.opsecurity.main;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public interface OPMessages {

	public static File f = new File(OPSecurity.getInstance().getDataFolder().getAbsolutePath(), File.separator + "messages.yml");
	public static FileConfiguration MSG_CONFIG = YamlConfiguration.loadConfiguration(f);
	
	public static final String PREFIX = OPUtils.parseString(MSG_CONFIG.getString("messages.prefix"));
	public static final String SET_PASSWORD = PREFIX + OPUtils.parseString(MSG_CONFIG.getString("messages.set-password"));
	public static final String REQUEST_PLAYER_PASSWORD = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.request-player"));
	public static final String REQUEST_GLOBAL_PASSWORD = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.request-global"));
	public static final String NO_PERMISSION = PREFIX + OPUtils.parseString(MSG_CONFIG.getString("messages.no-permission"));
	public static final String INCORRECT_PASSWORD = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.incorrect-password"));
	public static final String CORRECT_PASSWORD = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.correct-password"));
	public static final String KICK_MESSAGE = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.kick"));
	public static final String BROADCAST_PERMISSION_MESSAGE = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.permission-message"));
	public static final String BROADCAST_MESSAGE = OPUtils.parseString(PREFIX + MSG_CONFIG.getString("messages.broadcast-message"));
}
