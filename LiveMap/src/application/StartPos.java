package application;


public class StartPos 
{
	private double mainX;
	private double mainY;
	
	/**
	 * 
	 * @param x the top left corner of the starting position
	 * @param y the top left corner of the starting position
	 */
	public StartPos(double x, double y)
	{
		mainX = x;
		mainY = y;
	}
	
	/**
	 * 
	 * @param x the x coordinate you want to check
	 * @param y the y coordinate you want to check
	 * @return true if its within the boundry of the starting position
	 */
	public boolean withinBoundries(double x,double y)
	{
		if((mainX<=x && x<=(mainX+0.762)) && (mainY<=y && y<=mainY+0.762))
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @return the x of the Starting Position
	 */
	public double getX()
	{
		return mainX;
	}
	
	/**
	 * 
	 * @return the y of the Starting Position
	 */
	public double getY()
	{
		return mainY;
	}
	
	
}
