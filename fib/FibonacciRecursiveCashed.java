package fib;

import java.util.HashMap;

public class FibonacciRecursiveCashed 
{		
	private static HashMap<Integer,Long> operationCash = new HashMap<Integer,Long> ();
	
	public static long getFibFromPosition(int position)
	{
		Helper.addCallInformation("FibonacciRecursiveCashed init");
		if(position==0)
			return 0;
		
		position = Math.abs(position);
		return _getFibFromPosition(position);	
	}
	
	protected static long _getFibFromPosition(int position)
	{
		if(position<2)
		{
			Helper.addCallInformation("FibonacciRecursiveCashed : return 1");
			return 1;
		}
		
		if(operationCash.containsKey(position))	
			return operationCash.get(position);		
		else
			Helper.addCallInformation("FibonacciRecursiveCashed : not cashed for "+position);
		
		long result = _getFibFromPosition(position-1) + _getFibFromPosition(position-2);
		
		operationCash.put(position,result);
		return result;	
	}

	public static void clearCash()
	{
		operationCash.clear();
	}
}