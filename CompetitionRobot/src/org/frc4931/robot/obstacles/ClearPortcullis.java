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

package org.frc4931.robot.obstacles;

import org.frc4931.robot.arm.Arm;
import org.frc4931.robot.arm.MoveArmTo;
import org.frc4931.robot.drive.DriveSystem;
import org.frc4931.robot.drive.TimedDrive;
import org.strongback.command.CommandGroup;

//FIXME Runs in the background stopping the drive train
public class ClearPortcullis extends CommandGroup {
	private static final double DRIVE_SPEED = 0.0; // in percent throttle
	private static final double DRIVE_TIME = 0.0; // in seconds
    private static final double RAISED_ANGLE = 90.0; // in degrees
	private static final double DRIVE_SPEED_2 = 0.0; // in percent throttle
	private static final double DRIVE_TIME_2 = 0.0; // in seconds
    private static final double LOWERED_ANGLE = 0.0; //in degrees
	
	public ClearPortcullis(Arm arm, DriveSystem driveSystem) {
		sequentially(
                new TimedDrive(driveSystem, DRIVE_SPEED,0.0, DRIVE_TIME),
                new MoveArmTo(arm, RAISED_ANGLE),
				new TimedDrive(driveSystem, DRIVE_SPEED_2,0.0, DRIVE_TIME_2),
                new MoveArmTo(arm, LOWERED_ANGLE)
        );
	}
}