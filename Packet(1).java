public class Packet {

	private int timeArrive;
	private int timeToDest;
	
	public Packet()
	{
		timeArrive = 0;
	}
			
	public void setTimeArrive(int timeArrive)
	{
		this.timeArrive = timeArrive;
	}
	
	public void setTimeToDest(int timeToDest)
	{
		this.timeToDest = timeToDest;
	}
	
	public int getTimeArrive()
	{
		return timeArrive;
	}
	
	public int getTimeToDest()
	{
		return timeToDest;
	}

}
