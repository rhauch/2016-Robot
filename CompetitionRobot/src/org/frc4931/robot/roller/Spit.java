package org.frc4931.robot.roller;

import org.strongback.command.Command;

public class Spit extends Command {
    private static final double SPIT_DURATION = 2.0; // in seconds

	private Roller roller;
	
	public Spit(Roller roller) {
        super(SPIT_DURATION, roller);

        this.roller = roller;
	}
	
	@Override
	public boolean execute() {
        roller.spit();

        // Automatically times out
        return false;
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