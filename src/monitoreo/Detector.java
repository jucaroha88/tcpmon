package monitoreo;

import java.io.*;

import edu.uca.javacurso.tcpmon.Notificador;
import edu.uca.javacurso.tcpmon.db.TCPMonDB;
import edu.uca.javacurso.tcpmon.mail.Mailer;

public class Detector{

	private ArchivoPropiedades archivo;
	
	public Detector() {
		super();
	}

	
	public static void main( String args[] )
	{
		Detector monitor = new Detector();

		TCPMonDB db = new TCPMonDB("ledb");
		Mailer mailer = new Mailer();
		Notificador notificador = new Notificador(db, mailer);
		
		monitor.ejecutar(notificador);
	}
	
	public void ejecutar(Notificador notificador){
		//Leer directorio
		String path = "Servidores/";
		
		File dir = new File(path);
		File[] ficheros = dir.listFiles();
		
		if (ficheros != null){
			
			//Recorrer uno a uno el directorio para extraer el nombre
			for(int i=0; i<ficheros.length; i++){
			//inicializamos el archivo de propiedades
				
				System.out.println(path + ficheros[i].getName());
				
				archivo = new ArchivoPropiedades(path + ficheros[i].getName());
			
				archivo.cargarPropiedades();
				
				//Hilo que maneja cada archivo properties
				new Submonitor(archivo, notificador).start();
				
			}
			
			
		}else{
			System.err.println("No hay Nada en el Directorio!");
		}
		
	}
}
