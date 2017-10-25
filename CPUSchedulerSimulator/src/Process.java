import java.util.Comparator;

public class Process implements Comparable<Process> { 
	
	/* Input attributes */
	private long PID = 0;      // [ 0 - sizeof(long) ]
	private long arrivalTime = 0;  // set by scheduler
	private long CPUBurst = 0;
	private long priority = 0; // [0(high) - 9 (low)]
	
	/* Output attributes */
	private long startTime = 0;   
	private long completeTime = 0;  
	private long waitTime = 0;   
	private long turnaroundTime = 0;
	private boolean active = false;
	private boolean started = false;
	private boolean completed = false;
	private boolean arrived = false;
    
	public Process() {
		// TODO Auto-generated constructor stub
	}
	
	Process(long pid, long at, long bt, long pr){
    	PID = pid;
    	CPUBurst = bt;
    	arrivalTime = at;
    	priority = pr;
    	startTime = 0;   
    	completeTime = 0;  
    	waitTime = 0;   
    	turnaroundTime = 0;
    	active = false;
    	started = false;
    	completed = false;
    	arrived = false;
    }
    
	/* Accessors: Input */
    public long getPID() 		{	return PID;			}
    public long getBurstTime() 	{	return CPUBurst;	}   
    public long getArrivalTime(){	return arrivalTime;	}
    public long getPriority() 	{	return priority;	}
    
    /* Accessors: Output */
    public long getCompleteTime() 	{	return completeTime;	}
    public long getStartTime() 		{	return startTime;		}
    public long getWaitTime() 		{	return waitTime;		}
    public long getTurnaroundTime()	{ 	return turnaroundTime; 	}  
    public boolean getArrived() 	{	return arrived;			}
    public boolean getStarted() 	{	return started;			}
    public boolean getCompleted() 	{	return completed;		}
    public boolean getActive() 		{	return active;			}
    
    /* Mutators */
    public void setCompleteTime(long  v) 	{	this.completeTime = v;	}
    public void setStartTime(long v) 		{	this.startTime = v;		}
    public void setArrived(boolean  v) 		{	this.arrived = v;		}
    public void setStarted(boolean v) 		{	this.started = v;		}
    public void setCompleted(boolean v) 	{	this.completed = v;		}
    public void setActive(boolean v) 		{	this.active = v;		}

    public void restore()	{
    	startTime    = 0;
    	waitTime     = 0;
    	active   = false;
    	started  = false;
    	completed = false;
    	arrived  = false;
    }

    public boolean isActive() {		return active;	}
 
    public boolean isCompleted() {	return completed;}

    public boolean isStarted() {	return started;	}
    
    public boolean isArrived() { 	return arrived; }
	
	public int compareTo(Process p1) {
		return (int) (p1.getArrivalTime() - arrivalTime);
	}
	
	public void executing(long t){		    	    
		CPUBurst--;		
		if (CPUBurst == 0)	{
			completed = true;
			active = false;
			completeTime = t;
		}
	}

	public void waiting(long t){
		waitTime++;
		
	}	
	

} // ENDS class Process