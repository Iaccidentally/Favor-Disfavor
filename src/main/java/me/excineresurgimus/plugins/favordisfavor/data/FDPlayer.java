package me.excineresurgimus.plugins.favordisfavor.data;

/**
 * Class designed to manage the player data modularly.
 * @author JabJabJab
 *
 */
public class FDPlayer 
{
	/**
	 * String Object to represent Player's name;
	 */
	private String player_name;
	
	/**
	 * Value used to determine the favoritism of the player.
	 * 
	 * Rules go as such:
	 * 
	 *  value > 0 = favoritism;
	 *  value < 0 = disfavoritism;
	 *  0 = You suck.
	 */
	private byte player_value;
	
	/**
	 * Main Constructor.
	 * @param player_name - The name of the player.
	 * @param player_value 
	 */
	public FDPlayer(String player_name, byte player_value)
	{
		//Sets variable(s).
		this.player_name = player_name;
		this.player_value = player_value;
	}
	
	/**
	 * Returns a Player instance's name.
	 * @return
	 */
	public String get_name()
	{
		return this.player_name;
	}

	/**
	 * Returns the player's value.
	 * 
	 *  Rules go as such:
	 *  value > 0 = favoritism;
	 *  value < 0 = disfavoritism;
	 *  0 = You suck.
	 * @return
	 */
	public byte get_value()
	{
		return this.player_value;
	}
	
	
	/**
	 * Favorites the player
	 */
	public boolean favorite()
	{
		byte before = this.player_value;
		this.player_value = player_value > Byte.MIN_VALUE ? this.player_value-- : this.player_value;
		if(before == player_value)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Disfavorites the player
	 * @return 
	 */
	public boolean disfavorite()
	{
		byte before = this.player_value;
		this.player_value = player_value < Byte.MAX_VALUE ? this.player_value++ : this.player_value;
		if(before == player_value)
		{
			return false;
		}
		return true;
	}

	public void set_value(byte b) 
	{
		this.player_value = b;
	}
}
