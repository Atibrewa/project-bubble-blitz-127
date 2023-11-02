package edu.macalester.comp127.hw2;

import Graphics.CanvasWindow;
import Graphics.Line;

import java.awt.Color;

@SuppressWarnings("WeakerAccess")
public class VisualCannonTester {

    public static final double SPEED = 100;
    public static final int WINDOW_PADDING = 30;

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Cannon Angles", 1100, 600);
        Cannon cannon = new Cannon(WINDOW_PADDING, canvas.getHeight() - WINDOW_PADDING, 0);
        canvas.add(cannon);

        for (int angle = 0; angle <= 90; angle += 5) {
            cannon.updateCannon(angle);

            double angleInDegrees = angle;
            double maxXBound = canvas.getWidth();
            double maxYBound = canvas.getHeight() - WINDOW_PADDING;

            CannonBall cannonBall = new CannonBall(cannon.getX(), cannon.getY(), SPEED, angleInDegrees, maxXBound, maxYBound);

            cannonBall.addToCanvas(canvas);
            boolean ballIsInMotion = true;
            while (ballIsInMotion) {
                double oldX = cannonBall.getCenterX();
                double oldY = cannonBall.getCenterY();
                ballIsInMotion = cannonBall.updatePosition(0.1);
                Line tracer = new Line(oldX, oldY, cannonBall.getCenterX(), cannonBall.getCenterY());
                tracer.setStrokeColor(Color.LIGHT_GRAY);
                canvas.add(tracer);
                canvas.draw();
                canvas.pause(5);
            }
        }
    }
}
