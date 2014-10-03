package fib;

public class FibonacciFormel 
{
	public static long getFibFromPosition(int position)
	{
		double tmp, helper = Math.sqrt(5.0);
		position+=1;//correct index
		
		tmp = Math.round(
				(1/helper)
				*
				( Math.pow(((1 + helper)/2) , new Double(position)) 
						
				- Math.pow(((1 - helper)/2) , new Double(position)) )
						  );
		
		Helper.addCallInformation("FibonacciFormel calculation");		
		return Math.round(tmp);
	}
}
