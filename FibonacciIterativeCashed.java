package fib;

import java.util.HashMap;

public class FibonacciIterativeCashed
{
	private static HashMap<Integer,Long> operationCash = new HashMap<Integer,Long> ();
	
	public static long getFibFromPosition(int position)
	{
		if(operationCash.containsKey(position))		
			return operationCash.get(position);
		
		long result = FibonacciIterative.getFibFromPosition(position);
		
		operationCash.put(position,result);
		return result;
	}
	
	public static void clearCash()
	{
		operationCash = new HashMap<Integer,Long> ();
	}
}
