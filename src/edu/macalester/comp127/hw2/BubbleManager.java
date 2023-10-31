package edu.macalester.comp127.hw2;

import Graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class keeps track of creating the bubbles on the screen and handling when they pop.
 */
public class BubbleManager {

    private CanvasWindow canvas;
    private List<Bubble> bubbles;
    private Random random;

    public static final int MIN_BUBBLES = 3;
    public static final int MAX_BUBBLES = 6;
    public static final int MIN_RADIUS = 5;
    public static final int MAX_RADIUS = 100;

    /**
     * Constructs a bubble manager for the specified window object.
     */
    public BubbleManager(CanvasWindow canvas) {
        bubbles = new ArrayList<>();
        random = new Random();
        this.canvas = canvas;
    }

    /**
     * Generates a random number of bubbles placed randomly on the screen.
     */
    public void generateBubbles() {
        int numBubbles = randomInt(MIN_BUBBLES, MAX_BUBBLES);
        for (int i = 0; i < numBubbles; i++) {
            double x = randomDouble(0, canvas.getWidth());
            double y = randomDouble(0, canvas.getHeight() - BubbleBlitz.WINDOW_PADDING);
            double radius = randomDouble(MIN_RADIUS, MAX_RADIUS);
            Bubble bubble = new Bubble(x, y, radius);
            canvas.add(bubble);
            bubbles.add(bubble);
        }
    }

    /**
     * Checks whether the cannonball hits any of the bubbles, popping the first intersecting bubble if so.
     *
     * @return true If a bubble has been destroyed
     */
    public boolean testHit(CannonBall cannonBall) {
        // The following is java's syntax for a "for each" loop, i.e. "for each bubble b in the bubbles list, do..."
        for (Bubble b : bubbles) {
            if (b.intersects(cannonBall)) {
                popBubble(b);
                return true;
            }
        }
        return false;
    }

    /**
     * Destroys a bubble when it has been hit by a cannonball.
     */
    private void popBubble(Bubble b) {
        canvas.remove(b);
        bubbles.remove(b);
    }

    /**
     * Removes all the bubbles from the canvas and the list.
     */
    public void removeAllBubbles() {
        // The following is java's syntax for a "for each" loop, i.e. "for each bubble, b, in the bubbles list do..."
        for (Bubble b : bubbles) {
            canvas.remove(b);
        }
        bubbles.clear();
    }

    /**
     * Check for existing bubbles
     * @return true if bubbles still exist that have not been popped.
     */
    public boolean bubblesStillExist() {
        return bubbles.size() > 0;
    }

    public int getNumberOfBubbles() {
        return bubbles.size();
    }

    /**
     * Convenience to return a random floating point number, min ≤ n < max.
     */
    private double randomDouble(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }

    /**
     * Convenience to return a random integer, min ≤ n ≤ max.
     * Note that max is inclusive.
     */
    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Updates positions of each of the bubbles to move them from left to right
     */
    public void moveBubbles() {
        for (Bubble bubble : bubbles) {
            double newX = bubble.updateX(0.1, canvas.getWidth());
            bubble.setX(newX);
            canvas.draw();
        }
    }
}
