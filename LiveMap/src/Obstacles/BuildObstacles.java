package Obstacles;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.geometry.Point2D;

public class BuildObstacles 
{
	public final static Point2D[] POSITIONS ={new Point2D(225,337),new Point2D(225,271)
	,new Point2D(225,204),new Point2D(225,137),new Point2D(225,70),new Point2D(519,0)
	,new Point2D(519,66),new Point2D(519,133),new Point2D(519,133),new Point2D(519,200)
	,new Point2D(519,267)
	};
//	public final static Point2D Point2D1= new Point2D(255,337);
//	public final static Point2D Point2D2= new Point2D(255,271);
//	public final static Point2D Point2D3= new Point2D(255,204);
//	public final static Point2D Point2D4= new Point2D(255,137);
//	public final static Point2D Point2D5= new Point2D(255,70);
//	public final static Point2Ds Point2D6= new Point2Ds(519,0);
//	public final static Point2Ds Point2D7= new Point2Ds(225,66);
//	public final static Point2Ds Point2D8= new Point2Ds(225,133);
//	public final static Point2Ds Point2D9= new Point2Ds(225,200);
//	public final static Point2Ds Point2D10= new Point2Ds(225,267);
	private Scanner s;
	private Map<Point2D,Obstacles> m=new HashMap<Point2D,Obstacles>();
	public BuildObstacles()
	{
		s=new Scanner(System.in);
		boolean done=true;
		int i=1;
		while(!done)
		{
			m.put(POSITIONS[i],new Obstacles(s.nextInt()));
		}
	}
	public String toString()
	{
		return m.toString();
	}
	public static void main(String[] args)
	{
		BuildObstacles o=new BuildObstacles();
		System.out.println(o.toString());
	}
}
