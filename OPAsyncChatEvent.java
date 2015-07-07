package dev.kilovice.opsecurity.listeners;

import net.md_5.bungee.api.ChatColor;  

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.kilovice.opsecurity.main.OPConfig;
import dev.kilovice.opsecurity.main.OPMessages;
import dev.kilovice.opsecurity.main.OPSecurity;

public class OPAsyncChatEvent implements Listener{

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(OPSecurity.pw.contains(p.getName()))
		{
		String tpw = e.getMessage();
		String cpw = (String) OPConfig.plrpws.get(p.getName());
		String gpw = OPConfig.globalpw;
		String s = OPConfig.cmdtp;
		e.setCancelled(true);
		switch(s.toLowerCase())
		{
		case "player":
			if(tpw.equals(cpw))
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.ctpw));
				execCmd(p);
			}
			else{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.inctpw));
			}
			break;
			
		case "global":
			if(tpw.equals(gpw))
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.ctpw));
				execCmd(p);
			}
			else{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', OPMessages.inctpw));
			}
			break;
		}
		}
		
	}
	public void execCmd(Player p)
	{
		OPSecurity.pw.remove(p.getName());
		String rawcmd = OPSecurity.tempcmd.get(p.getName());
		rawcmd = rawcmd.substring(1);
		try{
		Bukkit.getServer().dispatchCommand(p, rawcmd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
