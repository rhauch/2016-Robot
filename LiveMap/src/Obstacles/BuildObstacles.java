package Obstacles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.geometry.Point2D;

/**
 * {@link BuildObstacles} is used to construct the obstacles for {@link Map}
 * @author Julian
 *
 */
public class BuildObstacles 
{
	//Positions of all Obstacles spots
	public final static Point2D[] POSITIONS ={new Point2D(225,337),new Point2D(225,271)
	,new Point2D(225,204),new Point2D(225,137),new Point2D(225,70),new Point2D(519,0)
	,new Point2D(519,66),new Point2D(519,133),new Point2D(519,133),new Point2D(519,200)
	,new Point2D(519,267)};
	
	private Scanner s;
	
	//map that stores the values
	private Map<Point2D,Obstacles> mapOfPositions=new HashMap<Point2D,Obstacles>();
	public BuildObstacles(boolean side)
	{
		//blue 1-5,red 6-10
		int i;
		int sideI;
		if(side)
		{
			sideI=5;
			i=1;
		}
		else
		{
			sideI=10;
			i=5;
		}
			while(i<=sideI)
			{
				s=new Scanner(System.in);
				System.out.println("Please input for Position" +i);
				Obstacles temp = new Obstacles(s.nextLine().toUpperCase());
				if(temp.checkIfGood(temp.getObstacle()))
				{
					this.putter(i,temp);
					i++;
				}
				else
				{
					System.out.println("Invalid input pls try again");
				}
			}
	}
	
	/**
	 * putter puts the thing in the map
	 * @param p int 1-10 for Position
	 * @param o Obstacle for Position
	 */
	public void putter(int p,Obstacles o)
	{
		mapOfPositions.put(POSITIONS[p],o);
	}
	
	/**
	 * returns the Obstacle at spot
	 * @param x Position wanted
	 * @return Obstacle at indicated spot
	 */
	public Obstacles getObstacle(int x)
	{
		return mapOfPositions.get(POSITIONS[x]);
	}
}
