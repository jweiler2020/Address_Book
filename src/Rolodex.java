import java.util.Scanner;
import java.io.*;

public class Rolodex {
	
	private Card[] cards; //This will eventually be replaced by Card[] cards
	private int size;
	private int capacity;

	public Rolodex()
	{
		cards = new Card[1];
		size = 0;
		capacity = 1;
	}
	
	public static void main (String args[])
	{
		//Create a FileInterface object
		FileInterface fi = new FileInterface();
		Rolodex rolo;
		if(fi.checkFile())
		{
			//Create a new Rolodex object by loading the contacts
			rolo = fi.loadContacts();
		}
		else
		{
			//Create default Rolodex object
			rolo = new Rolodex();
			System.out.println("Could not find file 'backup.data'. Creating empty address book.");
		}
		
		//Prompt the user to add, delete, print, or quit
		String input;
		Scanner kb = new Scanner(System.in);
		boolean done = false;
		while(!done)
		{
			System.out.print("(A)dd, (D)elete, (P)rint, (S)ort, (Q)uit: ");
			input = kb.nextLine();
			switch(input.toLowerCase())
			{
				case("a"):
				case("add"):
					rolo.add(userInpCard(kb));
					break;
				case("d"):
				case("delete"):
					System.out.print("Enter the index of the person you would like to delete: ");
					int index = kb.nextInt();
					kb.nextLine();
					if(!rolo.delete(index))
						System.out.println("Error: Invalid index entered");
					break;
				case("p"):
				case("print"):
					System.out.println("The current list:");
					System.out.println(rolo);
					break;
				case("s"):
				case("sort"):
					rolo.sort();
					System.out.println("Done sorting list");
					break;
				case("q"):
				case("quit"):
					done = true;
					fi.saveContacts(rolo.getCards());
					break;
				default:
					System.out.println("Error: Invalid command. Please enter a valid command");
					break;
			}
		}
	}
	
	public static Card userInpCard(Scanner kb)
	{
		String[] info = new String[6];
		
		System.out.println("Enter the information of the person in the following order:");
		System.out.println("Last Name, First Name, Phone Number, Address 1, Address 2, email");
		
		for(int i = 0; i < 6; i++)
		{
			System.out.print("> ");
			info[i] = kb.nextLine();
		}
		
		return new Card(info);
	}

	public void add(Card card)
	{
		//Adds name to the cards array (resizing cards by 1)
		size++;
		if(size > capacity)
		{
			Card[] temp = new Card[capacity*2];
			capacity *= 2;
			for(int i = 0; i < size-1; i++)
			{
				temp[i] = cards[i];
			}
			temp[size-1] = card;
			cards = temp;
		}
		else
		{
			cards[size-1] = card;
		}
	}
	
	public boolean delete(int index)
	{
		//Deletes the element at the specified index (resizing cards by -1)
		//Returns true if successful, false otherwise (i.e. if index is out of bounds)
		if(index < size)
		{
			size--;
			for (int i = index; i < size; i++)
			{
				cards[i] = cards[i + 1];
			}
			if (size <= capacity / 2)
			{
				Card[] temp = new Card[capacity / 2];
				for (int i = 0; i < size; i++)
				{
					temp[i] = cards[i];
				}
				cards = temp;
			}
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		if(size != 0)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			//for (int i = 0; i < cards.length; i++)
			for(int i = 0; i < size; i++)
			{
				sb.append("[");
				sb.append(cards[i]);
				sb.append("]");
				sb.append(", ");
			}
			sb.delete(sb.length()-2, sb.length());
			sb.append("]");
			return sb.toString();
		}
		else
			return "[]";
	}
	
	public int getSize() { return size; }
	
	public int getCapacity() { return capacity; }

	public Card[] getCards() { return cards; }

	// Sorting methods
	public void sort()
	{
		//Sorts the cards array alphabetically
		quicksort(cards, 0, size-1);
	}
	
	private void quicksort(Card[] c, int lo, int hi)
	{
		if(lo < hi)
		{
			int p = partition(c, lo, hi);
			quicksort(c, lo, p-1);
			quicksort(c, p+1, hi);
		}
	}
	
	private int partition(Card[] c, int lo, int hi)
	{
		String pivot = c[hi].toString().toLowerCase();
		int i = lo;
		for(int j = lo; j < hi; j++)
		{
			if(c[j].toString().toLowerCase().compareTo(pivot) < 0)
			{
				swap(c, i, j);
				i++;
			}
		}
		swap(c, i, hi);
		return i;
	}
	
	private void swap(Card[] c, int i, int j)
	{
		Card temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
}