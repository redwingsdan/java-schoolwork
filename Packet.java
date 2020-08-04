
public class Packet {

	private static int packetCount = 0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;
	
	public Packet()
	{
		packetCount = packetCount + 1;
		id = packetCount;
		packetSize = size();
		timeArrive = 0;
		timeToDest = (int)(packetSize/100);
	}
	
	public void setPacketCount(int packetCount)
	{
		this.packetCount = packetCount;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setPacketSize(int packetSize)
	{
		this.packetSize = packetSize;
	}
	
	public void setTimeArrive(int timeArrive)
	{
		this.timeArrive = timeArrive;
	}
	
	public void setTimeToDest(int timeToDest)
	{
		this.timeToDest = timeToDest;
	}
	
	public int getPacketCount()
	{
		return packetCount;
	}
	
	public int getPacketID()
	{
		return id;
	}
	
	public int getPacketSize()
	{
		return packetSize;
	}
	
	public int getTimeArrive()
	{
		return timeArrive;
	}
	
	public int getTimeToDest()
	{
		return timeToDest;
	}
	
	public int size()
	{
		int size;
		size = (int)(Math.random() * 10000);
		return size;
	}
	
	public String toString()
	{
		String msg = "";
		//msg = "Packet #" + id + " arrive at simulation unit " + timeArrive + " with packet size " + timeToDest;
		msg = "[" + id + ", " + timeArrive + ", " + timeToDest + "]";
		return msg;
	}
}
