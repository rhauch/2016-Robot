package org.frc4931.testcommands;

import static org.fest.assertions.Assertions.assertThat;
import org.frc4931.commands.Periscope.DeployCommand;
import org.frc4931.commands.Periscope.RetractCommand;
import org.frc4931.robot.Periscope;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

public class RetractCommandTest 
{
	private MockServo p;
	private MockServo y;
	private Periscope m;
	private RetractCommand d;
	
	@Before
	public void beforeEach()
	{
		p= new MockServo();
		y= new MockServo();
		m= new Periscope(p,y);
		d= new RetractCommand(m);
	}
	
	@Test
	public void shouldRetractCommand()
	{
		p.moveToAngle(90);
		assertThat(p.getTargetAngle()).isEqualTo(90);
		d.execute();
		assertThat(p.getTargetAngle()).isEqualTo(0);
	}
}

