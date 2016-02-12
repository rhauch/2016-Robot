/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 * 
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.frc4931.robot;

import org.frc4931.robot.drive.DriveSystem;
import org.strongback.command.Command;

/**
 * The command that drives the robot at a constant forward and turn speed for a specific duration.
 */
public class TimedDriveCommand extends Command {

    private final DriveSystem drive;
    private final double driveSpeed;
    private final double turnSpeed;
    
    /**
     * Create a new autonomous command.
     * @param drive the chassis
     * @param driveSpeed the speed at which to drive forward; should be [-1.0, 1.0]
     * @param turnSpeed the speed at which to turn; should be [-1.0, 1.0]
     * @param duration the duration of this command; should be positive
     */
    public TimedDriveCommand( DriveSystem drive, double driveSpeed, double turnSpeed, double duration ) {
        super(duration, drive);
        this.drive = drive;
        this.driveSpeed = driveSpeed;
        this.turnSpeed = turnSpeed;
    }
    
    @Override
    public boolean execute() {
        drive.arcade(driveSpeed, turnSpeed);
        return false;   // not complete; it will time out automatically
    }
    
    @Override
    public void interrupted() {
        drive.stop();
    }
    
    @Override
    public void end() {
        drive.stop();
    }

}