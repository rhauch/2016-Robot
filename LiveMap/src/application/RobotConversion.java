package application;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import static application.MetersToPixels.convertPixels;
import static application.MetersToPixels.convertMeters;

public class RobotConversion 
{
	private NetworkTable table;
	private double startX =0;
	private double startY =0;
	private double rotat = 0;
	
	/**
	 * this is the method that calls the network tabels to get the robots position
	 */
	public RobotConversion() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("192.168.1.130");//address of the table
		table = NetworkTable.getTable("locator");//what the robot's position is named in the network table
	}
	/*
	 * notes(need to keep the coordinates in meters till the last posible time
	 * have to put the starting positions in 50 pixels = 1.0125 meters
	 * robot is 30 by 30 inch
	 * 1 meter = 39.37 inches)
	 * this is the actual robot's position retriever since it give the variables that store the robots 
	 * location the actual value
	 */
	public RobotConversion(double x,double y)
	{
		startX =x;
		startY =y;
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("192.168.1.130");
		table = NetworkTable.getTable("locator");
	}

	/**
	 * 
	 * @return the x coordinate of the robot in pixels
	 */
	public int xCoord()
	{
		double x = convertPixels(table.getNumber("posY", 0))+ startX;
		System.out.println(x +" "+ startX);
		return (int) x;
	}
	
	/**
	 * 
	 * @return the y coordinte of the robot in pixels
	 */
	public int yCoord()
	{
		double y = convertPixels(table.getNumber("posX", 0))+ startY;
		System.out.println(y +" "+ startY);
		return (int) y;
	}
	
	/**
	 * sets the robots rotational value to have a direction in the map
	 * @param r
	 */
	public void setRotation(double r)
	{
		rotat = r;
	}
	
	/**
	 * 
	 * @return the angle of rotation of the robot on the map
	 */
	public double rotation()
	{
		double r = (180 - table.getNumber("heading",0)) + rotat;
		return r;
	}
}
