package me.excineresurgimus.plugins.favordisfavor.main;

import java.io.IOException;

import me.excineresurgimus.plugins.favordisfavor.data.*;

public class FDEngine 
{
	/**
	 * Boolean variable that specifies whether or not the FDMan has an interface, for starting purposes.
	 */
	public boolean has_an_interface;
	
	/**
	 * Interface object to retain modularity.
	 */
	private IFavorDisfavor current_interface;

	/**
	 * Database instance to be used by the server.
	 */
	private IFDDataBase data_base;
	
	/**
	 * Server Identification for Text cosmetics & reference.
	 * @author JabJabJab
	 *
	 */
	public static enum Server_Type
	{
		UNKNOWN,
		Bukkit
	}
	
	/**
	 * Database Identification for storage types.
	 * @author JabJabJab
	 *
	 */
	public static enum Database_Type
	{
		FLATFILE
	}
	
	/**
	 * Server Type using this system.
	 */
	private Server_Type server_type = Server_Type.UNKNOWN;
	
	/**
	 * Server's Name using this system.
	 */
	private String server_name;

	private Database_Type database_type;
	
	/**
	 * Main Constructor of FDMain.
	 */
	public FDEngine(String server_name, Server_Type server_type, Database_Type database_type)
	{
		//Sets variable(s).
		this.server_name = server_name;
		this.server_type = server_type;
		this.database_type = database_type;
		
		initialize();
	}
	
	/**
	 * Initializes the Favorite/Disfavorite engine.
	 */
	private void initialize() 
	{
		if(this.database_type == Database_Type.FLATFILE)
		{
			try 
			{
				this.data_base = FDFlatFileHelper.load_flatfile();
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
	public boolean shut_down()
	{
		System.out.println("Stopping the Favorite / Disfavorite Engine...");
		System.out.println("Saving...");
		return save();
	}
	
	/**
	 * Saves the database. Simple enough.
	 * @return
	 */
	public boolean save()
	{
		try
		{
			boolean has_saved = false;
			if(this.database_type == Database_Type.FLATFILE)
			{			
				has_saved = FDFlatFileHelper.save_flat_file((FlatFile) this.data_base);
			}
			if(!has_saved)
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
		catch(Exception e)
		{
			System.err.println("DATABASE DID NOT SAVE!!!");
			return false;
		}
	}
	
	/**
	 * Returns the Database instance being used by the FDEngine.
	 * @return
	 */
	public IFDDataBase get_database()
	{
		return this.data_base;
	}
	
	public IFavorDisfavor get_interface()
	{
		return this.current_interface;
	}
	
	/**
	 * Sets the Database instance to be used by the FDEngine. (why?)
	 * @param db
	 */
	public void set_database(IFDDataBase db)
	{
		this.data_base = db;
	}

	/**
	 * Returns whether or not the FDMain instance has an interface to work with.
	 * @return
	 */
	public boolean has_interface()
	{
		return has_an_interface;
	}
	
	/**
	 * Private method to internally configure Interface logic.
	 * @param b
	 */
	private void set_has_interface(boolean b)
	{
		this.has_an_interface = b;
	}
	
	/**
	 * Sets the interface to communicate with the Favor / Disfavor service.
	 * @param i
	 */
	public void set_interface(IFavorDisfavor i)
	{
		this.current_interface = i;
		if(i != null)
		{
			set_has_interface(true);
		}
	}
	
	/**
	 * Returns a 'Server_Type' Enumeration object representing the architecture the Server is using this system.
	 * @return
	 */
	public Server_Type get_server_type()
	{
		return this.server_type;
	}
	
	/**
	 * Returns a String Object representing the Server's name using this system.
	 * @return
	 */
	public String get_server_name()
	{
		return this.server_name;
	}
	
	public boolean Favorite(String player_name)
	{
		return this.data_base.favorite(player_name);
	}
	
	public boolean Disfavorite(String player_name)
	{
		return this.data_base.disfavorite(player_name);
	}

	public byte get_player_value(String player_name) 
	{
		return this.data_base.get_player_value(player_name);
	}

	public boolean reset_player(String player_name) 
	{
		return this.data_base.reset_player(player_name);
	}
	
}
