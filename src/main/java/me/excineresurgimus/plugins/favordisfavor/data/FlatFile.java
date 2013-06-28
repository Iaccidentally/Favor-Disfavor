package me.excineresurgimus.plugins.favordisfavor.data;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class Storing the data for FDMain usage.
 * @author JabJabJab
 *
 */
public class FlatFile implements IFDDataBase
{
	/**
	 * ArrayList to contain all players in a database.
	 */
	private ArrayList<FDPlayer> list_players;
	
	/**
	 * HashMap to contain all players in a map to access by name of the player.
	 */
	private HashMap<String, FDPlayer> map_players_by_name;

	/**
	 * Main constructor.
	 */
	public FlatFile() 
	{
		//Declares the Variable(s).
		this.list_players = new ArrayList<FDPlayer>();
		map_players_by_name = new HashMap<String, FDPlayer>();
	}
	
	public boolean add_player(FDPlayer fdPlayer) 
	{
		
		if(list_players.contains(fdPlayer))
		{
			System.err.println("FlatFile Database already contains this player!");
			return false;
		}
		else
		{
			list_players.add(fdPlayer);
			map_players_by_name.put(fdPlayer.get_name(), fdPlayer);
			return true;
		}
	}
	
	/**
	 * 
	 * @param player_name
	 * @return
	 */
	public byte get_player_value(String player_name)
	{
		FDPlayer player = map_players_by_name.get(player_name);
		if(player == null)
		{
			System.err.println("Player does not exist in the database! Returning 0.");
			return 0;
		}
		else
		{
			return player.get_value();
		}
	}
	
	/**
	 * Favorites the player selected. If the player does not exist, one is created for his or her place.
	 * @param player_name
	 */
	public boolean favorite(String player_name)
	{
		//Player object to have fun with.
		FDPlayer player = get_player(player_name);
	
		//Favorite the player.
		return player.favorite();
	}
	
	public boolean disfavorite(String player_name)
	{
		//Player object to have fun with.
		FDPlayer player = get_player(player_name);

		//Disfavorite the player.
		return player.disfavorite();
	}
	
	public FDPlayer get_player(String player_name)
	{
		
		FDPlayer player = map_players_by_name.get(player_name);
		
		//Player does not exist yet. We need to create it.
		if(player == null)
		{
			System.out.println("Player Added to database: " + player_name);
			player = new FDPlayer(player_name, (byte)0);
			map_players_by_name.put(player_name, player);
			list_players.add(player);
		}
		//then we return the player.
		return player;
	}

	/**
	 * Returns the list of players stored on this FlatFile instance.
	 * @return
	 */
	public ArrayList<FDPlayer> get_player_list() 
	{
		return this.list_players;
	}

	public boolean reset_player(String player_name) 
	{
		FDPlayer player = map_players_by_name.get(player_name);
		player.set_value((byte)0);
		return false;
	}
	
}
