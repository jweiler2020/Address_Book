import java.util.Scanner;

public class Rolodex {
	
	private String[] names; //This will eventually be replaced by Card[] cards
	
	public Rolodex(){
		names = new String[0];
	}
		
	public static void main (String args[]) {
				
		//Create a new Rolodex object
		Rolodex rolo = new Rolodex();
		
		//Prompt the user to add, delete, print, or quit
		String input = "";
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
					System.out.print("Enter the name you would like to add: ");
					String name = kb.nextLine();
					rolo.add(name);
					break;
				case("d"):
				case("delete"):
					System.out.print("Enter the index of the name you would like to delete: ");
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
					break;
				default:
					System.out.println("Error: Invalid command. Please enter a valid command");
					break;
			}
		}
	}
	
	public void add(String name){
		//Adds name to the names array (resizing names by 1)
		String[] temp = new String[names.length + 1];
		for(int i = 0; i < names.length; i++)
		{
			temp[i] = names[i];
		}
		temp[temp.length - 1] = name;
		names = temp;
	}
	
	public boolean delete(int index){
		//Deletes the element at the specified index (resizing names by -1)
		//Returns true if successful, false otherwise (i.e. if index is out of bounds)
		if(index > names.length - 1)
			return false;
		String[] temp = new String[names.length - 1];
		names[index] = null;
		int i = 0;
		for(String n : names)
		{
			if(n != null)
			{
				temp[i] = n;
				i++;
			}
		}
		names = temp;
		return true;
	}
	
	public String toString(){
		if(names.length != 0)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(names[0]);
			for (int i = 1; i < names.length; i++)
			{
				sb.append(", ");
				sb.append(names[i]);
			}
			sb.append("]");
			return sb.toString();
		}
		else
			return "[]";
	}
	
	// Sorting methods
	public void sort(){
		//Sorts the names array alphabetically
		quicksort(names, 0, names.length-1);
	}
	
	private void quicksort(String[] a, int lo, int hi)
	{
		if(lo < hi)
		{
			int p = partition(a, lo, hi);
			quicksort(a, lo, p-1);
			quicksort(a, p+1, hi);
		}
	}
	
	private int partition(String[] a, int lo, int hi)
	{
		String pivot = a[hi].toLowerCase();
		int i = lo;
		for(int j = lo; j < hi; j++)
		{
			if(a[j].toLowerCase().compareTo(pivot) < 0)
			{
				swap(a, i, j);
				i++;
			}
		}
		swap(a, i, hi);
		return i;
	}
	
	private void swap(String[] a, int i, int j)
	{
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}