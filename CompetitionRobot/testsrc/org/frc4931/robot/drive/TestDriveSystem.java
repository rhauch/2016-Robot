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

package org.frc4931.robot.drive;

import org.frc4931.robot.components.MockIMU;
import org.junit.Before;
import org.junit.Test;
import org.strongback.drive.TankDrive;
import org.strongback.mock.Mock;
import org.strongback.mock.MockMotor;

import static org.junit.Assert.assertEquals;

public class TestDriveSystem {
    private MockMotor leftMotor;
    private MockMotor rightMotor;
    private MockIMU imu;
    private DriveSystem driveSystem;

    @Before
    public void beforeEach() {
        leftMotor = Mock.stoppedMotor();
        rightMotor = Mock.stoppedMotor();
        imu = new MockIMU();
        driveSystem = new DriveSystem(new TankDrive(leftMotor, rightMotor), imu);
    }

    /**
     * Tests {@link DriveSystem#arcade(double, double)}.
     */
    @Test
    public void testArcade() {
        driveSystem.arcade(1.0, 0.0);
        assertEquals(1.0, leftMotor.getSpeed(), 0.0);
        assertEquals(1.0, rightMotor.getSpeed(), 0.0);

        driveSystem.arcade(-1.0, 0.0);
        assertEquals(-1.0, leftMotor.getSpeed(), 0.0);
        assertEquals(-1.0, rightMotor.getSpeed(), 0.0);

        driveSystem.arcade(0.0, 1.0);
        assertEquals(-1.0, leftMotor.getSpeed(), 0.0);
        assertEquals(1.0, rightMotor.getSpeed(), 0.0);

        driveSystem.arcade(0.0, -1.0);
        assertEquals(1.0, leftMotor.getSpeed(), 0.0);
        assertEquals(-1.0, rightMotor.getSpeed(), 0.0);
    }

    /**
     * Tests {@link DriveSystem#stop()} to make sure it fully stops the drivetrain.
     */
    @Test
    public void testStop() {
        leftMotor.setSpeed(1.0);
        rightMotor.setSpeed(1.0);

        driveSystem.stop();
        assertEquals(0.0, leftMotor.getSpeed(), 0.0);
        assertEquals(0.0, leftMotor.getSpeed(), 0.0);
    }
}
