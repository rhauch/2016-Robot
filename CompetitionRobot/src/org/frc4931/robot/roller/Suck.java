package org.frc4931.robot.roller;

import org.frc4931.robot.components.InfraredSensor;
import org.strongback.command.Command;
import org.strongback.components.Switch;
import org.strongback.components.ui.FlightStick;


public class Suck extends Command {
	private final Roller roller;
	private final Switch shouldContinue;
	
	public Suck(Roller roller,Switch swtch) {
        super(roller);
        shouldContinue=swtch;
		this.roller = roller;
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