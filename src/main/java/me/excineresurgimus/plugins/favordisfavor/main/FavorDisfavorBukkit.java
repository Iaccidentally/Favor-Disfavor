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

	@Override
	public boolean favor(String playerName)
	{
		return getEngine().Favorite(playerName);
	}

	@Override
	public boolean disfavor(String playerName)
	{
		return getEngine().Disfavorite(playerName);
	}

	@Override
	public boolean resetPlayer(String playerName)
	{
		return getEngine().resetPlayer(playerName);
	}

	@Override
	public byte getPlayerValue(String playerName)
	{
		return getEngine().getPlayerValue(playerName);
	}
}
