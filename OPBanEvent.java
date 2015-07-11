package dev.kilovice.opsecurity.main;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OPBanEvent extends Event implements Cancellable{

	private Boolean cancelled = false;
	private static final HandlerList handlers = new HandlerList();

	@SuppressWarnings("deprecation")
	public OPBanEvent(Player player)
	{
		OPDebug.log(this.getClass(), "Event Called.");
		if(cancelled == true){
			OPDebug.log(this.getClass(), "Event Cancelled.");
			return;
		}
		OPDebug.log(this.getClass(), "Event not Cancelled.");
		if(!OPConfig.checkNull("config.commands")){
			for(String command : OPConfig.cmdList){
				String cmd = command.replace("%player%", player.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			}
		}
		
		if(!OPConfig.checkNull("config.perm-msg")){
			for(String msg : OPConfig.permMsgList){
				for(Player all : Bukkit.getOnlinePlayers()){
					if(all.hasPermission(OPPermissions.VIEW_BROADCAST))
					all.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.replace("%player%", player.getName())));
				}
		}
		if(!OPConfig.checkNull("config.bc-msg")){
			for(String msg : OPConfig.bcMsgList){
				for(Player all : Bukkit.getOnlinePlayers()){
					all.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.replace("%player%", player.getName())));
				}
			}
		}
		if(!OPConfig.checkNull("config.ban")){
			if(OPConfig.player_nameban){
				player.setBanned(true);
				if(!OPConfig.checkNull("config.kick-msg")){
					player.kickPlayer(ChatColor.translateAlternateColorCodes('&', OPConfig.kickMsg.replace("%player%", player.getName())));
				}
			}
		}
		if(!OPConfig.checkNull("config.ban-ip")){
			if(OPConfig.player_ipBan){
				Bukkit.getServer().banIP(player.getAddress().getAddress().getHostAddress());
			}
		}
		if(!OPConfig.checkNull("config.de-op")){
		   if(OPConfig.player_deop){
			   player.setOp(false);
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
