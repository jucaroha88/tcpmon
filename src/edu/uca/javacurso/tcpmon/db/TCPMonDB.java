package edu.uca.javacurso.tcpmon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TCPMonDB {
	public TablaMails tablamails = null;
	private Connection conn=null;
	private String nombre=null;
	
	/*
	 * Al instanciar, se carga el driver (si todavia no esta
	 * cargado)
	 */
	public TCPMonDB() {
		//cargamos driver
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		this.tablamails = new TablaMails(this);
	}
	
	/* se carga el driver y se conecta a la base de datos */
	public TCPMonDB(String dbname){
		this();
		this.conectar(dbname);
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.shutdown();
		super.finalize();
	}
	
	public Statement createStatement() throws SQLException{
		return this.conn.createStatement();
	}
	
	public void conectar(String dbname){
		this.nombre=dbname;
		String connectionURL = "jdbc:derby:"+this.nombre;
		String connectionURLcreate = connectionURL+";create=true";
		try {
			this.conn = DriverManager.getConnection(connectionURL);
		} catch (SQLException e) {
			//no se pudo conectar a la bd... tratamos de crear
			try{
				this.conn = DriverManager.getConnection(connectionURLcreate);
				this.tablamails.crear();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void shutdown(){
		String connectionURL = "jdbc:derby:"+this.nombre+";shutdown=true";
		try{
			this.conn=DriverManager.getConnection(connectionURL); //siempre tira una excepcion
		}catch(Exception e){
			
		}
	}

}
