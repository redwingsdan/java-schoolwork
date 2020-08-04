
public class CoursePlanner {

	private String name;
	private String dept;
	private int code;
	private byte section;
	private String teacher;
	
	public CoursePlanner()
	{
		this.name = null;
		this.dept = null;
		this.code = 0;
		this.section = 0;
		this.teacher = null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDept()
	{
		return dept;
	}
	
	public int getCode()
	{
		return code;
	}
	
	public byte getSection()
	{
		return section;
	}
	
	public String getTeacher()
	{
		return teacher;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDept(String dept)
	{
		this.dept = dept;
	}

	
	public void setCode(int code)
	{
		this.code = code;
	}

	
	public void setSection(byte section)
	{
		this.section = section;
	}

	
	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}

	public Object clone()
	{
		CoursePlanner result;
		try
		{
			result = (CoursePlanner)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new RuntimeException("Could not clone this object");
		}
		return result;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof CoursePlanner)
		{
			CoursePlanner test = (CoursePlanner) obj;
			return (test.name == name) && (test.dept == dept) && (test.code == code) && (test.section == section) && (test.teacher == teacher);
		}
		else
			return false;
	}
	
}
