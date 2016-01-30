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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.strongback.Strongback;
import org.strongback.components.Motor;
import org.strongback.components.Switch;
import org.strongback.components.TalonSRX;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.FlightStick;
import org.strongback.hardware.Hardware;

public class Robot extends IterativeRobot {
	private static final int KICKER_MOTOR_CAN_ID = 0;
	private TalonSRX motor;
	private FlightStick joystick;
	private Switch trigger;
	private ContinuousRange throttle;
	
	private boolean velocityPositive = true, buttonDown = false;
	
	@Override
    public void robotInit() {
		Strongback.configure().recordDataToFile("/home/lvuser/").recordEventsToFile("/home/lvuser/",2097152);
    }

    @Override
    public void teleopInit() {
        // Start Strongback functions ...
        Strongback.start();
        motor = Hardware.Motors.talonSRX(KICKER_MOTOR_CAN_ID);
        joystick = Hardware.HumanInterfaceDevices.logitechAttack3D(0);
        trigger = joystick.getTrigger();
        throttle = joystick.getThrottle();
    }

    @Override
    public void teleopPeriodic() {
    	if(trigger.isTriggered()){
    		if(!buttonDown){
    			velocityPositive = !velocityPositive;
    			buttonDown = true;
    		}
    	}
    	else{
    		buttonDown = false;
    	}
    	double speed = Math.abs(throttle.read());
    	if(velocityPositive == true)
    		motor.setSpeed(speed);
    	else
    	    motor.setSpeed(-speed);
    }

    @Override
    public void disabledInit() {
        // Tell Strongback that the robot is disabled so it can flush and kill commands.
        Strongback.disable();
    }
}