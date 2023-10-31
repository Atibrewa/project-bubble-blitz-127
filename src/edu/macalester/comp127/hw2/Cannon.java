package edu.macalester.comp127.hw2;

import Graphics.Line;

/**
 * A cannon represented as a Line graphical object pointing a specific direction based on the input angle.
 */
public class Cannon extends Line {

    public static final double CANNON_LENGTH = 25;
    private double angleRadians;

    public Cannon(double centerX, double centerY, double angleDegrees) {
        super(centerX, centerY, 0, 0);
        if (angleDegrees < 0 || angleDegrees > 180) {
            throw new IllegalArgumentException("angleDegrees must be an angle between 0 and 180 degrees");
        }

        angleRadians = Math.toRadians(angleDegrees);
        double x2 = centerX + CANNON_LENGTH * Math.cos(angleRadians);
        double y2 = centerY + CANNON_LENGTH * -Math.sin(angleRadians);
        setEndPosition(x2, y2);
        setStrokeWidth(5);
    }

    /**
     * Animates the cannon to point at a new direction based on the angleDegrees
     * @param angleDegrees between 0 and 180
     */
    public void updateCannon(double angleDegrees) {
        if (angleDegrees < 0 || angleDegrees > 180) {
            throw new IllegalArgumentException("angleDegrees must be an angle between 0 and 180 degrees");
        }

        double newAngleRadians = Math.toRadians(angleDegrees);

        for (int i = 0; i < 10; i++) {
            double alpha = i / 10.0;
            double angle = lerp(angleRadians, newAngleRadians, alpha);
            double x2 = getX1() + CANNON_LENGTH * Math.cos(angle);
            double y2 = getY1() + CANNON_LENGTH * -Math.sin(angle);
            setEndPosition(x2, y2);
            pause(10);
        }

        double x2 = getX1() + CANNON_LENGTH * Math.cos(newAngleRadians);
        double y2 = getY1() + CANNON_LENGTH * -Math.sin(newAngleRadians);
        setEndPosition(x2, y2);
    }

    /**
     * Helper method to linearly interpolate between two angles.
     * @param point1 starting point
     * @param point2 ending point
     * @param alpha 0-1 value for how far along between the starting and ending points
     * @return interpolated value
     */
    private double lerp(double point1, double point2, double alpha) {
        return point1 + alpha * (point2 - point1);
    }

    /**
     * Pauses the program for milliseconds
     * @param milliseconds
     */
    private void pause(double milliseconds) {
        try {
            int millis = (int) milliseconds;
            int nanos = (int) Math.round((milliseconds - millis) * 1000000);
            Thread.sleep(millis, nanos);
        } catch (InterruptedException ex) {
            /* Empty */
        }
    }
}
