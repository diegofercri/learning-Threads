package ejercicio1;

import java.util.Scanner;

public class ConstruyeCadena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar al usuario la letra tope
        System.out.print("Introduce la letra tope: ");
        char tope = scanner.next().toLowerCase().charAt(0);

        // Validar y volver a solicitar la letra tope hasta que esté en el rango válido (a-z)
        while (tope < 'a' || tope > 'z') {
            System.out.println("La letra tope debe ser una letra minúscula entre 'a' y 'z'.");
            System.out.print("Introduce la letra tope: ");
            tope = scanner.next().toLowerCase().charAt(0);
        }

        // Crear el objeto compartido que manejará la cadena
        CadenaLetras cadenaLetras = new CadenaLetras(tope);

        // Crear e inicializar los hilos con el objeto compartido
        MontaCadena hilo1 = new MontaCadena("Hilo1", cadenaLetras);
        MontaCadena hilo2 = new MontaCadena("Hilo2", cadenaLetras);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que los hilos finalicen su ejecución
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            // Capturar cualquier interrupción mientras se espera a los hilos
            System.out.println("Error esperando la finalización de los hilos.");
        }
        
        // Imprimir el resultado final de la cadena construida
        System.out.println("Resultado final de la cadena: " + cadenaLetras.getCadena());
    }
}
