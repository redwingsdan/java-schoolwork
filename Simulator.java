import java.util.Collection;
import java.util.LinkedList;

public class Simulator {
	
	public static final int MAX_PACKETS = 3;
	Router dispatcher = new Router();
	Collection<Router> routers = new LinkedList<Router>();
	int totalServiceTime = 0;
	int totalPacketsArrived = 0;
	int packetsDropped = 0;
	double arrivalProb = 0.0;
	int maxBufferSize = 0;
	int minPacketSize = 0;
	int maxPacketSize = 0;
	int bandwidth = 0;
	int duration = 0;
	
	public double simulate()
	{
		
	}
	
	private int randInt(int minVal, int maxVal)
	{
		int random;
//		for (int i = 0; i < MAX_PACKETS; i++)
//		{
		random = (int)(Math.random()*(maxVal-minVal) + minVal);
		return random;
	}
	public static void main(String [] args)
	{		
		
		
	}
}
