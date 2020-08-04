
public class CSE346 {

	private static int randInt(int minVal, int maxVal)
	{
		int random;
		random = (int)(Math.random()*(maxVal-minVal) + minVal);
		return random;
	}
	
	private static int packetArrival(double prob)
	{
		int r = 0;
		int count = 0;
		for(int i = 0; i < 10; i++)
		{
			r = randInt(0,50);
			if(r < (prob*50))
			{
				count++;
			}
		}
		return count;
	}
	
	public static void main(String [] args)
	{
		double p = 0.02;
		int total = 0;
		int avg = 0;
		int i = 0;
		double avgbusy = 0.0;
		double avgdrop = 0.0;
		double totaldrop = 0.0;
		double totalbusy = 0.0;
		int[][] arr = new int[50][2];
		while(p <= 1.0)
		{
			int num = 0;
			num = packetArrival(p);
			System.out.println("Probability = " + p + "\tPackets arrived = " + num);
			if(num > 3)
			{
				total += (num-3);
				System.out.println((num-3) + " packets dropped\n");
				arr[i][0] = 3;
				arr[i][1] = (num-3);
				i++;
			}
			else
			{
				arr[i][0] = num;
				arr[i][1] = 0;
				i++;
			}
			p += 0.02;
			p = (int)(p * 100 + 0.5)/100.0;
		}
		System.out.println("Total packets dropped = " + total);
		System.out.println();
		for(i = 0; i < 50; i++)
		{
			for(int j = 0; j <= 1; j++)
			{
				System.out.print("Row " + (i+1) + "\t " + arr[i][j] + "\t");
				totalbusy += arr[i][0];
				totaldrop += arr[i][1];
			}
			System.out.println();
		}
		avgdrop = totaldrop / 100.0;
		avgbusy = totalbusy / 100.0;
		System.out.println("BUSY = " + avgbusy);
		System.out.println("DROP = " + avgdrop);
	}
}
