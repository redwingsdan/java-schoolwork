public class Planner {
	
	private final int MAX_COURSES = 51;
	private CoursePlanner[] cArray;
	private int items;
	
	public Planner()
	{
		cArray = new CoursePlanner[MAX_COURSES];
		this.items = 0;
	}
	
	public int getItems()
	{
		return items;
	}
	
	public int size()
	{
		return items;
	}
	
	public void addCourse(CoursePlanner newCourse, int position) throws IllegalArgumentException
	{
		if(items == (MAX_COURSES - 1))
		{
			throw new IllegalArgumentException ("Memory is full.");
		}
		int temp = size();
		while(position < temp)
		{
		cArray[temp+1] = cArray[temp];
		temp--;
		}
		cArray[position] = newCourse;
		items++;
	}
//CHECK
	public void addCourse(CoursePlanner newCourse)
	{
		addCourse(newCourse, (size() + 1));
	}
	
	public void removeCourse(int position) throws IllegalArgumentException
	{
		if((items == (MAX_COURSES - 1)) || (position < 1))
		{
			throw new IllegalArgumentException ("This Course does not exist.");
		}
		int temp = size();
		while(position < temp)
		{
			cArray[temp-1] = cArray[temp];
			temp--;
		}
		cArray[size()] = null;
		items--;
	}
//CHECK
	public CoursePlanner getCourse(int position) throws IllegalArgumentException
	{
		if((items == (MAX_COURSES - 1)) || (position < 1))
		{
			throw new IllegalArgumentException ("This Course does not exist.");
		}
		return cArray[position];
	}
//CHECK
	public static void filter(Planner planner, String department)
	{
		Planner temp = new Planner();
		temp = planner;
		for(int i = 1; i <= temp.size(); i++)
		{
			CoursePlanner test = new CoursePlanner();
			test = temp.getCourse(i);
			if(test.getDept().equals(department))
			{
				System.out.println("Class # " + i);
			}
		}
	}
	//INCOMPLETE ABOVE
	public boolean exists(CoursePlanner course)
	{
		return equals(course);
	}
//CHECK
	public Object clone()
	{
		Planner result = new Planner();
	//	try
	//	{
	//		result = (Planner)super.clone();
	//	}
	//	catch (CloneNotSupportedException e)
	//	{
	//		throw new RuntimeException("Could not clone this object");
	//	}
		for(int i = 1; i <= size(); i++)
		{
			result.cArray[i] = cArray[i];
		}
		result.items = items;
		return result;
	}
	
	public void printAllCourses()
	{
		System.out.println("Planner: ");
		System.out.println("No. Course Name\t\t\tDepartment\tCode\tSection\t\tInstructor");
		System.out.println("-----------------------------------------------------------------------------------");
		int i = 1;
		while(i <= size())
		{
			System.out.println(i + " " + getCourse(i).getName() + "\t\t" + getCourse(i).getDept() + "\t\t" + getCourse(i).getCode() + "\t" + getCourse(i).getSection() + "\t\t" + getCourse(i).getTeacher());
			i++;
		}
	}
	//INCOMPLETE ABOVE
	public String toString()
	{
		System.out.println("No. Course Name\t\t\tDepartment\tCode\tSection\t\tInstructor");
		System.out.println("-----------------------------------------------------------------------------------");
		
		return this.size() + " " + this.getName() + "\t\t" + this.getDept() + "\t\t" + this.getCode() + "\t" + this.getSection() + "\t\t" + this.getTeacher();
	}
	//INCOMPLETE ABOVE

	
}
