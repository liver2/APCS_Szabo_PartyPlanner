/*
 * Purpose: Class that outlines the properties of a company and regulates
 * the method by which the list of companies is printed.
 */

/**
 * Party.java
 * Author: Oliver Szabo
 * Date: November 27, 2023, 11:59 PM
 * Purpose: Class that outlines the properties of a company and regulates
 * the method by which the list of companies is printed.
 */
public class Company
{
	private int number; // A company's identifying number
    private String name; // A company's name

	/*
	 * Simple constructor
	 * Args: int initNumber, String initName (initializing a new Company object's values)
	 * Returns: Company (creates a new Company object)
	 */
    public Company(int initNumber, String initName)
    {
      number = initNumber;
      name = initName;
    }

	/*
	 * Simple getter to return the company's identifying "number"
	 * Args: None (none needed)
	 * Returns: int (returns int value of number)
	 */
    public int getNumber()
    {
      return number;
    }

	/*
	 * Simple getter to return the name of the company
	 * Args: None (none needed)
	 * Returns: String (returns String value of name)
	 */
    public String getName()
    {
      return name;
    }

	/*
	 * Describes format by which the list of companies will be printed 
	 * Args: None (none needed)
	 * Returns: String (to be used with System.out.println)
	 */
    public String toString()
    {
      return number + " - " + name;
    }
}
