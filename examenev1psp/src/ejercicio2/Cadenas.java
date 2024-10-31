package ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Cadenas {
    public static void main(String[] args) {
        // Verifica que se proporcionen exactamente dos argumentos
        if (args.length == 2) {
            String cad = args[0]; // La cadena que se buscará
            File fichero = new File(args[1]); // El archivo en el que se buscará la cadena
            // Llama al método contarCadenas y obtiene el número de ocurrencias
            int numCadenas = contarCadenas(cad, fichero);
            // Imprime el resultado
            System.out.println("Cadena " + cad + " -> " + numCadenas);
        } else {
            // Si no se proporcionan los argumentos necesarios, no hace nada
        }
    }

    public static int contarCadenas(String cadena, File fichero) {
        int numCadenas = 0;
        try {
            // Lee el archivo línea por línea
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String c = null;
            while ((c = br.readLine()) != null) {
                boolean control = true;
                // Mientras se encuentren ocurrencias de la cadena en la línea actual
                while (control) {
                    if (c.contains(cadena)) {
                        numCadenas++; // Incrementa el contador de ocurrencias
                        // Elimina la primera ocurrencia de la cadena en la línea actual
                        c = c.replaceFirst(cadena, "");
                    } else {
                        control = false; // Sale del bucle si no se encuentra la cadena
                    }
                }
            }
            // Cierra los flujos de lectura
            fr.close();
            br.close();
            return numCadenas;
        } catch (IOException e) {
            // Maneja las excepciones de E/S
            e.printStackTrace();
        }
        return numCadenas; // Devuelve el número de ocurrencias encontradas
    }
}
