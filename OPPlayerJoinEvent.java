package dev.kilovice.opsecurity.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.kilovice.opsecurity.main.OPSecurity;

public class OPPlayerJoinEvent implements Listener{

	private OPSecurity plugin;
	public OPPlayerJoinEvent(OPSecurity pl)
	{
		this.plugin = pl;
		OPSecurity.registerEvents(plugin, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		OPSecurity.checkUpdate(e.getPlayer());
	}
}
