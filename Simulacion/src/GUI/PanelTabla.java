package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;







import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import main.Estado;
import main.Procesador;
import main.Proceso;

public class PanelTabla extends JPanel implements MouseListener{

	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scroll;
	private JButton botonProcesar;
	
	private JPopupMenu menuEmergente;
	private JMenuItem itemCrearProceso;


	private Procesador procesador;


	public PanelTabla(Procesador procesador, ActionListener listener){
		int width = 900;
		int heigth = 300;

		this.procesador = procesador;
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Prioridad", "Transicion", "Se bloquea?"};
		modeloTabla = new DefaultTableModel(0, identificadores.length);
		modeloTabla.setColumnIdentifiers(identificadores);	

		
		tabla = new JTable(modeloTabla){
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }};
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.addMouseListener(this);

		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(width, heigth));
		this.add(scroll);

		botonProcesar = new JButton("Procesar");
		botonProcesar.addActionListener(listener);
		this.add(botonProcesar);	
		
		
		menuEmergente = new JPopupMenu();
		itemCrearProceso = new JMenuItem("Crear Proceso");
		menuEmergente.add(itemCrearProceso);
	}

	public void listarProcesos(){
		Proceso procesoEjecucion = this.procesador.getProcesoEjecucion();
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		ArrayList<Proceso> procesosBloqueados = this.procesador.getProcesosBloqueados();
		ArrayList<Proceso> procesosTerminados = this.procesador.getProcesosTerminados();
		
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Prioridad", "Transicion", "Se bloquea?"};
		modeloTabla = new DefaultTableModel(0, identificadores.length);
		modeloTabla.setColumnIdentifiers(identificadores);
		if (procesoEjecucion!=null){
			agregarFila(procesoEjecucion);
		}
		for (int i = 0; i < procesosListos.size(); i++) {
			Proceso proceso = procesosListos.get(i);
			agregarFila(proceso);
		}
		for (int i = 0; i < procesosBloqueados.size(); i++) {
			Proceso proceso = procesosBloqueados.get(i);
			agregarFila(proceso);
		}
		for (int i = 0; i < procesosTerminados.size(); i++) {
			Proceso proceso = procesosTerminados.get(i);
			agregarFila(proceso);
		}
		//tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
		tabla.setModel(modeloTabla);
	}

	public void agregarFila(Proceso proceso){
		int row = this.modeloTabla.getRowCount();
		modeloTabla.setRowCount(row+1);
		modeloTabla.setValueAt(proceso.getIdentificador(), row, 0); // Identificador 
		modeloTabla.setValueAt(proceso.getEstadoActual(), row, 1);  // Estado
		modeloTabla.setValueAt(proceso.getTiempoFaltante(), row, 2);// Tiempo             
		modeloTabla.setValueAt(proceso.getPrioridad(), row, 3); // Prioridad
		modeloTabla.setValueAt(proceso.getTransicion(), row, 4); // Transicion 
		modeloTabla.setValueAt(proceso.getEstadoActual().equals(Estado.bloqueado), row, 5); // bloqueo?
	}
	
	public void actualizarTiempo(Proceso proceso){
		int indexProceso = 0;
		for (int i = 0; i < this.modeloTabla.getRowCount(); i++) {
			String procesoLista = (String)(this.modeloTabla.getValueAt(i, 0));
			if (procesoLista.equals(proceso.getIdentificador())) {
				indexProceso = i;
				break;
			}
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getButton());
		switch (e.getButton()) {		
		case 1: // Boton derecho
			
			break;
		case 3: // Boton derecho
			System.out.println(e.getX() + " " + e.getY());
			break;
		}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
