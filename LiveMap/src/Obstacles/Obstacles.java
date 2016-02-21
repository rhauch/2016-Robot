package Obstacles;
import java.util.Scanner;

import javafx.geometry.Point2D;

/**
 * 
 * @author Julian Nieto
 *
 */
public class Obstacles 
{
	public final static double[] TIME_FINAL ={0,1,2,3,4,5,6,7,8};
	public final static String LOWBAR = "LB";
	public final static String ROCKWALL = "RW";
	public final static String PORTCULLIS = "PC";
	public final static String SHOVELS_OF_FRIES = "CF";
	public final static String RAMPARTS = "RP";
	public final static String MOAT = "MT";
	public final static String DRAWBRIDGE = "DB";
	public final static String SALLY_PORT = "SP";
	public final static String ROUGH_TERRAIN = "RT";

	private String identifierString;
	private double time;
	/**
	 * Only pass the positions of the constants in the Position class
	 * @param o identifier number only Constants
	 * @param x Position  in constants
	 */
	public Obstacles(String o)
	{
			identifierString=o;
		
	}
	public String getObstacle()
	{
		return identifierString;
	}
	public String toString()
	{
		return ""+identifierString;
	}
	public boolean checkIfGood(String r)
	{
		if(r.equals(LOWBAR)||r.equals(PORTCULLIS)||r.equals(ROCKWALL)||
			r.equals(ROUGH_TERRAIN)||r.equals(ROCKWALL)||r.equals(SHOVELS_OF_FRIES)
			||r.equals(SALLY_PORT)||r.equals(DRAWBRIDGE)||r.equals(MOAT))
		{
			return true;
		}
		return false;
	}
	public void assignTime()
	{
		switch(identifierString)
		{
			case "LB": time=TIME_FINAL[1];
				break;
			case "CF": time=TIME_FINAL[2];
				break;
			case "RT": time=TIME_FINAL[3];
				break;
			case "RW": time=TIME_FINAL[0];
				break;
			case "MT": time=TIME_FINAL[4];
				break;
			case "PC": time=TIME_FINAL[5];
				break;
			case "DB": time=TIME_FINAL[6];
				break;
			case "SP": time=TIME_FINAL[7];
				break;
			case "RP": time=TIME_FINAL[9];
				break;
		}
	}
	public double getTime()
	{
		return time;
	}
}
