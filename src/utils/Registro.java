package utils;

import java.sql.Date;

public class Registro {
	private String alias;
	private String address;
	private String puerto;
	private String email;
	private String estado;
	private String fecha;
	
	public Registro(String a, String ad, String p, String em, String es, String fecha){
		this.alias = a;
		this.address = ad;
		this.puerto = p;
		this.estado = es;
		this.email = em;
		this.fecha = fecha;
		
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

}
