package dev.kilovice.opsecurity.main;

import org.bukkit.Bukkit;   
import org.bukkit.entity.Player;

public class OPScheduler implements Runnable{

	protected static int taskID;
	
	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(!OPConfig.WHITELIST.contains(player.getName()))
			{
			if(!OPConfig.checkNull("config.check-op")) {	
			if(OPConfig.CHECK_OP){
				if(player.isOp())
				{
					if(player != null){
					Bukkit.getServer().getPluginManager().callEvent(new OPBanEvent(player));
				}
				}
			}
			}
			if(!OPConfig.checkNull("config.check-perms")){
			if(OPConfig.CHECK_PERMS)
			{
				for(String perm : OPConfig.PERMISSION_LIST){
					if(player.hasPermission(perm))
					{
						Bukkit.getServer().getPluginManager().callEvent(new OPBanEvent(player));
					}
				}
			
			}
		}
			}
			
		}
	}
	
	public static void start(){
		taskID =  Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(OPSecurity.getInstance(), new OPScheduler(), OPConfig.TIMER_DELAY, OPConfig.TIMER_INTERVAL);
	}

	public static void end(){
		Bukkit.getServer().getScheduler().cancelTask(taskID);
	}
}
