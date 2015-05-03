package dev.kilovice.opsecurity.main;

import org.bukkit.Bukkit;

public class OPDebug {

	public static void bc(String classname, int i){
		Bukkit.broadcastMessage(classname + " " + i);
	}
}
