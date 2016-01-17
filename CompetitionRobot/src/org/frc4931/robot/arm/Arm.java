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

import org.strongback.components.AngleSensor;
import org.strongback.components.Motor;
import org.strongback.components.Switch;
import org.strongback.components.TalonSRX;

/**
 * The Arm is a subsystem of the robot that can be used to raise and lower to specific angles.
 * It is used to traverse tall defenses and open gates.
 */
public class Arm {
    public static final double MOTOR_SPEED = 1.0;

    private final Motor motor;
    private AngleSensor angleSensor;
    private final Switch homeSwitch;

    private double zeroAngle;

    /**
     * Constructs a new Arm subsystem given a motor, an angle sensor, and a home switch.
     * @param motor The motor to control the arm. Positive speeds should raise the arm; negative should lower it.
     * @param angleSensor The angle sensor that provides the angle of the arm.
     * @param homeSwitch The switch that should trigger when the arm is in its home position.
     */
    public Arm(Motor motor, AngleSensor angleSensor, Switch homeSwitch) {
        this.motor = motor;
        this.angleSensor = angleSensor;
        this.homeSwitch = homeSwitch;

        zeroAngle = 0.0;
    }

    /**
     * Constructs an Arm that uses the sensors provided by a Talon SRX motor controller.
     * @param controller The Talon SRX controller to pull
     */
    public Arm(TalonSRX controller) {
        this(controller, controller.getEncoderInput(), controller.getReverseLimitSwitch());
    }

    /**
     * Raises the arm, which increases the current angle.
     */
    public void raise() {
        motor.setSpeed(MOTOR_SPEED);
    }

    /**
     * Lowers the arm, which decreases the current angle.
     */
    public void lower() {
        motor.setSpeed(-1 * MOTOR_SPEED);
    }

    /**
     * Stops rotation of the arm, which stops the change in current angle.
     */
    public void stop() {
        motor.stop();
    }

    /**
     * Gets the current angle relative to its zero angle.
     * Positive angles are relatively higher, whereas negative angles are relatively lower.
     * @return The angle of the arm.
     */
    public double getAngle() {
        return angleSensor.getAngle() - zeroAngle;
    }

    /**
     * Sets the zero angle of the arm to its current angle.
     * Any subsequent calls to {@link #getAngle()} will be relative to its new zero angle.
     */
    public void zero() {
        // I prefer using this rather than AngleSensor.zero(). Stack traces can build up for getAngle() for an
        // angle sensor that is zeroed multiple times, depending upon the implementation.
        zeroAngle = angleSensor.getAngle();
    }

    /**
     * Determines whether the arm is at its home position.
     * @return true if the home switch has been triggered, indicating the arm is at home.
     */
    public boolean isAtHome() {
        return homeSwitch.isTriggered();
    }
}
