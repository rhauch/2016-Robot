package org.frc4931.robot.SallyPortCommands;

import org.frc4931.robot.TimedDriveCommand;
import org.frc4931.robot.arm.Arm;
import org.frc4931.robot.arm.MoveArmTo;
import org.frc4931.robot.drive.DriveSystem;

public class ClearSallyPort extends org.strongback.command.CommandGroup
{
	private final double ANGLE = 0.0;
	private final double DRIVE_SPEED = -20;
	private final double DRIVE_TIME = 2;
	public ClearSallyPort(DriveSystem d,Arm a)
	{
		//TODO fine tune this
		sequentially(new MoveArmTo(ANGLE,a), new TimedDriveCommand(d, DRIVE_SPEED, 0, DRIVE_TIME));
	}
}
