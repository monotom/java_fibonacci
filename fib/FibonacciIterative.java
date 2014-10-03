package fib;

public class FibonacciIterative 
{
	public static long getFibFromPosition(int position)
	{
		if(position == 0)
			return 0;
		
		position = Math.abs(position);
		long last = 1,lastlast = 1, tmp;
				
		for(int i = 1; i < position;i++)
		{
			tmp  = last;
			last = last + lastlast;
			lastlast = tmp;
			
			Helper.addCallInformation("FibonacciIterativ: i = "+i);
		}					
		return last;
	}
}
