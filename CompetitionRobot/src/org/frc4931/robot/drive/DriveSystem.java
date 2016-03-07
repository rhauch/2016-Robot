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

import org.strongback.command.Requirable;
import org.strongback.components.DistanceSensor;
import org.strongback.drive.TankDrive;

/**
 * The subsystem of the robot that controls the drive train.
 */
public class DriveSystem implements Requirable {
    private final TankDrive drive;
    private final DistanceSensor forwardProximity;

    /**
     * Constructs a new drive system given a TankDrive it should control.
     * @param drive The drive train that will be controlled by the subsystem.
     * @param forwardProximity The distance sensor that measures the proximity to objects in front of the robot.
     */
    public DriveSystem(TankDrive drive, DistanceSensor forwardProximity) {
        this.drive = drive;
        this.forwardProximity = forwardProximity;
    }

    /**
     * Sets the speed in terms of a lateral (drive) speed  and a turn speed.
     * @param driveSpeed The speed at which to drive. Positive values mean forward; negative values mean backward.
     * @param turnSpeed The speed at which to turn. Positive values mean leftward; negative values mean rightward.
     */
    public void arcade(double driveSpeed, double turnSpeed) {
        drive.arcade(driveSpeed, turnSpeed);
    }

    /**
     * Sends a signal to the drive train to stop motion.
     */
    public void stop() {
        drive.stop();
    }

    public DistanceSensor getForwardProximity() {
        return forwardProximity;
    }
}
