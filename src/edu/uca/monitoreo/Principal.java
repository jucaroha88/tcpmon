package edu.uca.monitoreo;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* 
 *Interfaz Grafica para la creacion y edicion de 
 *archivos properties
 *
 *Owner: Martin Zorrilla
 *Matricula: 54888
 *
 */
public class Principal extends JPanel
                        implements ActionListener {
    protected JButton b1, b2;

    public Principal() {
        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
        
        b1 = new JButton("Editar", leftButtonIcon);
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
        b1.setActionCommand("editar");
        //b1.setBounds(10,10,200,200);
        

        b2 = new JButton("Crear", middleButtonIcon);
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);
        //yo agregue
        b2.setActionCommand("crear");
        //b1.setBounds(10,250,200,200);        

        //Listen for actions on buttons 1 and 3.
        b1.addActionListener(this);
        b2.addActionListener(this);

        b1.setToolTipText("Click this button to disable the middle button.");
        b2.setToolTipText("This middle button does nothing when you click it.");

        //Add Components to this container, using the default FlowLayout.
        add(b1);
        add(b2);
    }

    
    /*Dependiendo del boton presionado se llama a una u otra funcion*/
    public void actionPerformed(ActionEvent e) {
        //si se presiona el boton del crear
        if ("crear".equals(e.getActionCommand())) {
            Crear.Inicio1();
        }
        if ("editar".equals(e.getActionCommand())) {
            Editar.Inicio2();
        }     
    
        
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Principal.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            //System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Crear-Editar Properties");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400,400);
        
        //Create and set up the content pane.
        Principal newContentPane = new Principal();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(450,250,400,200);
    }

    public static void main(String[] args) {
    	//crear y mostrar la  la GUI 
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
    
}