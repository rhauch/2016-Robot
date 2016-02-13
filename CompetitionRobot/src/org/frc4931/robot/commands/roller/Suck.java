package org.frc4931.robot.commands.roller;

import org.frc4931.robot.Roller;
import org.strongback.Strongback;

public class Suck extends org.strongback.command.Command{
	private Roller roller;
	
	public Suck(Roller roller){
		this.roller = roller;
	}
	
	@Override
	public boolean execute(){
		if(roller.ballIn()==true){
			roller.stop();
			return true;
		}
		else{
			roller.suck();
			return false;
		}
	}
}