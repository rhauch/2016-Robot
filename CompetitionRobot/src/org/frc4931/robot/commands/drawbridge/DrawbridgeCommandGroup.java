package org.frc4931.robot.commands.drawbridge;

import org.frc4931.robot.arm.Arm;

public class DrawbridgeCommandGroup extends org.strongback.command.CommandGroup
{
	
	private Arm arm;
	
	public DrawbridgeCommandGroup(Arm a)
	{
		this.raise();
		this.simultaneously(commands)
		
	}
	public void raise()
	{
		arm.raise();
	}
	public void stop()
	{
		arm.stop();
	}
	public void lower()
	{
		arm.lower();
	}
}
