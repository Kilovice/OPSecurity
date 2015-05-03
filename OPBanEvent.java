package dev.kilovice.opsecurity.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OPBanEvent extends Event implements Cancellable{

	private Boolean cancelled = false;
	private static final HandlerList handlers = new HandlerList();
	public OPBanEvent(Player player)
	{
		
		if(cancelled != false){
			return;
		}
		if(!OPConfig.checkNull("config.commands")){
			for(String command : OPConfig.cmdList){
				String cmd = command.replace("%player%", player.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			}
		}
		
		if(!OPConfig.checkNull("config.perm-msg")){
			for(String msg : OPConfig.permMsgList){
				for(Player all : Bukkit.getOnlinePlayers()){
					all.sendMessage(msg.replace('&', '§').replace("%player%", player.getName()));
				}
		}
		if(!OPConfig.checkNull("config.bc-msg")){
			for(String msg : OPConfig.bcMsgList){
				for(Player all : Bukkit.getOnlinePlayers()){
					all.sendMessage(msg.replace('&', '§').replace("%player%", player.getName()));
				}
			}
		}
		if(!OPConfig.checkNull("config.ban")){
			if(OPConfig.player_nameban){
				player.setBanned(true);
				if(!OPConfig.checkNull("config.kick-msg")){
					player.kickPlayer(OPConfig.kickMsg.replace("%player%", player.getName()).replace('&', '§'));
				}
			}
		}
		if(!OPConfig.checkNull("config.ban-ip")){
			if(OPConfig.player_ipBan){
				Bukkit.getServer().banIP(player.getAddress().getAddress().getHostAddress());
			}
		}
	}
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public HandlerList getHandlerList(){
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
	
}
