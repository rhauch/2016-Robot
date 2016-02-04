package Obstacles;
/**
 * 
 * @author Julian Nieto
 *
 */
public class Obstacles 
{
	public final static int LOWBAR = 0;
	public final static int ROCKWALL = 1;
	public final static int PORTCULLIS = 2;
	public final static int SHOVELS_OF_FRIES = 3;
	public final static int RAMPARTS = 4;
	public final static int MOAT = 5;
	public final static int DRAWBRIDGE = 6;
	public final static int SALLY_PORT = 7;
	public final static int ROUGH_TERRAIN = 8;

	private Positions position;
	private int identifierNum;
	/**
	 * Only pass the positions of the constants in the Position class
	 * @param o identifier number only Constants
	 * @param x Position  in constants
	 */
	public Obstacles(int o,Positions x)
	{
		identifierNum=o;
		position=x;
		
	}
	public int getObstacle()
	{
		return identifierNum;
	}
	public Positions getPos()
	{
		return position;
	} 
}
