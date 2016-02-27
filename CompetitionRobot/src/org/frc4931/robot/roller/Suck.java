package org.frc4931.robot.roller;

import org.strongback.command.Command;
import org.strongback.components.Switch;


public class Suck extends Command {
	private final Roller roller;
	private final Switch shouldContinue;
	
	public Suck(Roller roller,Switch swtch) {
        super(roller);
        this.roller=roller;
        shouldContinue=swtch;
	}
	
	@Override
	public boolean execute() {
		roller.suck();
		return !shouldContinue.isTriggered()|| roller.ballIn();
	}

    @Override
    public void end() {
        roller.stop();
    }
}