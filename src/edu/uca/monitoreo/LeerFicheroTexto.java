package edu.uca.monitoreo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFicheroTexto  {

	static String cadenafinal = "";
	
	public static String Leer(String nombre) {
		
		try {
		
			FileReader fr = new FileReader(nombre);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena; 
			while ((sCadena = bf.readLine())!=null) {
				//System.out.println(sCadena);
				cadenafinal = cadenafinal+sCadena+'\n';
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		return cadenafinal;

	}

}