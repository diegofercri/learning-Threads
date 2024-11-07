package threads;

import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UsoThreads {
    public static void main(String[] args) {
        JFrame marco = new MarcoRebote();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
}


class PelotaHilos implements Runnable {
    private Pelota pelota;
    private Component componente;
    private SolicitaSuspender controlSuspension; // MOD: Controlador para suspender y reanudar el hilo

    public PelotaHilos(Pelota pelota, Component unComponente, SolicitaSuspender controlSuspension) {
        this.pelota = pelota;
        this.componente = unComponente;
        this.controlSuspension = controlSuspension; // MOD: Asignación del controlador de suspensión
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                controlSuspension.esperandoParaReanudar(); // MOD: Suspensión del hilo cuando está en espera
                pelota.mueve_pelota(componente.getBounds()); // Mueve la pelota
                componente.repaint(); // Redibuja la pantalla
                Thread.sleep(4); // Controla la velocidad de animación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Interrumpe el hilo si es necesario
            }
        }
    }
}


class Pelota {
    private static final int TAMX = 15;
    private static final int TAMY = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public void mueve_pelota(Rectangle2D limites) {
        x += dx;
        y += dy;

        if (x < limites.getMinX()) {
            x = limites.getMinX();
            dx = -dx;
        }

        if (x + TAMX >= limites.getMaxX()) {
            x = limites.getMaxX() - TAMX;
            dx = -dx;
        }

        if (y < limites.getMinY()) {
            y = limites.getMinY();
            dy = -dy;
        }

        if (y + TAMY >= limites.getMaxY()) {
            y = limites.getMaxY() - TAMY;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, TAMX, TAMY);
    }
}


class LaminaPelota extends JPanel {
    private ArrayList<Pelota> pelotas = new ArrayList<>();

    public void add(Pelota b) {
        pelotas.add(b); // Añadir pelota a la lista segura
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Pelota b : pelotas) {
            g2.fill(b.getShape()); // Dibujar cada pelota en la lista
        }
    }
}


class MarcoRebote extends JFrame {
    private LaminaPelota lamina;
    private Thread t; // MOD: Variable para controlar el hilo en ejecución
    private PelotaHilos tarea;
    private SolicitaSuspender controlSuspension = new SolicitaSuspender(); // MOD: Controlador para suspender y reanudar

    public MarcoRebote() {
        setBounds(600, 300, 400, 350);
        setTitle("Rebotes");
        lamina = new LaminaPelota();
        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones = new JPanel();

        ponerBoton(laminaBotones, "Start", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                comienza_el_juego(); // MOD: Inicia el juego solo si no hay otro hilo en ejecución
            }
        });

        ponerBoton(laminaBotones, "Stop", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                suspender(); // MOD: Suspende el hilo en ejecución
            }
        });

        ponerBoton(laminaBotones, "Continue", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                reanudar(); // MOD: Reanuda el hilo suspendido
            }
        });

        ponerBoton(laminaBotones, "Exit", new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                System.exit(0); // Cierra la aplicación
            }
        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    public void ponerBoton(Container c, String titulo, ActionListener oyente) {
        JButton boton = new JButton(titulo);
        c.add(boton);
        boton.addActionListener(oyente);
    }

    public void comienza_el_juego() {
        if (t == null || !t.isAlive()) { // MOD: Verifica si no hay otro hilo en ejecución
            Pelota pelota = new Pelota();
            lamina.add(pelota);

            tarea = new PelotaHilos(pelota, lamina, controlSuspension); // MOD: Asigna el controlador de suspensión al hilo
            t = new Thread(tarea); // MOD: Asigna la tarea al hilo
            t.start(); // MOD: Inicia el hilo
        } else {
            System.out.println("Ya hay un hilo en ejecución."); // MOD: Mensaje si ya existe un hilo activo
        }
    }

    public void suspender() {
        if (t != null && t.isAlive()) {
            controlSuspension.set(true); // MOD: Suspende el hilo en ejecución
        }
    }

    public void reanudar() {
        if (t != null && t.isAlive()) {
            controlSuspension.set(false); // MOD: Reanuda el hilo suspendido
        }
    }
}
