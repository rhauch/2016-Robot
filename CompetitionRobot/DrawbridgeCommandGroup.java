package org.frc4931.robot.command;

import org.frc4931.robot.arm.Arm;
import org.frc4931.robot.drive.DriveSystem;

public class DrawbridgeCommandGroup extends org.strongback.command.CommandGroup
{
	private DriveSystem drive;
	private Arm armCommand;				
	public DrawbridgeCommandGroup(Arm a,DriveSystem d)
	{
		drive=d;
		armCommand=a;
		sequentially(new MoveArmTo(),new DriveStraight(drive,1,1),
				simultaneously(new MoveArmTo(),new DriveStraight(drive,-1,-1)));
	}
}
