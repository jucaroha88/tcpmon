package edu.uca.javacurso.tcpmon.db;

import java.sql.ResultSet;
import java.sql.Timestamp;

public class TablaMailsReg {
	String alias;
	String address;
	String puerto;
	String email;
	String estado;
	String fecha;
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
}