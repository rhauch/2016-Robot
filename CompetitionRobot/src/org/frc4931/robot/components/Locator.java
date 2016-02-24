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

import org.frc4931.robot.math.Quaternion;
import org.frc4931.robot.math.Vector3d;
import org.strongback.annotation.Immutable;

public class Locator {
    private final IMU imu;

    private volatile long lastUpdate;
    private volatile IMU.State imuState;
    private State state;

    public Locator(IMU imu) {
        this.imu = imu;
        zero();
    }

    public synchronized void zero() {
        state = new State(Vector3d.ZERO, Vector3d.ZERO, Vector3d.ZERO);
        lastUpdate = System.currentTimeMillis();
    }

    public synchronized void update() {
        long updateTime = System.currentTimeMillis();
        double delta = (updateTime - lastUpdate) / 1000.0;
        lastUpdate = updateTime;

        imuState = imu.getState();
        Quaternion quaternion = imuState.quaternionOrientation;
        Vector3d linearAccel = imuState.linearAccel;

        // Orient the linear acceleration to absolute axes
        Vector3d acceleration = linearAccel.rotate(quaternion.conjugate());

        // V = Vi + A * dt
        Vector3d velocity = state.velocity.add(acceleration.mul(delta));

        // P = Pi + V * dt
        Vector3d position = state.position.add(velocity.mul(delta));

        state = new State(acceleration, velocity, position);
    }

    public synchronized IMU.State getImuState() {
        return imuState;
    }

    public synchronized State getState() {
        return state;
    }

    @Immutable
    public class State {
        public Vector3d acceleration;
        public Vector3d velocity;
        public Vector3d position;

        State(Vector3d acceleration, Vector3d velocity, Vector3d position) {
            this.acceleration = acceleration;
            this.velocity = velocity;
            this.position = position;
        }
    }
}
