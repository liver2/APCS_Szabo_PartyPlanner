/**
 * Party.java
 * Author: Oliver Szabo
 * Date: November 27, 2023, 11:59 PM
 * Purpose: Class that outlines the guidelines for a party, arranges employees
 * at tables, and runs methods that allow user interaction with the party, 
 * its attendees, and its properties.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Party
{
	private int maxPpl; // The maximum people a Party object will allow
	private int tables; // The number of tables at a Party
	private int pplPerTable; // The number of people per table, as calculated from above values
	
	/*
	 * Constructor that calculates the number of people per table at a Party
	 */
	public Party(int initMaxPpl, int initTables)
	{
		maxPpl = initMaxPpl;
		tables = initTables;
		pplPerTable = maxPpl/tables;
	}
	
	/*
	 * Getter that returns the maximum number of people a Party allows
	 */
	public int getMaxPpl()
	{
		return maxPpl;
	}
	
	/*
	 * Getter that returns the number of tables at a Party
	 */
	public int getTables()
	{
		return tables;
	}
	
	/*
	 * Getter that returns the number of people per table, as calculated from above values
	 */
	public int getPplPerTable()
	{
		return pplPerTable;
	}
	
	/*
	 * Method that automatically arranges guests at tables based on
	 * established guidelines (10 people per table, no more than 1 
	 * representative from each company per table, 100 max)
	 */
	public void arrangeEmployeesAtTables(ArrayList<Person>[] tables, ArrayList<Person> unregistered)
	{	
		for (Person p : unregistered)
		{
			for (int i = 0; i < this.tables; i++)
			{
				if (checkTable(tables[i],p.getCompanyNumber()))
				{
					tables[i].add(p);
					p.setTableNumber(i+1);
					break;
				}
			}
		}
	}
	
	/*
	 * Prints an error message for the user that notifies them of unregistered
	 * guests
	 */
	public void unregisteredError(ArrayList<Person> unregistered)
	{
		System.out.println("------");
		System.out.println("The following guests were unable to be placed at a table:\n");
		for (Person p : unregistered)
		{
			if (p.getTableNumber() == 0) System.out.println(p.toString());
		}
		System.out.println("------");
	}	
	
	/*
	 * Returns true if a guest with company number coNumber can be
	 * placed at a certain table specified in the arguments.
	 */
	public boolean checkTable(ArrayList<Person> table, int coNumber)
	{
		if (table.size() == 10) return false;
		for (Person p : table)
		{
			if (p.getCompanyNumber() == coNumber)
			{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks that the number of people attending the Party does
	 * not exceed the limits set by the user
	 */
	public boolean checkAttendeeNum(ArrayList<Person>[] tables)
	{
		int pplCount = 0;
		for (int i = 0; i < this.tables; i++)
		{
			pplCount += tables[i].size();
		}
		
		if (pplCount > this.maxPpl) return false;
		else return true; 
	}

	/*
	 * Returns 1 more than the highest ID any person has so that new guests
	 * can be registered with a new ID
	 */
	public int maxId(ArrayList<Person>[] tables, ArrayList<Person> unregistered)
	{
		int max = 0;
		for (int i = 0; i < this.tables; i++) 
		{
			for (Person p : tables[i]) 
			{
				if (p.getId() > max) max = p.getId();
			}
		}
		
		for (Person p : unregistered)
		{
			if (p.getId() > max) max = p.getId();
		}

		return max + 1;
	}
	
	/*
	 * Allows user to register a guest by prompting them for details,
	 * scanning the inputs to prompts and finding a table for the given
	 * guest according to the inputs of the user and the guidelines
	 * of the Party
	 */
	public void registerGuest(ArrayList<Person>[] tables, ArrayList<Person> unregistered, ArrayList<Company> companies)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------");
		System.out.println("Please enter the first name of the guest.");
		String f = scan.nextLine();
		System.out.println("Please enter the last name of the guest.");
		String l = scan.nextLine();
		int input;
		do			
		{					
			System.out.println("Please enter the number associated with this person's company:");
			System.out.println(companies);
			
			input = 0;				
			try
			{
				input = scan.nextInt();
			}
			catch (InputMismatchException e) {}
				
			System.out.println("------");	
			
			if (input < 1 || input > companies.size())
			{
				System.out.println("Invalid input.");
				System.out.println("------");
				scan.nextLine();
			}
		}
		while (input < 1 || input > companies.size());
		
		int table = 0;
		
		for (int i = 0; i < this.tables; i++)
		{
			if (!checkAttendeeNum(tables)) break;
			if (checkTable(tables[i],input)) table = i;
		}
		
		Person registered = new Person(maxId(tables,unregistered),f,l,input);
		registered.setTableNumber(table+1);
		
		tables[table].add(registered);
		
		if (table != 0) System.out.println("This guest has been registered at table " + (table+1) + ".");
		else System.out.println("We could not register this guest.");
	}
	
	/*
	 * Prints a roster by the table number, detailing the name, ID, 
	 * company No. of each person sitting at a table specified by the
	 * user
	 */
	public void rosterByTable(ArrayList<Person>[] tables)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------");
		int input;
		do
		{
			System.out.println("Please enter the table number you would like to look up.");
			input = 0;				
			try
			{
				input = scan.nextInt();
			}
			catch (InputMismatchException e) {}
				
			System.out.println("------");	
			
			if (input < 1 || input > this.tables)
			{
				System.out.println("Invalid input.");
				System.out.println("------");
				scan.nextLine();
			}
		} 
		while (input < 1 || input > this.tables);
		
		System.out.println("Here is the roster for table " + input + ".");
		
		for(Person p : tables[input-1])
		{
			System.out.println(p.toString());
		}
	}
	
	/*
	 * Prints a roster of guests by company number, detailing their
	 * table number, name, ID, etc.
	 */
	public void rosterByCompany(ArrayList<Person>[] tables, ArrayList<Company> companies, ArrayList<Person> unregistered)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------");
		int input;
		do
		{
			System.out.println("Please enter the company number you would like to look up.");
			System.out.println(companies);
			input = 0;				
			try
			{
				input = scan.nextInt();
			}
			catch (InputMismatchException e) {}
				
			System.out.println("------");	
			
			if (input < 1 || input > companies.size())
			{
				System.out.println("Invalid input.");
				System.out.println("------");
				scan.nextLine();
			}
		} 
		while (input < 1 || input > companies.size());
		
		for (Person p : unregistered)
		{
			if (p.getCompanyNumber() == input && p.getTableNumber() == 0) System.out.println(p.toString());
		}
		
		for (int i = 0; i < this.tables; i++)
		{
			for (Person p : tables[i])
			{
				if (p.getCompanyNumber() == input) System.out.println(p.toString());
			}
		}
		System.out.println("------");
	} 
	
	/*
	 * Prompts user for name to search for a specific guest in the database
	 */
	public void searchGuest(ArrayList<Person>[] tables, ArrayList<Person> unregistered)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the first name of the guest you would like to search for.");
		String f = scan.nextLine();
		System.out.println("Please enter the last name of the guest you would like to search for.");
		String s = scan.nextLine();
		System.out.println("");
		boolean found = false;

		for (int i = 0; i < this.tables; i++)
		{
			for (Person p : tables[i])
			{
				if (f.equals(p.getFirstName()) && s.equals(p.getLastName())) 
				{
					System.out.println(p.toString());
					found = true;
				}
			}
		}

		for (Person p : unregistered) 
		{
			if (f.equals(p.getFirstName()) && s.equals(p.getLastName())) 
			{
				System.out.println(p.toString());
				found = true;
			}
		}

		if (found == false) System.out.println("Person not found.");
	}
}
