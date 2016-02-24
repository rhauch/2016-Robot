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

import java.util.Vector;

/**
 * Defines a complex number with three imaginary terms, i, j, and k.
 * <br>Each term has similar properties but are considered to be different numbers. The most important relationship is
 * their products: {@code i^2 = j^2 = k^2 = ijk = -1}. Algebraically, we find that the product of two of the numbers
 * produces the other number; however, that multiplication is noncommutative.
 * <br>See http://wikipedia.org/wiki/Quaternion for a more detailed explanation of the properties of quaternions.
 */
public class Quaternion {
    /**
     * The multiplicative identity quaternion; represents no rotation.
     */
    public static final Quaternion IDENTITY = new Quaternion(1.0, 0.0, 0.0, 0.0);

    private final double w;
    private final double x;
    private final double y;
    private final double z;
    private final int hash;

    /**
     * Constructs a quaternion with given term coefficients.
     * The formula for the quaternion is as follows: {@code w + xi + yj + zk}.
     * @param w The real term of the quaternion.
     * @param x The coefficient of i.
     * @param y The coefficient of j.
     * @param z The coefficient of k.
     */
    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;

        int hash;
        long temp;
        temp = Double.doubleToLongBits(w);
        hash = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        this.hash = hash;
    }

    public Quaternion(Quaternion quat) {
        this(quat.w, quat.x, quat.y, quat.z);
    }

    /**
     * Calculates the magnitude of a quaternion.
     *
     * @return The length/magnitude of the quaternion.
     */
    public double length() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * Calculates the unit quaternion/norm of the quaternion.
     * @return The unit quaternion calculated.
     */
    public Quaternion normalize() {
        double length = length();

        return new Quaternion(w / length, x / length, y / length, z / length);
    }

    /**
     * Calculates the conjugate of a quaternion.
     * For a quaternion {@code w + xi + yj + zk}, the conjugate {@code w - xi - yj - zk}.
     * @return The calculated conjugate.
     */
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }
    /**
     * Multiplies a quaternion by a scale.
     * <br> This has a direct effect on the magnitude of a quaternion.
     * @param scale The scale to multiply with the quaternion.
     * @return The product of the quaternion and its scale.
     */
    public Quaternion mul(double scale) {
        return new Quaternion(w * scale, x * scale, y * scale, z * scale);
    }

    /**
     * Multiplies two quaternions using a multiplicity table.
     * <br> This multiplication is non-commutative.
     * @param rQuat The right-hand quaternion of the multiplication.
     * @return The product of the two quaternions.
     */
    public Quaternion mul(Quaternion rQuat) {
        return new Quaternion(
                w * rQuat.w - x * rQuat.x - y * rQuat.y - z * rQuat.z,
                x * rQuat.w + w * rQuat.x + y * rQuat.z - z * rQuat.y,
                y * rQuat.w + w * rQuat.y + z * rQuat.x - x * rQuat.z,
                z * rQuat.w + w * rQuat.z + x * rQuat.y - y * rQuat.x
        );
    }

    /**
     * Divides a quaternion by a scale factor.
     * <br>This division has a direct effect on the magnitude of the quaternion.
     * @param scale The scale by which the quaternion is divided.
     * @return The quotient of the quaternion and its scale.
     */
    public Quaternion div(double scale) {
        return new Quaternion(w / scale, x / scale, y / scale, z / scale);
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quaternion)) return false;

        Quaternion that = (Quaternion) o;

        return Double.compare(that.w, w) == 0 &&
                Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0;

    }

    @Override
    public int hashCode() {
        return hash;
    }
}