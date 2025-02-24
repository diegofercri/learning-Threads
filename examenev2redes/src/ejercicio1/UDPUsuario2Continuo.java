package ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPUsuario2Continuo {
    public static void main(String[] args) {
        // Puertos de comunicación
        int puertoRecepcion = 9877; // Puerto donde escucha Usuario2
        int puertoEmision = 9876;   // Puerto donde escucha Usuario1

        try (DatagramSocket socket = new DatagramSocket(puertoRecepcion)) {
            Scanner scanner = new Scanner(System.in); // Para leer la entrada del usuario desde la consola
            InetAddress direccionServidor = InetAddress.getByName("localhost"); // Dirección IP del servidor (localhost)

            System.out.println("Usuario 2 - Esperando mensajes...");
            
            boolean usuario1Termino = false; // Indica si el Usuario 1 ha enviado "*"
            boolean usuario2Termino = false; // Indica si el Usuario 2 ha enviado "*"

            while (true) {
                // RECIBIR MENSAJE
                byte[] bufferRecibir = new byte[1024]; // Buffer para almacenar el mensaje recibido
                DatagramPacket paqueteRecibir = new DatagramPacket(bufferRecibir, bufferRecibir.length); // Crear un paquete para recibir datos
                
                socket.receive(paqueteRecibir); // Esperar a recibir un paquete
                String mensajeRecibido = new String(paqueteRecibir.getData(), 0, paqueteRecibir.getLength()); // Convertir los bytes recibidos a String
                
                System.out.println("Usuario 1: " + mensajeRecibido); // Mostrar el mensaje recibido en la consola

                // Comprobar si el Usuario 1 ha enviado "*"
                if (mensajeRecibido.equals("*")) {
                    usuario1Termino = true; // Marcar que el Usuario 1 ha terminado
                }

                // Comprobar si ambos usuarios han enviado "*"
                if (usuario1Termino && usuario2Termino) {
                    System.out.println("Usuario 1 - Ambos usuarios han enviado '*'. Conexión cerrada.");
                    break; // Salir del bucle
                }

                // ENVIAR MENSAJE
                System.out.print("Usuario 2: "); // Solicitar mensaje al usuario
                String mensajeEnviar = scanner.nextLine(); // Leer el mensaje desde la consola
                byte[] bufferEnviar = mensajeEnviar.getBytes(); // Convertir el mensaje a bytes para enviarlo

                // Crear un paquete UDP con el mensaje, dirección y puerto de destino
                DatagramPacket paqueteEnviar = new DatagramPacket(
                        bufferEnviar, bufferEnviar.length, direccionServidor, puertoEmision);
                
                socket.send(paqueteEnviar); // Enviar el paquete a través del socket

                // Comprobar si el Usuario 2 ha enviado "*"
                if (mensajeEnviar.equals("*")) {
                    usuario2Termino = true; // Marcar que el Usuario 2 ha terminado
                    System.out.println("Usuario 2 - Esperando confirmación de Usuario 1...");
                }

                // Comprobar si ambos usuarios han enviado "*"
                if (usuario1Termino && usuario2Termino) {
                    System.out.println("Usuario 2 - Ambos usuarios han enviado '*'. Conexión cerrada.");
                    break; // Salir del bucle
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir cualquier error que ocurra durante la ejecución
        }
    }
}