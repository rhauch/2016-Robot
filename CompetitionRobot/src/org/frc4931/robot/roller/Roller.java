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

package org.frc4931.robot.roller;

import org.frc4931.robot.components.InfraredSensor;
import org.strongback.command.Requirable;
import org.strongback.components.Motor;

public class Roller implements Requirable 
{
	private final InfraredSensor iA;
	private final InfraredSensor iB;
	private final Motor wheels;
	
	public Roller(Motor wheels,InfraredSensor iRSA,InfraredSensor iRSB) {
		this.wheels = wheels;
		iA = iRSA;
		iB=  iRSB;
	}
	
	/*
	 * Pulls a ball in.
	 */
	public void suck() {
		wheels.setSpeed(-1.0);
	}
	
	/*
	 * Pushes a ball out.
	 */
	public void spit() {
		wheels.setSpeed(1.0);
	}
	
	/*
	 * Stops the wheels (used after suck() or spit()).
	 */
	public void stop(){
		wheels.stop();
	}
	
	/*
	 * Checks if a ball is in.
	 */
	public boolean ballIn() {
		return iA.getInput() && iB.getInput();
	}
}