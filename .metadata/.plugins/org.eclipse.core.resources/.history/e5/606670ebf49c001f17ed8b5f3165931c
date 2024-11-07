package threads;

import java.awt.geom.*;
import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList; // MOD: Import to avoid ConcurrentModificationException
import java.awt.*;
import java.awt.event.*;

public class ThreadUsage {
    public static void main(String[] args) {
        JFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class BallRunnable implements Runnable {
    private Ball ball;
    private Component component;
    private SuspendRequest suspendControl; // MOD: Control for suspending and resuming the thread

    public BallRunnable(Ball ball, Component component, SuspendRequest suspendControl) {
        this.ball = ball;
        this.component = component;
        this.suspendControl = suspendControl; // MOD: Assigning the suspend control
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                suspendControl.waitForResume(); // MOD: Suspends the thread when in wait mode
                ball.moveBall(component.getBounds()); // Move the ball / Mueve la pelota
                component.repaint(); // Redraw the screen / Redibuja la pantalla
                Thread.sleep(4); // Controls animation speed / Controla la velocidad de animación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Interrupts the thread if needed / Interrumpe el hilo si es necesario
            }
        }
    }
}

class Ball {
    private static final int BALL_WIDTH = 15;
    private static final int BALL_HEIGHT = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public void moveBall(Rectangle2D bounds) {
        x += dx;
        y += dy;

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

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, BALL_WIDTH, BALL_HEIGHT);
    }
}

class BallPanel extends JPanel {
    private CopyOnWriteArrayList<Ball> balls = new CopyOnWriteArrayList<>(); // MOD: Thread-safe list to avoid ConcurrentModificationException / Lista segura para evitar ConcurrentModificationException

    public void add(Ball b) {
        balls.add(b); // Add ball to the safe list / Añadir pelota a la lista segura
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape()); // Draw each ball in the list / Dibujar cada pelota en la lista
        }
    }
}

class BounceFrame extends JFrame {
    private BallPanel panel;
    private Thread t; // MOD: Variable to control the running thread / Variable para controlar el hilo en ejecución
    private BallRunnable task;
    private SuspendRequest suspendControl = new SuspendRequest(); // MOD: Suspend and resume controller / Controlador para suspender y reanudar

    public BounceFrame() {
        setBounds(600, 300, 400, 350);
        setTitle("Bounces");
        panel = new BallPanel();
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        addButton(buttonPanel, "Start!", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                startGame(); // MOD: Starts the game only if no other thread is running / Inicia el juego solo si no hay otro hilo en ejecución
            }
        });

        addButton(buttonPanel, "Suspend", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                suspend(); // MOD: Suspend the running thread / Suspende el hilo en ejecución
            }
        });

        addButton(buttonPanel, "Resume", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                resume(); // MOD: Resume the suspended thread / Reanuda el hilo suspendido
            }
        });

        addButton(buttonPanel, "Exit", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0); // Closes the application / Cierra la aplicación
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void startGame() {
        if (t == null || !t.isAlive()) { // MOD: Checks if no other thread is running / Verifica si no hay otro hilo en ejecución
            Ball ball = new Ball();
            panel.add(ball);

            task = new BallRunnable(ball, panel, suspendControl); // MOD: Assign the suspend control to the thread / Asigna el controlador de suspensión al hilo
            t = new Thread(task); // MOD: Assign the task to the thread / Asigna la tarea al hilo
            t.start(); // MOD: Start the thread / Inicia el hilo
        } else {
            System.out.println("A thread is already running."); // MOD: Message if there is already an active thread / Mensaje si ya existe un hilo activo
        }
    }

    public void suspend() {
        if (t != null && t.isAlive()) {
            suspendControl.set(true); // MOD: Suspend the running thread / Suspende el hilo en ejecución
        }
    }

    public void resume() {
        if (t != null && t.isAlive()) {
            suspendControl.set(false); // MOD: Resume the suspended thread / Reanuda el hilo suspendido
        }
    }
}
