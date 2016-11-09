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

package org.frc4931.robot;

import org.strongback.command.Requirable;
import org.strongback.components.Motor;
import org.strongback.components.Switch;

/**
 * @author Randall Hauch
 *
 */
public class Arm implements Requirable {
    
    private Motor lowerMotor;
    private Motor upperMotor;
    private Switch lowerExtendLimit;
    private Switch upperExtendLimit;
    
    public Arm( Motor lowerMotor, Motor upperMotor, Switch lowerLimit, Switch upperLimit) {
        this.lowerMotor = lowerMotor;
        this.upperMotor = upperMotor;
        this.lowerExtendLimit = lowerLimit;
        this.upperExtendLimit = upperLimit;
    }
    
    public boolean moveToPickupPosition() {
        lowerMotor.setSpeed(0.3);
        upperMotor.setSpeed(0.2);
        if ( lowerExtendLimit.isTriggered()) {
            lowerMotor.stop();
        }
        if ( upperExtendLimit.isTriggered()) {
            upperMotor.stop();
        }
        return lowerMotor.getSpeed() != 0.0 && upperMotor.getSpeed() != 0.0;
    }
    
    public void raiseToHighestPosition() {
        
    }

    public void grabItem() {
        
    }
    
    public void dropItem() {
        
    }

}
