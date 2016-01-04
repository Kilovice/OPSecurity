package dev.kilovice.opsecurity.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OPBanEvent extends Event implements Cancellable, OPMessages, OPPermissions{

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
			for(String command : OPConfig.COMMAND_LIST){
				String cmd = command.replace("%player%", player.getName());
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
			}
		}
		
		if(!OPConfig.checkNull("config.perm-msg")){
			for(String msg : OPConfig.PERMISSION_MESSAGE_LIST){
				for(Player all : Bukkit.getOnlinePlayers()){
					if(all.hasPermission(VIEW_BROADCAST))
					all.sendMessage(OPUtils.parseString(msg, player));
				}
		}
		if(!OPConfig.checkNull("config.bc-msg")){
			for(String msg : OPConfig.BROADCAST_MESSAGE_LIST){
				for(Player all : Bukkit.getOnlinePlayers()){
					all.sendMessage(OPUtils.parseString(msg, player));
				}
			}
		}
		if(!OPConfig.checkNull("config.ban")){
			if(OPConfig.PLAYER_BAN_NAME){
				player.setBanned(true);
					player.kickPlayer(OPUtils.parseString(KICK_MESSAGE, player));
				
			}
		}
		if(!OPConfig.checkNull("config.ban-ip")){
			if(OPConfig.PLAYER_BAN_IP){
				Bukkit.getServer().banIP(player.getAddress().getAddress().getHostAddress());
			}
		}
		if(!OPConfig.checkNull("config.de-op")){
		   if(OPConfig.DEOP_PLAYER){
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
