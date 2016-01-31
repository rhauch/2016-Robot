package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import static application.MetersToPixels.convert;

public class Map {
	private RobotConversion robot;
	private static final double PIXELS_PER_METER = 49.382716049;
	private Image imageField = new Image("/application/Field.png",814,400,true,false);
	private Image imageBA = new Image("/application/BlueDot.png",39,39,true,false);
	private Image imageRA = new Image("/application/RedDot.png",39,39,true,false);
	private Image imageSP = new Image("/application/StartingPosition.png",39,39,true,false);
	private int sx = 0;
	private int sy = 0;
	private boolean flag = false;
	
	public void update(GraphicsContext gc)
	{
		if(flag == true)
		{
		int xCord = robot.xCoord();
		int yCord = robot.yCoord();
		double rotation = robot.rotation();
		gc.drawImage(imageField,0,0);
		gc.save();
		rotate(gc, rotation, xCord + imageBA.getWidth() / 2, yCord + imageBA.getHeight() / 2);
		gc.drawImage(imageBA, xCord, yCord, 39, 39);
		gc.restore();
		}
		else
		{
			gc.drawImage(imageField,0,0);
			gc.drawImage(imageSP, convert(2), convert(1), 39, 39);
			gc.drawImage(imageSP, convert(2), convert(2), 39, 39);
		}
	}
	
	public void handleClick(int x,int y)
	{
		if(flag == false)
		{
			sx = x;
			sy = y;
			if((convert(2)<=sx && sx<=convert(2.762)) && (convert(1)<=sy && sy<=convert(1.762)))
			{
			flag = true;
			robot = new RobotConversion(convert(2), convert(1));
			System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
			}
			else if((convert(2)<=sx && sx<=convert(2.762)) && (convert(2)<=sy && sy<=convert(2.762)))
			{
			flag = true;
			robot = new RobotConversion(convert(2), convert(2));
			System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
			}
		}
		else
			System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
	}
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
