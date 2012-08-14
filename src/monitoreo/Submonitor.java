package monitoreo;

import java.io.*;
import java.net.*;

import org.apache.log4j.*;

import edu.uca.javacurso.tcpmon.Notificador;
import edu.uca.monitoreo.CrearUnFichero;

import java.util.*;
import java.sql.SQLException;
import java.text.*;

public class Submonitor extends Thread{
	
	public static final int default_retry_interval = 10;
	public static final int default_check_interval = 10;
	
	Notificador notificador;

	private Socket monitor;

	//variables para el archivo properties
	private String ip_servicio = "";
	private int puerto = 0;
	private int check_interval = 0;
	private int retry_interval = 0;
	private String alias = "";
	private String mail = "";
	private String estado = "";
	private String fecha_actual="";
	
	private ArchivoPropiedades archivo;
	
	static final Logger logger = Logger.getLogger(Submonitor.class);

	
	public Submonitor(ArchivoPropiedades archivo, Notificador notificador) {
		super();
		
		this.notificador = notificador;
		
		//Extraemos todos las propiedades del archivo propiedades
		this.archivo=archivo;
		this.ip_servicio = archivo.getValorPropiedad("address");
		
		StringTokenizer puertos = new StringTokenizer(
				archivo.getValorPropiedad("ports_list"), ",");
		
		this.puerto = Integer.parseInt(puertos.nextToken());
		try{
			this.retry_interval = Integer.parseInt(archivo.getValorPropiedad("retry_interval"));
		}catch(Exception e){
			this.retry_interval = default_retry_interval;
		}
		try {
			this.check_interval = Integer.parseInt(archivo.getValorPropiedad("check_interval"));
		} catch (Exception e) {
			this.check_interval = default_check_interval;
		}
		
		this.alias = archivo.getValorPropiedad("alias");
		
		this.mail = archivo.getValorPropiedad("email_notification");
		
		
	}


	public void run(){
		
		BasicConfigurator.configure();
		
		while(true){
		
			try {
				
				//Intentamos conectar al ip y puerto del servicio
				monitor = new Socket( InetAddress.getByName( ip_servicio ), puerto );
				
				//Fecha actual
				Date ahora = new Date();
				SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
				fecha_actual = formateador.format(ahora);
				
				archivo.escribirPropiedad("current_state", "OK");
				archivo.escribirPropiedad("last_check", fecha_actual);
				
				logger.info("Conexion Exitosa" + " con " + alias + " " + fecha_actual);

				estado = archivo.getValorPropiedad("current_state");
				
				cerrarConexion();
				
				//Se duerme durante un intervalo para checkear de nuevo el servicio
				try {
					Submonitor.sleep(check_interval*60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}catch ( IOException excepcionES ) {
		       
			   logger.error("Conexion Fallida" + " con " + alias + " " + fecha_actual);
		       

			   Date ahora1 = new Date();
			   SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			   fecha_actual = formateador.format(ahora1);
			   
			   SimpleDateFormat formateador_sql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   String fecha_sql = formateador_sql.format(ahora1);
				
			   archivo.escribirPropiedad("current_state", "FAIL");
		       estado = archivo.getValorPropiedad("current_state");
		       
		       archivo.escribirPropiedad("last_check", fecha_actual);
		       archivo.escribirPropiedad("last_notificacion", fecha_actual);
			   
		       try {
				this.notificador.notificar(alias, ip_servicio, Integer.toString(puerto), mail, estado, fecha_sql);
			   } catch (SQLException e) {
				e.printStackTrace();
			   }
			   
			   //Verificacion si falla el servicio
			   try {
					Submonitor.sleep(retry_interval*60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   
			}
		}
	}
	

	
	public void cerrarConexion(){
		try {
			monitor.close();
		 }
		 catch( IOException excepcionES ) {
			 excepcionES.printStackTrace();
		 }
	}

}
