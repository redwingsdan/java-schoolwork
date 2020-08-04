import java.util.Scanner;


public class TrainManager {

	public static void main(String[] args)
	{
		TrainLinkedList t1 = new TrainLinkedList();
		Scanner stdin = new Scanner(System.in);
		Scanner stdin1 = new Scanner(System.in);
		Scanner stdin2 = new Scanner(System.in);
		boolean flag = false;
		boolean planflag = false;
		
		while(flag == false)
		{
			System.out.println("(F) Move Cursor Forward\n(B) Move Cursor Backward\n(I) Insert Car After Cursor\n(R) Remove Car At Cursor\n(L) Set Load At Cursor\n(S) Search For Product\n(T) Print Train\n(M) Print Manifest\n(D) Remove Dangerous Cars\n(Q) Quit");
			System.out.print("Enter a selection: ");
			String l = stdin.next();
			switch(l.toLowerCase())
			{
			case "f":
				try
				{
				t1.cursorForward();
				System.out.println("Cursor moved forward.");
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
					System.out.println();
					break;
				}
				break;
				
			case "b":
				try
				{
				t1.cursorBackward();
				System.out.println("Cursor moved backward.");
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
					System.out.println();
					break;
				}
				break;		
				
			case "i":

				System.out.println("Enter car length in meters: ");
				while(!stdin1.hasNextDouble())
				{
					System.out.println("Invalid input, try again.");
					System.out.print("Enter car length in meters: ");
					stdin1.nextLine();
				}
				double length = stdin1.nextDouble();
				
				System.out.println("Enter car weight in tons: ");
				while(!stdin1.hasNextDouble())
				{
					System.out.println("Invalid input, try again.");
					System.out.print("Enter car weight in tons: ");
					stdin1.nextLine();
				}
				double weight = stdin1.nextDouble();
				
				TrainCar newCar = new TrainCar(length, weight);
				t1.insertAfterCursor(newCar);
				ProductLoad l1 = new ProductLoad("Empty", 0, 0.0, false);
				newCar.setLoad(l1);
				System.out.println("New train car " + newCar.getCarLength() + " meters " + newCar.getCarWeight() + " tons inserted into train.");
				if(t1.size() != 1)
				{
					t1.cursorForward();
				}
				break;
				
			case "r":
				try
				{
					TrainCar removed = t1.removeCursor();
					System.out.println("Car successfully unlinked. The following load has been removed from the train:\n");
					System.out.println("Name\tWeight (t)\tValue ($)\tDangerous\n===========================================");				
					System.out.println(t1.getCursorData().getLoad().getName() + "\t" + t1.getCursorData().getLoad().getWeight() + "\t" + t1.getCursorData().getLoad().getValue() + "\t" + t1.getCursorData().getLoad().getisDangerous());
				//	TrainCar removed = t1.removeCursor();
				}
				catch(NullPointerException e)
				{
					System.out.println("No car exists at this location.");
					break;
				}
				break;
				
			case "l":
				
				System.out.println("Enter product name: ");
				while(!stdin.hasNext())
				{
					System.out.println("Invalid input, try again.");
					System.out.print("Enter product name: ");
					stdin.nextLine();
				}
				String name1 = stdin.next();
				System.out.println("Enter product weight in tons: ");
				while(!stdin1.hasNextDouble())
				{
					System.out.println("Invalid input, try again.");
					System.out.print("Enter product weight in tons: ");
					stdin1.nextLine();
				}
				double weight1 = stdin1.nextDouble();
				System.out.println("Enter product value in dollars: ");
				while(!stdin1.hasNextDouble())
				{
					System.out.println("Invalid input, try again.");
					System.out.print("Enter product value in dollars: ");
					stdin1.nextLine();
				}
				double value1 = stdin1.nextDouble();
				System.out.println("Enter is product dangerous? (y/n): ");
				boolean inputflag = false;
				boolean danger = false;
				while (inputflag == false)
				{
					String input = stdin2.nextLine().toLowerCase();
					if(input.equals("y"))
					{
						danger = true;
						inputflag = true;
					}
					else if(input.equals("n"))
					{
						danger = false;
						inputflag = true;
					}
					else
					{
						System.out.println("Invalid input, try again.");
						System.out.print("Enter is product dangerous? (y/n): ");
						stdin2.nextLine();
					}
				}
				try
				{
					ProductLoad p2 = new ProductLoad(name1, weight1, value1, danger);
					TrainCar testCar = t1.getCursorData();
					testCar.setLoad(p2);
					System.out.println(testCar.getLoad().getWeight() + " tons of " + testCar.getLoad().getName() + " added to the current car.");
				}
				catch(NullPointerException e)
				{
					System.out.println("No car exists here.");
				}
				break;
	
			case "s":
				String name3 = null;
				String name2 = null;
				boolean dangerflag1 = false;
				double weight4 = 0.0;
				double value4 = 0.0;
				int count = 0;
				t1.setCursor(t1.getHead());
				try
				{
				name3 = t1.getCursorData().getLoad().getName();	
				}
				catch(NullPointerException e)
				{
					System.out.println("No cars are present to search from.");
					break;
				}
				System.out.print("Enter product name: ");
				while(t1.getCursor() != t1.getTail())
				{
					String temp = stdin.nextLine();
					name2 = stdin.nextLine();
					if(name3.equals(name2))
					{
						count++;
						weight4 = weight4 + t1.getCursorData().getLoad().getWeight();
						value4 = value4 + t1.getValue();
						
						if(t1.isDangerous() == true)
						{
							dangerflag1 = true;
						}				
					}
					t1.cursorForward();
				}
					if(name3.equals(name2))
					{
						count++;
						weight4 = weight4 + t1.getCursorData().getLoad().getWeight();
						value4 = value4 + t1.getValue();
						
						if(t1.isDangerous() == true)
						{
							dangerflag1 = true;
						}	
						
						System.out.print("The following products were found on " + count + " cars: \n" + "\tName\tWeight (t)\tValue ($)\tDangerous\n================================================\n");
						System.out.print("\t" + name3 + "\t" + weight4 + "\t" + value4 + "\t");
						if(dangerflag1 == true)
						{
							System.out.println("Yes");
						}
						else
						{
							System.out.println("No");
						}
					}
					else
					{
						System.out.println("No record of " + name2 + " on board train.");
					}
				break;
				
			case "t":
			//	double tempweight = t1.getCursorData().getCarWeight();
				boolean dangerflag = false;
				TrainCarNode temp = new TrainCarNode();
				try
				{
					temp = t1.getCursor();
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
				}
				//System.out.println(tempweight);
				//System.out.print("Train: " + t1.size() + " cars, " + t1.getLength() + " meters, " + t1.getWeight() + " tons, $" + t1.getValue() + " value, ");
				double length3 = 0.0;
				double weight3 = 0.0;
				double value3 = 0.0;
				t1.setCursor(t1.getHead());
				try
				{
					while(t1.getCursor() != t1.getTail())
					{
						length3 = length3 + t1.getLength();
						weight3 = weight3 + t1.getWeight();
						value3 = value3 + t1.getValue();
						
						if(t1.isDangerous() == true)
						{
							dangerflag = true;
						}					
						t1.cursorForward();
					}
				}
				catch(NullPointerException e)
				{
				//	System.out.println(e.getMessage());
				}
				try
				{
					length3 = length3 + t1.getLength();
					weight3 = weight3 + t1.getWeight();
					value3 = value3 + t1.getValue();
					if(t1.isDangerous() == true)
					{
						dangerflag = true;
					}
					System.out.print("Train: " + t1.size() + " cars, " + length3 + " meters, " + weight3 + " tons, $" + value3 + " value, ");
					if(dangerflag == true)
					{
						System.out.println(" dangerous.");
					}
					else
					{
						System.out.println(" not dangerous.");
					}
				}
				catch(NullPointerException e)
				{
					//System.out.println(e.getMessage());
				}
				t1.setCursor(temp);
				break;
			case "m":
				t1.printManifest();
				break;
				
			case "d":
				TrainCarNode temp1 = new TrainCarNode();
				try
				{
					temp1 = t1.getCursor();
					System.out.println("Dangerous cars successfully removed from the train.");
					t1.removeDangerousCars();
				}
				catch(NullPointerException e)
				{
				//	System.out.print(e.getMessage());
				}
				t1.setCursor(temp1);
				break;

			case "q":		
				flag = true;
				break;
				
			default:
				System.out.println("Please enter a valid value.\n\n");
			}
			try
			{
			System.out.println(t1.getCursorData().toString());
			}
			catch(NullPointerException e)
			{
	//			System.out.print(e.getMessage());
				try
				{
				t1.resetCursor();
				}
				catch(NullPointerException e1)
				{

				}
				try
				{
					if(t1.getCursor().getNextNode() == null)
					{
						t1.getCursor().setNextNode(t1.getTail());
					}
				}
				catch(NullPointerException e1)
				{
				//	System.out.print(e1.getMessage());
					try
					{
					t1.resetCursor();
					}
					catch(NullPointerException e2)
					{
					//	System.out.print(e2.getMessage());
					}
				}
				try
				{
					if(t1.getCursor().getPrevNode() == null)
					{
						t1.getCursor().setPrevNode(t1.getHead());
					}
				}
				catch(NullPointerException e1)
				{
					//System.out.print(e1.getMessage());
					try
					{
					t1.resetCursor();
					}
					catch(NullPointerException e2)
					{
					//	System.out.print(e2.getMessage());
					}
				}
				try
				{
				t1.resetCursor();
				}
				catch(NullPointerException e1)
				{
				//	System.out.println(e1.getMessage());
				}
			}
		}
		System.out.println("Program terminating successfully...");
		stdin.close();
		stdin1.close();
	}
}
