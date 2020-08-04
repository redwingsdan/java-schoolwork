
public class Complexity {

	private int n_power;
	private int log_power;
	
	public Complexity()
	{
		n_power = 0;
		log_power = 0;
	}
	
	public void setN_Power(int n_power)
	{
		this.n_power = n_power;
	}
	
	public void setLog_Power(int log_power)
	{
		this.log_power = log_power;
	}
	
	public int getN_Power()
	{
		return n_power;
	}
	
	public int getLog_Power()
	{
		return log_power;
	}
	
	public String toString()
	{
		String msg = "";
		boolean flag = false;
		if(this.n_power > 1)
		{
			msg = "O(n ^ " + n_power;
			if(this.log_power != 0)
			{
				flag = true;
			}
		}
		else if (this.n_power == 1)
		{
			msg = "O(n";
			if(this.log_power != 0)
			{
				flag = true;
			}
		}
		if(flag == true)
		{
			msg = msg + " * ";
		}
		if(flag == false)
		{
			msg = msg + ").";
		}
		if(this.n_power == 0)
		{
			msg = "O(";
		}
		if(this.log_power > 1)
		{
			msg = msg + "log (n) ^ " + log_power + ").";
		}
		else if (this.log_power == 1)
		{
			msg = msg + "log (n)" +  ").";
		}
		if((this.n_power == 0) && (this.log_power == 0))
		{
			msg = "O(1)";
		}
		return msg;
	}
}
