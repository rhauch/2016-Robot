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

package org.frc4931.tables;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableServer {
    private static NetworkTable locatorTable;
    private static double posX = 0;
    private static double posY = 0;
    private static double heading = 0;

    public static void main(String[] args) {
        try {
            NetworkTable.setServerMode();
            NetworkTable.initialize();
            locatorTable = NetworkTable.getTable("locator");
            while (true) {
                glide(4, 0, 0, 8, 5000);
            }
        } finally {
            NetworkTable.shutdown();
        }
    }

    public static void glide(double startX, double startY, double endX, double endY, long delta) {
        posX = startX;
        posY = startY;

        double deltaX = (endX - startX) * 100 / delta;
        double deltaY = (endY - startY) * 100 / delta;

        heading = Math.toDegrees(Math.atan2(deltaY, deltaX));
        for (int i = 0; i < delta / 100; i++) {
            posX += deltaX;
            posY += deltaY;

            update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update() {
//        System.out.print(posX);
//        System.out.print("\t");
//        System.out.println(posY);
        locatorTable.putNumber("posX", posX);
        locatorTable.putNumber("posY", posY);
        locatorTable.putNumber("heading", heading);
    }
}
