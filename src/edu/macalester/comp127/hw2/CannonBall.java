package edu.macalester.comp127.hw2;

import Graphics.CanvasWindow;
import Graphics.Ellipse;

/**
 * Represents a cannon ball that follows a parabolic arc based on physics equations.
 */
@SuppressWarnings("WeakerAccess")
public class CannonBall {
    public static final double GRAVITY = -9.8;
    public static final double BALL_RADIUS = 2.5;

    private double xPos;
    private double yPos;
    private double xVelocity;
    private double yVelocity;
    private double maxX;
    private double maxY;

    private Ellipse ballShape;

    public CannonBall(
            double centerX,
            double centerY,
            double initialSpeed,
            double initialAngle,
            double maxX,
            double maxY) {
                ballShape = new Ellipse(centerX, centerY, BALL_RADIUS * 2, BALL_RADIUS * 2);
                xPos = centerX;
                yPos = centerY;
                this.maxX = maxX;
                this.maxY = maxY;
                double initialAngleRadians = Math.toRadians(initialAngle);
                xVelocity = initialSpeed * Math.cos(initialAngleRadians);   // initial x velocity
                yVelocity = initialSpeed * - Math.sin(initialAngleRadians);  // initial y velocity
    }

    /**
     * Update the cannon ball's position if it is in bounds
     * @return true if the ball is in within the maxXBound and maxYBound
     */
    public boolean updatePosition(double dt) {
        xPos += (xVelocity * dt);
        yPos += (yVelocity * dt);
        if ((0 < xPos  && xPos < maxX) && (0 < yPos && yPos < maxY) ) {
            ballShape.setPosition(xPos, yPos);
            yVelocity -= GRAVITY * dt;
            return true;
        } else {
            return false;
        }
    }

    public double getCenterX() {
        return xPos;
    }

    public double getCenterY() {
        return yPos;
    }

    /**
     * Adds the cannonball's shape to the given canvas.
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    /**
     * Removes the cannonball's shape from the given canvas.
     */
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }
}
