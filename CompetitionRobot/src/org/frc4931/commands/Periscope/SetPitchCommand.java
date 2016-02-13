
package org.frc4931.commands.Periscope;

import org.frc4931.robot.Periscope;


public class SetPitchCommand extends org.strongback.command.Command
{
	private Periscope m;
	private double t;
	public SetPitchCommand(Periscope p,double d)
	{
		super(p);
		m=p;
		t=d;
	}

	@Override
	public boolean execute() 
	{
		m.setPitch(t);
		return false;
	}
	
}
