/*
 * Copyright (c) 2016 FRC Team 4931
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
		if(angle>90)
			angle=90;
		else if(angle<0)
			angle=0; 
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
		if(angle>180)
		{
			angle=180.0;
		}
		if(angle<0)
		{
			angle=0.0;
		}
		yawController.moveToAngle(angle);
	}
}
	
