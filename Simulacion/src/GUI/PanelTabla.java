package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;






import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import main.Procesador;
import main.Proceso;

public class PanelTabla extends JPanel{
	
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scroll;
	
	private Procesador procesador;
	
	public PanelTabla(Procesador procesador){
		int width = 900;
		int heigth = 500;
		
		this.procesador = procesador;
		modeloTabla = new DefaultTableModel(0, 4);
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Transición"};
		modeloTabla.setColumnIdentifiers(identificadores);	
		
		tabla = new JTable(modeloTabla);
		tabla.getTableHeader().setReorderingAllowed(false);		
		
		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(width, heigth));
		this.add(scroll);
		
		JButton botonProcesar= new JButton("Procesar");
		this.add(botonProcesar);		
	}
	
	public void listarProcesos(){
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		modeloTabla = new DefaultTableModel(procesosListos.size(), 4);
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Transición"};
		modeloTabla.setColumnIdentifiers(identificadores);
		
		for (int i = 0; i < procesosListos.size(); i++) {
			Proceso proceso = procesosListos.get(i);
			
			modeloTabla.setValueAt(proceso.getIdentificador(), i, 0); // Sumamos 1 porque la fila 0 está reservada para los titulos
			modeloTabla.setValueAt(proceso.getEstadoActual(), i, 1); // Sumamos 1 porque la fila 0 está reservada para los titulos
		}
		//tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
		tabla.setModel(modeloTabla);
	}

}
