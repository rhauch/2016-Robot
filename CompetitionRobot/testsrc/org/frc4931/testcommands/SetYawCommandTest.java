package org.frc4931.testcommands;
import static org.fest.assertions.Assertions.assertThat;

import org.frc4931.commands.Periscope.SetYawCommand;
import org.frc4931.robot.Periscope;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

public class SetYawCommandTest 
{
	private MockServo p;
	private MockServo y;
	private Periscope m;
	private SetYawCommand d;
	private double angle;
	
	@Before
	public void beforeEach()
	{
		angle  = 90;
		p= new MockServo();
		y= new MockServo();
		m= new Periscope(p,y);
		d= new SetYawCommand(m,angle);
	}
	
	@Test
	public void shouldSetYawCommand()
	{
		y.moveToAngle(0);
		assertThat(y.getTargetAngle()).isEqualTo(0);
		d.execute();
		assertThat(y.getTargetAngle()).isEqualTo(90);
	}
}

