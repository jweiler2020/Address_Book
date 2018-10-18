import java.util.Scanner;
import java.io.*;

public class FileInterface
{
	private String fileName;
	private File f;

	public FileInterface()
	{
		fileName = "backup.data";
		f = new File(fileName);
	}

	public FileInterface(String file)
	{
		fileName = file;
		f = new File(fileName);
	}

	public boolean checkFile() { return f.exists(); }
	
	public Rolodex loadContacts()
	{
		Rolodex rolo = new Rolodex();
		try
		{
			Scanner fs = new Scanner(f);
			String[] temp = new String[6];
			String[] cur;
			while(fs.hasNextLine())
			{
				rolo.add(new Card(fs.nextLine().split("; ")));
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file " + fileName);
			return null;
		}
		return rolo;
	}
	
	public void saveContacts(Card[] cards)
	{
		try
		{
			PrintWriter pw = new PrintWriter(f);
			for (Card c : cards)
			{
				if(c != null)
				{
					pw.write(c.toString());
					pw.write("\n");
				}
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file " + fileName);
		}
	}
}
