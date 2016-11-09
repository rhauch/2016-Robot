
package org.frc4931.robot;

import org.strongback.components.Motor;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

public class Robot extends edu.wpi.first.wpilibj.IterativeRobot {

    private static final int JOYSTICK_PORT = 1; // in driver station
    private static final int LEFT_FRONT_MOTOR_PORT = 1;
    private static final int LEFT_REAR_MOTOR_PORT = 2;
    private static final int RIGHT_FRONT_MOTOR_PORT = 3;
    private static final int RIGHT_REAR_MOTOR_PORT = 4;

    private TankDrive drive;
    private ContinuousRange driveSpeed;
    private ContinuousRange turnSpeed;

    @Override
    public void robotInit() {
        // Set up the robot hardware ...
        Motor leftFront = Hardware.Motors.talon(LEFT_FRONT_MOTOR_PORT);
        Motor leftRear = Hardware.Motors.talon(LEFT_REAR_MOTOR_PORT);
        Motor rightFront = Hardware.Motors.talon(RIGHT_FRONT_MOTOR_PORT).invert();
        Motor rightRear = Hardware.Motors.talon(RIGHT_REAR_MOTOR_PORT).invert();

        Motor left = Motor.compose(leftFront, leftRear);
        Motor right = Motor.compose(rightFront, rightRear);
        drive = new TankDrive(left, right);

        // Set up the human input device ...
        FlightStick joystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_PORT);
        driveSpeed = joystick.getPitch();
        turnSpeed = joystick.getYaw().invert();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        drive.arcade(driveSpeed.read(), turnSpeed.read());
    }

    @Override
    public void disabledInit() {
        drive.stop();
    }
}