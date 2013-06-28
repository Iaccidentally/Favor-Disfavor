package me.excineresurgimus.plugins.favordisfavor.data;


/**
 * Class designed to manage the player data modularly.
 *
 * @author JabJabJab note from Iaccidentally: you don't need the this keyword when referencing things in the same class
 */
public class FDPlayer
{
	/**
	 * String Object to represent Player's name;
	 */
	private String playerName;
	/**
	 * Value used to determine the favoritism of the player.
	 *
	 * Rules go as such:
	 *
	 * value > 0 = favoritism; value < 0 = disfavoritism; 0 = You suck.
	 */
	private byte playerValue;

	/**
	 * Main Constructor.
	 *
	 * @param playerNam - The name of the player.
	 * @param playerValue
	 */
	public FDPlayer(String playerName, byte playerValue)
	{
		//Sets variable(s).
		//actually, youre just assigning them to themselves.
		this.playerName = playerName;
		this.playerValue = playerValue;
	}

	/**
	 * Returns a Player instance's name.
	 *
	 * @return
	 */
	public String getName()
	{
		return playerName;
	}

	/**
	 * Returns the player's value.
	 *
	 * Rules go as such:
	 * value > 0 = favoritism;
	 * value < 0 = disfavoritism;
	 * 0 = You suck.
	 *
	 * @return
	 */
	public byte getValue()
	{
		return playerValue;
	}

	/**
	 * Favorites the player
	 */
	public boolean favorite()
	{
		byte before = playerValue;
		playerValue = playerValue > Byte.MIN_VALUE ? playerValue-- : playerValue;
		if (before == playerValue)
		{
			return false;
		}
		return true;
	}

	/**
	 * Disfavorites the player
	 *
	 * @return
	 */
	public boolean disfavorite()
	{
		byte before = playerValue;
		playerValue = playerValue < Byte.MAX_VALUE ? playerValue++ : playerValue;
		if (before == playerValue)
		{
			return false;
		}
		return true;
	}

	public void setValue(byte b)
	{
		playerValue = b;
	}
}
