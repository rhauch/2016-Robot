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
package org.frc4931.robot.components;

/**
 * This is a mock class to imitate a servo that use the {@link Servo}`
 * @author Julian
 *
 */
public class MockServo implements Servo 
{
	private double angle;
	private double minAngle;
	private double maxAngle;
	
	/**
	 * This gets the servo's target angle
	 * @return double the angle
	 */
	@Override
	public double getTargetAngle() {
		return angle;
	}

	/**
	 * This tells the servo to move to that angle
	 * @param angle the inputed angle
	 */
	@Override
	public void moveToAngle(double angle) {
		this.angle=angle;
		
	}

	/**
	 * This gets the minimum angle the servo can move to
	 * @return double: the Min Angle
	 */
	@Override
	public double getMinAngle() {
		return minAngle;
	}

	/**
	 * This gets the maximum angle the servo can move to
	 * @return double: the Max Angle
	 */
	@Override
	public double getMaxAngle() {
		return maxAngle;
	}
	
	/**
	 * This sets the servos minimum angle
	 * @param angle inputed set angle
	 */
	public void setMinAngle(double angle)
	{
		minAngle=angle;
	}
	
	/**
	 * This sets the servos maximum angle
	 * @param angle inputed set angle
	 */
	public void setMaxAngle(double angle)
	{
		maxAngle=angle;
	}
}
