package fib;

import java.util.Vector;

public class Helper 
{
	private static long startTime;
	private static long endTime;
	private static long lastBetweenTime;
	private  static int callCounter=0; 
	
	private static Vector<String> callInformations = new Vector<String>();
		
	public static void addCallInformation(String input)
	{		
		addCallInformation(input,true,true);
	}
	
	public static void addCallInformation(String input,boolean count)
	{		
		addCallInformation(input,count,true);
	}
	
	public static void addCallInformation(String input,boolean count,boolean print)
	{		
		if(print)
			System.out.println(input);
			
		if(count)
		{
			callCounter++;
			callInformations.add("("+callCounter+")   ->   \t"+input);
		}
		else
			callInformations.add("( i ) "+input);
	}
	
	public static void resetCallInformations()
	{
		callInformations.removeAllElements();
		callCounter=0;
	}
	
	public static int countCallInformations()
	{
		return callCounter;
	}
	
	public static void startTimer()
	{
		startTime = getNow();
	}
	
	public static void stopTimer()
	{
		endTime = getNow();
	}
	
	public static long getDuration()
	{
		return endTime-startTime;
	}
	
	public static long getBetweenTime()
	{
		if(lastBetweenTime < 1)
			lastBetweenTime = startTime;
		
		long now = getNow();
		long value = now - lastBetweenTime;
		lastBetweenTime = now;
		return value;		
	}
	
	public static long getNow()
	{
		return System.nanoTime();
	}
	
	public static String getInformation()
	{
		return getInformation(true);
	}
	
	public static String getCallInformations()
	{
		return vectorArray2String(callInformations);
	}
	
	public static String getInformation(boolean full)
	{
		String value = "";
		if (full) 
			value+=vectorArray2String(callInformations);
		
		value += "duration: "+(Double.parseDouble(""+Helper.getDuration())/1000000000)+" s \n";
		value += "callCounter: "+Helper.countCallInformations()+" \n";
		
		return value;
	}
		
	public static String vectorArray2String(Vector<String> a)
	{
		int index = 0;
		String result = "";
		try
		{
			while(true)			
			{
				result += a.get(index)+"\n";				
				index++;
			}
		}
		catch(Exception e)
		{
			return result;
		}
	}
}