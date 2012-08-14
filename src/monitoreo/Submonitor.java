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
	
	Notificador notificador;

	private Socket monitor;

	//variables para el archivo properties
	private String ip_servicio = "";
	private int puerto = 0;
	private int check_interval = 0;
	private String alias = "";
	private String mail = "";
	private String estado = "";
	private String fecha_actual="";
	
	private ArchivoPropiedades archivo;
	
	static final Logger logger = Logger.getLogger(Submonitor.class);

	
	public Submonitor(ArchivoPropiedades archivo, Notificador notificador) {
		super();
		
		this.notificador = notificador;
		
		this.archivo=archivo;
		this.ip_servicio = archivo.getValorPropiedad("address");
		
		StringTokenizer puertos = new StringTokenizer(
				archivo.getValorPropiedad("ports_list"), ",");
		
		this.puerto = Integer.parseInt(puertos.nextToken());
		//this.check_interval = Integer.parseInt(archivo.getValorPropiedad("check_interval"));
		
		this.alias = archivo.getValorPropiedad("alias");
		
		this.mail = archivo.getValorPropiedad("email_notification");
		
		
	}


	public void run(){
		
		BasicConfigurator.configure();
		
		//check interval
			try {
				
				monitor = new Socket( InetAddress.getByName( ip_servicio ), puerto );
				

				Date ahora = new Date();
				SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
				fecha_actual = formateador.format(ahora);
				
				
				
				archivo.escribirPropiedad("current_state", "OK");
				archivo.escribirPropiedad("last_check", fecha_actual);
				
				logger.info("Conexion Exitosa" + " con " + alias + " " + fecha_actual);

				estado = archivo.getValorPropiedad("current_state");
				//guardar_db(alias, ip_servicio, puerto, mail, estado, fecha);
				//enviar_mail(alias, ip_servicio, puerto, mail, estado, fecha);
				
				
				cerrarConexion();
				
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
			   
		       //enviar_mail(alias, ip_servicio, puerto, mail, estado, fecha);
		       //guardar_db(alias, ip_servicio, puerto, mail, estado, fecha);
		       try {
				this.notificador.notificar(alias, ip_servicio, Integer.toString(puerto), mail, estado, fecha_sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
