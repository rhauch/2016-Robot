
package org.frc4931.commands.Periscope;

import org.frc4931.robot.Periscope;


public class RetractCommand extends org.strongback.command.Command
{
	private Periscope m;
	public RetractCommand(Periscope p)
	{
		super(p);
		m=p;
	}

	@Override
	public boolean execute() 
	{
		m.retract();
		if(m.getPitch()==90)
		{
			return true;
		}
		return false;
	}
	
}
