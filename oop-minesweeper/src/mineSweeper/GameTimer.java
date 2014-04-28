package mineSweeper;

public class GameTimer {
	
	public void start()
	{
		startTime=System.currentTimeMillis();
	}
	
	public long giveTime()
	{
		return (System.currentTimeMillis()-startTime);
	}
	
	public void stopTime()
	{
		stopTime=System.currentTimeMillis()-startTime;
	}
	
	public long giveStopTime()
	{
		return stopTime;
	}
	public void zeroTime()
	{
		stopTime=0;
	}
	
	private long stopTime;
	private long startTime;

}
