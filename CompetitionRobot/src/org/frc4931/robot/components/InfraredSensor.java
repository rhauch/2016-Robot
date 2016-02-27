package org.frc4931.robot.components;


import org.strongback.components.Switch;
import org.strongback.hardware.Hardware;


public class InfraredSensor
{
	public Switch v;
	public InfraredSensor(int portNumber)
	{
		v=Hardware.Switches.normallyClosed(portNumber);
	}
	
	/**
	 * true if not obstuted,false if not
	 * @return boolean
	 */
	public boolean getInput()
	{
		return v.isTriggered();
	}
}
