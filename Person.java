/**
 * Person.java
 * Author: Oliver Szabo
 * Date: November 27, 2023, 11:59 PM
 * Purpose: A class to store data about the attendees, and their properties
 * with regards to the party.
 */

/*
 * Purpose: A class to store data about the attendees, and their properties
 * with regards to the party.
 */
public class Person 
{
    private int id;
    private String firstName;
    private String lastName;
    private int companyNumber;
    private int tableNumber;
  
	/*
	 * Simple constructor that initially sets a Person's table number to
	 * 0 (unregistered)
	 * Returns: Person (creates a new person)
	 * Args: int initId, String initFirstName, String initLastName, 
	 *       int initCompanyNumber (initial variable values to assign)
	 */
    public Person (int initId, String initFirstName, String initLastName, int initCompanyNumber) 
    {
      id = initId;
      firstName = initFirstName;
      lastName = initLastName;
      companyNumber = initCompanyNumber;
      tableNumber = 0;
    }
	
	/*
	 * Simple getter for a Person's id
	 * Args: None (none needed)
	 * Returns: int (returns the int value of a person's id)
	 */
    public int getId()
    {
      return id;
    }

	/*
	 * Simple getter for a Person's firstName
	 * Args: None (none needed)
	 * Returns: String (returns the String value of a person's firstName)
	 */
    public String getFirstName()
    {
      return firstName;
    }

	/*
	 * Simple getter for a Person's lastName
	 * Args: None (none needed)
	 * Returns: String (returns the String value of a person's lastName)
	 */
    public String getLastName()
    {
      return lastName;
    }

	/*
	 * Simple getter for a Person's companyNumber
	 * Args: None (none needed)
	 * Returns: int (returns the int value of a person's companyNumber)
	 */
    public int getCompanyNumber()
    {
      return companyNumber;
    }

	/*
	 * Simple getter for a Person's tableNumber
	 * Args: None (none needed)
	 * Returns: int (returns the int value of a person's tableNumber)
	 */
    public int getTableNumber()
    {
      return tableNumber;
    }

	/*
	 * Simple setter for a Person's tableNumber
	 * Args: int value (the new tableNumber value)
	 * Returns: void
	 */
    public void setTableNumber(int value)
    {
      tableNumber = value;
    }
  
	/*
	 * Method that returns a string that can be used with System.out.println()
	 * to quickly display the details of a Person
	 * Args: None (none needed)
	 * Returns: String (to be used with System.out.println)
	 */
    public String toString()
    {
      String result = "";
      result += "ID: " + id + "\n";
      result += "Name: " + firstName + " " + lastName + "\n";
      result += "Company Number: " + companyNumber + "\n";
      result += "Table Number: " + tableNumber + "\n";
      return result;
    }
}
