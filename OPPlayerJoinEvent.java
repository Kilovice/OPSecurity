package dev.kilovice.opsecurity.listeners;

import org.bukkit.event.Listener;

import dev.kilovice.opsecurity.main.OPSecurity;

public class OPPlayerJoinEvent implements Listener{

	private OPSecurity plugin;
	public OPPlayerJoinEvent(OPSecurity pl)
	{
		this.plugin = pl;
		OPSecurity.registerEvents(plugin, this);
	}
}
