package org.frc4931.testcommands;

import static org.fest.assertions.Assertions.assertThat;
import org.frc4931.commands.Periscope.DeployCommand;
import org.frc4931.robot.Periscope;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

public class DeployCommandTest 
{
	private MockServo p;
	private MockServo y;
	private Periscope m;
	private DeployCommand d;
	
	@Before
	public void beforeEach()
	{
		p= new MockServo();
		y= new MockServo();
		m= new Periscope(p,y);
		d= new DeployCommand(m);
	}
	
	@Test
	public void shouldDeployCommand()
	{
		p.moveToAngle(0);
		assertThat(p.getTargetAngle()).isEqualTo(0);
		d.execute();
		assertThat(p.getTargetAngle()).isEqualTo(90);
	}
}
