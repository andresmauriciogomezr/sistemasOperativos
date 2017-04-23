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

public class PanelTabla extends JPanel {

    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JScrollPane scroll;
    private JButton botonProcesar;

    private JPopupMenu menuEmergente;
    private JMenuItem itemCrearProceso;

    private Procesador procesador;

    public PanelTabla(Procesador procesador, ActionListener listener) {
        int width = 900;
        int heigth = 300;

        this.procesador = procesador;
        String[] identificadores = {"Identificador", "Tiempo", "Tamanio", "# Particion"};
        modeloTabla = new DefaultTableModel(0, identificadores.length);
        modeloTabla.setColumnIdentifiers(identificadores);

        tabla = new JTable(modeloTabla) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tabla.getTableHeader().setReorderingAllowed(false);
        //tabla.addMouseListener(this);

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

    public void listarComunes() {
        ArrayList<Proceso> procesos = this.procesador.getProcesosCargados();
        
        String[] identificadores = {"Identificador", "Tiempo", "Tamanio", "# Particion"};
        modeloTabla = new DefaultTableModel(0, identificadores.length);
        modeloTabla.setColumnIdentifiers(identificadores);

        for (int i = 0; i < procesos.size(); i++) {
            Proceso proceso = procesos.get(i);
            agregarFila(proceso);
            System.out.println(proceso.getPrioridad());
        }
        //tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
        tabla.setModel(modeloTabla);
    }

    public void listarProcesos() {
        ArrayList<Proceso> procesosCargados = this.procesador.getProcesosCargados();
        

        for (int i = 0; i < procesosCargados.size(); i++) {
            Proceso proceso = procesosCargados.get(i);
            agregarFila(proceso);
        }
        //tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
        tabla.setModel(modeloTabla);
    }

    public void agregarFila(Proceso proceso) {
        int row = this.modeloTabla.getRowCount();
        modeloTabla.setRowCount(row + 1);
        modeloTabla.setValueAt(proceso.getIdentificador(), row, 0); // Identificador 
        modeloTabla.setValueAt(proceso.getTiempoEjecucion(), row, 1); // Tiempo
        modeloTabla.setValueAt(proceso.getTamanio(), row, 2); // Tamanio
        modeloTabla.setValueAt(proceso.getIndexParticion()+1, row, 3); // Particion
    }

    public String cambiarAPalabra(Boolean bool) {
        if (bool == true) {
            return "Si";
        }
        return "No";
    }

    public void actualizarTiempo(Proceso proceso) {
        int indexProceso = 0;
        for (int i = 0; i < this.modeloTabla.getRowCount(); i++) {
            String procesoLista = (String) (this.modeloTabla.getValueAt(i, 0));
            if (procesoLista.equals(proceso.getIdentificador())) {
                indexProceso = i;
                break;
            }
        }
    }

}
