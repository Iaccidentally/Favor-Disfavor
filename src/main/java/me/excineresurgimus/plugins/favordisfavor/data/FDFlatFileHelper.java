package me.excineresurgimus.plugins.favordisfavor.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;


/**
 * Class designed to properly load the FlatFile data.
 * @author JabJabJab
 *
 */
public class FDFlatFileHelper 
{
	/**
	 * FlatFile's default location. Might modify this in the future.
	 */
	public static final File flat_file_location = new File("FDData/Database.db");
	
	/**
	 * Returns a FlatFile Data format of favoritism / disfavoritism.
	 * @return
	 * @throws IOException
	 */
	public static FlatFile load_flatfile() throws IOException
	{
		//Our Returned object.
		FlatFile flat_file = null;
		
		//Our exists boolean for properly creating the database if it does not exist yet.
		boolean flat_file_exists = true;
		
		//If the flatfile does not exist:
		if(!flat_file_location.exists())
		{
			//Attempt to create it, returning a boolean.
			flat_file_exists = create_flat_file();
		}
		//If this boolean is false, Shit has gone terribly wrong and you should consider uninstalling windows.
		if(!flat_file_exists)
		{
			flat_file = new FlatFile();
			FileInputStream fstream = new FileInputStream(flat_file_location);
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) 
			{
				try
				{
					//Split the string by a comma.
					String[] split_string = strLine.split(",");
					
					//Sort the appropriate data values.
					String player_name = split_string[0],
						   player_value = split_string[1];

					//Adds the player to the list.
					flat_file.add_player
					(
						new FDPlayer(player_name, Byte.parseByte(player_value))
					);
				}
				catch(Exception e)
				{
					System.err.println("Error Occured while parsing FlatFile: " + e.toString());
					continue;
				}
				// Print the content on the console
				System.out.println (strLine);
			}
			
			//Close the input stream
			in.close();
		}
		
		//Then we return the flatfile instance.
		return flat_file;
		
	}
	
	/**
	 * Creates the flat_file directory.
	 * @return
	 */
	private static boolean create_flat_file()
	{
		try
		{
			flat_file_location.mkdirs();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Saves the FlatFile Object to file.
	 * @param flat_file
	 * @return
	 * @throws IOException 
	 */
	public static boolean save_flat_file(FlatFile flat_file)
	{
		//Our Writer Object.
		Writer output = null;
		try
		{
			//Create our Writer object.
			output = new BufferedWriter(new FileWriter(flat_file_location));
			
			//Saves each player.
			for(FDPlayer player : flat_file.get_player_list())
			{
				output.write(player.get_name() + ',' + player.get_value());
			}
			//Closes stream for funzies.
			return true;
		}
		catch(Exception e)
		{
			System.err.println("Exception while saving FlatFile Database: " + e.toString());
			return false;
		}
		finally
		{
			try 
			{
				output.close();
			}
			catch (IOException e) 
			{
				//If you got here, You fucking fail hard.
			}
		}
	}
}
