package org.frc4931.robot.roller;

import org.strongback.command.Command;

public class Suck extends Command {
	private Roller roller;
	
	public Suck(Roller roller) {
        super(roller);
		this.roller = roller;
	}
	
	@Override
	public boolean execute() {
		roller.suck();
        return roller.ballIn();
	}

    @Override
    public void end() {
        roller.stop();
    }
}