import java.util.Scanner;
import java.io.*;

public class FileInterface
{
	private String fileName = "backup.data";
	
	public Card[] loadContacts()
	{
		try
		{
			File f = new File(fileName);
			Scanner fs = new Scanner(f);
			while(fs.hasNextLine())
			{
				// TODO
			}
			return new Card[]{};
		} catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file " + fileName);
			return new Card[]{};
		}
	}
	
	public void saveContacts(Card[] cards)
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
