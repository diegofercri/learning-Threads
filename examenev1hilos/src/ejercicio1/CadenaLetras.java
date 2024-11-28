package ejercicio1;

public class CadenaLetras {
    private StringBuilder cadena = new StringBuilder(); // Cadena compartida que se construye
    private char tope; // Letra tope hasta la que se debe construir la cadena

    public CadenaLetras(char tope) {
        this.tope = tope; // Inicializamos la letra tope
    }

    // Método sincronizado para obtener la cadena actual
    public synchronized String getCadena() {
        return cadena.toString();
    }

    // Método sincronizado para construir la cadena alfabética
    public synchronized void crearCadena(String nombreHilo) {
        try {
            // Mientras la cadena esté vacía o su última letra sea menor que la letra tope
            while (cadena.length() == 0 || cadena.charAt(cadena.length() - 1) < tope) {
                char siguienteLetra;
                
                // Si la cadena está vacía, comenzamos con 'a', de lo contrario, calculamos la siguiente letra
                if (cadena.length() == 0) {
                    siguienteLetra = 'a';
                } else {
                    siguienteLetra = (char) (cadena.charAt(cadena.length() - 1) + 1);
                }

                // Si la siguiente letra supera la letra tope, salimos del bucle
                if (siguienteLetra > tope) {
                    break;
                }

                // Añadimos la siguiente letra a la cadena
                cadena.append(siguienteLetra);

                // Imprimimos el estado actual de la cadena
                System.out.println("En ejecución: " + nombreHilo + " Cadena= " + cadena);
                System.out.println(siguienteLetra + " <--> " + tope);

                // Pausa para simular la ejecución pausada del hilo
                Thread.sleep(500);

                // Notificamos a otros hilos que pueden continuar
                notifyAll();

                // Si hemos alcanzado la letra tope, indicamos que se completó la cadena y salimos del bucle
                if (siguienteLetra == tope) {
                    System.out.println("La cadena se ha completado");
                    break;
                }

                // Ponemos el hilo en espera para que otros hilos puedan ejecutar
                wait();
            }
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, capturamos la excepción y marcamos la interrupción
            Thread.currentThread().interrupt();
            System.out.println("Hilo interrumpido: " + nombreHilo);
        }
    }
}
