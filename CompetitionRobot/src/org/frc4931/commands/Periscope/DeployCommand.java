package org.frc4931.commands.Periscope;

import org.frc4931.robot.Periscope;


public class DeployCommand extends org.strongback.command.Command
{
	private Periscope m;
	public DeployCommand(Periscope p)
	{
		super(p);
		m=p;
	}

	@Override
	public boolean execute() 
	{
		m.deploy();
		if(m.getPitch()==90)
		{
			return true;
		}
		return false;
	}
	
}
