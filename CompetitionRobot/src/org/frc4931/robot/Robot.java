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

/* Created Sun Jan 10 12:59:55 CST 2016 */
package org.frc4931.robot;

import org.frc4931.robot.drive.DriveSystem;
import org.strongback.Strongback;
import org.strongback.components.Motor;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

    public static final String LOG_FILES_DIRECTORY_PATH = "/home/lvuser/";

    private static final int LEFT_FRONT_MOTOR_CAN_ID = 0;
    private static final int LEFT_REAR_MOTOR_CAN_ID = 1;
    private static final int RIGHT_FRONT_MOTOR_CAN_ID = 2;
    private static final int RIGHT_REAR_MOTOR_CAN_ID = 3;

    private DriveSystem drive;
    private FlightStick joystick;
    private ContinuousRange driveSpeed;
    private ContinuousRange turnSpeed;
    private ContinuousRange throttle;

    @Override
    public void robotInit() {
        Strongback.configure()
                  .recordDataToFile(LOG_FILES_DIRECTORY_PATH)
                  .recordEventsToFile(LOG_FILES_DIRECTORY_PATH, 2097152);

        // Define the motors and the drive system ...
        Motor leftFrontMotor = Hardware.Motors.talonSRX(LEFT_FRONT_MOTOR_CAN_ID);
        Motor leftRearMotor = Hardware.Motors.talonSRX(LEFT_REAR_MOTOR_CAN_ID);
        Motor rightFrontMotor = Hardware.Motors.talonSRX(RIGHT_FRONT_MOTOR_CAN_ID);
        Motor rightRearMotor = Hardware.Motors.talonSRX(RIGHT_REAR_MOTOR_CAN_ID);
        Motor leftMotors = Motor.compose(leftFrontMotor, leftRearMotor);
        Motor rightMotors = Motor.compose(rightFrontMotor, rightRearMotor);
        TankDrive tankDrive = new TankDrive(leftMotors, rightMotors);
        drive = new DriveSystem(tankDrive);

        // Define the interface components ...
        joystick = Hardware.HumanInterfaceDevices.logitechAttack3D(0);
        throttle = joystick.getThrottle().map(t -> (t + 1.0) / 2.0);
        driveSpeed = joystick.getPitch().scale(this::throttle);
        turnSpeed = joystick.getRoll().scale(this::throttle).invert();

        // Register the functions that run when the switches change state ...
        Strongback.switchReactor().onTriggered(joystick.getTrigger(), drive::toggleDirectionFlipped);

        // Set up the data recorder to capture the left & right motor speeds and the sensivity.
        // We have to do this before we start Strongback...
        Strongback.dataRecorder()
                  .register("Left motors", leftMotors)
                  .register("Right motors", rightMotors)
                  .register("Sensitivity", throttle.scaleAsInt(1000));
    }

    /**
     * Function that returns the current throttle in both autonomous and teleop.
     * 
     * @return the current throttle
     */
    private double throttle() {
        return throttle.read();
    }

    @Override
    public void autonomousInit() {
        // Set the maximum throttle in autonomous to be 100% ...
        this.throttle = () -> 1.0;
    }

    @Override
    public void autonomousPeriodic() {
        // Start Strongback functions ...
        Strongback.start();
        Strongback.submit(new TimedDriveCommand(drive, 0.1, 0.1, 2.0));
    }

    @Override
    public void teleopInit() {
        // Kill anything running, and start it ...
        Strongback.restart();

        // Use the joystick's throttle ...
        throttle = joystick.getThrottle().map(t -> (t + 1.0) / 2.0);
    }

    @Override
    public void teleopPeriodic() {
        drive.arcade(driveSpeed.read(), turnSpeed.read());
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }
}