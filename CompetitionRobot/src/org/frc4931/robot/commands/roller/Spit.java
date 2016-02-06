package org.frc4931.robot.commands.roller;

import org.frc4931.robot.Roller;
import org.strongback.command.Command;

public class Spit extends org.strongback.command.Command{
	private Roller roller;
	private static final double spitTime = 0.0; // in seconds
	private double startTime;
	
	public Spit(Roller roller){
		this.roller = roller;
	}
	
	public void initialize(){
		startTime = System.currentTimeMillis()/1000;
	}
	
	@Override
	public boolean execute() {
		if((System.currentTimeMillis()/1000)-startTime >= spitTime){
			roller.stop();
			return true;
		}
		else{
			roller.spit();
			return false;
		}
	}
}