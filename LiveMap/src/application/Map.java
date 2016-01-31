package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Map {
	private RobotConversion robot;
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
			gc.drawImage(imageSP, 332, 141, 39, 39);
			gc.drawImage(imageSP, 332, 241, 39, 39);
		}
	}
	
	public void handleClick(int x,int y)
	{
		if(flag == false)
		{
			sx = x;
			sy = y;
			if((332<=sx && sx<=371) && (141<=sy && sy<=180))
			{
			flag = true;
			robot = new RobotConversion(332, 141);
			System.out.println(x +" "+ y +" " + (x+50) +" "+ (y+50));
			}
			else if((332<=sx && sx<=382) && (241<=sy && sy<=291))
			{
			flag = true;
			robot = new RobotConversion(332, 241);
			System.out.println(x +" "+ y +" " + (x+50) +" "+ (y+50));
			}
		}
		else
			System.out.println(x +" "+ y +" " + (x+50) +" "+ (y+50));
	}
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
