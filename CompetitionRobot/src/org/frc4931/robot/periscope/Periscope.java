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

package org.frc4931.robot.periscope;

import edu.wpi.first.wpilibj.vision.USBCamera;
import javafx.scene.Camera;
import  org.frc4931.robot.components.Servo;
import org.strongback.command.Requirable;

import edu.wpi.first.wpilibj.CameraServer;

/**
 * Periscope because we have discussed about having a camera mounted on a 
 * pitch and yaw head. 
 *  
 * @author Julian
 *
 *
 */
public class Periscope implements Requirable {
    public static final double MIN_PITCH = 0;
    public static final double MAX_PITCH = 90;
    public static final double MIN_YAW = 0;
    public static final double MAX_YAW = 180;

	private final USBCamera camera;
	private final Servo pitchController;
	private final Servo yawController;
	
	/**
	 * creates periscope with 2 {@link Servo}s
     * @param camera The camera that is to be controlled
	 * @param pitchController {@link Servo} that controls pitch
	 * @param yawController {@link Servo} that controls yaw
	 */
	public Periscope(USBCamera camera, Servo pitchController, Servo yawController) {
        this.camera = camera;
        this.pitchController = pitchController;
        this.yawController = yawController;

        if (camera != null) {
            CameraServer.getInstance().setQuality(50);
            CameraServer.getInstance().startAutomaticCapture(camera);
        }
	}
	
	/**
	 * Sets the pitch to the highest possible angle.
	 */
	public void deploy() {
		pitchController.moveToAngle(MAX_PITCH);
	}
	
	/**
	 * Sets the pitch to the lowest possible angle.
	 */
	public void retract() {
		pitchController.moveToAngle(MIN_PITCH);
	}
	
	/**
	 * getPitch() returns current pitch angle between 
	 * 0(being parallel to the floor) and 90 degrees
	 * @return double the target angle
	 */
	public double getPitch() {
		return pitchController.getTargetAngle();
	}
	
	/**
	* setPitch() sets pitch between 0(being parallel to the floor) and 90 degrees, 
	* if larger than 90 round down to 90, if smaller than 0 round up to 0
	* 
	* @param angle the inputed angles 
	*/
	public void setPitch(double angle) {
        angle = Math.max(MIN_PITCH, Math.min(angle, MAX_PITCH));
		pitchController.moveToAngle(angle);
	}

	/**
	 * returns current yaw angle between 0(being parallel to the floor)-180 degrees
	 *  (90 being the middle which is facing forward
	 * @return double the target angle
	 */
	public double getYaw() {
		return yawController.getTargetAngle();
	}
	
	/**
	 *  sets pitch between 0(being parallel to the floor)-180 degrees, 
	 *  if larger than 180 round down to 180, if smaller than 0 round up to 0
	 * 
	 * @param angle the angle inputed
	 */
	public void setYaw(double angle) {
        angle = Math.max(MIN_YAW, Math.min(angle, MAX_YAW));
		yawController.moveToAngle(angle);
	}
}
	
