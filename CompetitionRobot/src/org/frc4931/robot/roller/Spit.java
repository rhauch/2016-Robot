package org.frc4931.robot.roller;

import org.strongback.command.Command;
import org.strongback.components.Switch;

public class Spit extends Command {

	private final Roller roller;
	private final Switch shouldContinue;
	
	public Spit(Roller roller, Switch swtch) {
        super(roller);
        shouldContinue = swtch;
        this.roller = roller;
	}
	
	@Override
	public boolean execute() {
		//made by Jacob
		roller.spit();
		return !shouldContinue.isTriggered();
	}

    @Override
    public void interrupted() {
        roller.stop();
    }

    @Override
    public void end() {
        roller.stop();
    }
}