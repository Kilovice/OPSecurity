package dev.kilovice.opsecurity.listeners;

import org.bukkit.entity.Player; 
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev.kilovice.opsecurity.main.OPConfig;
import dev.kilovice.opsecurity.main.OPConfigException;
import dev.kilovice.opsecurity.main.OPDebug;
import dev.kilovice.opsecurity.main.OPMessages;
import dev.kilovice.opsecurity.main.OPSecurity;

public class OPPlayerCommandPreprocessEvent implements Listener, OPMessages{

	private static OPSecurity plugin;
	public OPPlayerCommandPreprocessEvent(OPSecurity pl)
	{
		plugin = pl;
		OPSecurity.registerEvents(plugin, this);
	}
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onCmd(PlayerCommandPreprocessEvent e){
		OPDebug.log(this.getClass(), "Event Fired.");
		String rawcmd = e.getMessage();
		
		String[] cmd = e.getMessage().split(" ");
		Player p = e.getPlayer();
		if(!OPConfig.checkNull("config.command-password"))
		{
			if(!OPConfig.COMMAND_PASSWORD)
			{
				return;
			}
	    if(OPConfig.PASSWORD_COMMANDS.contains(cmd[0].substring(1)))
	    {	
		String s = OPConfig.COMMAND_TYPE;
		e.setCancelled(true);	
		if(s.equalsIgnoreCase("player") || s.equalsIgnoreCase("global"))
			{
			OPDebug.log(this.getClass(), "Checking Password Type . . .");
				switch(s.toLowerCase())
				{
				case "player":
					OPDebug.log(this.getClass(), "Password type: 'Player'");
					p.sendMessage(REQUEST_PLAYER_PASSWORD);
					break;
					
				case "global":
					OPDebug.log(this.getClass(), "Password type: 'Global'");
					p.sendMessage(REQUEST_GLOBAL_PASSWORD);
					break;
				}
				OPDebug.log(this.getClass(), "Saving Command to Map");
				OPSecurity.pw.add(p.getName());
				OPSecurity.tempcmd.put(p.getName(), rawcmd);
			}
			else{
				throw new OPConfigException("Invalid Configuration!", "config.command-password");
			}
		}
		}
		
	}
}
