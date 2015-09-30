package dev.kilovice.opsecurity.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.kilovice.opsecurity.main.OPSecurity;
import dev.kilovice.opsecurity.main.OPUtils;

public class OPSecurityCommand implements CommandExecutor{
	
	private static OPSecurity plugin;
	public OPSecurityCommand(OPSecurity pl)
	{
		plugin = pl;
		plugin.getCommand("opsecurity").setExecutor(this);
		plugin.getCommand("ops").setExecutor(this);
	}
	public boolean onCommand(CommandSender s, Command c, String l, String[] args)
	{
		if(l.equalsIgnoreCase("opsecurity") || l.equalsIgnoreCase("ops"))
		{
			if(args.length == 0)
			{
				s.sendMessage(OPUtils.parseString("&f&l&m[+]=======================[]>"));
				String msg = OPUtils.parseString("&c&lOP&f&oSecurity &7v&8[&7&o" + plugin.getDescription().getVersion() + "&8] &c&lby Kilovice");
				if(s instanceof Player)
				{
					Player p = (Player)s;
					TextComponent m = new TextComponent(msg);
					m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(OPUtils.parseString("&b&lClick to view Plugin Page!")).create()));
					m.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/opsecurity.6665/"));
					p.spigot().sendMessage(m);
				}
				else
				{
					s.sendMessage(msg);
				}
				s.sendMessage(OPUtils.parseString("&f&l&m[+]=======================[]>"));
			}
		}
		return false;
	}
}
