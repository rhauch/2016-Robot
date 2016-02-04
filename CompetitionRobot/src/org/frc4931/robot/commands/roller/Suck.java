package org.frc4931.robot.commands.roller;

import org.frc4931.robot.Roller;

public class Suck extends org.strongback.command.Command{
	private Roller roller;
	
	public Suck(Roller roller){
		this.roller = roller;
	}
	
	@Override
	public boolean execute() {
		roller.suck();
		while(roller.ballIn() == false);
		roller.stop();
		return true;
	}
}