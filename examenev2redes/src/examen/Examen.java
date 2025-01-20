package examen;

import java.io.*;
import java.net.*;
import java.util.*;

public class Examen {
    public static void main(String[] args) {
        String urlDestino = "http://www.example.com"; // URL de ejemplo
        String rutaArchivo = "C:/xampp/htdocs/practica_red_psp_1/ejemplo.html"; // Ruta donde guardar el archivo
        String navegadorRuta = "\"C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe\""; // Ruta del navegador Firefox

        try {
            // 1. Descargar el código HTML de la web
            System.out.println("Descargando el código HTML de: " + urlDestino);
            URL url = new URL(urlDestino);
            URLConnection conexion = url.openConnection();

            // Leer el contenido de la página
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea); // Mostrar el código HTML en la consola
                contenido.append(linea).append("\n"); // Guardarlo en un StringBuilder
            }
            reader.close();

            // 2. Guardar el código en un archivo local en el servidor Apache
            System.out.println("Guardando el código HTML en: " + rutaArchivo);
            File archivo = new File(rutaArchivo);
            archivo.getParentFile().mkdirs(); // Crear directorios si no existen
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(contenido.toString());
            }
            System.out.println("Archivo guardado correctamente.");

            // 3. Abrir el archivo en el navegador Firefox
            System.out.println("Abriendo el archivo en el navegador...");
            Process proceso = new ProcessBuilder(navegadorRuta, "http://localhost/practica_red_psp_1/ejemplo.html").start();
            proceso.waitFor(); // Esperar a que el navegador abra la página

            // 4. Obtener y mostrar información sobre la URL y sus cabeceras
            System.out.println("\nInformación de la URL:");
            System.out.println("Protocolo: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Puerto: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("File: " + url.getFile());

            System.out.println("\nInformación de las cabeceras:");
            Map<String, List<String>> cabeceras = conexion.getHeaderFields();
            // Uso de Iterator para recorrer las cabeceras
            Iterator<Map.Entry<String, List<String>>> it = cabeceras.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, List<String>> entrada = it.next();
                System.out.println(entrada.getKey() + ": " + entrada.getValue());
            }

            System.out.println("\nCampos individuales de las cabeceras:");
            System.out.println("Content-Length: " + conexion.getContentLength());
            System.out.println("Content-Type: " + conexion.getContentType());
            System.out.println("Last-Modified: " + conexion.getLastModified());
            System.out.println("Date: " + conexion.getDate());
            System.out.println("Expiration: " + conexion.getExpiration());

        } catch (MalformedURLException e) {
            System.err.println("URL mal formada: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Error al abrir el navegador: " + e.getMessage());
        }
    }
}
