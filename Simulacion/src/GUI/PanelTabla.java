package GUI;

import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.Procesador;
import main.Proceso;

public class PanelTabla extends JPanel{
	
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scroll;
	
	private Procesador procesador;
	
	public PanelTabla(Procesador procesador){
		this.procesador = procesador;
		modeloTabla = new DefaultTableModel(1, 2);
		String[] identificadores = {"Identificador", "Estado"};
		modeloTabla.setColumnIdentifiers(identificadores);		
		
		tabla = new JTable(modeloTabla);
		tabla.getTableHeader().setReorderingAllowed(false);		
		
		scroll = new JScrollPane(tabla);
		
		this.add(scroll);
	}
	
	public void listarProcesos(){
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		modeloTabla = new DefaultTableModel(procesosListos.size(), 2);
		String[] identificadores = {"Identificador", "Estado"};
		modeloTabla.setColumnIdentifiers(identificadores);
		
		for (int i = 0; i < procesosListos.size(); i++) {
			Proceso proceso = procesosListos.get(i);
			modeloTabla.setValueAt(proceso.getIdentificador(), i, 0); // Sumamos 1 porque la fila 0 está reservada para los titulos
			modeloTabla.setValueAt(proceso.getEstadoActual(), i, 1); // Sumamos 1 porque la fila 0 está reservada para los titulos
		}
		
		tabla.setModel(modeloTabla);
	}

}
