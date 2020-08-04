
public class TrainLinkedList {

	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;
	
//	private TrainCarNode dummy;
//	private TrainCar testcar = new TrainCar(1, 1);
//	private ProductLoad testp = new ProductLoad("test", 1, 1, false);
//	abstract TrainLinkedList;
	private int items;
	
	public TrainLinkedList()
	{
	//	testcar.setLoad(testp);
//		dummy.setCar(testcar);
//		TrainCarNode dummy = new TrainCarNode();
//		TrainCar testcar = new TrainCar(1, 1);
//		ProductLoad testp = new ProductLoad("test", 1, 1, false);
//		testcar.setLoad(testp);
//		dummy.setNextNode(tail);
//		dummy.setPrevNode(head);
		
		head = null;
		tail = null;
		cursor = null;
//		head.setNextNode(dummy);
		items = 0;
	}
	
	public TrainCarNode getHead()
	{
		return head;
	}
	
	public TrainCarNode getTail()
	{
		return tail;
	}
	
	public TrainCarNode getCursor() throws NullPointerException
	{
		if(cursor == null)
		{
			throw new NullPointerException("No car exists.");
		}
		return cursor;
	}
	
	public void setHead(TrainCarNode node)
	{
		head = node;
	}
	
	public void setCursor(TrainCarNode node)
	{
		cursor = node;
	}
	
	public void setTail(TrainCarNode node)
	{
		tail = node;
	}
	
	public void resetCursor() throws NullPointerException
	{
		if(head == null)
		{
			throw new NullPointerException("");
		}
		cursor = head.getNextNode();	
	}
	
	public int getItems()
	{
		return this.items;
	}
	
	public TrainCar getCursorData() throws NullPointerException
	{
		if(cursor != null)
		{
			return cursor.getCar();
		}
		else
		{
			throw new NullPointerException("No car exists at this location.");
		}
	}
	
	public void setCursorData(TrainCar car)
	{
		if(cursor != null)
		{
			cursor.setCar(car);
		}
		else
		{
			throw new NullPointerException("Cursor is null, could not set data.");
		}
	}
	
	public void cursorForward() throws NullPointerException
	{
		if(cursor != tail)
		{
			cursor = cursor.getNextNode();
		}
		else
		{
			//cursor = cursor.getPrevNode();
			throw new NullPointerException("No next car, cannot move cursor forward.");
		}
	}
	
	public void cursorBackward() throws NullPointerException
	{
		if(cursor != head)
		{
			cursor = cursor.getPrevNode();
		}
		else
		{
			//cursor = cursor.getNextNode();
			throw new NullPointerException("No previous car, cannot move backward.");
		}
	}
	
	public void insertAfterCursor(TrainCar newCar)
	{
//		if(items == 0)
//		{
//			TrainCarNode c1 = new TrainCarNode(newCar);
//			head = c1;
//			tail = c1;
//			cursor = head;
//			head.setNextNode(c1);
//			tail.setPrevNode(c1);
//			c1.setPrevNode(head);
//			c1.setNextNode(tail);
//			cursor.setNextNode(c1);
//			cursor.setPrevNode(head);
//			c1.setNextNode(tail);
//			c1.setPrevNode(head);
//			head = c1;
//			tail = c1;
//			tail.setPrevNode(c1);
			//cursor.setNextNode(c1);
			//cursor.setPrevNode(head);
//			items++;
//		}
//		else
//		{
//			{
//				cursor = head;
//			}
//			TrainCarNode c2 = new TrainCarNode(newCar);
//			c2.setNextNode(cursor.getNextNode());
//			cursor.setNextNode(c2);
//			c2.setPrevNode(cursor);
//			if(cursor == head)
//			{
//				cursor.setPrevNode(head);
//				cursor = c2.getPrevNode();
//			}
//			cursor.setPrevNode(cursor.getPrevNode().getNextNode());
//			items++;
//		}
//		cursor = c1;
		
		TrainCarNode n1 = new TrainCarNode(newCar);
		if(head == null)
		{
			head = tail = cursor = n1;
		}
		else
		{
			if(cursor == tail)
			{
				cursor.setNextNode(n1);
				n1.setPrevNode(cursor);
				tail = n1;
			}
			else
			{
				n1.setNextNode(cursor.getNextNode());
				n1.setPrevNode(cursor);
				cursor.getNextNode().setPrevNode(n1);
				cursor.setNextNode(n1);
			}
		}
		items++;
	}
	
	public TrainCar removeCursor()
	{
	//	if(cursor != tail)
	//	{
			if(cursor.getPrevNode() != null)
			{
				cursor.getPrevNode().setNextNode(cursor.getNextNode());
			}
			if(cursor.getNextNode() != null)
			{
				cursor.getNextNode().setPrevNode(cursor.getPrevNode());
			}
			if(head.getNextNode() == cursor)
			{
				head.setNextNode(cursor.getNextNode());
			}
			if(tail.getPrevNode() == cursor)
			{
				tail.setPrevNode(cursor.getPrevNode());
			}
			resetCursor();
	//		TrainCarNode nodePtr;
	//		TrainCarNode prevPtr;
	//		nodePtr = head;
	//		prevPtr = nodePtr;
	//		nodePtr = nodePtr.getNextNode();
	//		prevPtr.setNextNode(nodePtr.getNextNode());
	//		cursor.setNextNode(cursor.getNextNode());
	//		cursorBackward();
			
	//		cursor.setNextNode(cursor.getNextNode());
	//		if(cursor.getNextNode() == null)
	//		{
	//			tail = cursor;
	//		}
			items--;
			return cursor.getCar();
	//	}
	//	else
	//	{
	//		throw new NullPointerException("Cursor is currently null. There is no Train Car to be removed.");
	//	}
	}
	
