package org.frc4931.robot;
import  org.frc4931.robot.componets.Servo;

/**
 * Periscope because we have discussed about having a camera mounted on a 
 * pitch and yaw head. 
 *  
 * @author Julian
 *
 *
 */
public class Periscope 
{
	private static final double DEPLOY = 90;
	private static final double RETRACT=0;
	private final Servo pitchController;
	private final Servo yawController;
	//TODO figure out how camera works
	
	/**
	 * creates periscope with 2 {@link Servo}s
	 * @param pitch {@link Servo} that controls pitch
	 * @param yaw {@link Servo} that controls yaw
	 */
	public Periscope(Servo pitch,Servo yaw)
	{
		pitchController=pitch;
		yawController=yaw;
	}
	
	/**
	 *deploy() turns {@link Servo} to lift camera arm
	 */
	public void deploy()
	{
		pitchController.moveToAngle(DEPLOY);
	}
	
	/**
	 * retract() turns {@link Servo} to lower camera arm
	 */
	public void retract()
	{
		pitchController.moveToAngle(RETRACT);
	}
	
	/**
	 * getPitch() returns current pitch angle between 
	 * 0(being parallel to the floor) and 90 degrees
	 * @return double the target angle
	 */
	public double getPitch()
	{
		return pitchController.getTargetAngle();
	}
	
	/**
	* setPitch() sets pitch between 0(being parallel to the floor) and 90 degrees, 
	* if larger than 90 round down to 90, if smaller than 0 round up to 0
	* 
	* @param angle the inputed angles 
	*/
	public void setPitch(double angle)
	{
		pitchController.moveToAngle(angle);
	}
	
	/**
	 * returns current yaw angle between 0(being parallel to the floor)-180 degrees
	 *  (90 being the middle which is facing forward
	 * @return double the target angle
	 */
	public double getYaw()
	{
		return yawController.getTargetAngle();
	}
	
	/**
	 *  sets pitch between 0(being parallel to the floor)-180 degrees, 
	 *  if larger than 180 round down to 180, if smaller than 0 round up to 0
	 * 
	 * @param angle the angle inputed
	 */
	public void setYaw(double angle)
	{
		yawController.moveToAngle(angle);
	}
}
	
