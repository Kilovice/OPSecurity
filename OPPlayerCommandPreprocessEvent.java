package dev.kilovice.opsecurity.listeners;
 
import net.md_5.bungee.api.ChatColor; 

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dev.kilovice.opsecurity.main.OPConfig;
import dev.kilovice.opsecurity.main.OPConfigException;
import dev.kilovice.opsecurity.main.OPMessages;
import dev.kilovice.opsecurity.main.OPSecurity;

public class OPPlayerCommandPreprocessEvent implements Listener{

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e){
		
		
		String rawcmd = e.getMessage();
		
		String[] cmd = e.getMessage().split(" ");
		Player p = e.getPlayer();
		if(!OPConfig.checkNull("config.command-password"))
		{
			if(!OPConfig.cmdpw)
			{
				return;
			}
	    if(OPConfig.pwcmds.contains(cmd[0].substring(1)))
	    {	
		String s = OPConfig.cmdtp;
		e.setCancelled(true);	
		if(s.equalsIgnoreCase("player") || s.equalsIgnoreCase("global"))
			{
				switch(s.toLowerCase())
				{
				case "player":
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.rqtppw));
					break;
					
				case "global":
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.rqtgpw));
					break;
				}
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
