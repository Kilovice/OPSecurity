package dev.kilovice.opsecurity.listeners;

import net.md_5.bungee.api.ChatColor;   

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.kilovice.opsecurity.main.OPConfig;
import dev.kilovice.opsecurity.main.OPDebug;
import dev.kilovice.opsecurity.main.OPMessages;
import dev.kilovice.opsecurity.main.OPSecurity;

public class OPAsyncChatEvent implements Listener, OPMessages{

	private static OPSecurity plugin;
	public OPAsyncChatEvent(OPSecurity pl)
	{
		plugin = pl;
		OPSecurity.registerEvents(plugin, this);
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		OPDebug.log(this.getClass(), "Event Fired.");
		Player p = e.getPlayer();
		if(OPSecurity.pw.contains(p.getName()))
		{
		String tpw = e.getMessage();
		String cpw = (String) OPConfig.PLAYER_PASSWORD.get(p.getName());
		String gpw = OPConfig.GLOBAL_PASSWORD;
		String s = OPConfig.COMMAND_TYPE;
		e.setCancelled(true);
		OPDebug.log(this.getClass(), "Checking Password type");
		switch(s.toLowerCase())
		{
		case "player":
			OPDebug.log(this.getClass(), "Password is type: 'Player'");
			if(tpw.equals(cpw))
			{
				OPDebug.log(this.getClass(), "Correct Password Entered");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', CORRECT_PASSWORD));
				execCmd(p);
			}
			else{
				OPDebug.log(this.getClass(), "Incorrect Password Entered");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', INCORRECT_PASSWORD));
			}
			break;
			
		case "global":
			OPDebug.log(this.getClass(), "Password is type: 'Global'");
			if(tpw.equals(gpw))
			{
				OPDebug.log(this.getClass(), "Correct Password Entered");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', CORRECT_PASSWORD));
				execCmd(p);
			}
			else{
				OPDebug.log(this.getClass(), "Incorrect Password Entered");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', INCORRECT_PASSWORD));
			}
			break;
		}
		}
		
	}
	public void execCmd(Player p)
	{
		OPDebug.log(this.getClass(), "Dispatching Command for Player: '" + p.getName() + "'");
		OPDebug.log(this.getClass(), "Command: '" + OPSecurity.tempcmd.get(p.getName()) + "'");
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
