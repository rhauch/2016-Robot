package org.frc4931.robot.commands.portcullis;

import org.frc4931.robot.Portcullis;
import org.frc4931.robot.drive.Drive;
import org.frc4931.robot.drive.DriveSystem;

public class Clear extends org.strongback.command.CommandGroup{
	private static final double driveSpeed = 0.0; // in percent throttle
	private static final double driveTime = 0.0; // in seconds
	private static final double driveSpeed2 = 0.0; // in percent throttle
	private static final double driveTime2 = 0.0; // in seconds
	
	public Clear(Portcullis portcullis, DriveSystem driveSystem){
		sequentially(new Drive(driveSystem,driveSpeed,0.0,driveTime),new Raise(portcullis),
				new Drive(driveSystem,driveSpeed2,0.0,driveTime2),new Lower(portcullis));
	}
}