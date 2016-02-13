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

package org.frc4931.robot.periscope;
import static org.fest.assertions.Assertions.assertThat;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.vision.USBCamera;
import org.frc4931.robot.components.MockServo;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link Periscope}
 * @author Julian
 *
 */
public class TestPeriscope {
	private MockServo pitch;
	private MockServo yaw;
	private Periscope periscope;
	
	
	@Before
	public void beforeEach() {
		pitch = new MockServo();
		yaw   = new MockServo();
		periscope = new Periscope(null, pitch,yaw);
	}
	
	/**
	 * Tests {@link Periscope#deploy()}
	 */
	@Test
	public void shouldDeploy() {
		pitch.moveToAngle(Periscope.MIN_PITCH);
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MIN_PITCH);
		periscope.deploy();
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MAX_PITCH);
		
	}
	
	/**
	 * Tests {@link Periscope#retract()}
	 */
	@Test
	public void shouldRetract() {
		pitch.moveToAngle(Periscope.MAX_PITCH);
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MAX_PITCH);
		periscope.retract();
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MIN_PITCH);
	}
	
	/**
	 * Tests {@link Periscope#setPitch(double)}
	 */
	@Test
	public void shouldHighSetPitch() {
		pitch.moveToAngle(0);
		assertThat(pitch.getTargetAngle()).isEqualTo(0);
		periscope.setPitch(Periscope.MAX_PITCH + 90);
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MAX_PITCH);
	}
	
	/**
	 * Tests {@link Periscope#setPitch(double)}
	 */
	@Test
	public void shouldLowSetPitch() {
		pitch.moveToAngle(0);
		assertThat(pitch.getTargetAngle()).isEqualTo(0);
		periscope.setPitch(Periscope.MIN_PITCH - 90);
		assertThat(pitch.getTargetAngle()).isEqualTo(Periscope.MIN_PITCH);
	}
	
	/**
	 * Tests {@link Periscope#setYaw(double)}
	 */
	@Test
	public void shouldLowSetYaw() {
		pitch.moveToAngle(0);
		assertThat(yaw.getTargetAngle()).isEqualTo(0);
		periscope.setYaw(Periscope.MIN_YAW - 90);
		assertThat(yaw.getTargetAngle()).isEqualTo(Periscope.MIN_YAW);
	}
	
	/**
	 * Tests {@link Periscope#setYaw(double)}
	 */
	@Test
	public void shouldHighSetYaw() {
		pitch.moveToAngle(0);
		assertThat(yaw.getTargetAngle()).isEqualTo(0);
		periscope.setYaw(Periscope.MAX_YAW + 90);
		assertThat(yaw.getTargetAngle()).isEqualTo(Periscope.MAX_YAW);
	}
}
