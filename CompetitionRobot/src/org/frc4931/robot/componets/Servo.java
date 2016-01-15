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

package org.frc4931.robot.componets;
/**
 * Servo interface lays out what a servo is
 * 
 * A servo is a motor that can be set to a position
 * 
 * @author Julian
 */
public interface Servo 
{
	/**
	 * getTargetAngle returns the last angle the servo was
	 * told to move to
	 * 
	 * @return double the last angle the servo was told 
	 * between {@link #getMinAngle()} and {@link #getMaxAngle()}
	 */
	public double getTargetAngle();
	
	/**
	 * moveToAngle() moves this {@link Servo} to specified angle if larger or 
	 * smaller clamp it between {@link #getMinAngle()} and {@link #getMaxAngle()}
	 * 
	 * @param angle the target angle
	 */
	public void moveToAngle(double angle);
	
	/**
	 * getMinAngle() returns the smallest angle possible by this servo
	 * @return double the minimum angle that can be achieved by this servo
	 */
	public double getMinAngle();
	
	/**
	 * getMaxAngle() returns the largest angle possible by this servo
	 * @return double the maximum angle that can be achieved by this servo
	 */
	public double getMaxAngle();
}
