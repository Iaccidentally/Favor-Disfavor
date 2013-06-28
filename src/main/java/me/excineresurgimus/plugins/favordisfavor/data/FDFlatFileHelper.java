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
 *
 * @author JabJabJab
 * @fixed by Iaccidentally - welcome to Java.
 *
 */
public class FDFlatFileHelper
{
	/**
	 * FlatFile's default location. Might modify this in the future.
	 */
	public static final File flatfileLocation = new File("FDData/Database.db");

	/**
	 * Returns a FlatFile Data format of favoritism / disfavoritism.
	 *
	 * @return
	 * @throws IOException
	 */
	public static FlatFile loadFlatfile() throws IOException
	{
		//Our returned object.
		FlatFile flatFile = null;

		//Our exists boolean for properly creating the database if it does not exist yet.
		boolean flatFileExists = true;

		//If the flatfile does not exist:
		if (!flatfileLocation.exists())
		{
			//Attempt to create it, returning a boolean.
			flatFileExists = createFlatFile();
		}
		//If this boolean is false, Shit has gone terribly wrong and you should consider uninstalling windows.
		//If you are running this on windows, you have bigger problems and should consider unistalling your brain.
		if (!flatFileExists)
		{
			flatFile = new FlatFile();
			FileInputStream fstream = new FileInputStream(flatfileLocation);

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
					String[] splitString = strLine.split(",");

					//Sort the appropriate data values.
					String playerName = splitString[0],
							playerValue = splitString[1];

					//Adds the player to the list.
					flatFile.addPlayer(
							new FDPlayer(playerName, Byte.parseByte(playerValue)));
				}
				catch (Exception e)
				{
					System.err.println("Error Occured while parsing FlatFile: " + e.toString());
					continue;
				}
				// Print the content on the console
				System.out.println(strLine);
			}

			//Close the input stream
			in.close();
		}

		//Then we return the flatfile instance.
		//no shit sherlock
		return flatFile;

	}

	/**
	 * Creates the flatFile directory.
	 *
	 * @return
	 */
	private static boolean createFlatFile()
	{
		try
		{
			flatfileLocation.mkdirs();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Saves the FlatFile Object to file.
	 *
	 * @param flatFile
	 * @return
	 * @throws IOException
	 */
	public static boolean saveFlatFile(FlatFile flatfile)
	{
		//Our Writer Object.
		Writer output = null;
		try
		{
			//Create our Writer object.
			output = new BufferedWriter(new FileWriter(flatfileLocation));

			//Saves each player.
			for (FDPlayer player : flatfile.getPlayerList())
			{
				output.write(player.getName() + ',' + player.getValue());
			}
			//Closes stream for funzies.
			return true;
		}
		catch (Exception e)
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
				//If you wrote this code, you should learn Java.
			}
		}
	}
}
