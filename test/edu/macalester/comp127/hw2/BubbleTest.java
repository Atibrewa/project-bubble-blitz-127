package edu.macalester.comp127.hw2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleTest {

    @Test
    public void bubbleIntersectsOverlappingBall() {
        Bubble bubble = new Bubble(10, 20, 6);
        assertTrue(bubble.intersects(new CannonBall(5, 19, 0, 0, 0, 0)));  // partially overlapping
        assertTrue(bubble.intersects(new CannonBall(11, 23, 0, 0, 0, 0))); // entirely inside
    }

    @Test
    public void bubbleIntersectsBallAtSinglePoint() {
        Bubble bubble = new Bubble(5, 9, 2);
        assertTrue(bubble.intersects(new CannonBall(0.5, 9, 0, 0, 0, 0))); // left
        assertTrue(bubble.intersects(new CannonBall(9.5, 9, 0, 0, 0, 0))); // right
        assertTrue(bubble.intersects(new CannonBall(5, 4.5, 0, 0, 0, 0))); // top
        assertTrue(bubble.intersects(new CannonBall(5, 13.5, 0, 0, 0, 0))); // bottom
    }

    @Test
    public void bubbleDoesNotIntersectNearbyBall() {
        Bubble bubble = new Bubble(5, 9, 1.99);  // slightly smaller radius
        assertFalse(bubble.intersects(new CannonBall(0.5, 9, 0, 0, 0, 0))); // left
        assertFalse(bubble.intersects(new CannonBall(9.5, 9, 0, 0, 0, 0))); // right
        assertFalse(bubble.intersects(new CannonBall(5, 4.5, 0, 0, 0, 0))); // top
        assertFalse(bubble.intersects(new CannonBall(5, 13.5, 0, 0, 0, 0))); // bottom
    }

}
