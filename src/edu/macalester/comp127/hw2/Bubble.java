package edu.macalester.comp127.hw2;

import Graphics.Ellipse;
import Graphics.GraphicsGroup;

import java.awt.Color;

/**
 * Represents a bubble that could be popped by a CannonBall.
 */
public class Bubble extends GraphicsGroup {

    private double centerX;
    private double centerY;
    private double radius;
    private int direction;

    public static final int NUM_LAYERS = 5;
    public static final Color BUBBLE_COLOR = new Color(201, 150, 216, 55);

    /**
     * Constructs a bubble centered on the centerX/Y position with the specified radius.
     */
    public Bubble(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.setPosition(centerX - radius, centerY - radius);
        createBubbleDrawing();
    }

    /**
     * Create concentric ellipses to represent the bubble.
     */
    private void createBubbleDrawing() {
        double curRadius = radius;
        for (int i = 0; i < NUM_LAYERS; i++) {
            Ellipse ellipse = new Ellipse(radius - curRadius, radius - curRadius, 2 * curRadius, 2 * curRadius);
            ellipse.setFillColor(BUBBLE_COLOR);
            ellipse.setFilled(true);
            ellipse.setStroked(false);
            add(ellipse);
            curRadius -= radius / NUM_LAYERS;
            direction = 1;
        }
    }

    /**
     * Tests for intersections between the given cannon ball and this bubble.
     *
     * @return true If the given ball intersects this bubble (even tangentially).
     */
    public boolean intersects(CannonBall ball) {
        double actualDistance = radius + CannonBall.BALL_RADIUS;
        double currentDistance = Math.sqrt(Math.pow(centerX - ball.getCenterX(), 2) + Math.pow((centerY - ball.getCenterY()), 2));
        if (currentDistance <= actualDistance) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Returns new position of bubble and changes direction when it reaches the edges of the canvas
     */
    public double updateX(double dt, double width) {
        if (centerX > width) {
            direction = -1;
        } else if (centerX < 0) {
            direction = 1;
        }
        centerX += 10 * dt * direction;
        return centerX;
    }
}
