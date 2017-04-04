package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;







import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import main.Estado;
import main.Procesador;
import main.Proceso;

public class PanelListas extends JPanel{

	private DefaultTableModel modeloTablaListos;
	private JTable tablaListos;
	private JScrollPane scrollListos;
	
	private DefaultTableModel modeloTablaBloqueados;
	private JTable tablaBloqueados;
	private JScrollPane scrollBloqueados;
	
	private JButton botonProcesar;

	private Procesador procesador;


	public PanelListas(Procesador procesador, ActionListener listener){
		int width = 450;
		int heigth = 350;

		this.procesador = procesador;
		String[] identificadores = {"Lista Procesos listos"};
		modeloTablaListos = new DefaultTableModel(0, identificadores.length);
		modeloTablaListos.setColumnIdentifiers(identificadores);	

		
		tablaListos = new JTable(modeloTablaListos){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
		tablaListos.getTableHeader().setReorderingAllowed(false);
		scrollListos = new JScrollPane(tablaListos);
		scrollListos.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollListos);

		
		String[] identificadores2 = {"Lista Procesos Bloqueados"};
		modeloTablaBloqueados = new DefaultTableModel(0, identificadores2.length);
		modeloTablaBloqueados.setColumnIdentifiers(identificadores2);	

		
		tablaBloqueados = new JTable(modeloTablaBloqueados){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tablaBloqueados.getTableHeader().setReorderingAllowed(false);		

			scrollBloqueados = new JScrollPane(tablaBloqueados);
			scrollBloqueados.setPreferredSize(new Dimension(width, heigth));
			this.add(scrollBloqueados);
		
		
		
		

		botonProcesar = new JButton("Procesar");
		botonProcesar.addActionListener(listener);
		//this.add(botonProcesar);		
	}

	public void listarProcesos(){
		Proceso procesoEjecucion = this.procesador.getProcesoEjecucion();
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		ArrayList<Proceso> procesosBloqueados = this.procesador.getProcesosBloqueados();
		ArrayList<Proceso> procesosTerminados = this.procesador.getProcesosTerminados();
		
		String[] identificadores = {"Lista Procesos listos"};
		modeloTablaListos = new DefaultTableModel(0, identificadores.length);
		modeloTablaListos.setColumnIdentifiers(identificadores);
		
		String[] identificadores2 = {"Lista Procesos Bloqueados"};
		modeloTablaBloqueados = new DefaultTableModel(0, identificadores2.length);
		modeloTablaBloqueados.setColumnIdentifiers(identificadores2);	
		
		for (int i = 0; i < procesosListos.size(); i++) {
			Proceso proceso = procesosListos.get(i);
			agregarListo(proceso);
		}
		for (int i = 0; i < procesosBloqueados.size(); i++) {
			Proceso proceso = procesosBloqueados.get(i);
			agregarBloqueado(proceso);
		}		
		//tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
		tablaListos.setModel(modeloTablaListos);
		tablaBloqueados.setModel(modeloTablaBloqueados);
	}

	public void agregarListo(Proceso proceso){
		int row = this.modeloTablaListos.getRowCount();
		modeloTablaListos.setRowCount(row+1);
		modeloTablaListos.setValueAt(proceso.getIdentificador(), row, 0); 
		
	}
	
	public void agregarBloqueado(Proceso proceso){
		int row = this.modeloTablaBloqueados.getRowCount();
		modeloTablaBloqueados.setRowCount(row+1);
		modeloTablaBloqueados.setValueAt(proceso.getIdentificador(), row, 0); 
	}
	

}
