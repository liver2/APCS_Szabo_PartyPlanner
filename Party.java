import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Party
{
	private int maxPpl;
	private int tables;
	private int pplPerTable;
	
	public Party(int initMaxPpl, int initTables)
	{
		maxPpl = initMaxPpl;
		tables = initTables;
		pplPerTable = maxPpl/tables;
	}
	
	public int getMaxPpl()
	{
		return maxPpl;
	}
	
	public int getTables()
	{
		return tables;
	}
	
	public int getPplPerTable()
	{
		return pplPerTable;
	}
	
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
	
	public boolean checkTable(ArrayList<Person> table, int employeeNumber)
	{
		if (table.size() == 10) return false;
		for (Person p : table)
		{
			if (p.getCompanyNumber() == employeeNumber)
			{
				return false;
			}
		}
		return true;
	}
	
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
	}
	
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
			if (checkAttendeeNum(tables)) break;
			if (checkTable(tables[i],input)) table = i;
		}
		
		tables[table-1].add(new Person(maxId(tables,unregistered),f,l,input));
		
		if (table != 0) System.out.println("This guest has been registered at table " + table + ".");
		else System.out.println("We could not register this guest.");
	}
	
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
			if (p.getCompanyNumber() == input) System.out.println(p.toString());
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
	
	public void searchGuest(ArrayList<Person>[] tables, ArrayList<Person> unregistered)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the first name of the guest you would like to search for.");
		String f = scan.nextLine();
		System.out.println("Please enter the last name of the guest you would like to search for.");
		String s = scan.nextLine();
		boolean found = false;

		for (int i = 0; i < this.tables; i++)
		{
			for (Person p : tables[i])
			{
				if (f.equals(p.getFirstName()) && s.equals(p.getLastName()) 
				{
					System.out.println(p.toString());
					found = true;
				}
			}
		}

		for (Person p : unregistered) 
		{
			if (f.equals(p.getFirstName()) && s.equals(p.getLastName()) 
			{
				System.out.println(p.toString());
				found = true;
		}

		if (found == false) System.out.println("Person not found.");
	}
}
