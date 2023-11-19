import java.util.ArrayList;

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
	
	public void registerGuest(ArrayList<Person>[] tables)
	{
		System.out.println("
	}
}
