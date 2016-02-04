package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import static application.MetersToPixels.convertPixels;
import static application.MetersToPixels.convertMeters;

public class Map {
	private RobotConversion robot;
	private StartPos sp1, sp2, sp3, sp4;
	private static final double PIXELS_PER_METER = 49.382716049;
	private Image imageField = new Image("/application/Field.png",814,400,true,false);
	private Image imageBA = new Image("/application/BlueDot.png",39,39,true,false);
	private Image imageRA = new Image("/application/RedDot.png",39,39,true,false);
	private Image imageSP = new Image("/application/StartingPosition.png",39,39,true,false);
	private double sx = 0;
	private double sy = 0;
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
			gc.drawImage(imageSP, convertPixels(7), convertPixels(5), 39, 39);
			sp1 = new StartPos(7,5);
			gc.drawImage(imageSP, convertPixels(3), convertPixels(1), 39, 39);
			sp2 = new StartPos(3,1);
		}
	}
	
	public void handleClick(int x,int y)
	{
		if(flag == false)
		{
			sx = (double) (convertMeters(x));
			sy = (double) (convertMeters(y));
			if(sp1.withinBoundries(sx, sy))
			{
			flag = true;
			robot = new RobotConversion(convertPixels(sp1.getX()), convertPixels(sp1.getY()));
			//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
			}
			else if(sp2.withinBoundries(sx, sy))
			{
			flag = true;
			robot = new RobotConversion(convertPixels(sp2.getX()), convertPixels(sp2.getY()));
			//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
			}
		}
		else
			System.out.println((convertMeters(x)) +" "+ (convertMeters(y)));
	}
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
