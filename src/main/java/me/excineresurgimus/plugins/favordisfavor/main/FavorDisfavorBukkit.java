package me.excineresurgimus.plugins.favordisfavor.main;

public class FavorDisfavorBukkit implements IFavorDisfavor
{
	private FDEngine fdEngine;
	public FavorDisfavorBukkit(FDEngine fdEngine)
	{
		this.fdEngine = fdEngine;
	}
	
	private FDEngine get_engine()
	{
		return this.fdEngine;
	}

	public boolean favor(String player_name) 
	{
		return get_engine().Favorite(player_name);
	}

	public boolean disfavor(String player_name) 
	{
		return get_engine().Disfavorite(player_name);
	}

	public boolean reset_player(String player_name) 
	{
		return get_engine().reset_player(player_name);
	}

	public byte get_player_value(String player_name) 
	{
		return get_engine().get_player_value(player_name);
	}
}
