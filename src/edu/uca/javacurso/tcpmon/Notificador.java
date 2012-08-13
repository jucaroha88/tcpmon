package edu.uca.javacurso.tcpmon;

import java.sql.SQLException;

import edu.uca.javacurso.tcpmon.db.TCPMonDB;
import edu.uca.javacurso.tcpmon.db.TablaMailsReg;
import edu.uca.javacurso.tcpmon.mail.Mailer;

public class Notificador {
	TCPMonDB db;
	Mailer mailer;
	
	public Notificador(TCPMonDB db, Mailer mailer) {
		this.db=db;
		this.mailer = mailer;
	}
	
	public synchronized void notificar(String alias, String address, String puerto,
			String email, String estado, String fecha) throws SQLException{
		this.db.tablamails.insertar(new TablaMailsReg(alias, address, puerto, email, estado, fecha));
		this.mailer.sendReportingMail(email, address);
	}
}
