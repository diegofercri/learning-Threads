package threads;

import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadsUses {
    public static void main(String[] args) {
        JFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// This class implements Runnable to allow moving a Ball on a separate thread
class BallThread implements Runnable {
    
    public BallThread(Ball ball, Component component) {
        this.ball = ball;
        this.component = component;
    }
    
    private Ball ball;
    private Component component;
    
    // The run method defines the behavior of the thread
    public void run() {
        
        System.out.println("Thread interruption status at start: " + Thread.currentThread().isInterrupted());
        while (!Thread.currentThread().isInterrupted()) {
            
            // Move the ball and repaint the component
            ball.moveBall(component.getBounds());
            component.paint(component.getGraphics());
            
            try {
                Thread.sleep(4); // Pause to create the animation effect
            } catch (InterruptedException e) {
                // Restore the interruption status
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("Thread interruption status at end: " + Thread.currentThread().isInterrupted());
        
    }
}

// Class Ball - Handles the movement of the ball and its shape
class Ball {
    public void moveBall(Rectangle2D bounds) {
        
        // Update position based on direction
        x += dx;
        y += dy;

        // Handle collisions with the bounds (bounce effect)
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }

        if (x + BALL_WIDTH >= bounds.getMaxX()) {
            x = bounds.getMaxX() - BALL_WIDTH;
            dx = -dx;
        }

        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }

        if (y + BALL_HEIGHT >= bounds.getMaxY()) {
            y = bounds.getMaxY() - BALL_HEIGHT;
            dy = -dy;
        }

    }

    // Returns the shape of the ball as an Ellipse2D
    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, BALL_WIDTH, BALL_HEIGHT);
    }

    private static final int BALL_WIDTH = 15;
    private static final int BALL_HEIGHT = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

}

// BallPanel class extends JPanel and is responsible for rendering the balls
class BallPanel extends JPanel {

    public void add(Ball b) {
        balls.add(b);
    }

    // Paints all balls in the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    private ArrayList<Ball> balls = new ArrayList<>();
}

// BounceFrame class - The main window that sets up the GUI and thread controls
class BounceFrame extends JFrame {

    public BounceFrame() {
        setBounds(600, 300, 400, 350);
        setTitle("Bouncing Balls");
        
        // Panel for rendering the balls
        ballPanel = new BallPanel();
        add(ballPanel, BorderLayout.CENTER);
        
        // Panel for buttons at the bottom
        JPanel buttonPanel = new JPanel();
        
        addButton(buttonPanel, "Start", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                startGame();
            }
        });

        addButton(buttonPanel, "Exit", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        addButton(buttonPanel, "Stop", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                stopGame();
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to add a button to a container and set its action listener
    public void addButton(Container container, String title, ActionListener listener) {
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    Thread thread;

    // Starts the game by adding a ball and starting a thread to animate it
    public void startGame() {
        
        Ball ball = new Ball();
        ballPanel.add(ball);

        /* Original loop in single-threaded version
        for (int i = 0; i <= 1000; i++) {
            ball.moveBall(ballPanel.getBounds());
            ballPanel.paint(ballPanel.getGraphics());
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
        
        // Use a new thread to move the ball
        Runnable r = new BallThread(ball, ballPanel);
        thread = new Thread(r);
        thread.start();
    }

    // Stops the game by interrupting the ball thread
    public void stopGame(){
        thread.interrupt();
    }

    private BallPanel ballPanel;
    
}
