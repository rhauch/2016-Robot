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
import java.util.Random;
import org.frc4931.robot.math.EulerAngle;
import org.frc4931.robot.math.Quaternion;
import org.frc4931.robot.math.Vector3d;
import org.strongback.Executable;

public class BoschIMU implements IMU, Executable {
    private final I2C link;

    private volatile State state;
    private volatile long lastExecute;

    public BoschIMU(I2C.Port port) {
        link = new I2C(port, 0x28);
        link.write(0x3D, 0x0C); // OPR_MODE = NDOF
        zero();
    }

    public synchronized void execute(long timeInMillis) {
        double dTime;
        if (lastExecute == -1) {
            dTime = 0.0;
        } else {
            dTime = (timeInMillis - lastExecute) / 1000.0;
        }
        lastExecute = timeInMillis;

        byte[] buffer = new byte[20];
        link.read(0x1A, 20, buffer); // EUL, QUA_Data, LIA_Data
        int[] intBuffer = new int[20];
        for (int i = 0; i < intBuffer.length; i++) {
            intBuffer[i] = Byte.toUnsignedInt(buffer[i]);
        }


        double heading = (intBuffer[0] | (intBuffer[1] << 8) // Read data from buffer
                << 16 >> 16) // Fix sign
                / 16.0; // 1 degree = 16 LSB
        double roll = (intBuffer[2] | (intBuffer[3] << 8) << 16 >> 16) / 16.0;
        double pitch = (intBuffer[4] | (intBuffer[5] << 8) << 16 >> 16) / 16.0;

        double quatW = (intBuffer[6] | (intBuffer[7] << 8) // Read data from buffer
                << 16 >> 16) // Fix sign
                / 16384.0; // 1 (no unit) = 2^14 LSB;
        double quatX = (intBuffer[8] | (intBuffer[9] << 8) << 16 >> 16) / 16384.0;
        double quatY = (intBuffer[10] | (intBuffer[11] << 8) << 16 >> 16) / 16384.0;
        double quatZ = (intBuffer[12] | (intBuffer[13] << 8) << 16 >> 16) / 16384.0;

        double accX = (intBuffer[14] | (intBuffer[15] << 8) // Read data from buffer
                << 18 >> 18) // Fix sign for 14 bit numbers
                / 100.0; // 1 m/s^2 = 100 LSB
        double accY = (intBuffer[16] | (intBuffer[17] << 8) << 18 >> 18) / 100.0;
        double accZ = (intBuffer[18] | (intBuffer[19] << 8) << 18 >> 18) / 100.0;

        EulerAngle euler = new EulerAngle(heading, roll, pitch);
        Quaternion quaternion = new Quaternion(quatW, quatX, quatY, quatZ);
        Vector3d linAcceleration = new Vector3d(accX, accY, accZ);
        state = new State(euler, quaternion, linAcceleration, state, dTime);
    }

    public synchronized void zero() {
        state = new State(EulerAngle.ZERO, Quaternion.IDENTITY, Vector3d.ZERO);
        lastExecute = -1;
    }

    @Override
    public synchronized State getState() {
        return state;
    }
}
