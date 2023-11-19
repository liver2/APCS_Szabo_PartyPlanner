public class Company
{
	private int number;
    private String name;
    private int registrees;

    public Company(int initNumber, String initName)
    {
      number = initNumber;
      name = initName;
      registrees = 0;
    }

    public int getNumber()
    {
      return number;
    }

    public String getName()
    {
      return name;
    }

    public int getRegistreeCount()
    {
      return registrees;
    }

    public String toString()
    {
      return number + " - " + name;
    }
}
