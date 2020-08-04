
public class TrainCar {

	private double carLength;
	private double carWeight;
	private ProductLoad load;
	
	public TrainCar()
	{
		this.carLength = 0.0;
		this.carWeight = 0.0;
		this.load = null;
	}
	
	public TrainCar(double carLength, double carWeight) throws IllegalArgumentException
	{
		if(carWeight < 0.0)
		{
			throw new IllegalArgumentException ("This TrainCar could not be created due to an invalid weight.");
		}
		if(carLength < 0.0)
		{
			throw new IllegalArgumentException ("This TrainCar could not be created due to an invalid length.");
		}
		this.carLength = carLength;
		this.carWeight = carWeight;
	}
	
//	public void setcarLength(double carLength) throws IllegalArgumentException
//	{
//		if(carLength < 0.0)
//		{
//			throw new IllegalArgumentException ("This is an invalid carLength.");
//		}
//		this.carLength = carLength;
//	}
	
//	public void setcarWeight(double carWeight) throws IllegalArgumentException
//	{
//		if(carWeight < 0.0)
//		{
//			throw new IllegalArgumentException ("This is an invalid carWeight.");
//		}
//		this.carWeight = carWeight;
//	}
	
	public void setLoad(ProductLoad load)
	{
		this.load = load;
	}
	
	public double getCarLength() 
	{
	//	if(carLength == 0.0)
	//	{
	//		throw new NullPointerException ("This is an invalid length.");
	//	}
		return this.carLength;
	}
	
	public double getCarWeight()
	{
		return this.carWeight;
	}
	
	public ProductLoad getLoad()
	{
		return this.load;
	}
	
	public boolean isEmpty()
	{
		if(this.load == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