	public int size()
	{
		return getItems();
//		int size = 0;
//		TrainCarNode nodePtr = head;
//		while (nodePtr != null)
//		{
//			size++;
//			nodePtr = nodePtr.getNextNode();
//		}
//		return size;
	}
	
	public double getLength()
	{
		return cursor.getCar().getCarLength();
	}
	
	public double getValue()
	{
		return cursor.getCar().getLoad().getValue();
	}
	
	public double getWeight()
	{
		return (cursor.getCar().getCarWeight() + cursor.getCar().getLoad().getWeight());
	}
	
	public boolean isDangerous()
	{
		return cursor.getCar().getLoad().getisDangerous();
	}
	
	public void findProduct(String name)
	{
		double length = 0;
		double weight = 0;
		double value = 0;
		boolean danger = false;
		while(cursor != tail)
		{
			String test = cursor.getCar().getLoad().getName();		
			if(name.equals(test))
			{
				 length = length + getLength();
				 value = value + getValue();
				 weight = weight + getWeight();
				 danger = isDangerous();
			}
			cursorForward();
		}
		System.out.println("PRODUCT\tLENGTH\tWEIGHT\tVALUE\tDANGEROUS");
		System.out.println(name + "\t" + length + "\t" + weight + "\t" + value + "\t" + danger);
	}
	
	public void printManifest()
	{
		TrainCarNode temp = cursor;
		double length = 0;
		double weight1 = 0;
		double weight2 = 0;
		double value = 0;
		boolean danger = false;
		boolean endflag = false;
		String name = null;
		int i = 1;
		System.out.println("CAR:\t\t\t\t\tLOAD: ");
		System.out.print("Num\tLength (m)\tWeight (t)\t |\t");
		System.out.println("Name\tWeight (t)\tValue ($)\tDangerous");
		cursor = head;
		while(cursor != tail)
		{
	//		try
	//		{
			name = cursor.getCar().getLoad().getName();	
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("name error");
	//			System.out.println();
	//		}
	//		try
	//		{
			length = getLength();
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("length error");
	//			System.out.println();
	//		}
	//		try
	//		{
			value = getValue();
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("value error");
	//			System.out.println();
	//		}
	//		try
	//		{
		    weight1 = cursor.getCar().getCarWeight();
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("weight1 error");
	//			System.out.println();
	//		}
	//		try
	//		{
		    weight2 = cursor.getCar().getLoad().getWeight();
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("weight2 error");
	//			System.out.println();
	//		}
	//		try
	//		{
		    danger = isDangerous();	
	//		}
	//		catch(NullPointerException e)
	//		{
	//			System.out.println("danger error");
	//			System.out.println();
	//		}
			System.out.print(i + "\t" + length + "\t\t" + weight1 + "\t\t|\t");
			System.out.println(name + "\t" + weight2 + "\t" + value + "\t" + danger);
			try
			{
			cursorForward();
			}
			catch(NullPointerException e)
			{
				System.out.println("");
			}
			i++;
			if(size() == 1)
			{
				endflag = true;
				break;
			}
		}
		if(endflag == false)
		{
			try
			{
				name = cursor.getCar().getLoad().getName();	
				length = getLength();
				value = getValue();
				weight1 = cursor.getCar().getCarWeight();
				weight2 = cursor.getCar().getLoad().getWeight();
				danger = isDangerous();
				System.out.print(i + "\t" + length + "\t\t" + weight1 + "\t\t|\t");
				System.out.println(name + "\t" + weight2 + "\t" + value + "\t" + danger);
			}
			catch(NullPointerException e)
			{
				System.out.println("No car exists at this location.");
			}
		}
		cursor = temp;
	}
	
	public void removeDangerousCars()
	{
		cursor = head;
		while(cursor != tail)
		{
			if(isDangerous() == true)
			{
				removeCursor();
			}
			cursorForward();
		}
		if(isDangerous() == true)
		{
			removeCursor();
		}
	}
	
	public String toString()
	{
/*
		String msg = "PRODUCT\tLENGTH\tWEIGHT\tVALUE\tDANGEROUS\n";
		double length = 0;
		double weight = 0;
		double value = 0;
		boolean danger = false;
		String name = null;
		while(cursor != tail)
		{
			name = cursor.getCar().getLoad().getName();		
			length = getLength();
			value = getValue();
		    weight = getWeight();
			danger = isDangerous();			
			msg = msg + name + "\t" + length + "\t" + weight + "\t" + value + "\t" + danger + "\n";
			cursorForward();
		}
		return msg;
		*/
		String msg = "";
		while(cursor != tail)
		{
			msg = msg + cursor.toString();
			cursorForward();
		}
		return msg;
	}
}
