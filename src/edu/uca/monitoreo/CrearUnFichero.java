package edu.uca.monitoreo;
import java.io.*;

public class CrearUnFichero {

	public static void Crear(String nombre, String contenido) {
	   // Creamos el objeto que encapsula el fichero
	   FileWriter fichero = null;
	   PrintWriter pw = null;
	   
	   try {
           fichero = new FileWriter("Servidores/"+nombre+".properties");
           pw = new PrintWriter(fichero);
           //for (int i = 0; i < 10; i++)
            pw.write(contenido);
         
       } catch (Exception e) {
           e.printStackTrace();
       }finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

	}

}