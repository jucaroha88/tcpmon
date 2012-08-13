package edu.uca.javacurso.tcpmon.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	public static List<TablaMailsReg> registrosFromResultSet(ResultSet rs) throws SQLException{
		List<TablaMailsReg> lista = new ArrayList<TablaMailsReg>();
		while(rs.next()){
			lista.add(new TablaMailsReg(rs.getString("alias"),
					rs.getString("address"),
					rs.getString("puerto"),
					rs.getString("email"),
					rs.getString("estado"),
					rs.getTimestamp("fecha").toString()
					));
		}
		return lista;
	}
	
	public List<TablaMailsReg> getRegistrosPorCampoVarchar(String nombreCampo, String valor) throws SQLException{
		String query = "SELECT * FROM "+tableName+" WHERE "+nombreCampo+" = "+"'"+valor+"'";
		Statement stmt = this.db.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return registrosFromResultSet(rs);
	}
	
	public List<TablaMailsReg> getRegistrosPorRangoFecha(String fechaInicio, String fechaFin) throws SQLException{
		String query = "SELECT * FROM "+tableName+" WHERE fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"'";
		Statement stmt = this.db.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return registrosFromResultSet(rs);
	}
}
