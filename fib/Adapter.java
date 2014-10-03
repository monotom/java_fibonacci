package fib;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Adapter extends Thread 
{
	final static char ITERATIV = '1';
	final static char ITERATIV_CASHED = '2';
	final static char RECURSIVE = '3';
	final static char RECURSIVE_CASHED = '4';
	final static char FORMEL = '5';
	final static char MATRIX = '6';
		
	char actualType='1';
	int fromPos = 15,toPos = 15, steps = 1;
	boolean finished = false;
	
	public Adapter()
	{
		
	}	

	public Adapter(char type,int fromPos,int toPos,int steps)
	{
		config(type, fromPos, toPos, steps);
	}
	
	public int getFrom()
	{
		return fromPos;
	}
	
	public int getTo()
	{
		return toPos;
	}
	
	public int getSteps()
	{
		return steps;
	}
	
	public String getTypeName()
	{
		switch(actualType)
		{
			default:
			case ITERATIV:
				return "ITERATIV";
			case ITERATIV_CASHED:
				return "ITERATIV_CASHED";
			case RECURSIVE:
				return "RECURSIVE";
			case RECURSIVE_CASHED:
				return "RECURSIVE_CASHED";
			case FORMEL:
				return "FORMEL";
			case MATRIX:
				return "MATRIX(todo)";
		}
	}
		
	public void config(char type,int fromPos,int toPos,int steps)
	{
		this.actualType = type;
		this.fromPos 		= fromPos;
		this.toPos 		= toPos;
		this.steps 		= steps;
	}
	
	public void run()
	{
		int run = 1;
		long fib;			 
		
		Double percentFactor = (new Double(""+(toPos-fromPos))/new Double(""+steps)
																		/100);
		
		Helper.startTimer();
		switch(actualType)
		{
			default:
			case ITERATIV:
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciIterative.getFibFromPosition(i);
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+"\n\n",false);			
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}	
				break;
			case ITERATIV_CASHED:
				FibonacciIterativeCashed.clearCash();
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciIterativeCashed.getFibFromPosition(i);
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+"\n\n",false);			
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}	
				break;
			case RECURSIVE:
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciRecursive.getFibFromPosition(i);	
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+"\n\n",false);		
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}	
				break;
			case RECURSIVE_CASHED:
				FibonacciRecursiveCashed.clearCash();
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciRecursiveCashed.getFibFromPosition(i);
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+"\n\n",false);
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}		
				break;
			case FORMEL:
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciFormel.getFibFromPosition(i);	
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+"\n\n",false);	
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}	
				break;
			case MATRIX:
				for(int i = fromPos; i <= toPos; i+=steps )
				{
					Helper.addCallInformation("Run :"+run+", Pos :"+i,false);
					fib = FibonacciMatrix.getFibFromPosition(i);	
					Helper.addCallInformation("Run :"+run+", Pos :"+i+", Fib :"+fib+" \n\n",false);	
					run++;
					this.fireProgressIncreased(i*percentFactor);
				}	
				break;		
		}
		Helper.stopTimer();
		this.fireThreadStopped();
	}	
	
	private PropertyChangeListener propertyChangeListener = null;

    private void fireProgressIncreased(int value) 
    {       
        PropertyChangeEvent event = new PropertyChangeEvent(
                this, "PROGRESS_UPDATED", null, new Integer(value));
       
        if (propertyChangeListener != null)
            propertyChangeListener.propertyChange(event);
    }
    
    private void fireProgressIncreased(Double value) 
    {
    	fireProgressIncreased(new Integer(""+Math.round(value)));
    }
   
    private void fireThreadStopped() {
       
        PropertyChangeEvent event = new PropertyChangeEvent(
                this, "THREAD_STOPPED", null, null);
       
        if (propertyChangeListener != null)
            propertyChangeListener.propertyChange(event);
       
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListener = listener;
    }

    public int getMaxProgress() {
        return 100;
    } 
}
