
package org.frc4931.robot;

import org.strongback.Strongback;
import org.strongback.components.DistanceSensor;
import org.strongback.components.Motor;
import org.strongback.components.Relay;
import org.strongback.components.Solenoid;
import org.strongback.components.Solenoid.Direction;
import org.strongback.components.Switch;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.FlightStick;
import org.strongback.drive.TankDrive;
import org.strongback.hardware.Hardware;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
    private Motor leftRearMotor;
    private Motor rightRearMotor;
    private Motor leftFrontMotor;
    private Motor rightFrontMotor;
    private ContinuousRange driveSpeed;
    private ContinuousRange turnSpeed;
    private TankDrive tankDrive;
    private DistanceSensor distanceSensor;
    private Solenoid redFlagSolenoid;
    private Relay yellowFlagRelay;
    private Switch ballInRobot;
    private Arm arm;

    @Override
    public void robotInit() {

        leftFrontMotor = Hardware.Motors.victorSP(2);
        leftRearMotor = Hardware.Motors.victorSP(3);
        rightFrontMotor = Hardware.Motors.victorSP(0);
        rightRearMotor = Hardware.Motors.victorSP(1);
        Motor leftMotors = Motor.compose(leftFrontMotor, leftRearMotor);
        Motor rightMotors = Motor.compose(rightFrontMotor, rightRearMotor).invert();
        tankDrive = new TankDrive(leftMotors, rightMotors);
        // s = Hardware.Switches.normallyOpen(8);
        FlightStick driverJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(0);
        ContinuousRange throttle = driverJoystick.getThrottle().map(t -> (1.0 - t) / 2);
        driveSpeed = driverJoystick.getPitch().scale(throttle::read).invert();
        turnSpeed = driverJoystick.getYaw().scale(throttle::read);

        distanceSensor = Hardware.DistanceSensors.analogUltrasonic(9, 24.0);
        redFlagSolenoid = Hardware.Solenoids.doubleSolenoid(11, 10, Direction.STOPPED);
        yellowFlagRelay = Hardware.Solenoids.relay(12);
        ballInRobot = Hardware.Switches.normallyClosed(8);
        
        Motor armUpperMotor = Hardware.Motors.victorSP(4);
        Motor armLowerMotor = Hardware.Motors.victorSP(5);
        Switch armLowerSwitch = Hardware.Switches.normallyOpen(6);
        Switch armUpperSwitch = Hardware.Switches.normallyOpen(7);
        arm = new Arm(armLowerMotor, armUpperMotor, armLowerSwitch, armUpperSwitch);
        
    }

    @Override
    public void disabledInit() {
        tankDrive.stop();
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
        double feetAway = distanceSensor.getDistanceInFeet();
        if (feetAway > 0.5) {
            tankDrive.arcade(driveSpeed.read(), turnSpeed.read());
        } else {
            tankDrive.stop();
        }

        if (feetAway < 1.0) {
            Strongback.submit(new MoveArmToGrabbingPosition(arm));
        } else {
            Strongback.submit(new RaiseArmCommand(arm));
        }
        
        if (ballInRobot.isTriggered()) {
            yellowFlagRelay.on();
        } else {
            yellowFlagRelay.off();
        }
    }

}
