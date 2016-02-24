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

import java.nio.FloatBuffer;
import java.util.Vector;

/**
 * A 3-dimensional vector represented as the distance and direction from the origin to a point.
 */
public class Vector3d {
    public static final Vector3d ZERO = new Vector3d(0.0, 0.0, 0.0);

    private final double x;
    private final double y;
    private final double z;
    private final int hash;

    /**
     * Constructs a vector from a set of coordinates.
     * @param x The value of the x-coordinate.
     * @param y The value of the y-coordinate.
     * @param z The value of the z-coordinate.
     */
    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        int hash;
        long temp;
        temp = Double.doubleToLongBits(x);
        hash = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        hash = 31 * hash + (int) (temp ^ (temp >>> 32));
        this.hash = hash;
    }

    /**
     * Constructs a vector from the elements of another vector.
     * @param vec The vector from which to copy elements.
     */
    public Vector3d(Vector3d vec) {
        this(vec.x, vec.y, vec.z);
    }

    /**
     * Calculates the length (or magnitude) of a vector.
     *
     * @return The calculated length of the vector.
     */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Calculates the normalized version of this vector.
     *
     * <br>A normalized vector retains its direction, but its length is scaled to a value of 1.
     * @return The calculated normalized vector.
     */
    public Vector3d normalize() {
        double length = length();

        return new Vector3d(x / length, y / length, z / length);
    }

    /**
     * Adds each respective element of two vectors.
     *
     * @param rVec The right-hand vector of the addition.
     * @return A new vector containing the results of the addition.
     */
    public Vector3d add(Vector3d rVec) {
        return new Vector3d(x + rVec.x, y + rVec.y, z + rVec.z);
    }

    /**
     * Subtracts each respective element of two vectors.
     *
     * @param rVec The right-hand vector of the subtraction.
     * @return A new vector containing the results of the subtraction.
     */
    public Vector3d sub(Vector3d rVec) {
        return new Vector3d(x - rVec.x, y - rVec.y, z - rVec.z);
    }

    /**
     * Multiplies each element of the vector by a constant scale.
     *
     * @param scale The scalar of the multiplication.
     * @return A new vector containing the results of the multiplication.
     */
    public Vector3d mul(double scale) {
        return new Vector3d(x * scale, y * scale, z * scale);
    }

    /**
     * Multiplies each respective element of two vectors.
     *
     * @param rVec The right-hand vector of the multiplication.
     * @return A new vector containing the results of the multiplication.
     */
    public Vector3d mul(Vector3d rVec) {
        return new Vector3d(x * rVec.x, y * rVec.y, z * rVec.z);
    }

    /**
     * Divides each element of the vector by a constant scale.
     *
     * @param scale The scalar of the division.
     * @return A new vector containing the results of the division.
     */
    public Vector3d div(double scale) {
        return new Vector3d(x / scale, y / scale, z / scale);
    }

    /**
     * Divides each respective element of two vectors.
     *
     * @param rVec The right-hand vector of the division.
     * @return A new vector containing the results of the division.
     */
    public Vector3d div(Vector3d rVec) {
        return new Vector3d(x / rVec.x, y / rVec.y, z / rVec.z);
    }

    /**
     * Performs a cross product operation on two vectors.
     *
     * <br>A cross product forms a vector which is perpendicular to both vectors and has a magnitude that is
     * proportional to the angle between the two vectors.
     * <br>Cross products are non-commutative. When the factors are swapped, the result will be negated.
     * @param rVec The right-hand vector of the multiplication.
     * @return The cross product of the two vectors.
     */
    public Vector3d cross(Vector3d rVec) {
        return new Vector3d(
                y * rVec.z - z * rVec.y,
                z * rVec.x - x * rVec.z,
                x * rVec.y - y * rVec.x
        );
    }

    /**
     * Performs a dot product operation on two vectors.
     *
     * <br> There are various definitions of a dot product. In this case, it refers to the sum of the products of each
     * respective element. This is also known as a "scalar product."
     * @param rVec The right-hand vector of the multiplication.
     * @return The result of the multiplication.
     */
    public double dot(Vector3d rVec) {
        return x * rVec.x + y * rVec.y + z * rVec.z;
    }

    /**
     * Performs a linear interpolation between two vectors.
     *
     * <br> Although this is commonly used for points in between the ends, it can also be used for points outside of
     * the range between the two vectors. Such calculations would be called "extrapolations."
     * @param dest The destination vector of the interpolation.
     * @param factor Represents a point in between the two vectors, where 0 is at the source vector and 1 is at the
     *               destination vector.
     * @return The vector at the given point between the two endpoints.
     */
    public Vector3d lerp(Vector3d dest, double factor) {
        return dest.sub(this).mul(factor).add(this);
    }

    public Vector3d rotate(Quaternion quat) {
        double qW = quat.getW();
        double qX = quat.getX();
        double qY = quat.getY();
        double qZ = quat.getZ();

        // Quaternion rotation formula taken from https://en.wikipedia.org/wiki/Rotation_matrix#Quaternion
        //TODO Test and verify the algorithm

        return new Vector3d(
                (1  -  2 * qY * qY  -  2 * qZ * qZ) * x +
                (2 * qX * qY  -  2 * qZ * qW) * y +
                (2 * qX * qZ  +  2 * qY * qW) * z,
                (2 * qX * qY  +  2 * qZ * qW) * x +
                (1  -  2 * qX * qX  -  2 * qZ * qZ) * y +
                (2 * qY * qZ  -  2 * qX * qW) * z,
                (2 * qX * qZ  -  2 * qY * qW) * x +
                (2 * qY * qZ  +  2 * qX * qW) * y +
                (1  -  2 * qX * qX  -  2 * qY * qY) * z);
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
        if (!(o instanceof Vector3d)) return false;

        Vector3d vector3D = (Vector3d) o;

        return Double.compare(vector3D.x, x) == 0 &&
                Double.compare(vector3D.y, y) == 0 &&
                Double.compare(vector3D.z, z) == 0;

    }

    @Override
    public int hashCode() {
        return hash;
    }
}
