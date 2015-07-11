package dev.kilovice.opsecurity.main;

public class OPDebug {

	public static void log(Class<?> classname, String s){
		if(OPSecurity.getInstance().getConfig() == null)
		{
			return;
		}
		if(!OPConfig.checkNull("config.debug"))
		{
		if(OPConfig.debug)
		{
		OPSecurity.log.info(classname.getSimpleName() + ": " + s);
	}
		}
		else
		{
			
		}
	}
	public static void log(String s1, String s){
		if(OPSecurity.getInstance().getConfig() == null)
		{
			return;
		}
		if(!OPConfig.checkNull("config.debug"))
		{
		if(OPConfig.debug)
		{
		OPSecurity.log.info(s1 + ": " + s);
	}
		}
	}
}
