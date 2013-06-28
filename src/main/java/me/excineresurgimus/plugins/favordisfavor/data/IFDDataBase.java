package me.excineresurgimus.plugins.favordisfavor.data;

import java.util.ArrayList;


public interface IFDDataBase
{
	public boolean add_player(FDPlayer fdPlayer);

	public byte getPlayerValue(String player_name);

	public boolean favorite(String player_name);

	public boolean disfavorite(String player_name);

	public FDPlayer getPlayer(String player_name);

	public ArrayList<FDPlayer> getPlayerList();

	public boolean resetPlayer(String player_name);
}
