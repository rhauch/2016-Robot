package org.frc4931.robot;

import org.strongback.components.Motor;
import org.strongback.components.Switch;

public class Roller {
	private Motor wheels; // the motor for the wheels that pull a ball in or push one out
	private Switch ballIn; // a switch inside the robot that indicates if a ball is in
	
	public Roller(Motor wheels, Switch ballIn){
		this.wheels = wheels;
		this.ballIn = ballIn;
	}
	
	/*
	 * Pulls a ball in.
	 */
	public void suck(){
		wheels.setSpeed(1.0);
	}
	
	/*
	 * Pushes a ball out.
	 */
	public void spit(){
		wheels.setSpeed(-1.0);
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
	public boolean ballIn(){
		return ballIn.isTriggered();
	}
}