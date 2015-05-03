package dev.kilovice.opsecurity.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OPScheduler implements Runnable{

	protected static int taskID;
	
	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(OPConfig.whitelist.contains(player.getName()))
			{
			return;
			}
			if(OPConfig.checkOP){
				if(player.isOp())
				{
					if(player != null){
					Bukkit.getServer().getPluginManager().callEvent(new OPBanEvent(player));
				}
				}
			}
			if(OPConfig.checkPerms)
			{
				for(String perm : OPConfig.permList){
					if(player.hasPermission(perm))
					{
						Bukkit.getServer().getPluginManager().callEvent(new OPBanEvent(player));
					}
				}
			
			}
			
		}
	}
	
	public static void start(){
		taskID =  Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(OPSecurity.getInstance(), new OPScheduler(), OPConfig.timerDelay, OPConfig.timerInterval);
	}

	public static void end(){
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}
}
