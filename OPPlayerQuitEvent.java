package dev.kilovice.opsecurity.listeners;

import org.bukkit.entity.Player; 
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.kilovice.opsecurity.main.OPDebug;
import dev.kilovice.opsecurity.main.OPSecurity;

public class OPPlayerQuitEvent implements Listener{

	private static OPSecurity plugin;
	public OPPlayerQuitEvent(OPSecurity pl)
	{
		plugin = pl;
		OPSecurity.registerEvents(plugin, this);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		OPDebug.log(this.getClass(), "Event Fired.");
		Player p = e.getPlayer();
		if(OPSecurity.pw.contains(p.getName()))
		{
			OPSecurity.pw.remove(p.getName());
		}
	}
}

