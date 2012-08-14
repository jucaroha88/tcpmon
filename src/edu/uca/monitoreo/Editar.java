package edu.uca.monitoreo;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Editar extends JFrame implements ActionListener{
    private JTextField textfield1;
    private JLabel label1;
    private JButton boton1, boton2;
	private JTextPane textpane;
	private String cadpane, cad;
    
    public Editar() {
    	
    	setTitle(" Editar Properties");
    	
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
        
        boton2=new JButton("Guardar");
        boton2.setBounds(10,450,100,30);
        add(boton2);
        boton2.addActionListener(this);
        
    }
    
    /*Concepto Gral: 
     * abrir lo que se carga en cad
     * si existe cargar en cadpane
     * editar y al darle guardar 
     * que sobreescriba el archivo*/
    
    public void actionPerformed(ActionEvent e) {
    	/*al darle el boton 1 abrir el archivo
         * si existe y cargar en cadpane el contenido
         * para si visualizacion*/
    	
    	if (e.getSource()==boton1) {
            //obtenemos lo que se ingreso en el text
    		cad=textfield1.getText();
    		
    		/*abrimos el archivo que esta en cad 
             * y cargamos su contenido en cadpane*/		
    		cadpane = LeerFicheroTexto.Leer("Servidores/"+cad+".properties");
            
    		/*cargamos el contenido de cadpane en textpane
    		 * para visualizar en GUI*/
    		textpane.setText(cadpane);
        }
        
    	/*al darle el boton 2 abrir el archivo 
    	 *cargar en cadpane lo que hay y escribir eso
    	 *en el archivo*/
        if (e.getSource()==boton2) {
           // cad=textfield1.getText();
            cadpane=textpane.getText();
            /*no le agregamos .properties aca por que se hace
             * eso en "crear un archivo"*/
            CrearUnFichero.Crear(cad, cadpane);
            //System.out.println("Nombre del Archivo: "+cad);
            //System.out.println(cadpane);
        }     
    }
    
    public static void Inicio2() {
        Editar formulario1=new Editar();
        formulario1.setBounds(200,200,500,500);
        formulario1.setVisible(true);
    }
}