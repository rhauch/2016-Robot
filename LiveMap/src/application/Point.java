package application;

public class Point 
{
	private double mainX;
	private double mainY;
	
	public Point(double x,double y)
	{
		mainX = x;
		mainY = y;
	}
	
	public double getX()
	{
		return mainX;
	}
	
	public double getY()
	{
		return mainY;
	}
	
	public void setX(double x)
	{
		mainX = x;
	}
	
	public void setY(double y)
	{
		mainY = y;
	}
}
