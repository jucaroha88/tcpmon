package edu.uca.javacurso.tcpmon.db;

import edu.uca.javacurso.tcpmon.db.TablaMails;

public class DBTests {
	public static void main(String[] args) {
		try{
			TCPMonDB db = new TCPMonDB("/home/jk/workspace/detector/ledb");
//			db.tablamails.insertar(new TablaMailsReg("claroline", "201.217.5.148", "80", "stu@claro.edu.py", "chobola", "2012-08-12 10:51:32"));
//			db.tablamails.insertar(new TablaMailsReg("router", "192.168.1.1", "80", "jucaroha88@gmail.com", "routerchobola", "2012-08-12 11:55:32"));
//			db.tablamails.insertar(new TablaMailsReg("asd", "201.217.8.148", "544", "asd@claro.edu.py", "maomeno", "2012-08-11 10:31:32"));
//			db.tablamails.insertar(new TablaMailsReg("poi", "192.168.1.3", "998", "jucaroha88@gmail.com", "yyyy...", "2012-02-12 11:55:32"));
			
//			System.out.println(db.tablamails.getRegistrosPorRangoFecha("2012-07-01 10:00:00", "2012-08-13 20:30:00").toString());
			System.out.println(db.tablamails.getRegistrosPorRangoFecha("2012-07-01 10:00:00", "2012-08-13 20:30:00").toString());
//			System.out.println(db.tablamails.getAllRegistros());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("asd");
	}
}
