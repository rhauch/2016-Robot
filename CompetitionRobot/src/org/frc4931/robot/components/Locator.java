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

package org.frc4931.robot.components;

import edu.wpi.first.wpilibj.Timer;
import org.frc4931.robot.components.IMU;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Locator {
    private final IMU imu;

    private long lastUpdate;
    private double heading;
    private double roll;
    private double pitch;
    private double accX;
    private double accY;
    private double accZ;
    private double velX;
    private double velY;
    private double velZ;
    private double posX;
    private double posY;
    private double posZ;

    public Locator(IMU imu) {
        this.imu = imu;
        lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        long updateTime = System.currentTimeMillis();
        double delta = (updateTime - lastUpdate) / 1000.0;
        lastUpdate = updateTime;

        heading = imu.getHeading();
        roll = imu.getRoll();
        pitch = imu.getPitch();
        double w = -1 * imu.getQuatW(); // Flip the spin; then it will be the inverse of our orientation.
        double x = imu.getQuatX();
        double y = imu.getQuatY();
        double z = imu.getQuatZ();
        double relAccX = imu.getAccX();
        double relAccY = imu.getAccY();
        double relAccZ = imu.getAccZ();

        // Quaternion rotation formula taken from https://en.wikipedia.org/wiki/Rotation_matrix#Quaternion
        //TODO Test and verify the algorithm

        accX = (1  -  2 * y * y  -  2 * z *z) * relAccX +
                (2 * x * y  -  2 * z * w) * relAccY +
                (2 * x * z  +  2 * y * w) * relAccZ;
        accY = (2 * x * y  +  2 * z * w) * relAccX +
                (1  -  2 * x * x  -  2 * z * z) * relAccY +
                (2 * y * z  -  2 * x * w) * relAccZ;
        accZ = (2 * x * z  -  2 * y * w) * relAccX +
                (2 * y * z  +  2 * x * w) * relAccY +
                (1  -  2 * x * x  -  2 * y * y) * relAccZ;

        // V = Vi + A * dt
        velX += accX * delta;
        velY += accY * delta;
        velZ += accZ * delta;

        // P = Pi + V * dt
        posX += velX * delta;
        posY += velY * delta;
        posZ += velZ * delta;
    }

    public IMU getIMU() {
        return imu;
    }

    public double getHeading() {
        return heading;
    }

    public double getRoll() {
        return roll;
    }

    public double getPitch() {
        return pitch;
    }

    public double getAccX() {
        return accX;
    }

    public double getAccY() {
        return accY;
    }

    public double getAccZ() {
        return accZ;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public double getVelZ() {
        return velZ;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getPosZ() {
        return posZ;
    }
}
