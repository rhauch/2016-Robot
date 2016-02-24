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

package org.frc4931.robot.math;

public class EulerAngle {
    public static final EulerAngle ZERO = new EulerAngle(0.0, 0.0, 0.0);

    private final double heading;
    private final double roll;
    private final double pitch;
    private final int hash;

    public EulerAngle(double heading, double roll, double pitch) {
        this.heading = heading;
        this.roll = roll;
        this.pitch = pitch;

        int hash;
        long temp;
        temp = Double.doubleToLongBits(heading);
        hash = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(roll);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pitch);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        this.hash = hash;
    }

    public EulerAngle(EulerAngle eulerAngle) {
        this(eulerAngle.heading, eulerAngle.roll, eulerAngle.pitch);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EulerAngle)) return false;

        EulerAngle that = (EulerAngle) o;

        return Double.compare(that.heading, heading) == 0 &&
                Double.compare(that.roll, roll) == 0 &&
                Double.compare(that.pitch, pitch) == 0;

    }

    @Override
    public int hashCode() {
        return hash;
    }
}
