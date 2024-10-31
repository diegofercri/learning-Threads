package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TresProcesos {

    public static void main(String[] args) {
        try {
            // Proceso 1: Lanzar la aplicación mspaint
            ProcessBuilder pb1 = new ProcessBuilder("mspaint.exe");
            Process proceso1 = pb1.start();

            // Proceso 2: Ejecutar el comando "nslookup www.elpais.com"
            ProcessBuilder pb2 = new ProcessBuilder("nslookup", "www.elpais.com");
            Process proceso2 = pb2.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso2.getInputStream()));
            String line;
            // Pintamos las líneas del proceso 2 en la consola
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
    		
         // Proceso 3: Ejecutar la clase java HolaMundo
            ProcessBuilder pb3 = new ProcessBuilder(
                "java", 
                "-cp",
            	/* !!IMPORTANTE!! La ruta está hardcodeada por lo que no funcionará si lo ejecutamos desde otras rutas !!IMPORTANTE!! */
                "C:/GitHub/learning-MultiThreads/examenev1psp/bin", 
                "ejercicio1.HolaMundo"
            );
            Process proceso3 = pb3.start();

            BufferedReader reader3 = new BufferedReader(new InputStreamReader(proceso3.getInputStream()));
            String line3;
            // Pintamos las líneas del proceso 3 en la consola
            while ((line3 = reader3.readLine()) != null) {
                System.out.println(line3);
            }


            // Esperar a que todos los procesos terminen
            proceso1.waitFor();
            proceso2.waitFor();
            proceso3.waitFor();
            
            System.out.println("Todos los procesos han terminado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
