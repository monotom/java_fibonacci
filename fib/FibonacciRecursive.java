package fib;

public class FibonacciRecursive 
{		
	public static long getFibFromPosition(int position)
	{
		Helper.addCallInformation("FibonacciRekursiv init");
		if(position==0)
			return 0;
		
		position = Math.abs(position);
		return _getFibFromPosition(position);	
	}
	
	protected static long _getFibFromPosition(int position)
	{
		if(position<2)
		{
			Helper.addCallInformation("FibonacciRekursiv : return 1");
			return 1;
		}
				
		return _getFibFromPosition(position-1)+_getFibFromPosition(position-2);
	}
}