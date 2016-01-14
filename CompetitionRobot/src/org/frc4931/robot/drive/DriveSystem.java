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

import org.strongback.drive.TankDrive;

/**
 * The subsystem of the robot that controls the drive train.
 *
 * Unique to this drive system is the ability to reverse the direction of the controls. This allows the driver to
 * utilize the arm in the back as well as modules in the front of the robot. The default direction is with the module
 * side in front.
 */
public class DriveSystem {
    private final TankDrive drive;

    private boolean directionFlipped;

    /**
     * Constructs a new drive system with the default orientation given a pair of drive train motors.
     * @param drive The drive train that will be controlled by the subsystem.
     */
    public DriveSystem(TankDrive drive) {
        this.drive = drive;
        directionFlipped = false;
    }

    /**
     * Sets the speed in terms of a lateral (drive) speed  and a turn speed.
     * @param driveSpeed The speed at which to drive. Positive values mean forward; negative values mean backward.
     * @param turnSpeed The speed at which to turn. Positive values mean leftward; negative values mean rightward.
     */
    public void arcade(double driveSpeed, double turnSpeed) {
        if (directionFlipped) {
            driveSpeed *= -1.0;
        }

        drive.arcade(driveSpeed, turnSpeed);
    }

    /**
     * Sends a signal to the drive train to stop motion.
     */
    public void stop() {
        drive.stop();
    }

    /**
     * Determines whether the input for driveSpeed is being flipped.
     * This can also be used to determine whether the arm is in front: true means the arm is in front, whereas false
     * means the module is in front.
     * @return true if the drive speed is being flipped.
     */
    public boolean isDirectionFlipped() {
        return directionFlipped;
    }

    /**
     * Sets whether the driveSpeed in {@link #arcade(double, double)} will be flipped in future calls.
     * If true is used, the drive speed will be negated, thus causing the arm side to act as the logical front of the
     * robot. if false is used, the drive speed will remain unaltered, making the module side the logical front.
     * @param directionFlipped Whether the drive speed should be flipped.
     */
    public void setDirectionFlipped(boolean directionFlipped) {
        this.directionFlipped = directionFlipped;
    }

    /**
     * Toggles the current state of the driveSpeed flip.
     * If the module side is in front, the arm side will become front, and vice versa. Likewise, if driveSpeed has been
     * negated in past calls to {@link #arcade(double, double)}, they will no longer be.
     */
    public void toggleDirectionFlipped() {
        directionFlipped = !directionFlipped;
    }
}
