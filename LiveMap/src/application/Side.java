package application;

public class Side 
{
	private boolean side;
	
	/**
	 *  chooses the side of the map based on were the user clicks
	 * @param x the x coord of were you click
	 */
	public Side(int x)
	{
		if(x<408)
		{
			side = true;
		}
		else
			side = false;
	}
	
	/**
	 * 
	 * @return true if blue false if red
	 */
	public boolean getSide()
	{
		return side;
	}
}
