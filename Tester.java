import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class Tester
{
	public static void main(String[] args) 
	{
		System.out.println("Welcome!");
		
		ArrayList<String> peopleString = new ArrayList<String>();
		ArrayList<ArrayList<String>> peopleStringSeparated = new ArrayList<ArrayList<String>>();
		Scanner scanInput = new Scanner(System.in);
			
		try
		{
			File partyGuests = new File("partyguests.txt");
			Scanner scan = new Scanner(partyGuests);
			int i = 0;
			
			while (scan.hasNextLine())
			{
				peopleString.add(scan.nextLine());
				i++;
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
		
		for(int i = 0; i < peopleString.size(); i++)
		{
			for(int j = 0; j < 5; j++)
			{
				peopleStringSeparated.add(new ArrayList<String>());
			}
			
			peopleStringSeparated.get(0).add(peopleString.get(i));
			String[] arrPeople = peopleString.get(i).split(",",4);
			peopleStringSeparated.get(1).add(arrPeople[0]);
			peopleStringSeparated.get(2).add(arrPeople[1]);
			peopleStringSeparated.get(3).add(arrPeople[2]);
			peopleStringSeparated.get(4).add(arrPeople[3]);
		}
		
		ArrayList<Person> unregistered = new ArrayList<Person>();
		
		for(int i = 0; i < peopleString.size(); i++)
		{
			unregistered.add(new Person(Integer.parseInt(peopleStringSeparated.get(1).get(i)),
											  peopleStringSeparated.get(2).get(i),
											  peopleStringSeparated.get(3).get(i),
										Integer.parseInt(peopleStringSeparated.get(4).get(i))));
		}
		
		Party ihrpsConference = new Party(100,10);
		
		@SuppressWarnings("unchecked")
		ArrayList<Person>[] table = new ArrayList[ihrpsConference.getTables()];
		
		for (int i = 0; i < ihrpsConference.getTables(); i++)
		{
			table[i] = new ArrayList<Person>();
		}
		
		ihrpsConference.arrangeEmployeesAtTables(table, unregistered);
		ihrpsConference.unregisteredError(unregistered);
		
		while(true)
		{
			System.out.println("Please select an option from the menu.");
			System.out.println("1. Register Guest");
			System.out.println("2. Print roster by table number");
			System.out.println("3. Print roster by company number");
			System.out.println("4. Search Guest");
			System.out.println("------");
			
			int input;
			try
			{
				do
				{
					input = scanInput.nextInt();
					
					if (input < 1 || input > 4)
					{
						System.out.println("Invalid input.");
						System.out.println("------");
					}
				}
				while (input < 1 || input > 4);
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid input.");
				System.out.println("------");
			}
		}
	}
}
