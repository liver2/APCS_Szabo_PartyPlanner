import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/*
 * Purpose: Contains the maian method, reads .txt files to import data, 
 * interprets data, creates a Party object, and hosts menu.
 */

/**
* Tester.java 
* Author: Oliver Szabo
* Date: November 27, 2023, 11:59 PM
* Purpose: Contains the main method, reads .txt files to import data, 
* interprets data, creates a Party object, and hosts menu. 
*/
public class Tester
{
	/*
	 * Main method which hosts ArrayLists of Persons corresponding to
	 * a party's unregistered and registered guests, an ArrayList of Companies
	 * for the companies in attendance, a Party object and its methods, and
	 * a menu from which the user can access the different methods of the
	 * declared Party object.
	 * Args: String[] args
	 * Returns: void
	 */
	public static void main(String[] args) 
	{
		System.out.println("Welcome!");
		System.out.println("If you haven't done so already, please load in the following files:");
		System.out.println("partyguests.txt");
		System.out.println("companies.txt"); // Inviting user to load in files.
		System.out.println("------");
		System.out.println("Beginning file import process.");
		
		ArrayList<String> peopleString = new ArrayList<String>(); 
		ArrayList<String> companiesString = new ArrayList<String>(); // Create ArrayLists for the raw Strings imported from .txt file
		
		ArrayList<ArrayList<String>> peopleStringSeparated = new ArrayList<ArrayList<String>>(); 
		ArrayList<ArrayList<String>> companiesStringSeparated = new ArrayList<ArrayList<String>>(); // 2D ArrayLists of Strings to separate values
		
		Scanner scanInput = new Scanner(System.in);
			
		// Credit for importing file methods: https://www.w3schools.com/java/java_files_read.asp	
		// Challenge: Decided on writing two separate data importing processes, because with the 
		// difference in formats between Company and Person, it was easier to import data separately
			
		/*
		 * Loads data from "partyguests.txt" into an ArrayList<String>
		 */
		try
		{
			File partyGuests = new File("partyguests.txt");
			Scanner scanGuests = new Scanner(partyGuests);
			
			while (scanGuests.hasNextLine())
			{
				String scanned = scanGuests.nextLine();
				
				if (!scanned.equals(""))
				{
					peopleString.add(scanned);
				}
			}
		}
		catch (FileNotFoundException e) // Challenge: FileNotFoundException needs to be imported for try/catch to work.
		{
			System.out.println("------");
			System.out.println("File 'partyguests.txt' not found. Please place this file in the same directory as Tester.java.");
			System.exit(1);
		}
		
		/*
		 * Loads data from "companies.txt" into an ArrayList<String>
		 */
		try
		{
			File companies = new File("companies.txt");
			Scanner scanCompanies = new Scanner(companies);
			
			while (scanCompanies.hasNextLine())
			{
				String scanned = scanCompanies.nextLine();
				
				if (!scanned.equals("")) // Filtering out empty lines
				{
					companiesString.add(scanned);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("------");
			System.out.println("File 'companies.txt' not found. Please place this file in the same directory as Tester.java.");
			System.exit(1);
		}	
		
		/*
		 * Splits String of imported data into separate Strings for
		 * data imported from "partyguests.txt"
		 */
		for(int i = 0; i < peopleString.size(); i++)
		{
			for(int j = 0; j < 5; j++)
			{
				peopleStringSeparated.add(new ArrayList<String>());
			}
			
			peopleStringSeparated.get(0).add(peopleString.get(i)); // Challenge: Decided on numbering 1st column due to better organization
			String[] arrPeople = peopleString.get(i).split(",",4);
			peopleStringSeparated.get(1).add(arrPeople[0]);
			peopleStringSeparated.get(2).add(arrPeople[1]);
			peopleStringSeparated.get(3).add(arrPeople[2]);
			peopleStringSeparated.get(4).add(arrPeople[3]);
		}
		
		/*
		 * Splits String of imported data into separate Strings for
		 * data imported from "companies.txt"
		 */
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
		
		/*
		 * Creates two ArrayLists that represent the unregistered guests
		 * and the companies, importing data into new objects
		 */
		ArrayList<Person> unregistered = new ArrayList<Person>(); 
		ArrayList<Company> companies = new ArrayList<Company>();
		
		for(int i = 0; i < peopleString.size(); i++)
		{
			unregistered.add(new Person(Integer.parseInt(peopleStringSeparated.get(1).get(i)), 
											  peopleStringSeparated.get(2).get(i), // Challenge: Had to do some more research about wrapper classes
											  peopleStringSeparated.get(3).get(i),
										Integer.parseInt(peopleStringSeparated.get(4).get(i))));
		}
		
		for(int i = 0; i < companiesString.size(); i++)
		{
			companies.add(new Company(Integer.parseInt(companiesStringSeparated.get(0).get(i)),
											companiesStringSeparated.get(1).get(i)));
		}
		
		/*
		 * Initializes a Party object and creates and initializes an
		 * array of ArrayLists, representing the different tables at which
		 * guests will sit
		 */
		Party ihrpsConference = new Party(100,10);
		
		@SuppressWarnings("unchecked") // Credit: Marcus Twyford
		ArrayList<Person>[] table = new ArrayList[ihrpsConference.getTables()]; // Created Array of ArrayLists to ensure number of tables is fixed
		
		for (int i = 0; i < ihrpsConference.getTables(); i++)
		{
			table[i] = new ArrayList<Person>(); // Initially ran into problems initializing each individual array list
		}
		
		/*
		 * See Party.java for more information on these methods
		 */
		ihrpsConference.arrangeEmployeesAtTables(table, unregistered);
		ihrpsConference.unregisteredError(unregistered);
		
		/*
		 * Acts as the main "hub" for a user's actions when interacting
		 * with the Party object declared earlier
		 */
		while(true) // Inspired by Arduino to have an endless loop of menus so that user can simply close when finished
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
				catch (InputMismatchException e) {} // Challenge: Decided to keep input as 0 if user input is invalid
					
				System.out.println("------");	
				if (input < 1 || input > 4)
				{
					System.out.println("Invalid input.");
					System.out.println("------");
					scanInput.nextLine();
				}
				
				if (input == 1) ihrpsConference.registerGuest(table,unregistered,companies);
				else if (input == 2) ihrpsConference.rosterByTable(table);
				else if (input == 3) ihrpsConference.rosterByCompany(table,companies,unregistered);
				else if (input == 4) ihrpsConference.searchGuest(table,unregistered);
			}
			while (input < 1 || input > 4);
		}
	}
}
