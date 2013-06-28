package me.excineresurgimus.plugins.favordisfavor.main;


public interface IFavorDisfavor
{
	public boolean favor(String playerName);

	public boolean disfavor(String playerName);

	public boolean resetPlayer(String playerName);

	public byte getPlayerValue(String playerName);
}
