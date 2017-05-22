package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import main.Condensacion;

import main.Estado;
import main.InformacionTransicion;
import main.Particion;
import main.Procesador;
import main.Proceso;

public class PanelListasParticiones extends JPanel {

    // Tabla para listos
    private DefaultTableModel modeloTablaParticiones;
    private JTable tablaParticiones;
    private JScrollPane scrollParticiones;

    //Tabla ejecutados
    private DefaultTableModel modeloTablaCondensaciones;
    private JTable tablaCondensaciones;
    private JScrollPane scrollCondensaciones;

    //Tabla terminados
    private DefaultTableModel modeloTablaTerminados;
    private JTable tablaTerminados;
    private JScrollPane scrollTerminados;

    //Procesador procesador;
    Particion particion;
    Procesador procesador;

    JPanel panelTablas;
    JLabel labelTitulo;

    public PanelListasParticiones(Procesador procesador) {

        int width = 350;
        int heigth = 350;
        //this.procesador = procesador;
        this.procesador = procesador;

        //La decoraci�n
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Listos
        String[] identificadoresParticiones = {"Lista Particiones"};
        modeloTablaParticiones = new DefaultTableModel(0, identificadoresParticiones.length);
        modeloTablaParticiones.setColumnIdentifiers(identificadoresParticiones);
        tablaParticiones = new JTable(modeloTablaParticiones) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaParticiones.getTableHeader().setReorderingAllowed(false);
        scrollParticiones = new JScrollPane(tablaParticiones);
        scrollParticiones.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollParticiones);

        String[] identificadoresEjecutados = {"Lista Condensaciones"};
        modeloTablaCondensaciones = new DefaultTableModel(0, identificadoresEjecutados.length);
        modeloTablaCondensaciones.setColumnIdentifiers(identificadoresEjecutados);
        tablaCondensaciones = new JTable(modeloTablaCondensaciones) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaCondensaciones.getTableHeader().setReorderingAllowed(false);
        scrollCondensaciones = new JScrollPane(tablaCondensaciones);
        scrollCondensaciones.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollCondensaciones);

        // Terminados
        String[] identificadoresTerminados = {"Lista Particiones Terminadas"};
        modeloTablaTerminados = new DefaultTableModel(0, identificadoresTerminados.length);
        modeloTablaTerminados.setColumnIdentifiers(identificadoresTerminados);
        tablaTerminados = new JTable(modeloTablaTerminados) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaTerminados.getTableHeader().setReorderingAllowed(false);
        scrollTerminados = new JScrollPane(tablaTerminados);
        scrollTerminados.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollTerminados);

    }

    public void listarProcesos() {
        ArrayList<Condensacion> condensacions = this.procesador.getCondensaciones();
        ArrayList<Particion> particiones = this.procesador.getHistorialParticiones();
        ArrayList<String> particionesTerminadas = this.procesador.getListaParticionesTerminadas();
        
        for (int i = 0; i < particiones.size(); i++) {
            agregarParticion(particiones.get(i));
        }
        
        for (int i = 0; i < condensacions.size(); i++) {
            agregarCondensacion(condensacions.get(i));
        }
        
        for (int i = 0; i < particionesTerminadas.size(); i++) {
            agregarTerminado(particionesTerminadas.get(i));
        }
    }

    public void agregarParticion(Particion particion) {
        int row = this.modeloTablaParticiones.getRowCount();
        modeloTablaParticiones.setRowCount(row + 1);
        modeloTablaParticiones.setValueAt("Particion " + (particion.getIndex()+1), row, 0);
    }

    public void agregarCondensacion(Condensacion condensacion) {
        int row = this.modeloTablaCondensaciones.getRowCount();
        modeloTablaCondensaciones.setRowCount(row + 1);
        modeloTablaCondensaciones.setValueAt("Particion" + (condensacion.getParticion1().getIndex()+1) +  " (" 
                + condensacion.getParticion1().getTamanio() +  ") + Particion " + (condensacion.getParticion2().getIndex()+1)
                +  " (" + condensacion.getParticion2().getTamanio() + ") = Particion "+ (condensacion.getParticionfinal().getIndex()+1)
                +  " (" + condensacion.getParticionfinal().getTamanio() + ")", row, 0);
    }

    public void agregarTerminado(String proceso) {
        int row = this.modeloTablaTerminados.getRowCount();
        modeloTablaTerminados.setRowCount(row + 1);
        modeloTablaTerminados.setValueAt(proceso, row, 0);
    }
}
