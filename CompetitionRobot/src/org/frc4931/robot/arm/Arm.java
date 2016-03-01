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

import org.strongback.command.Requirable;
import org.strongback.components.AngleSensor;
import org.strongback.components.Switch;
import org.strongback.control.TalonController;

/**
 * The Arm is a subsystem of the robot that can be used to raise and lower to specific angles.
 * It is used to traverse tall defenses and open gates.
 */
public class Arm implements Requirable {
    public static final double MOTOR_SPEED = 1.0;

    private final TalonController controller;
    private final AngleSensor angleSensor;
    private final Switch homeSwitch;

    /**
     * Constructs a new Arm subsystem given a Talon SRX motor controller.
     * @param controller The Talon SRX that is responsible for controlling the arm.
     */
    public Arm(TalonController controller) {
        this.controller = controller;
        angleSensor = controller.getEncoderInput();
        //FIXME Strongback assigns reverse limit switch to forward
        homeSwitch = controller.getForwardLimitSwitch();
    }

    /**
     * Get this controller's current control mode.
     *
     * @return the control mode; never null
     */
    public TalonController.ControlMode getControlMode() {
        return controller.getControlMode();
    }

    /**
     * Set the control mode for this controller.
     *
     * @param mode the control mode; may not be null
     * @return this object so that methods can be chained; never null
     */
    public TalonController setControlMode(TalonController.ControlMode mode) {
        return controller.setControlMode(mode);
    }

    public void zero() {
        angleSensor.zero();
    }

    /**
     * Gets the current angle relative to its zero angle.
     * Positive angles are relatively higher, whereas negative angles are relatively lower.
     * @return The angle of the arm.
     */
    public double getAngle() {
        return angleSensor.getAngle();
    }

    /**
     * Set the target angle of the arm's PID loop.
     * @param targetAngle The new target angle that the arm should move to.
     */
    public void setTargetAngle(double targetAngle) {
        controller.withTarget(targetAngle);
    }

    public boolean isAtTarget() {
        return controller.isWithinTolerance();
    }

    public boolean isAtHome() {
        return homeSwitch.isTriggered();
    }

    public void raise() {
        controller.setSpeed(MOTOR_SPEED);
    }

    public void lower() {
        controller.setSpeed(-MOTOR_SPEED);
    }

    public void stop() {
        controller.stop();
    }
}
