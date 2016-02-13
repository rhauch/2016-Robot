package org.frc4931.robot.commands.portcullis;

import org.frc4931.robot.Portcullis;
import org.strongback.command.Command;

public class Raise extends org.strongback.command.Command {
	private Portcullis portcullis;
	private static final double armTime = 0.0; // in seconds
	
	public Raise(Portcullis portcullis){
		this.portcullis = portcullis;
	}
	
	@Override
	public boolean execute() {
		portcullis.raise();
		Command.pause(armTime);
		portcullis.stop();
		return true;
	}
}