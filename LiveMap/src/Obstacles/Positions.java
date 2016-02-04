package Obstacles;
/**
 * 
 * @author Julian
 *
 */
public class Positions 
{
	public final static Positions POSITION0= new Positions(255,337);
	public final static Positions POSITION2= new Positions(255,271);
	public final static Positions POSITION3= new Positions(255,204);
	public final static Positions POSITION4= new Positions(255,137);
	public final static Positions POSITION5= new Positions(255,70);
	public final static Positions POSITION6= new Positions(519,0);
	public final static Positions POSITION7= new Positions(225,66);
	public final static Positions POSITION8= new Positions(225,133);
	public final static Positions POSITION9= new Positions(225,200);
	public final static Positions POSITION10= new Positions(225,267);
	private int x;
	private int y;
	public Positions(int x1,int y1)
	{
		x=x1;
		y=y1;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public boolean equals(Positions z)
	{
		if((this.getX()==z.getX())&&(this.getY()==z.getY()))
		{
			return true;
		}
		else
			return false;
	}
}
