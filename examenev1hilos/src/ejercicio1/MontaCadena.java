package ejercicio1;

public class MontaCadena extends Thread {
    private CadenaLetras cadenaLetras; // Objeto compartido que gestiona la cadena
    private String nombreHilo; // Nombre del hilo

    // Constructor para inicializar el hilo con su nombre y el objeto compartido
    public MontaCadena(String nombreHilo, CadenaLetras cadenaLetras) {
        this.nombreHilo = nombreHilo;
        this.cadenaLetras = cadenaLetras;
    }

    // Método principal del hilo que ejecuta la construcción de la cadena
    @Override
    public void run() {
        // El hilo llama al método sincronizado de la clase CadenaLetras para construir la cadena
        cadenaLetras.crearCadena(nombreHilo);
    }
}
