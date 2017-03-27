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

import main.Procesador;
import main.Proceso;

public class PanelTabla extends JPanel{

	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scroll;
	private JButton botonProcesar;

	private Procesador procesador;


	public PanelTabla(Procesador procesador, ActionListener listener){
		int width = 900;
		int heigth = 500;

		this.procesador = procesador;
		modeloTabla = new DefaultTableModel(0, 4);
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Transici�n"};
		modeloTabla.setColumnIdentifiers(identificadores);	

		
		tabla = new JTable(modeloTabla){
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }};


		tabla = new JTable(modeloTabla);

		tabla.getTableHeader().setReorderingAllowed(false);		

		scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(width, heigth));
		this.add(scroll);

		botonProcesar = new JButton("Procesar");
		botonProcesar.addActionListener(listener);
		this.add(botonProcesar);		
	}

	public void listarProcesos(){
		Proceso procesoEjecucion = this.procesador.getProcesoEjecucion();
		ArrayList<Proceso> procesosListos = this.procesador.getProcesosListos();		
		ArrayList<Proceso> procesosBloqueados = this.procesador.getProcesosBloqueados();
		ArrayList<Proceso> procesosTerminados = this.procesador.getProcesosTerminados();
		
		String[] identificadores = {"Identificador", "Estado", "Tiempo", "Transici�n"};
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
//		System.out.println("columnas: " + modeloTabla.getColumnCount());
//		System.out.println("filas: " + modeloTabla.getRowCount());
		int row = this.modeloTabla.getRowCount();
		modeloTabla.setRowCount(row+1);
		modeloTabla.setValueAt(proceso.getIdentificador(), row, 0); // Sumamos 1 porque la fila 0 est� reservada para los titulos
		modeloTabla.setValueAt(proceso.getEstadoActual(), row, 1); // Sumamos 1 porque la fila 0 est� reservada para los titulos
		modeloTabla.setValueAt(proceso.getTiempoFaltante(), row, 2); // Sumamos 1 porque la fila 0 est� reservada para los titulos            
		modeloTabla.setValueAt(proceso.getTransicion(), row, 3); // Sumamos 1 porque la fila 0 est� reservada para los titulos
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

}
