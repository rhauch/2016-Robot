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

import org.strongback.command.Command;
import org.strongback.control.TalonController;

/**
 * Lowers the arm until the home switch is triggered. After that, the arm is zeroed.`
 */
public class CalibrateArm extends Command {
    private final Arm arm;

    public CalibrateArm(Arm arm) {
        super(arm);
        this.arm = arm;
    }

    @Override
    public void initialize() {
        arm.setSoftLimitsEnabled(false);
        arm.setControlMode(TalonController.ControlMode.PERCENT_VBUS);
        arm.raise();
    }

    @Override
    public boolean execute() {
        return arm.isAtHome();
    }

    @Override
    public void end() {
        arm.stop();
        pause(1.0);
        arm.zero();
        arm.setSoftLimitsEnabled(true);
    }
}
