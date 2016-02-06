package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import static application.MetersToPixels.convertPixels;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static application.MetersToPixels.convertMeters;

public class Map {
	private RobotConversion robot;
	private StartPos[] sp = new StartPos[6];
	private Side side;
	private Rectangle[] startBS = {new Rectangle(1,1,39,39),new Rectangle(7,5,39,39),new Rectangle(4,5,39,39)
		,new Rectangle(3,2,39,39),new Rectangle(3,6,39,39),new Rectangle(4,7.2,39,39)};
	private Rectangle[] startRS = {new Rectangle(8.2,4.1,39,39),new Rectangle(9.4,5.2,39,39),new Rectangle(14.8,6.8,39,39)
		,new Rectangle(13.9,3.9,39,39),new Rectangle(11.6,3.1,39,39),new Rectangle(15.5,5.3,39,39)};
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
				for(int i=0;i<6;i++)
				{
				gc.fillRect(convertPixels(startBS[i].getX()), convertPixels(startBS[i].getY()), startBS[i].getWidth(), startBS[i].getHeight());
				sp[i] = new StartPos(startBS[i].getX(),startBS[i].getY());
				}
			}
			else
			{
				gc.drawImage(imageField,0,0);
				for(int i=0;i<6;i++)
				{
				gc.fillRect(convertPixels(startRS[i].getX()), convertPixels(startRS[i].getY()), startRS[i].getWidth(), startRS[i].getHeight());
				sp[i] = new StartPos(startRS[i].getX(),startRS[i].getY());
				}
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
				for(int i = 0; i<6;i++)
				{
					if(sp[i].withinBoundries(sx, sy))
					{
						flag = true;
						robot = new RobotConversion(convertPixels(sp[i].getX()), convertPixels(sp[i].getY()));
						robot.setRotation(-90);
						//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
					}
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
				for(int i = 0; i<6;i++)
				{
					if(sp[i].withinBoundries(sx, sy))
					{
						flag = true;
						robot = new RobotConversion(convertPixels(sp[i].getX()), convertPixels(sp[i].getY()));
						robot.setRotation(90);
						//System.out.println((x/PIXELS_PER_METER) +" "+ (y/PIXELS_PER_METER));
					}
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
