package edu.uca.javacurso.tcpmon.db;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.StringTokenizer;

public class TablaMailsReg {
	private String alias;
	private String address;
	private String puerto;
	private String email;
	private String estado;
	private String fecha;
	public TablaMailsReg(String alias, String address, String puerto,
			String email, String estado, String fecha) {
		super();
		this.alias = alias;
		this.address = address;
		this.puerto = puerto;
		this.email = email;
		this.estado = estado;
		this.fecha = fecha;
	}

	public String generarInsert(){
		String query = "INSERT INTO "+TablaMails.tableName+" (alias,address,puerto,email,estado,fecha) VALUES ('" +
																										alias+"', '" +
																										address+"', '"+
																										puerto+"', '"+
																										email+"', '"+
																										estado+"', '"+
																										fecha+"' )";
		return query;
	}
	
	@Override
	public String toString() {
		return "("+alias+","+address+","+puerto+","+email+","+estado+","+fecha+")";
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}