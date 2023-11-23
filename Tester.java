import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Tester
{
	public static void main(String[] args) 
	{
		System.out.println("Welcome!");
		System.out.println("If you haven't done so already, please load in the following files:");
		System.out.println("partyguests.txt");
		System.out.println("companies.txt");
		System.out.println("------");
		System.out.println("Beginning file import process.");
		
		ArrayList<String> peopleString = new ArrayList<String>();
		ArrayList<String> companiesString = new ArrayList<String>();
		
		ArrayList<ArrayList<String>> peopleStringSeparated = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> companiesStringSeparated = new ArrayList<ArrayList<String>>();
		
		Scanner scanInput = new Scanner(System.in);
			
		try
		{
			File partyGuests = new File("partyguests.txt");
			Scanner scanGuests = new Scanner(partyGuests);
			
			while (scanGuests.hasNextLine())
			{
				peopleString.add(scanGuests.nextLine());
			}
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File 'partyguests.txt' not found. Please place this file in the same directory as Tester.java.");
			System.exit(1);
		}
		
		try
		{
			File companies = new File("companies.txt");
			Scanner scanCompanies = new Scanner(companies);
			
			while (scanCompanies.hasNextLine())
			{
				String scanned = scanCompanies.nextLine();
				
				if (!scanned.equals("")) // filtering out empty lines
				{
					companiesString.add(scanned);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File 'companies.txt' not found. Please place this file in the same directory as Tester.java.");
			System.exit(1);
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
		
		for(int i = 0; i < companiesString.size(); i++)
		{
			for(int j = 0; j < 2; j++)
			{
				companiesStringSeparated.add(new ArrayList<String>()); 
			}
			
			String[] arrPeople = companiesString.get(i).split(",",2);
			companiesStringSeparated.get(0).add(arrPeople[0]);
			companiesStringSeparated.get(1).add(arrPeople[1]);
		}
		
		ArrayList<Person> unregistered = new ArrayList<Person>();
		ArrayList<Company> companies = new ArrayList<Company>();
		
		for(int i = 0; i < peopleString.size(); i++)
		{
			unregistered.add(new Person(Integer.parseInt(peopleStringSeparated.get(1).get(i)),
											  peopleStringSeparated.get(2).get(i),
											  peopleStringSeparated.get(3).get(i),
										Integer.parseInt(peopleStringSeparated.get(4).get(i))));
		}
		
		for(int i = 0; i < companiesString.size(); i++)
		{
			companies.add(new Company(Integer.parseInt(companiesStringSeparated.get(0).get(i)),
											companiesStringSeparated.get(1).get(i)));
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
			int input;
			
			do
			{	
				System.out.println("Please select an option from the menu.");
				System.out.println("1. Register Guest");
				System.out.println("2. Print roster by table number");
				System.out.println("3. Print roster by company number");
				System.out.println("4. Search Guest");
				System.out.println("------");
			
				input = 0;
				
				try
				{
					input = scanInput.nextInt();
				}
				catch (InputMismatchException e) {}
					
				System.out.println("------");	
				if (input < 1 || input > 4)
				{
					System.out.println("Invalid input.");
					System.out.println("------");
					scanInput.nextLine();
				}
			}
			while (input < 1 || input > 4);
		}
	}
}
