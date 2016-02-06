package Obstacles;
import java.util.Scanner;

/**
 * 
 * @author Julian Nieto
 *
 */
public class Obstacles 
{
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
}
