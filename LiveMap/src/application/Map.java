package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import static application.MetersToPixels.convertPixels;
import static application.MetersToPixels.convertMeters;

public class Map {
	private RobotConversion robot;
	private StartPos sp1, sp2, sp3, sp4;
	private Side side;
	private Image imageField = new Image("/application/Field.png",814,400,true,false);
	private Image imageBA = new Image("/application/BlueDot.png",39,39,true,false);
	private Image imageRA = new Image("/application/RedDot.png",39,39,true,false);
	private Image imageSP = new Image("/application/StartingPosition.png",39,39,true,false);
	private Image imageOb = new Image("/application/ObstacleHighlight.png",28,63,true,false);
	private Image imageRS = new Image("/application/RedSide.png",407,400,true,false);
	private Image imageBS = new Image("/application/BlueSide.png",407,400,true,false);
	private double sx = 0;
	private double sy = 0;
	private boolean flag = false;
	private boolean flag2 = false;
	
	public void update(GraphicsContext gc)
	{
		if(flag == true && flag2 == true)
		{
			int xCord = robot.xCoord();
			int yCord = robot.yCoord();
			double rotation = robot.rotation();
			gc.drawImage(imageField,0,0);
			gc.save();
			rotate(gc, rotation, xCord + imageBA.getWidth() / 2, yCord + imageBA.getHeight() / 2);
			if(side.getSide())
			gc.drawImage(imageBA, xCord, yCord, 39, 39);
			else
			gc.drawImage(imageRA, xCord, yCord, 39, 39);
			gc.restore();
			gc.drawImage(imageOb, 255, 337, 28, 63);
		}
		else if (flag == false && flag2 == true)
		{
			if(side.getSide())
			{
				gc.drawImage(imageField,0,0);
				gc.drawImage(imageSP, convertPixels(3), convertPixels(5), 39, 39);
				sp1 = new StartPos(3,5);
				gc.drawImage(imageSP, convertPixels(3), convertPixels(1), 39, 39);
				sp2 = new StartPos(3,1);
				gc.drawImage(imageSP, convertPixels(4.5), convertPixels(3), 39, 39);
				sp3 = new StartPos(4.5,3);
				gc.drawImage(imageSP, convertPixels(2), convertPixels(2), 39, 39);
				sp4 = new StartPos(2,2);
			}
			else
			{
				gc.drawImage(imageField,0,0);
				gc.drawImage(imageSP, convertPixels(13), convertPixels(5), 39, 39);
				sp1 = new StartPos(13,5);
				gc.drawImage(imageSP, convertPixels(8.2417), convertPixels(1), 39, 39);
				sp2 = new StartPos(8.2417,1);
				gc.drawImage(imageSP, convertPixels(14.5), convertPixels(3), 39, 39);
				sp3 = new StartPos(14.5,3);
				gc.drawImage(imageSP, convertPixels(12), convertPixels(2), 39, 39);
				sp4 = new StartPos(12,2);
			}
		}
		else
		{
			gc.drawImage(imageField,0,0);
			gc.drawImage(imageBS, 0, 0, 407, 400);
			gc.drawImage(imageRS, 408, 0, 407, 400);
		}
	}
	
	public void handleClick(int xc,int yc)
	{
		int x = xc;
		int y = yc;
		if(flag2 == false)
			side = new Side(x);
		if(side.getSide())
		{
			
			if(flag == false && flag2== true)
			{
				sx = (double) (convertMeters(x));
				sy = (double) (convertMeters(y));
				if(sp1.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp1.getX()), convertPixels(sp1.getY()));
					robot.setRotation(-90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp2.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp2.getX()), convertPixels(sp2.getY()));
					robot.setRotation(-90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp3.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp3.getX()), convertPixels(sp3.getY()));
					robot.setRotation(-90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp4.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp4.getX()), convertPixels(sp4.getY()));
					robot.setRotation(-90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
			}
			else
				System.out.println((convertMeters(x)) +" "+ (convertMeters(y)));
			flag2 = true;
		}
		else
		{
			
			if(flag == false && flag2==true)
			{
				sx = (double) (convertMeters(x));
				sy = (double) (convertMeters(y));
				if(sp1.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp1.getX()), convertPixels(sp1.getY()));
					robot.setRotation(90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp2.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp2.getX()), convertPixels(sp2.getY()));
					robot.setRotation(90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp3.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp3.getX()), convertPixels(sp3.getY()));
					robot.setRotation(90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
				else if(sp4.withinBoundries(sx, sy))
				{
					flag = true;
					robot = new RobotConversion(convertPixels(sp4.getX()), convertPixels(sp4.getY()));
					robot.setRotation(90);
					//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
				}
			}
			else
				System.out.println((convertMeters(x)) +" "+ (convertMeters(y)));
			flag2 = true;
		}
	}
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
