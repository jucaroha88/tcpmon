package edu.uca.javacurso.tcpmon.db;

import edu.uca.javacurso.tcpmon.db.TablaMails;

public class DBTests {
	public static void main(String[] args) {
		try{
			TCPMonDB db = new TCPMonDB("ledb");
//			db.tablamails.insertar(new TablaMailsReg("claroline", "201.217.5.148", "80", "stu@claro.edu.py", "chobola", "2012-08-12 10:51:32"));
//			db.tablamails.insertar(new TablaMailsReg("router", "192.168.1.1", "80", "jucaroha88@gmail.com", "routerchobola", "2012-08-12 11:55:32"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("todo bien");
	}
}
