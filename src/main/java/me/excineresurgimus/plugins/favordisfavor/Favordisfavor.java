package me.excineresurgimus.plugins.favordisfavor;

import me.excineresurgimus.plugins.favordisfavor.main.FDEngine;
import me.excineresurgimus.plugins.favordisfavor.main.FavorDisfavorBukkit;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class Favordisfavor extends JavaPlugin implements Listener
{
	private FDEngine fdEngine;
	private FavorDisfavorBukkit fdInterface;
	private Permission permission;

	public Favordisfavor()
	{
		init();
	}

	private void init()
	{
		this.fdEngine = new FDEngine("", FDEngine.Server_Type.Bukkit, FDEngine.Database_Type.FLATFILE);

		this.fdInterface = new FavorDisfavorBukkit(this.fdEngine);
		this.fdEngine.set_interface(fdInterface);
	}

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable()
	{
		this.fdEngine.shut_down();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
		Player player = (Player)sender;
		if (command.getLabel().toLowerCase().equals("favor"))
		{
			if (player.hasPermission("favordisfavor.favor") || player.isOp())
			{
				this.fdInterface.favor(args[0]);
				player.sendMessage("Player has been favored.");
				return true;
			}
		}
		else if (command.getLabel().toLowerCase().equals("disfavor"))
		{
			if (player.hasPermission("favordisfavor.disfavor") || player.isOp())
			{
				this.fdInterface.disfavor(args[0]);
				player.sendMessage("Player has been disfavored.");
				return true;
			}
		}
		else if (command.getLabel().toLowerCase().equals("getvalue"))
		{
			if (player.hasPermission("favordisfavor.getvalue") || player.isOp())
			{
				byte value = this.fdInterface.get_player_value(args[0]);
				player.sendMessage("Player " + args[0] + " has a score of " + value + '.');
				return true;
			}
		}
		return false;
	}
}
