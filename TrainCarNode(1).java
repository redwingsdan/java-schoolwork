
public class TrainCarNode {

	private TrainCarNode nextNode;
	private TrainCarNode prevNode;
	private TrainCar car;
	
	public TrainCarNode()
	{
		nextNode = null;
		prevNode = null;
		car = null;
	}
	
	public TrainCarNode(TrainCar car)
	{
		this.car = car;
	}
	
	public void setNextNode(TrainCarNode nextNode)
	{
		this.nextNode = nextNode;
	}
	
	public void setPrevNode(TrainCarNode prevNode)
	{
		this.prevNode = prevNode;
	}
	
	public void setCar(TrainCar car)
	{
		this.car = car;
	}
	
	public TrainCarNode getNextNode()
	{
		return this.nextNode;
	}
	
	public TrainCarNode getPrevNode()
	{
		return this.prevNode;
	}
	
	public TrainCar getCar()
	{
		return this.car;
	}
	
	public String toString()
	{
		String msg = "Num \tLength (m) \tWeight (t) \t|\tName \tWeight (t) \tValue ($) \tDangerous\n============================================================================\n";
		int i = 1; 
		if(car.isEmpty() == false)
		{
		//msg = msg + String.format("%3s%4s%6s", i, car.getCarLength(), car.getCarWeight());
		//msg = msg + "\n";
			msg = "TrainCar #" + i;
	//	i++;
		}
		return msg;
	}
	//INCOMPLETE METHOD
	
}