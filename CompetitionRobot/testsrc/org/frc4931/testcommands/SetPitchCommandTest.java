package org.frc4931.testcommands;
import static org.fest.assertions.Assertions.assertThat;
import org.frc4931.commands.Periscope.SetPitchCommand;
import org.frc4931.robot.Periscope;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

public class SetPitchCommandTest 
{
	private MockServo p;
	private MockServo y;
	private Periscope m;
	private SetPitchCommand d;
	private double angle;
	
	@Before
	public void beforeEach()
	{
		angle  = 90;
		p= new MockServo();
		y= new MockServo();
		m= new Periscope(p,y);
		d= new SetPitchCommand(m,angle);
	}
	
	@Test
	public void shouldSetPitchCommand()
	{
		p.moveToAngle(0);
		assertThat(p.getTargetAngle()).isEqualTo(0);
		d.execute();
		assertThat(p.getTargetAngle()).isEqualTo(90);
	}
}

