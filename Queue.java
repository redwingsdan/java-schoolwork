
public interface Queue {

	void enqueue(Packet item);
	
	Packet dequeue();
	
	Packet peek();
		
	int size();
	
	boolean isEmpty();
}
