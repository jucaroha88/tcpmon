package edu.uca.javacurso.tcpmon.db;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class TablaMails{
	
	final static String tableName = "mails";
	private TCPMonDB db;
	
	public TablaMails(TCPMonDB db) {
		this.db=db;
	}
	
	public void crear(){
		String query = "CREATE TABLE "+tableName+
							" (" +
							"alias varchar(255), " +
							"address varchar(255), " +
							"puerto varchar(255), " +
							"email varchar(255), " +
							"estado varchar(255), " +
							"fecha timestamp" +
							" ) ";
		try{
			Statement stmt = this.db.createStatement();
			stmt.executeUpdate(query);
		}catch(Exception e){
			System.err.println("SQLError en la consulta '"+query+"'");
			e.printStackTrace();
		}
	}
	
	public void insertar(TablaMailsReg reg) throws SQLException{
		String query = reg.generarInsert();
		try{
			Statement stmt = this.db.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException e){
			System.err.println("SQError en la consulta '"+query+"'");
			e.printStackTrace();
		}
	}
}
