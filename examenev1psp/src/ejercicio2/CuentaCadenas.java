package ejercicio2;

public class CuentaCadenas {
    public static void main(String[] args) {
        // Ruta del archivo donde se realizarán las búsquedas

    	// !!IMPORTANTE!! La ruta está hardcodeada por lo que no funcionará si lo ejecutamos desde otras rutas !!IMPORTANTE!!
        String archivo = "C:/GitHub/learning-MultiThreads/examenev1psp/src/ejercicio2/twain.txt";
        // Arreglo de cadenas que se buscarán en el archivo
        String[] cadenas = {"for", "Twain", "God", "pain", "doubt"};

        // Instancia de CrearProcesoCadenas para ejecutar los procesos de búsqueda
        CrearProcesoCadenas creador = new CrearProcesoCadenas();

        // Itera sobre el arreglo de cadenas
        for (String cadena : cadenas) {
            // Imprime la cadena que se está buscando
            System.out.println("Buscando cadena: " + cadena);
            // Llama al método ejecutarCadenas para buscar la cadena en el archivo
            creador.ejecutarCadenas(archivo, cadena);
        }
    }
}
