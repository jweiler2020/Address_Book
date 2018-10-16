import java.util.Scanner;
import java.io.*;

public class FileInterface
{
	private static String fileName = "backup.data";
	
	public static Rolodex loadContacts()
	{
		Rolodex rolo = new Rolodex();
		try
		{
			File f = new File(fileName);
			Scanner fs = new Scanner(f);
			String[] temp = new String[6];
			while(fs.hasNextLine())
			{
				for(int i = 0; i < 6; i++)
				{
					temp[i] = fs.next();
					rolo.add(new Card(temp));
				}
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file " + fileName);
			return null;
		}
		return rolo;
	}
	
	public static void saveContacts(Card[] cards)
	{
		try
		{
			File f = new File(fileName);
			PrintWriter pw = new PrintWriter(f);
			for(Card c : cards)
			{
				// TODO
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file " + fileName);
		}
	}
}
