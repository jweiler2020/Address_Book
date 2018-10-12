public class Card {
	//represents a single contact card in the Rolodex
	
	// last, first, phone, address1, address2, email
	private String[] info = new String[6];
	
	public Card(String[] data)  // or public Card (String[] data) if you wanna be fancy
	{
		for(int i = 0; i < 6 && i < data.length; i++)
		{
			info[i] = data[i];
		}
	}
	
	public static void main(String[] args)
	{
	}
	
	public String toString(){
		//A string representation of this contact
		//(we will call this in Rolodex's toString method)
		StringBuilder sb = new StringBuilder();
		for(String st : info)
		{
			sb.append(st);
			sb.append(", ");
		}
		sb.delete(sb.length()-2, sb.length()-1);
		
		return sb.toString();
	}
	
	//optionally, you may find accessor/modifier methods useful 
}

