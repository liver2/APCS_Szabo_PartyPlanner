public class Person 
{
  private int id;
  private String firstName;
  private String lastName;
  private int companyNumber;
  private int tableNumber;

  public Person (int initId, String initFirstName, String initLastName, int initCompanyNumber) 
  {
    id = initId;
    firstName = initFirstName;
    lastName = initLastName;
    companyNumber = initCompanyNumber;
    tableNumber = 0;
  }

  public int getId()
  {
    return id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public int getCompanyNumber()
  {
    return companyNumber;
  }

  public int getTableNumber()
  {
    return tableNumber;
  }

  public void setTableNumber(int value)
  {
    tableNumber = value;
  }

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
