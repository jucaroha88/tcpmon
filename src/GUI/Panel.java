package GUI;

import utils.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import edu.uca.javacurso.tcpmon.db.TCPMonDB;
import edu.uca.javacurso.tcpmon.db.TablaMailsReg;
import edu.uca.monitoreo.Editar;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel {
	TCPMonDB db;
	
	private JTable table;
	private JRadioButton rdbtnFecha;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnAddress;
	private JRadioButton rdbtnEstado;
	private JRadioButton rdbtnPuerto;
	private JRadioButton rdbtnEmail;
	private JTextField textFieldBuscar;
	private JTextField textFieldHasta;
	private JButton btnAceptar;
	private ButtonGroup botones = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public Panel(TCPMonDB db) {
		
		this.db = db;
		setLayout(null);

		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setBounds(41, 28, 62, 14);
		lblBuscar.setVerticalAlignment(SwingConstants.BOTTOM);
		add(lblBuscar);

		List<TablaMailsReg> registros = null;
		try {
			registros = db.tablamails.getAllRegistros();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*TablaMailsReg te = new TablaMailsReg("claroline-uca", "201.217.5.148", "80",
				"noc@uca.edu.py", "OK");

		registros.add(te);*/

		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(90, 25, 86, 20);
		// textField.setHorizontalAlignment(SwingConstants.CENTER);
		add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		utils.MyTableModel busqueda = new utils.MyTableModel(registros);

		table = new JTable(busqueda);
		table.setPreferredScrollableViewportSize(new Dimension(600, 80));
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 136, 525, 120);

		// Add the scroll pane to this panel.
		add(scrollPane);

		JSeparator separator = new JSeparator();
		scrollPane.setColumnHeaderView(separator);

		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		
		/**************************************************************************************************/
		

		rdbtnFecha = new JRadioButton("Rango de fechas");
		rdbtnFecha.setBounds(182, 19, 134, 23);
		add(rdbtnFecha);
		rdbtnFecha.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnFecha.setSelected(true);
		botones.add(rdbtnFecha);

		rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setBounds(183, 53, 119, 23);
		add(rdbtnEmail);
		botones.add(rdbtnEmail);

		rdbtnNewRadioButton = new JRadioButton("Alias");
		rdbtnNewRadioButton.setBounds(362, 19, 86, 23);
		add(rdbtnNewRadioButton);
		botones.add(rdbtnNewRadioButton);

		rdbtnAddress = new JRadioButton("Address");
		rdbtnAddress.setBounds(363, 52, 85, 23);
		add(rdbtnAddress);
		rdbtnAddress.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnAddress.setVerticalAlignment(SwingConstants.BOTTOM);
		botones.add(rdbtnAddress);

		rdbtnEstado = new JRadioButton("Estado");
		rdbtnEstado.setBounds(476, 18, 86, 23);
		add(rdbtnEstado);
		rdbtnEstado.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnEstado.setVerticalAlignment(SwingConstants.BOTTOM);
		botones.add(rdbtnEstado);

		rdbtnPuerto = new JRadioButton("Puerto");
		rdbtnPuerto.setBounds(476, 53, 86, 23);
		add(rdbtnPuerto);
		botones.add(rdbtnPuerto);
		
		textFieldHasta = new JTextField();
		textFieldHasta.setColumns(10);
		textFieldHasta.setBounds(90, 52, 86, 20);
		add(textFieldHasta);
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHasta.setBounds(41, 58, 62, 14);
		add(lblHasta);
		
		
		
		/**************************************************************************************************/
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(90, 83, 89, 23);
		add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	botonAccion(e);		            	
                System.out.println("You clicked the button");
            }
        });   
	}
	
	public void botonAccion (ActionEvent evnt){
		
		String buscar = textFieldBuscar.getText();
		String hasta = textFieldHasta.getText();
		
		List<TablaMailsReg> registros = null;
		/*TablaMailsReg te = new TablaMailsReg("modern", "201.217.5.148", "80",
				"noc@uca.edu.py", "OK");

		registros.add(te);*/
		try{
			if (rdbtnAddress.isSelected()){
				registros = db.tablamails.getRegistrosPorCampoVarchar("address", buscar);
				//tabla para mostrar direcciones
				System.out.println("el texto a buscar: " + buscar + "\n");
				table.setModel(new MyTableModel(registros));
				
			}
			
			if (rdbtnEmail.isSelected()){
				//tabla para mostrar emails
				System.out.println("el texto a buscar: " + buscar + "\n");
				registros = db.tablamails.getRegistrosPorCampoVarchar("email", buscar);
				table.setModel(new MyTableModel(registros));
			}
			
			if (rdbtnEstado.isSelected()){
				//tabla para mostrar el estado
				System.out.println("el texto a buscar: " + buscar + "\n");
				registros = db.tablamails.getRegistrosPorCampoVarchar("estado", buscar);
				table.setModel(new MyTableModel(registros));
			}
			
			if(rdbtnFecha.isSelected()){
				//tabla por rango de fechas
				System.out.println("el texto a buscar: " + buscar + "\n");
				System.out.println("el texto a hasta: " + hasta + "\n");
				try{
					registros = db.tablamails.getRegistrosPorRangoFecha(buscar, hasta);
					table.setModel(new MyTableModel(registros));
				}catch(Exception e){
					JOptionPane.showMessageDialog(this, "El formato de timestamp debe ser YYYY-MM-DD hh:mm:ss");
				}
			}
			
			if(rdbtnNewRadioButton.isSelected()){
				//tabla para mostrar el alias
				System.out.println("el texto a buscar: " + buscar + "\n");
				registros = db.tablamails.getRegistrosPorCampoVarchar("alias", buscar);
				table.setModel(new MyTableModel(registros));
			}
			
			if(rdbtnPuerto.isSelected()){
				//tabla para mostrar los puertos
				System.out.println("el texto a buscar: " + buscar + "\n");
				registros = db.tablamails.getRegistrosPorCampoVarchar("puerto", buscar);
				table.setModel(new MyTableModel(registros));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void createAndShowGUI(TCPMonDB db)  {
		// Create and set up the window.
				JFrame frame = new JFrame("Busqueda");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Create and set up the content pane.
				Panel newContentPane = new Panel(db);
				newContentPane.setOpaque(true); // content panes must be opaque
				frame.setContentPane(newContentPane);   

				// Display the window.
				frame.pack();
				frame.setVisible(true);
				frame.setSize(700, 400);
	}
	
	public static void mostrar(TCPMonDB db){
		Panel formulario1=new Panel(db);
        formulario1.setBounds(200,200,700,700);
        formulario1.setVisible(true);
	}
	

	/*public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}*/
}
