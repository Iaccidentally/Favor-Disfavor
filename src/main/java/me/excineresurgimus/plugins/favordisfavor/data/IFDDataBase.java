package me.excineresurgimus.plugins.favordisfavor.data;

import java.util.ArrayList;


public interface IFDDataBase
{
	public boolean addPlayer(FDPlayer fdPlayer);

	public byte getPlayerValue(String playerName);

	public boolean favorite(String playerName);

	public boolean disfavorite(String playerName);

	public FDPlayer getPlayer(String playerName);

	public ArrayList<FDPlayer> getPlayerList();

	public boolean resetPlayer(String playerName);
}
