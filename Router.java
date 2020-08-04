import java.util.Collection;
import java.util.Queue;
import java.util.LinkedList;

public abstract class Router implements Queue{
		
		//private Queue<Packet> data = new Queue<Packet>();
		//private Collection<Packet> data = new Queue<Packet>();
		private Queue<Packet> data = new LinkedList<Packet>();
		public Router()
		{
			//private Queue<Packet> data = new LinkedList<Packet>();
		}
		
		public void enqueue(Packet p)
		{
			data.add(p);
		}
		
		public Packet dequeue()
		{
			return data.remove();
		}
		
		public Packet peek()
		{
			return data.peek();
		}
		
		public int size()
		{
			return data.size();
		}
		
		public boolean isEmpty()
		{
			return data.isEmpty();
		}
		
		public String toString()
		{
			String msg = "";
			
			return msg;
		}
		
		public static int sendPacketTo(Collection routers)
		{
			int size = 0;
			int oldsize = 0;
			int num = -1;
			for(int i = 0; i < routers.size(); i++)
			{
				size = routers[i].size();
				if(oldsize < size)
				{
					num = i;
				}
			}
			return num;
		}

}
