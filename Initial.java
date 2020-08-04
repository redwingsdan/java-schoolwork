
public class Initial {
	public static void main(String [] args)
	{
		CoursePlanner math = new CoursePlanner();
		math.setName("Finite Mathematic Structures");
		math.setDept("AMS");
		math.setCode(301);
		math.setSection((byte) 4);
		math.setTeacher("Alan Tucker");
		
		System.out.println("Name = " + math.getName() + "\nDepartment = " + math.getDept() + "\nCode = " + math.getCode() + "\nSection = " + math.getSection() + "\nTeacher = " + math.getTeacher());
	
		CoursePlanner phy = new CoursePlanner();
		phy.setName("Classical Physics I");
		phy.setDept("PHY");
		phy.setCode(131);
		phy.setSection((byte) 1);
		phy.setTeacher("Thomas Hemmick");
		
		Planner.addCourse(math);
		Planner.addCourse(phy);
		System.out.println();
	}
}
