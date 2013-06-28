package me.excineresurgimus.plugins.favordisfavor.main;


public class FavorDisfavorBukkit implements IFavorDisfavor
{
	private FDEngine fdEngine;

	public FavorDisfavorBukkit(FDEngine fdEngine)
	{
		this.fdEngine = fdEngine;
	}

	private FDEngine getEngine()
	{
		return fdEngine;
	}

	public boolean favor(String playerName)
	{
		return getEngine().Favorite(playerName);
	}

	public boolean disfavor(String playerName)
	{
		return getEngine().Disfavorite(playerName);
	}

	public boolean resetPlayer(String playerName)
	{
		return getEngine().resetPlayer(playerName);
	}

	public byte getPlayerValue(String playerName)
	{
		return getEngine().getPlayerValue(playerName);
	}
}
