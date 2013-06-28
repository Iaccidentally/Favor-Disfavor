package me.excineresurgimus.plugins.favordisfavor.main;

import java.io.IOException;
import me.excineresurgimus.plugins.favordisfavor.data.*;


public class FDEngine
{
	/**
	 * Boolean variable that specifies whether or not the FDMan has an interface, for starting purposes.
	 */
	public boolean hasAnInterface;
	/**
	 * Interface object to retain modularity.
	 */
	private IFavorDisfavor currentInterface;
	/**
	 * Database instance to be used by the server.
	 */
	private IFDDataBase dataBase;


	/**
	 * Server Identification for Text cosmetics & reference.
	 *
	 * @author JabJabJab
	 *
	 */
	public static enum ServerType
	{
		UNKNOWN,
		Bukkit
	}


	/**
	 * Database Identification for storage types.
	 *
	 * @author JabJabJab
	 *
	 */
	public static enum DatabaseType
	{
		FLATFILE
	}
	/**
	 * Server Type using this system.
	 */
	private ServerType serverType = ServerType.UNKNOWN;
	/**
	 * Server's Name using this system.
	 */
	private String serverName;
	private DatabaseType databaseType;

	/**
	 * Main Constructor of FDMain.
	 */
	public FDEngine(String serverName, ServerType serverType, DatabaseType databaseType)
	{
		//Sets variable(s).
		//lolnope
		this.serverName = serverName;
		this.serverType = serverType;
		this.databaseType = databaseType;

		initialize();
	}

	/**
	 * Initializes the Favorite/Disfavorite engine.
	 */
	private void initialize()
	{
		if (this.databaseType == DatabaseType.FLATFILE)
		{
			try
			{
				this.dataBase = FDFlatFileHelper.loadFlatfile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 *
	 * @return properly_shut_down
	 */
	public boolean shutDown()
	{
		System.out.println("Stopping the Favorite / Disfavorite Engine...");
		System.out.println("Saving...");
		return save();
	}

	/**
	 * Saves the database. Simple enough.
	 *
	 * @return
	 */
	public boolean save()
	{
		try
		{
			boolean hasSaved = false;
			if (this.databaseType == DatabaseType.FLATFILE)
			{
				hasSaved = FDFlatFileHelper.saveFlatFile((FlatFile)this.dataBase);
			}
			if (!hasSaved)
			{
				System.err.println("DATABASE DID NOT SAVE!!!");
				return false;
			}
			else
			{
				System.out.println("Database successfully saved.");
				return true;
			}
		}
		catch (Exception e)
		{
			System.err.println("DATABASE DID NOT SAVE!!!");
			return false;
		}
	}

	/**
	 * Returns the Database instance being used by the FDEngine.
	 *
	 * @return
	 */
	public IFDDataBase getDatabase()
	{
		return this.dataBase;
	}

	public IFavorDisfavor getInterface()
	{
		return this.currentInterface;
	}

	/**
	 * Sets the Database instance to be used by the FDEngine. (why?)
	 *
	 * @param db
	 */
	public void setDatabase(IFDDataBase db)
	{
		this.dataBase = db;
	}

	/**
	 * Returns whether or not the FDMain instance has an interface to work with.
	 *
	 * @return
	 */
	public boolean hasInterface()
	{
		return hasAnInterface;
	}

	/**
	 * Private method to internally configure Interface logic.
	 *
	 * @param b
	 */
	private void setHasInterface(boolean b)
	{
		this.hasAnInterface = b;
	}

	/**
	 * Sets the interface to communicate with the Favor / Disfavor service.
	 *
	 * @param i
	 */
	public void setInterface(IFavorDisfavor i)
	{
		this.currentInterface = i;
		if (i != null)
		{
			setHasInterface(true);
		}
	}

	/**
	 * Returns a 'ServerType' Enumeration object representing the architecture the Server is using this system.
	 *
	 * @return
	 */
	public ServerType getServerType()
	{
		return this.serverType;
	}

	/**
	 * Returns a String Object representing the Server's name using this system.
	 *
	 * @return
	 */
	public String getServerName()
	{
		return this.serverName;
	}

	public boolean Favorite(String playerName)
	{
		return this.dataBase.favorite(playerName);
	}

	public boolean Disfavorite(String playerName)
	{
		return this.dataBase.disfavorite(playerName);
	}

	public byte getPlayerValue(String playerName)
	{
		return this.dataBase.getPlayerValue(playerName);
	}

	public boolean resetPlayer(String playerName)
	{
		return this.dataBase.resetPlayer(playerName);
	}
}
