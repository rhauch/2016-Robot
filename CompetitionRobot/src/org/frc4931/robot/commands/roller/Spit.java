package org.frc4931.robot.commands.roller;

import org.frc4931.robot.Roller;
import org.strongback.command.Command;

public class Spit extends org.strongback.command.Command{
	private Roller roller;
	private static final double spitTime = 0.0; // in seconds
	
	public Spit(Roller roller){
		this.roller = roller;
	}
	
	@Override
	public boolean execute() {
		roller.spit();
		Command.pause(spitTime);
		roller.stop();
		return true;
	}
}