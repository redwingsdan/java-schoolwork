
public class ProductLoad {

	private String name;
	private double weight;
	private double value;
	private boolean isDangerous;
	
	public ProductLoad()
	{
		this.name = null;
		this.weight = 0.0;
		this.value = 0.0;
		this.isDangerous = false;
	}
	
	public ProductLoad(String name, double weight, double value, boolean isDangerous) throws IllegalArgumentException
	{
		if(weight < 0.0)
		{
			throw new IllegalArgumentException ("This ProductLoad could not be created due to an invalid weight.");
		}
		if(value < 0.0)
		{
			throw new IllegalArgumentException ("This ProductLoad could not be created due to an invalid dollar value.");
		}
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isDangerous = isDangerous;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setWeight(double weight) throws IllegalArgumentException
	{
		if(weight < 0.0)
		{
			throw new IllegalArgumentException ("This is an invalid weight.");
		}
		this.weight = weight;
	}
	
	public void setValue(double value) throws IllegalArgumentException
	{
		if(value < 0.0)
		{
			throw new IllegalArgumentException ("This is an invalid dollar value.");
		}
		this.value = value;
	}
	
	public void setisDangerous(boolean isDangerous)
	{
		this.isDangerous = isDangerous;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getWeight()
	{
		return this.weight;
	}

	public double getValue()
	{
		return this.value;
	}

	public boolean getisDangerous()
	{
		return this.isDangerous;
	}
	
}
