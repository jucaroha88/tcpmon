package utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import edu.uca.javacurso.tcpmon.db.TablaMailsReg;


import utils.*;

public class MyTableModel extends AbstractTableModel  {
	public List<TablaMailsReg> registros = new ArrayList<TablaMailsReg>();
	public static final int ALIAS_COLUMN = 0;
    public static final int ADDRESS_COLUMN = 1;
    public static final int PUERTO_COLUMN = 2;
    public static final int EMAIL_COLUMN = 3;
    public static final int ESTADO_COLUMN = 4;
    public static final int FECHA_COUNT = 5;
	
	
	public MyTableModel(List<TablaMailsReg> registros) {
		super();
		this.registros = registros;
	}
	
	public MyTableModel(){
		//contructor default
	}

	public String[] columnNames = { "Alias", "Address", "Puerto",
			"Email", "Estado" };
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
	public int getRowCount() {
		return registros.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	public Object getValueAt(int row, int col) {
		TablaMailsReg celda = registros.get(row);
        switch (col) {
            case ALIAS_COLUMN:
                return celda.getAlias();
            case ADDRESS_COLUMN:
                return celda.getAddress();
            case PUERTO_COLUMN:
                return celda.getPuerto();
            case EMAIL_COLUMN:
                return celda.getEmail();
            case ESTADO_COLUMN:
                return celda.getEstado();
        }
        return null;
	}
	/*
	 * JTable uses this method to determine the default renderer/ editor
	 * for each cell. If we didn't implement this method, then the last
	 * column would contain text ("true"/"false"), rather than a check
	 * box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
	// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
	public void setValueAt(TablaMailsReg value, int row, int col) {
		registros.add(value);
		fireTableCellUpdated(row, col);
	}
	
	
}