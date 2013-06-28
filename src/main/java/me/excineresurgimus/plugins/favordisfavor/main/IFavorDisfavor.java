package me.excineresurgimus.plugins.favordisfavor.main;

public interface IFavorDisfavor 
{
	public boolean favor(String player_name);
	
	public boolean disfavor(String player_name);
	
	public boolean reset_player(String player_name);
	
	public byte get_player_value(String player_name);
	
}
