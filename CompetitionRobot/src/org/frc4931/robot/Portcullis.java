package org.frc4931.robot;

import org.frc4931.robot.arm.Arm;

public class Portcullis {
	private Arm arm; // arm that lifts the portcullis' gate
	
	public Portcullis(Arm arm){
		this.arm = arm;
	}
	
	/**
	 * Starts raising the arm (to be used once the arm is under the gate).
	 */
	public void raise(){
		arm.raise();
	}
	
	/**
	 * Starts lowering the arm (to be used once the robot is on the other side of the portcullis).
	 */
	public void lower(){
		arm.lower();
	}
	
	/**
	 * Stops the arm (to be used after calling raise() or lower()).
	 */
	public void stop(){
		arm.stop();
	}
}