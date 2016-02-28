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

import org.frc4931.robot.math.EulerAngle;
import org.frc4931.robot.math.Quaternion;
import org.frc4931.robot.math.Vector3d;
import org.strongback.annotation.Immutable;

@FunctionalInterface
public interface IMU {

    State getState();

    static IMU stationary() {
        return () -> new State(EulerAngle.ZERO, Quaternion.IDENTITY, Vector3d.ZERO);
    }

    @Immutable
    class State {
        public final EulerAngle euler;
        public final Quaternion quaternion;
        public final Vector3d linAcceleration;
        public final Vector3d absAcceleration;
        public final Vector3d absVelocity;
        public final Vector3d absPosition;

        State(EulerAngle euler, Quaternion quaternion, Vector3d linAcceleration) {
            this.euler = euler;
            this.quaternion = quaternion;
            this.linAcceleration = linAcceleration;
            absAcceleration = linAcceleration.rotate(quaternion.conjugate());
            absVelocity = Vector3d.ZERO;
            absPosition = Vector3d.ZERO;
        }

        State(EulerAngle euler, Quaternion quaternion, Vector3d linAcceleration, State oldState, double dTime) {
            this.euler = euler;
            this.quaternion = quaternion;
            this.linAcceleration = linAcceleration;
            absAcceleration = linAcceleration.rotate(quaternion.conjugate());
            absVelocity = oldState.absVelocity.add(absAcceleration.mul(dTime));
            absPosition = oldState.absPosition.add(absVelocity.mul(dTime));
        }
    }
}
