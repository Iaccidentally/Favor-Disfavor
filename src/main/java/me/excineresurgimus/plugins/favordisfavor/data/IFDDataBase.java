package me.excineresurgimus.plugins.favordisfavor.data;

import java.util.ArrayList;

public interface IFDDataBase 
{
	public boolean add_player(FDPlayer fdPlayer);
	public byte get_player_value(String player_name);
	public boolean favorite(String player_name);
	public boolean disfavorite(String player_name);
	public FDPlayer get_player(String player_name);
	public ArrayList<FDPlayer> get_player_list();
	public boolean reset_player(String player_name);
}
