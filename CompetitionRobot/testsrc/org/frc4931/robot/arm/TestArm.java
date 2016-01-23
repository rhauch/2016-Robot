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

package org.frc4931.robot.arm;

import org.junit.Before;
import org.junit.Test;
import org.strongback.mock.Mock;
import org.strongback.mock.MockAngleSensor;
import org.strongback.mock.MockMotor;
import org.strongback.mock.MockSwitch;

import static org.junit.Assert.assertEquals;

public class TestArm {
    private MockMotor motor;
    private MockAngleSensor angleSensor;
    private MockSwitch homeSwitch;
    private Arm arm;

    @Before
    public void beforeEach() {
        motor = Mock.stoppedMotor();
        angleSensor = Mock.angleSensor();
        homeSwitch = Mock.notTriggeredSwitch();
        arm = new Arm(motor, angleSensor, homeSwitch);
    }

    @Test
    public void testRaise() {
        arm.raise();
        assertEquals(Arm.MOTOR_SPEED, motor.getSpeed(), 0.0);
    }

    @Test
    public void testLower() {
        arm.lower();
        assertEquals(-Arm.MOTOR_SPEED, motor.getSpeed(), 0.0);
    }

    @Test
    public void testStop() {
        motor.setSpeed(1.0);

        arm.stop();
        assertEquals(0.0, motor.getSpeed(), 0.0);
    }

    @Test
    public void testZero() {
        angleSensor.setAngle(85.0);
        assertEquals(85.0, arm.getAngle(), 0.0);

        arm.zero();

        angleSensor.setAngle(120.0);
        assertEquals(35.0, arm.getAngle(), 0.0);
    }
}

