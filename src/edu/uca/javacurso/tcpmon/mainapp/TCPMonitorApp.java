package edu.uca.javacurso.tcpmon.mainapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import monitoreo.Detector;

import GUI.Panel;

import edu.uca.javacurso.tcpmon.Notificador;
import edu.uca.javacurso.tcpmon.db.TCPMonDB;
import edu.uca.javacurso.tcpmon.mail.Mailer;
import edu.uca.monitoreo.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TCPMonitorApp {

	private JFrame frame;
	
	Detector monitor;
	
	private TCPMonDB db;
	private Mailer mailer;
	private Notificador notificador;

	public TCPMonDB getDb() {
		return db;
	}

	public Notificador getNotificador() {
		return notificador;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TCPMonitorApp window = new TCPMonitorApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TCPMonitorApp() {
		this.db = new TCPMonDB("ledb");
		this.mailer = new Mailer();
		this.notificador = new Notificador(db, mailer);
		
		this.monitor = new Detector();
		monitor.ejecutar(notificador);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Editar Archivos de Configuracion");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		                Principal.createAndShowGUI(); 
		            }
		        });
			}
		});
		btnNewButton.setBounds(45, 30, 239, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegistroDeMails = new JButton("Registro de Mails Enviados");
		btnRegistroDeMails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Panel.createAndShowGUI(db);
			}
		});
		btnRegistroDeMails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRegistroDeMails.setBounds(66, 117, 202, 25);
		frame.getContentPane().add(btnRegistroDeMails);
	}

}
