package ejercicio2;

import java.io.IOException;

public class CrearProcesoCadenas {
    public void ejecutarCadenas(String archivo, String cadena) {
        /*
         * Crear un ProcessBuilder para ejecutar la clase Cadenas
         * "java" - el comando para ejecutar la JVM
         * "-cp" - especifica el classpath
         * "C:/GitHub/learning-MultiThreads/examenev1psp/bin/" - la ruta del classpath donde se encuentran las clases compiladas
         * "ejercicio2.Cadenas" - la clase que queremos ejecutar
         * "cadena" y "archivo" - argumentos pasados a la clase Cadenas
         */
    	
    	// !!IMPORTANTE!! La ruta está hardcodeada por lo que no funcionará si lo ejecutamos desde otras rutas !!IMPORTANTE!!
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "C:/GitHub/learning-MultiThreads/examenev1psp/bin/", "ejercicio2.Cadenas", cadena, archivo);
        
        // Redirigir la salida del proceso al flujo de entrada estándar del programa principal
        pb.inheritIO();
        try {
            // Iniciar el proceso
            Process proceso = pb.start();
            // Esperar a que el proceso termine
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            // Manejar las excepciones de E/S e interrupciones
            e.printStackTrace();
        }
    }
}
