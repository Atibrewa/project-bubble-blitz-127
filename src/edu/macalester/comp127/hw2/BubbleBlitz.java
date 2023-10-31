package edu.macalester.comp127.hw2;

import Graphics.CanvasWindow;
import Graphics.FontStyle;
import Graphics.GraphicsText;
import Graphics.Rectangle;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;

/**
 * The main game class to run the game popping bubbles.
 */
@SuppressWarnings("WeakerAccess")
public class BubbleBlitz {
    Scanner scanner = new Scanner(System.in);

    private Random random;
    private Cannon cannon;
    private BubbleManager bubbleManager;
    private CanvasWindow canvas;
    private CannonBall cannonball;
    private GraphicsText ballCountText;

    public static final int WINDOW_PADDING = 30;
    public static final Color SKY_COLOR = new Color(188, 217, 255);
    public static final Color GROUND_COLOR = new Color(122, 181, 107);

    public static void main(String[] args) {
        BubbleBlitz game = new BubbleBlitz();
        game.run();
    }

    public BubbleBlitz() {
        canvas = new CanvasWindow("BubbleBlitz", 800, 600);
        random = new Random();
        bubbleManager = new BubbleManager(canvas);
    }

    public void run() {
        resetGame();
        int count = 8;
        ballCount(count);
        canvas.draw();
        while (bubbleManager.bubblesStillExist() && count > 0) {
            double angle = updateAngle();
            creatingCannonBall(angle);
            while (cannonball.updatePosition(0.1) && ! bubbleManager.testHit(cannonball)) {
                bubbleManager.moveBubbles();
                canvas.draw();
                canvas.pause(10);
            }
            cannonball.removeFromCanvas(canvas);
            count--;
            ballCountText.setText(String.valueOf(count) + " balls left");
            canvas.draw();
        } 
        if (bubbleManager.bubblesStillExist()) {
            System.out.println("YOU LOST :(");
            loser();
        } else {
            run();
        }      
    }

    /**
     * Resets the canvas by removing everything and redrawing new bubbles and a new random placement for the cannon.
     */
    public void resetGame() {
        bubbleManager.removeAllBubbles();
        canvas.removeAll();
        createBackground();
        bubbleManager.generateBubbles();
        createCannon(random.nextDouble() * (canvas.getWidth() - WINDOW_PADDING) + WINDOW_PADDING, canvas.getHeight() - WINDOW_PADDING, 90);
        canvas.draw();
    }

    /**
     * Creates a cannon.
     * @param centerX The anchor position of the cannon
     * @param centerY The anchor position of the cannon
     * @param angleDegrees The direction of the cannon
     */
    private void createCannon(double centerX, double centerY, double angleDegrees) {
        cannon = new Cannon(centerX, centerY, angleDegrees);
        canvas.add(cannon);
    }

    /**
     * Creates the sky and ground background
     */
    private void createBackground() {
        Rectangle sky = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        sky.setFillColor(SKY_COLOR);
        sky.setFilled(true);
        canvas.add(sky);

        Rectangle ground = new Rectangle(0, canvas.getHeight() - WINDOW_PADDING, canvas.getWidth(), WINDOW_PADDING);
        ground.setFilled(true);
        ground.setFillColor(GROUND_COLOR);
        ground.setStroked(false);
        canvas.add(ground);
    }

    public double updateAngle() {
        System.out.println("Enter an angle b/w 0 to 180 degrees:");
        double angle = scanner.nextDouble();
        cannon.updateCannon(angle);
        canvas.draw();
        return angle;        
    }

    public void creatingCannonBall(double angle) {
        System.out.println("Enter the initial velocity:");
        double velocity = scanner.nextDouble();
        CannonBall cannonball = new CannonBall(cannon.getX2(), cannon.getY2(), velocity, angle, canvas.getWidth(), canvas.getHeight());
        cannonball.addToCanvas(canvas);
        this.cannonball=cannonball;
    }

    public void loser() {
        GraphicsText lostText = new GraphicsText("YOU LOST :)", canvas.getWidth()/4, canvas.getHeight()/2);
        lostText.setFont("Comic Sans MS", FontStyle.BOLD, 72.0);
        canvas.add(lostText);
        canvas.draw();
    }

    public void ballCount(int count) {
        ballCountText = new GraphicsText(String.valueOf(count) + " balls left", 30 , 40);
        ballCountText.setFontSize(20);
        ballCountText.setFillColor(Color.BLUE);;
        canvas.add(ballCountText);
        canvas.draw();
    }
}
