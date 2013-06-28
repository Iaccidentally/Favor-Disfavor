package me.excineresurgimus.plugins.favordisfavor.data;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class Storing the data for FDMain usage.
 *
 * @author JabJabJab
 *
 */
public class FlatFile implements IFDDataBase
{
	/**
	 * ArrayList to contain all players in a database.
	 */
	private ArrayList<FDPlayer> listPlayers;
	/**
	 * HashMap to contain all players in a map to access by name of the player.
	 */
	private HashMap<String, FDPlayer> mapPlayersByName;

	/**
	 * Main constructor.
	 */
	public FlatFile()
	{
		//Declares the Variable(s).
		listPlayers = new ArrayList<FDPlayer>();
		mapPlayersByName = new HashMap<String, FDPlayer>();
	}

	public boolean add_player(FDPlayer fdPlayer)
	{

		if (listPlayers.contains(fdPlayer))
		{
			System.err.println("FlatFile Database already contains this player!");
			return false;
		}
		else
		{
			listPlayers.add(fdPlayer);
			mapPlayersByName.put(fdPlayer.getName(), fdPlayer);
			return true;
		}
	}

	/**
	 *
	 * @param playerName
	 * @return
	 */
	public byte getPlayerValue(String playerName)
	{
		FDPlayer player = mapPlayersByName.get(playerName);
		if (player == null)
		{
			System.err.println("Player does not exist in the database! Returning 0.");
			return 0;
		}
		else
		{
			return player.getValue();
		}
	}

	/**
	 * Favorites the player selected. If the player does not exist, one is created for his or her place.
	 *
	 * @param playerName
	 */
	public boolean favorite(String playerName)
	{
		//Player object to have fun with.
		FDPlayer player = getPlayer(playerName);

		//Favorite the player.
		return player.favorite();
	}

	public boolean disfavorite(String playerName)
	{
		//Player object to have fun with.
		FDPlayer player = getPlayer(playerName);

		//Disfavorite the player.
		return player.disfavorite();
	}

	public FDPlayer getPlayer(String playerName)
	{

		FDPlayer player = mapPlayersByName.get(playerName);

		//Player does not exist yet. We need to create it.
		if (player == null)
		{
			System.out.println("Player Added to database: " + playerName);
			player = new FDPlayer(playerName, (byte)0);
			mapPlayersByName.put(playerName, player);
			listPlayers.add(player);
		}
		//then we return the player.
		//really? I would have never guessed that return player returns player.
		return player;
	}

	/**
	 * Returns the list of players stored on this FlatFile instance.
	 *
	 * @return
	 */
	public ArrayList<FDPlayer> getPlayerList()
	{
		return listPlayers;
	}

	public boolean resetPlayer(String playerName)
	{
		FDPlayer player = mapPlayersByName.get(playerName);
		player.setValue((byte)0);
		return false;
	}
}
