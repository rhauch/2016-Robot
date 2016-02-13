/*
 * Copyright (c) 2016 FRC Team 4931
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.frc4931.robot;
import static org.fest.assertions.Assertions.assertThat;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Periscope}
 * @author Julian
 *
 */
public class TestPersicope 
{
	private MockServo pitch;
	private MockServo yaw;
	private Periscope p;
	
	
	@Before
	public void beforeEach()
	{
		pitch = new MockServo();
		yaw   = new MockServo();
		p     = new Periscope(pitch,yaw);
	}
	
	/**
	 * Tests {@link deploy()}
	 */
	@Test
	public void shouldDeploy()
	{
		pitch.moveToAngle(0);
		assertThat(pitch.getTargetAngle()).isEqualTo(0);
		p.deploy();
		assertThat(pitch.getTargetAngle()).isEqualTo(90);
		
	}
	
	/**
	 * Tests {@link Retract()}
	 */
	public void shouldRetract()
	{
		pitch.moveToAngle(90);
		assertThat(pitch.getTargetAngle()).isEqualTo(90);
		p.deploy();
		assertThat(pitch.getTargetAngle()).isEqualTo(00);
	}
	
	/**
	 * Tests {@link setPitch()}
	 */
	public void shouldHighSetPitch()
	{
		pitch.moveToAngle(0);
		assertThat(pitch.getTargetAngle()).isEqualTo(00);
		p.setPitch(180);
		assertThat(pitch.getTargetAngle()).isEqualTo(90);
	}
	
	/**
	 * Tests {@link setPitch()}
	 */
	public void shouldLowSetPitch()
	{
		pitch.moveToAngle(0);
		assertThat(pitch.getTargetAngle()).isEqualTo(0);
		p.setPitch(-180);
		assertThat(pitch.getTargetAngle()).isEqualTo(0);
	}
	
	/**
	 * Tests {@link setYaw()}
	 */
	public void shouldLowSetYaw()
	{
		pitch.moveToAngle(0);
		assertThat(yaw.getTargetAngle()).isEqualTo(0);
		p.setYaw(-180);
		assertThat(yaw.getTargetAngle()).isEqualTo(0);
	}
	
	/**
	 * Tests {@link setYaw()}
	 */
	public void shouldHighSetYaw()
	{
		pitch.moveToAngle(0);
		assertThat(yaw.getTargetAngle()).isEqualTo(0);
		p.setYaw(1800);
		assertThat(yaw.getTargetAngle()).isEqualTo(180);
	}
}
