package org.frc4931.robot;

import org.frc4931.robot.arm.Arm;

public class DrawbridgeCommandGroup extends org.strongback.command.Command
{
	
	private Arm arm;
	@Override
	public boolean execute() 
	{
		super(arm)
		
		return false;
	}

}
