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

import org.frc4931.robot.Robot;
import org.frc4931.robot.arm.Arm;
import org.frc4931.robot.arm.CalibrateArm;
import org.frc4931.robot.arm.MoveArmTo;
import org.strongback.command.Command;
import org.strongback.command.CommandGroup;
import org.strongback.components.DistanceSensor;

/**
 * The command that performs autonomous mode for going through the cheval de frise.
 */
public class AutoChevalDeFrise extends CommandGroup {

    /**
     * Create a new command that drives forward at the given speed and duration.
     * 
     * @param drive the chassis
     * @param arm the arm subsystem
     * @param distanceToObstacle the distance sensor
     */
    public AutoChevalDeFrise(DriveSystem drive, Arm arm, DistanceSensor distanceToObstacle) {
        sequentially(simultaneously(new CalibrateArm(arm),
                                    new DriveUntil(drive, 0.43, () -> {
                                        return distanceToObstacle.getDistanceInInches() < 13.0;
                                    })),
                     new MoveArmTo(arm, Robot.ARM_CHEVAL_DOWN_ANGLE),
                     Command.pause(1.0),
                     new TimedDrive(drive, 0.8, 0.5),
                     simultaneously(new MoveArmTo(arm, 5.0),
                                    new TimedDrive(drive, 0.8, 1.5)));
    }
}