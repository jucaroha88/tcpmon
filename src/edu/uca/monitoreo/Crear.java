package edu.uca.monitoreo;
import javax.swing.*;

import java.awt.event.*;

public class Crear extends JFrame implements ActionListener{
    private JTextField textfield1;
    private JLabel label1;
    private JButton boton1;
	private JTextPane textpane;
    
    public Crear() {
    	
    	setTitle(" Crear Properties");
    	
        setLayout(null);
        label1=new JLabel("Usuario:");
        label1.setBounds(10,10,100,30);
        add(label1);
        
        textfield1=new JTextField();
        textfield1.setBounds(120,10,300,20);
        add(textfield1);
        
        //textfield1=new JTextField();
        textpane = new JTextPane();
        textpane.setBounds(120,50,300,300);
        textpane.setText("address=\nalias=\ncheck_interval=\ncurrent_state=\nemail_notification=\nhost_name=\nlast_check=\nlast_notificacion=\nmax_check_attempts=\nnotification_interval=\nports_list=\nretry_interval=\ntolerance_attempts=");
        add(textpane);
        
        boton1=new JButton("Aceptar");
        boton1.setBounds(10,400,100,30);
        add(boton1);
        boton1.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
            String cad=textfield1.getText();
            String cadpane=textpane.getText();
            /*creamos el archivo properties con ese nombre*/
            CrearUnFichero.Crear(cad, cadpane);
            System.out.println("Nombre del Archivo: "+cad);
            System.out.println(cadpane);
        }
    }
    
    public static void Inicio1() {
        Crear formulario1=new Crear();
        formulario1.setBounds(200,200,500,500);
        formulario1.setVisible(true);
    }
}