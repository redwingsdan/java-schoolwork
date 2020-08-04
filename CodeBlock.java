
public class CodeBlock {

	public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "elif", "else"};
	public static final int DEF = 0;
	public static final int FOR = 1;
	public static final int WHILE = 2;
	public static final int IF = 3;
	public static final int ELIF = 4;
	public static final int ELSE = 5;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	String name;
	String loopVariable;
	
	public CodeBlock()
	{
		blockComplexity = null;
		highestSubComplexity = null;
		name = null;
		loopVariable = null;
	}
	
	public void setBlockComplexity(Complexity blockComplexity)
	{
		this.blockComplexity = blockComplexity;
	}
	
	public void setHighestSubComplexity(Complexity highestSubComplexity)
	{
		this.highestSubComplexity = highestSubComplexity;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setLoopVariable(String loopVariable)
	{
		this.loopVariable = loopVariable;
	}
	
	public Complexity getBlockComplexity()
	{
		return blockComplexity;
	}
	
	public Complexity getHighestSubComplexity()
	{
		return highestSubComplexity;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getLoopVariable()
	{
		return loopVariable;
	}
	
}
