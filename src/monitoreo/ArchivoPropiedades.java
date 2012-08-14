package monitoreo;

import java.util.*;
import java.io.*;


public class ArchivoPropiedades {

	private Properties propiedades;
	private String archivo = "";
	
	
	public ArchivoPropiedades(String archivo) {
		super();
		propiedades = new Properties();
		this.archivo = archivo;
		
	}
	
	public boolean cargarPropiedades(){
		
		try{
			propiedades.load(new FileInputStream(archivo));
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	public void escribirPropiedad(String key, String value)
	{

		try {

            propiedades.setProperty(key,value);

            // Escribier en el archivo los cambios
            FileOutputStream fos = new FileOutputStream(archivo);
            
            propiedades.store(fos,null);
                        
         }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
         }catch (IOException e) {
        	System.out.println(e.getMessage());
         }

    }
	
	
	public String getValorPropiedad(String propiedad){
		return propiedades.getProperty(propiedad);
	}
	
}
