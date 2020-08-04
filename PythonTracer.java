import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ClassCastException;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Scanner;

public class PythonTracer {

	public static final int SPACE_COUNT = 4;
	
	static Stack s1 = new Stack();
	
	public static Complexity traceFile(String filename)throws IllegalArgumentException, FileNotFoundException, ClassCastException
	{
		try
		{
			FileInputStream fis = new FileInputStream(filename);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Specified file was not found.");
		}
		Scanner reader = new Scanner(new File(filename));
		String data = "";
		int indents = 0;
		int lineSpaces = 0;
		int count = 0;
		String backup = null;
		//Object oldTop = new Object();
		CodeBlock oldTop = new CodeBlock();
		Complexity oldTopComplexity = new Complexity();
		while(reader.hasNextLine())
		{
			data = reader.nextLine();
			if(data == null)
			{
				throw new IllegalArgumentException("File is null. Please choose a valid file.");
			}
			if(data.startsWith("#"))
			{
			//	System.out.println("This line is a comment");
			//	break;
			}
			if(data.startsWith(" "))
			{
				//lineSpaces++;
				//Move to next character
				lineSpaces = data.indexOf(data.trim());
			}
			indents = lineSpaces / SPACE_COUNT;
			while(indents < s1.size())
			{
				if(indents == 0)
				{
					System.out.println("Total complexity of stack.top is: ");
					break;
					//	break;
					//close file
				}
				else
				{
					oldTop = (CodeBlock) s1.pop();
					//if(oldTopComplexity.getLog_Power() < oldTop.getHighestSubComplexity().getLog_Power())
					//{
					//	oldTopComplexity = oldTop.getHighestSubComplexity();
					//}
					//if(oldTopComplexity.getN_Power() < oldTop.getHighestSubComplexity().getN_Power())
					//{
					//	oldTopComplexity = oldTop.getHighestSubComplexity();
					//}
					oldTopComplexity = oldTop.getHighestSubComplexity();
					try
					{
						if(oldTopComplexity.getN_Power() == 1)
						{
							count++;
						}
						oldTopComplexity.setN_Power(count);
						System.out.println(oldTopComplexity.toString());
						backup = oldTopComplexity.toString();
					}
					catch(NullPointerException e)
					{
						
					}
						//oldTopComplexity = (Complexity) oldTop;
					//set OldTopComplexity to complexity of oldTop
					
				}
			}
			if(data.contains("def "))
			{
				CodeBlock c1 = new CodeBlock();
				Complexity c2 = new Complexity();
				c2.setN_Power(0);
				c2.setLog_Power(0);
				c1.setBlockComplexity(c2);
				c1.setHighestSubComplexity(c2);
				s1.push(c2);
			}
			if(data.contains("for "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();	
					Complexity c2 = new Complexity();
					//figure out complexity at end of data
					if(data.contains("log"))
					{
						c2.setN_Power(0);
						c2.setLog_Power(1);
						c1.setBlockComplexity(c2);
						c1.setHighestSubComplexity(c2);
						s1.push(c1);
						System.out.println(s1.size());
					}
					else
					{
						c2.setN_Power(1);
						c2.setLog_Power(0);
						c1.setBlockComplexity(c2);
						c1.setHighestSubComplexity(c2);
						s1.push(c1);
						System.out.println(s1.size());
					}
				}
			}
			if(data.contains("while "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(0);
					c2.setLog_Power(1);
					c1.setBlockComplexity(c2);
					c1.setHighestSubComplexity(c2);
					s1.push(c1);
					//figure out loop variable
				}
			}
			if(data.contains("if "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(0);
					c2.setLog_Power(0);
					c1.setBlockComplexity(c2);
					try
					{
						if(oldTopComplexity.getLog_Power() < oldTop.getHighestSubComplexity().getLog_Power())
						{	
							c1.setHighestSubComplexity(c2);
						}
						else
						{
							try
							{
								CodeBlock ctest = (CodeBlock) s1.peek();
								c1.setHighestSubComplexity(ctest.getHighestSubComplexity());
							}
							catch(ClassCastException e)
							{
							
							}
						}
					}
					catch(NullPointerException e)
					{
						
					}
					s1.push(c1);
				}
			}
			if(data.contains("elif "))
			{
				CodeBlock c1 = new CodeBlock();
				Complexity c2 = new Complexity();
				c2.setN_Power(1);
				c2.setLog_Power(1);
				c1.setBlockComplexity(c2);
			}
			if(data.contains("else "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(1);
					c2.setLog_Power(0);
					c1.setBlockComplexity(c2);
					c1.setHighestSubComplexity(c2);
					s1.push(c1);
				}
			}
			
			else
			{
				//System.out.print("This line is being ignored.");
				//break;
			}
		}
		try
		{
			Complexity test = new Complexity();
			//System.out.println("SIZE OF STACK:" + s1.size());
			//CodeBlock test2 = new CodeBlock();
			//test2 = (CodeBlock) s1.pop();
			//System.out.println(test2.getHighestSubComplexity().getLog_Power());
			//System.out.println(test2.getHighestSubComplexity().getN_Power());
			test = (Complexity) s1.pop();
			if(oldTopComplexity.getLog_Power() > test.getLog_Power())
			{
				test.setLog_Power(oldTopComplexity.getLog_Power());
			}			
			if(oldTopComplexity.getN_Power() > test.getN_Power())
			{
				test.setN_Power(oldTopComplexity.getN_Power());
			}
			//return (Complexity) s1.pop();
			return test;
		}
		catch(NullPointerException e)
		{
			throw new NullPointerException(("The overall complexity of " + filename + " is " + backup + "\n"));
		}
		catch(EmptyStackException e)
		{
			throw new EmptyStackException();
		}
		catch(ClassCastException e)
		{
			throw new ClassCastException("");
		}
	}
	
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException, ClassCastException
	{
		Scanner input = new Scanner(System.in);
		String file = "";
		Complexity c1 = new Complexity();
		boolean flag = false;
		
		while(flag == false)
		{
			System.out.println("Please enter a file name (or 'quit' to quit): ");			
			file = input.next();
			if(!(file.toLowerCase().equals("quit")))
			{
				try
				{
				c1 = traceFile(file);
				System.out.println("The overall complexity of " + file + " is " + c1.toString() + "\n");
				}
				catch(FileNotFoundException e)
				{
					System.out.print("");
				}
				catch(EmptyStackException e)
				{
					System.out.println("Stack is empty. Could not pop value.");
				}
				catch(ClassCastException e)
				{
					System.out.println();
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
				}
			}
			else
			{
				flag = true;
				break;	
			}			
		}		
		System.out.println("Program terminating successfully...");
	}
	
}
