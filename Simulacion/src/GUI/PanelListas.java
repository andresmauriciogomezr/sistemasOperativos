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

	// Tabla para listos
	private DefaultTableModel modeloTablaListos;
	private JTable tablaListos;
	private JScrollPane scrollListos;
	
	// Tabla para listos
	private DefaultTableModel modeloTablaBloqueados;
	private JTable tablaBloqueados;
	private JScrollPane scrollBloqueados;
	
	// Tabla para suspendidos
	private DefaultTableModel modeloTablaSuspendidos;
	private JTable tablaSuspedidos;
	private JScrollPane scrollSuspendidos;
	
	// Tabla para destruidos
	private DefaultTableModel modeloTablaDestruidos;
	private JTable tablaDestruidos;
	private JScrollPane scrollDestruidos;
	
	// Tabla para destruidos
	private DefaultTableModel modeloTablaComunicados;
	private JTable tablaComunicados;
	private JScrollPane scrollComunicados;
	
	// Tabla para transiciones
	private DefaultTableModel modeloTablaTransiciones;
	private JTable tablaTransicioens;
	private JScrollPane scrollTransiciones;
	
	// Tabla para transiciones
		private DefaultTableModel modeloTablaEjecutados;
		private JTable tablaEjecutados;
		private JScrollPane scrollEjecutados;
	
	private JButton botonProcesar;

	private Procesador procesador;


	public PanelListas(Procesador procesador){
		int width = 300;
		int heigth = 350;

		this.procesador = procesador;
		
		// Listos
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
		
		// Ejecutados
		String[] identificadoresEjecutados = {"Lista Ejecutados"};
		modeloTablaEjecutados= new DefaultTableModel(0, identificadoresEjecutados.length);
		modeloTablaEjecutados.setColumnIdentifiers(identificadoresEjecutados);	
		tablaEjecutados= new JTable(modeloTablaEjecutados){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tablaEjecutados.getTableHeader().setReorderingAllowed(false);
		scrollEjecutados= new JScrollPane(tablaEjecutados);
		scrollEjecutados.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollEjecutados);

		// Bloqueados
		String[] identificadoresBloqueado = {"Lista Procesos Bloqueados"};
		modeloTablaBloqueados = new DefaultTableModel(0, identificadoresBloqueado.length);
		modeloTablaBloqueados.setColumnIdentifiers(identificadoresBloqueado);		
		tablaBloqueados = new JTable(modeloTablaBloqueados){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
		}};
		tablaBloqueados.getTableHeader().setReorderingAllowed(false);
		scrollBloqueados = new JScrollPane(tablaBloqueados);
		scrollBloqueados.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollBloqueados);
		
		// Suspendidos
		String[] identificadoresSuspendidos = {"Lista Procesos Suspendidos"};
		modeloTablaSuspendidos = new DefaultTableModel(0, identificadoresSuspendidos.length);
		modeloTablaSuspendidos.setColumnIdentifiers(identificadoresSuspendidos);		
		tablaSuspedidos = new JTable(modeloTablaSuspendidos){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
		}};
		tablaSuspedidos.getTableHeader().setReorderingAllowed(false);
		scrollSuspendidos= new JScrollPane(tablaSuspedidos);
		scrollSuspendidos.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollSuspendidos);
		
		// Destruidos
		String[] identificadoresDestruidos = {"Lista Procesos Destruidos"};
		modeloTablaDestruidos= new DefaultTableModel(0, identificadoresDestruidos.length);
		modeloTablaDestruidos.setColumnIdentifiers(identificadoresDestruidos);		
		tablaDestruidos= new JTable(modeloTablaDestruidos){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tablaDestruidos.getTableHeader().setReorderingAllowed(false);
		scrollDestruidos= new JScrollPane(tablaDestruidos);
		scrollDestruidos.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollDestruidos);
		
		// Comunicados
		String[] identificadoresComunicados = {"Lista Procesos Comunicados"};
		modeloTablaComunicados= new DefaultTableModel(0, identificadoresComunicados.length);
		modeloTablaComunicados.setColumnIdentifiers(identificadoresComunicados);		
		tablaComunicados = new JTable(modeloTablaComunicados){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tablaComunicados.getTableHeader().setReorderingAllowed(false);
		scrollComunicados= new JScrollPane(tablaComunicados);
		scrollComunicados.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollComunicados);
		
		// Transiciones
		String[] identificadoresTransiciones= {"Lista Transiciones"};
		modeloTablaTransiciones= new DefaultTableModel(0, identificadoresTransiciones.length);
		modeloTablaTransiciones.setColumnIdentifiers(identificadoresTransiciones);		
		tablaTransicioens= new JTable(modeloTablaTransiciones){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tablaTransicioens.getTableHeader().setReorderingAllowed(false);
		scrollTransiciones= new JScrollPane(tablaTransicioens);
		scrollTransiciones.setPreferredSize(new Dimension(width, heigth));
		this.add(scrollTransiciones);
		
		
	}

	public void listarProcesos(){
		Proceso procesoEjecucion = this.procesador.getProcesoEjecucion();
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		ArrayList<Proceso> procesosBloqueados = this.procesador.getProcesosBloqueados();
		ArrayList<Proceso> procesosSuspendidos = this.procesador.getProcesosSuspendidos();
		ArrayList<Proceso> procesosDestruidos = this.procesador.getProcesosDestruidos();
		ArrayList<Proceso> procesosComunicados = this.procesador.getProcesosComunicados();
		ArrayList<Proceso> procesosTerminados = this.procesador.getProcesosTerminados();
		
		ArrayList<Proceso> procesos= this.procesador.getListaComun();
		ArrayList<String> ejecutados = this.procesador.getListaEjecutados();
		
		String[] identificadores = {"Lista Procesos listos"};
		modeloTablaListos = new DefaultTableModel(0, identificadores.length);
		modeloTablaListos.setColumnIdentifiers(identificadores);
		
		String[] identificadores2 = {"Lista Procesos Bloqueados"};
		modeloTablaBloqueados = new DefaultTableModel(0, identificadores2.length);
		modeloTablaBloqueados.setColumnIdentifiers(identificadores2);	
		
		String[] identificadoresSuspendidos = {"Lista Procesos Suspendidos"};
		modeloTablaSuspendidos = new DefaultTableModel(0, identificadoresSuspendidos.length);
		modeloTablaSuspendidos.setColumnIdentifiers(identificadoresSuspendidos);	
		
		String[] identificadoresDestruidos = {"Lista Procesos Destruidos"};
		modeloTablaDestruidos= new DefaultTableModel(0, identificadoresDestruidos.length);
		modeloTablaDestruidos.setColumnIdentifiers(identificadoresDestruidos);	
		
		String[] identificadoresComunicados = {"Lista Procesos Comunicados"};
		modeloTablaComunicados= new DefaultTableModel(0, identificadoresComunicados.length);
		modeloTablaComunicados.setColumnIdentifiers(identificadoresComunicados);	
		
		String[] identificadoresTransiciones= {"Lista Transiciones"};
		modeloTablaTransiciones= new DefaultTableModel(0, identificadoresTransiciones.length);
		modeloTablaTransiciones.setColumnIdentifiers(identificadoresTransiciones);
		
		String[] identificadoresEjecutados = {"Lista Ejecutados"};
		modeloTablaEjecutados= new DefaultTableModel(0, identificadoresEjecutados.length);
		modeloTablaEjecutados.setColumnIdentifiers(identificadoresEjecutados);	
		
		for (int i = 0; i < procesosListos.size(); i++) {
			Proceso proceso = procesosListos.get(i);
			agregarListo(proceso);
		}
		for (int i = 0; i < procesosBloqueados.size(); i++) {
			Proceso proceso = procesosBloqueados.get(i);
			agregarBloqueado(proceso);
		}
		for (int i = 0; i < procesosSuspendidos.size(); i++) {
			Proceso proceso = procesosSuspendidos.get(i);
			agregarSuspendido(proceso);
		}	
		for (int i = 0; i < procesosDestruidos.size(); i++) {
			Proceso proceso = procesosDestruidos.get(i);
			agregarDestruido(proceso);
		}	
		for (int i = 0; i < procesosComunicados.size(); i++) {
			Proceso proceso = procesosComunicados.get(i);
			agregarComunicado(proceso);
		}
		
		for (int i = 0; i < procesos.size(); i++) {
			Proceso proceso = procesos.get(i);
			agregarTransicion(proceso.getTransicion());
		}
		
		for (int i = 0; i < ejecutados.size(); i++) {
			agregarTransicion(ejecutados.get(i));
		}
		
		//tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
		tablaListos.setModel(modeloTablaListos);
		tablaBloqueados.setModel(modeloTablaBloqueados);
		tablaSuspedidos.setModel(modeloTablaSuspendidos);
		tablaDestruidos.setModel(modeloTablaDestruidos);
		tablaComunicados.setModel(modeloTablaComunicados);
		tablaTransicioens.setModel(modeloTablaTransiciones);
		tablaEjecutados.setModel(modeloTablaEjecutados);
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
	
	public void agregarSuspendido(Proceso proceso){
		int row = this.modeloTablaSuspendidos.getRowCount();
		modeloTablaSuspendidos.setRowCount(row+1);
		modeloTablaSuspendidos.setValueAt(proceso.getIdentificador(), row, 0); 
	}
	
	public void agregarDestruido(Proceso proceso){
		int row = this.modeloTablaDestruidos.getRowCount();
		modeloTablaDestruidos.setRowCount(row+1);
		modeloTablaDestruidos.setValueAt(proceso.getIdentificador(), row, 0); 
	}
	
	public void agregarComunicado(Proceso proceso){
		int row = this.modeloTablaComunicados.getRowCount();
		modeloTablaComunicados.setRowCount(row+1);
		modeloTablaComunicados.setValueAt(proceso.getIdentificador(), row, 0); 
	}
	
	public void agregarTransicion(String proceso){
		int row = this.modeloTablaTransiciones.getRowCount();
		modeloTablaTransiciones.setRowCount(row+1);
		modeloTablaTransiciones.setValueAt(proceso, row, 0); 
	}
	

}
