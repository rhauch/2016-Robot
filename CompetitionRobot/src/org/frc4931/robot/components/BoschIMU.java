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

import edu.wpi.first.wpilibj.I2C;

public class BoschIMU implements IMU {
    private final I2C link;

    private double heading;
    private double roll;
    private double pitch;
    private double quatW;
    private double quatX;
    private double quatY;
    private double quatZ;
    private double accX;
    private double accY;
    private double accZ;

    public BoschIMU(I2C.Port port) {
        link = new I2C(port, 0x28);
        link.write(0x3D, 0x0C); // OPR_MODE = NDOF
        zero();
    }

    public void zero() {
        heading = 0.0;
        roll = 0.0;
        pitch = 0.0;
        quatW = 0.0;
        quatX = 0.0;
        quatY = 0.0;
        quatZ = 0.0;
        accX = 0.0;
        accY = 0.0;
        accZ = 0.0;
    }

    public void poll() {

        byte[] buffer = new byte[20];
        link.read(0x1A, 20, buffer); // EUL, QUA_Data, LIA_Data

        heading = (buffer[0] + (buffer[1] << 8)) // Read data from buffer
                / 16.0; // 1 degree = 16 LSB
        roll = (buffer[2] + (buffer[3] << 8)) / 16.0;
        pitch = (buffer[4] + (buffer[5] << 8)) / 16.0;

        quatW = (buffer[6] + (buffer[7] << 8)) // Read data from buffer
                / 16384.0; // 1 (no unit) = 2^14 LSB;
        quatX = (buffer[8] + (buffer[9] << 8)) / 16384.0;
        quatY = (buffer[10] + (buffer[11] << 8)) / 16384.0;
        quatZ = (buffer[12] + (buffer[13] << 8)) / 16384.0;

        accX = (buffer[14] + (buffer[15] << 8) // Read data from buffer
                << 18 >> 18) // Fix sign for 14 bit numbers
                / 100.0; // 1 m/s^2 = 100 LSB
        accY = (buffer[16] + (buffer[17] << 8) << 18 >> 18) / 100.0;
        accZ = (buffer[18] + (buffer[19] << 8) << 18 >> 18) / 100.0;
    }

    @Override
    public double getHeading() {
        return heading;
    }

    @Override
    public double getRoll() {
        return roll;
    }

    @Override
    public double getPitch() {
        return pitch;
    }

    @Override
    public double getQuatW() {
        return quatW;
    }

    @Override
    public double getQuatX() {
        return quatX;
    }

    @Override
    public double getQuatY() {
        return quatY;
    }

    @Override
    public double getQuatZ() {
        return quatZ;
    }

    @Override
    public double getAccX() {
        return accX;
    }

    @Override
    public double getAccY() {
        return accY;
    }

    @Override
    public double getAccZ() {
        return accZ;
    }
}
