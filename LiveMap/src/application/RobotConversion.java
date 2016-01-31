package application;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RobotConversion 
{
	private NetworkTable table;
	private static final double PIXELS_PER_METER = 49.382716049;
	private double startX =0;
	private double startY =0;
	
	
	public RobotConversion() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("192.168.1.130");
		table = NetworkTable.getTable("locator");
	}
	/*
	 * need to keep the coordinates in meters till the last posible time
	 * have to put the starting positions in 50 pixels = 1.0125 meters
	 * robot is 30 by 30 inch
	 * 1 meter = 39.37 inches
	 */
	public RobotConversion(double x,double y)
	{
		startX =x;
		startY =y;
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("192.168.1.130");
		table = NetworkTable.getTable("locator");
	}

	public int xCoord()
	{
		double x = (table.getNumber("posY", 0)+ startX);
		//System.out.println(x/PIXELS_PER_METER);
		return (int) x;
	}
	
	public int yCoord()
	{
		double y = (table.getNumber("posX", 0)+ startY);
		//System.out.println(y/PIXELS_PER_METER);
		return (int) y;
	}
	
	public double rotation()
	{
		double r = 180 - table.getNumber("heading",0);
		//System.out.println(r);
		return r;
	}
}
