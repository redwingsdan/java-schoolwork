import java.util.LinkedList;


public class Project2 {

	final static int N_SLOTS = 5000;		//UNITS OF TIME USED FOR EACH PROBABILITY
	private static LinkedList<Packet> data = new LinkedList<Packet>();	//DATA STRUCTURE TO STORE PACKETS LINKED LIST
	
	//ADDS PACKET TO LIST
	public static void enqueue(Packet p)
	{
		data.add(p);
	}
	
	//REMOVES THE PACKET IN THE LIST WHICH WAS ADDED AT THE EARLIEST TIME
	public static Packet dequeue()
	{
		return data.remove();
	}
	
	//RETURNS TRUE IF NOTHING IS CURRENTLY STORED IN THE LIST
	public static boolean isEmpty()
	{
		return data.isEmpty();
	}
	
	//GENERATES A RANDOM INTEGER NUMBER BETWEEN THE MINVAL AND MAXVAL-1
	private static int randInt(int minVal, int maxVal) {
		int random;
		random = (int)(Math.random()*(maxVal-minVal) + minVal);
		return random;
	}
	
	//RETURNS TRUE IF THE RANDOM NUMBER IS LESS THAN 40 (0-49) SO 80% CHANCE
	private static boolean checkDeparture()
	{
		int r = randInt(0,50);
		if(r < (40))
		{
			return true;
		}
		return false;
	}
	
	//RETURNS TRUE IF THE RANDOM NUMBER IS LESS THAN THE CURRENT PROBABILITY *50. PROBABILITY IS P
	private static boolean checkArrival(double p)
	{
		int r = randInt(0,50);
		if(r < (p*50))
		{
			return true;
		}
		return false;
	}
	
	//RETURNS THE DIFFERENCE BETWEEN THE TIME THE PACKET IS SENT FROM THE QUEUE AND THE TIME THE PACKET ARRIVED AT THE QUEUE
	private static int getPacketServiceTime(Packet p1, int n)
	{
		p1.setTimeToDest(n);
		int t = (p1.getTimeToDest()-p1.getTimeArrive());
		return t;
	}
	
	//REMOVES A PACKET FROM THE QUEUE AND RETURNS IT
	private static Packet getPacket()
	{
		return dequeue();
	}
	
	public static void main(String[] args)
	{
		int totalTime = 0;				//TOTAL DELAY TIME FOR ALL PACKETS
		int totalServiced = 0;			//TOTAL NUMBER OF PACKETS THAT ARE SERVICED
		double throughput = 0.00;		//AVERAGE THROUGHPUT FOR PACKETS (SHOULD MAX AT THE PROBABILITY THAT PACKETS DEPART OR 0.8)
		double delay = 0.00;			//AVERAGE TIME THE PACKETS SPENT IN THE QUEUE WAITING TO BE PROCESSED
		int x = 0;		//USED TO COUNT FOR THE ARRAY
		double[][] arr = new double[50][2];	//DATA ARRAY TO STORE THE THROUGHPUT AND DELAY VALUES FOR EACH PROBABILITY
		
		//ALL PROBABILITIES TESTED WITH 0.02 INCREMENT
		for(double p = 0.02; p <= 1.00; p=p+0.02)
		{
			p = (int)(p * 100 + 0.5)/100.0;		//FORMAT PROBABILITY
			totalServiced = 0;		//RESET FOR NEW PROB
			totalTime = 0;			//RESET FOR NEW PROB
			data.clear();			//EMPTY RESIDUAL PACKETS FROM THE LIST
			
			//RUNS FOR N_SLOTS TIME UNITS FOR THE GIVEN PROBABILITY VALUE
			for(int n = 1; n <= N_SLOTS; n++)
			{
				//System.out.println(n);
				
				//IF SOMETHING IS IN THE QUEUE AND A PACKET IS LEAVING
				if(Project2.checkDeparture() & !(Project2.isEmpty()))
				{
					Packet p1 = Project2.getPacket();	//PACKET THAT LEFT
					totalServiced++;		//PACKET LEFT SO INCREMENT
					totalTime += Project2.getPacketServiceTime(p1, n);	//GET THE SERVICE TIME FROM THE PACKET THAT LEFT
					//System.out.println("PACKETS SERVED = " + totalServiced);
					//System.out.println("TIME SPENT IN QUEUE = " + totalTime);
				}
				
				//IF A PACKET IS ARRIVING AT THIS TIME
				if(Project2.checkArrival(p))
				{
					Packet pack = new Packet();		//PACKET THAT ARRIVES
					pack.setTimeArrive(n);			//SETS THE ARRIVAL TIME OF THIS PACKET
					Project2.enqueue(pack);			//ADDS PACKET TO THE QUEUE
					//System.out.println("Packet arrived!");
				}
			}
			throughput = ((double)totalServiced / (double)N_SLOTS);		//FORMATTING AND CALCULATING THROUGHPUT
			arr[x][0] = throughput;		//ADD TO ARRAY
			//System.out.println("THROUGHPUT = " + throughput);
			
			//IF NOTHING WAS SERVICED OR ARRIVED
			if(totalServiced == 0)
			{
				delay = 0.00;
				arr[x][1] = delay;		//ADD TO ARRAY
			}
			
			//SOMETHING WAS PROCESSED
			else
			{
				delay = ((double)totalTime / (double)totalServiced);	//CALCULATING DELAY
				delay = (int)(delay * 1000 + 0.5)/1000.0;	//FORMATTING DELAY
				arr[x][1] = delay;		//ADD TO ARRAY
			}
			//System.out.println("DELAY = " + delay);
			x++;		//INCREMENT COUNTER
		}
		double z = 0.02;	//VARIABLE USED FOR PRINTING PROBABILITY
		System.out.println("\tTHROUGHPUT\tDELAY");
		System.out.println();
		System.out.println();
		
		//EACH PROBABILITY VALUE
		for(int k = 0; k < 50; k++) {
			
			//0 IS THROUGHPUT AND 1 IS DELAY
			for(int j = 0; j <= 1; j++) {
				System.out.print("Row " + (k+1) + "\t " + arr[k][j] + "\t");
			}
			System.out.print("\tProb = " + z);
			System.out.println();
			z+= 0.02;
			z = (int)(z * 100 + 0.5)/100.0;
		}
	}
}