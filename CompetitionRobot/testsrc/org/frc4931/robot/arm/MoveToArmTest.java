package org.frc4931.robot.arm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.strongback.command.Command;
import org.strongback.mock.Mock;
import org.strongback.mock.MockAngleSensor;
import org.strongback.mock.MockMotor;
import org.strongback.mock.MockSwitch;

public class MoveToArmTest {
	private MockMotor t;
	private Arm a;
	private MockAngleSensor as;
	private MockSwitch s;

	@Before
	public void beforeEach() {
		as = Mock.angleSensor();
		t = Mock.stoppedMotor();
		a = new Arm(t, as, s);
	}

	@Test
	public void moveUpToZero() {
		Command mat = new MoveArmTo(0, a);
		as.setAngle(-30);
		assertFalse(mat.execute());
		assertEquals(1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(-10);
		assertFalse(mat.execute());
		assertEquals(1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(0);
		assertTrue(mat.execute());
		mat.end();
		assertEquals(0, t.getSpeed(), 0.1);
	}

	@Test
	public void moveDownToZero() {
		Command mat = new MoveArmTo(0, a);
		as.setAngle(30);
		assertFalse(mat.execute());
		assertEquals(-1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(10);
		assertFalse(mat.execute());
		assertEquals(-1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(0);
		assertTrue(mat.execute());
		mat.end();
		assertEquals(0, t.getSpeed(), 0.1);
	}

	@Test
	public void moveBackAndForth() {
		Command mat = new MoveArmTo(0, a);
		as.setAngle(30);
		assertFalse(mat.execute());
		assertEquals(-1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(-30);
		assertFalse(mat.execute());
		assertEquals(1, Math.signum(t.getSpeed()), 0.1);
		as.setAngle(0);
		assertTrue(mat.execute());
		mat.end();
		assertEquals(0, t.getSpeed(), 0.1);
	}
}
