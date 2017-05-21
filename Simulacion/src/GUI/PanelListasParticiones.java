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

import main.Estado;
import main.InformacionTransicion;
import main.Particion;
import main.Procesador;
import main.Proceso;

public class PanelListasParticiones extends JPanel {

    // Tabla para listos
    private DefaultTableModel modeloTablaListos;
    private JTable tablaListos;
    private JScrollPane scrollListos;

    //Tabla ejecutados
    private DefaultTableModel modeloTablaEjecutados;
    private JTable tablaEjecutados;
    private JScrollPane scrollEjecutados;

    //Tabla expirados
    private DefaultTableModel modeloTablaExpirados;
    private JTable tablaExpirados;
    private JScrollPane scrollExpirados;

    //Tabla despachados
    private DefaultTableModel modeloTablaDespachados;
    private JTable tablaDespachados;
    private JScrollPane scrollDespachados;

    //Tabla Procesados
    private DefaultTableModel modeloTablaProcesados;
    private JTable tablaProcesados;
    private JScrollPane scrollProcesados;

    //Tabla NoProcesados
    private DefaultTableModel modeloTablaNoProcesados;
    private JTable tablaNoProcesados;
    private JScrollPane scrollNoProcesados;

    //Tabla terminados
    private DefaultTableModel modeloTablaTerminados;
    private JTable tablaTerminados;
    private JScrollPane scrollTerminados;

    //Procesador procesador;
    Particion particion;
    Procesador procesador;

    JPanel panelTablas;
    JLabel labelTitulo;

    public PanelListasParticiones(Particion particion, String titulo, Procesador procesador) {

        setBorder(BorderFactory.createEtchedBorder());
        int width = 150;
        int heigth = 150;
        this.particion = particion;
        //this.procesador = procesador;
        this.procesador = procesador;
        panelTablas = new JPanel();

        this.setLayout(new BorderLayout());

        //La decoraciï¿½n
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
     //   if (this.particion != null) {// listas para las particiones
            // Listos
            String[] identificadoresListos = {"Lista Procesos listos"};
            modeloTablaListos = new DefaultTableModel(0, identificadoresListos.length);
            modeloTablaListos.setColumnIdentifiers(identificadoresListos);
            tablaListos = new JTable(modeloTablaListos) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaListos.getTableHeader().setReorderingAllowed(false);
            scrollListos = new JScrollPane(tablaListos);
            scrollListos.setPreferredSize(new Dimension(width, heigth));
            //this.panelTablas.add(scrollListos);

            // Despachados
            String[] identificadoresDespachados = {"Lista Despachados (Listo a Ejecutado)"};
            modeloTablaDespachados = new DefaultTableModel(0, identificadoresDespachados.length);
            modeloTablaDespachados.setColumnIdentifiers(identificadoresDespachados);
            tablaDespachados = new JTable(modeloTablaDespachados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaDespachados.getTableHeader().setReorderingAllowed(false);
            scrollDespachados = new JScrollPane(tablaDespachados);
            scrollDespachados.setPreferredSize(new Dimension(width, heigth));
           // this.panelTablas.add(scrollDespachados);

            // Ejecutados
            String[] identificadoresEjecutados = {"Lista Ejecutados"};
            modeloTablaEjecutados = new DefaultTableModel(0, identificadoresEjecutados.length);
            modeloTablaEjecutados.setColumnIdentifiers(identificadoresEjecutados);
            tablaEjecutados = new JTable(modeloTablaEjecutados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaEjecutados.getTableHeader().setReorderingAllowed(false);
            scrollEjecutados = new JScrollPane(tablaEjecutados);
            scrollEjecutados.setPreferredSize(new Dimension(width, heigth));
            this.panelTablas.add(scrollEjecutados);

            // expirados
            String[] identificadoresExpirados = {"Lista Expirados(Ejecucion - Listo)"};
            modeloTablaExpirados = new DefaultTableModel(0, identificadoresExpirados.length);
            modeloTablaExpirados.setColumnIdentifiers(identificadoresExpirados);
            tablaExpirados = new JTable(modeloTablaExpirados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaExpirados.getTableHeader().setReorderingAllowed(false);
            scrollExpirados = new JScrollPane(tablaExpirados);
            scrollExpirados.setPreferredSize(new Dimension(width, heigth));
           // this.panelTablas.add(scrollExpirados);

            // Procesados
            String[] identificadoresProcesados = {"Lista Procesados"};
            modeloTablaProcesados = new DefaultTableModel(0, identificadoresProcesados.length);
            modeloTablaProcesados.setColumnIdentifiers(identificadoresProcesados);
            tablaProcesados = new JTable(modeloTablaProcesados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaProcesados.getTableHeader().setReorderingAllowed(false);
            scrollProcesados = new JScrollPane(tablaProcesados);
            scrollProcesados.setPreferredSize(new Dimension(width, heigth));
            this.panelTablas.add(scrollProcesados);

            // NoProcesados
            String[] identificadoresNoProcesados = {"Lista NoProcesados por tamaño"};
            modeloTablaNoProcesados = new DefaultTableModel(0, identificadoresNoProcesados.length);
            modeloTablaNoProcesados.setColumnIdentifiers(identificadoresNoProcesados);
            tablaNoProcesados = new JTable(modeloTablaNoProcesados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaNoProcesados.getTableHeader().setReorderingAllowed(false);
            scrollNoProcesados = new JScrollPane(tablaNoProcesados);
            scrollNoProcesados.setPreferredSize(new Dimension(width, heigth));
            this.panelTablas.add(scrollNoProcesados);

            // Terminados
            String[] identificadoresTerminados = {"Lista Procesos Terminados"};
            modeloTablaTerminados = new DefaultTableModel(0, identificadoresExpirados.length);
            modeloTablaTerminados.setColumnIdentifiers(identificadoresTerminados);
            tablaTerminados = new JTable(modeloTablaTerminados) {
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tablaTerminados.getTableHeader().setReorderingAllowed(false);
            scrollTerminados = new JScrollPane(tablaTerminados);
            scrollTerminados.setPreferredSize(new Dimension(width, heigth));

        this.add(panelTablas, BorderLayout.CENTER);

        labelTitulo = new JLabel(titulo);
        this.add(labelTitulo, BorderLayout.NORTH);
    }

    public void listarGenerales() {
    	
        ArrayList<String> procesosProcesados = this.procesador.getListaProcesados();
        ArrayList<String> procesosNoProcesados = this.procesador.getListaNoProcesados();
        //ArrayList<InformacionTransicion> procesosNoProcesados = this.procesador.getProcesosExpirados();
        
        ArrayList<InformacionTransicion> procesosDespachados = this.procesador.getProcesosDespachados();
        ArrayList<InformacionTransicion> procesosExpirados = this.procesador.getProcesosExpirados();

        for (int i = 0; i < procesosProcesados.size(); i++) {
            //Proceso proceso = ;
            agregarProcesado(procesosProcesados.get(i));
        }


        for (int i = 0; i < procesosDespachados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosDespachados.get(i);
            agregarDespachado(informacionTransicion);
        }


        for (int i = 0; i < procesosExpirados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosExpirados.get(i);
            agregarExpirado(informacionTransicion);
        }


        tablaListos.setModel(modeloTablaListos);
        tablaDespachados.setModel(modeloTablaDespachados);
        tablaExpirados.setModel(modeloTablaExpirados);
        tablaEjecutados.setModel(modeloTablaEjecutados);

        for (int i = 0; i < procesosProcesados.size(); i++) {
            //Proceso proceso = ;
            agregarProcesado(procesosProcesados.get(i));
        }

        for (int i = 0; i < procesosNoProcesados.size(); i++) {
            //Proceso proceso = ;
            agregarNoProcesado(procesosNoProcesados.get(i));
        }
    }

    public void listarProcesos() {
//        if (particion.getProcesosProcesados().isEmpty() == false) {
//            agregarProcesados();
//        }
       // if (particion.getProcesosNoProcesados().isEmpty() == false) {
                ArrayList<String> procesosNoProcesados = this.particion.getProcesosNoProcesados();
                ArrayList<String> procesosProcesados = this.particion.getProcesosProcesados();
                ArrayList<InformacionTransicion> procesosListos = this.particion.getProcesosListos();
                
                
                for (int i = 0; i < procesosProcesados.size(); i++) {
                    //Proceso proceso = ;
                    agregarProcesado(procesosProcesados.get(i));
                }
                for (int i = 0; i < procesosNoProcesados.size(); i++) {
                    //Proceso proceso = ;
                    agregarNoProcesado(procesosNoProcesados.get(i));
                }
                for (int i = 0; i < procesosListos.size(); i++) {
                    //Proceso proceso = ;
                    agregarListo(procesosListos.get(i).getIdentificadorProceso() + " - Tiempo: " + procesosListos.get(i).getTiempoLleva());
                }
            //tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
       // }
    }
    

    public void agregarProcesados() {
        ArrayList<InformacionTransicion> procesosListos = this.particion.getProcesosListos();
        ArrayList<InformacionTransicion> procesosDespachados = this.particion.getProcesosDespachados();
        ArrayList<InformacionTransicion> procesosExpirados = this.particion.getProcesosExpirados();
        ArrayList<String> procesosTerminados = this.particion.getProcesosTerminados();
        ArrayList<InformacionTransicion> ejecutados = this.particion.getProcesosEjecutados();
        ArrayList<String> procesosProcesados = this.particion.getProcesosProcesados();

        for (int i = 0; i < procesosProcesados.size(); i++) {
            //Proceso proceso = ;
            agregarProcesado(procesosProcesados.get(i));
        }

        for (int i = 0; i < procesosListos.size(); i++) {
            //Proceso proceso = ;
            agregarListo(procesosListos.get(i).getIdentificadorProceso());
        }

        for (int i = 0; i < procesosDespachados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosDespachados.get(i);
            agregarDespachado(informacionTransicion);
        }

        for (int i = 0; i < ejecutados.size(); i++) {
            agregarEjecutado(ejecutados.get(i).getIdentificadorProceso() + " Tiempo : " +ejecutados.get(i).getTiempoLleva() );
        }

        for (int i = 0; i < procesosExpirados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosExpirados.get(i);
            agregarExpirado(informacionTransicion);
        }

        for (int i = 0; i < procesosTerminados.size(); i++) {
            agregarTerminado(procesosTerminados.get(i));
        }

        tablaListos.setModel(modeloTablaListos);
        tablaDespachados.setModel(modeloTablaDespachados);
        tablaExpirados.setModel(modeloTablaExpirados);
        tablaEjecutados.setModel(modeloTablaEjecutados);
    }

    public void agregarListo(String proceso) {
        int row = this.modeloTablaListos.getRowCount();
        modeloTablaListos.setRowCount(row + 1);
        modeloTablaListos.setValueAt(proceso, row, 0);
    }

    public void agregarProcesado(String proceso) {
        int row = this.modeloTablaProcesados.getRowCount();
        modeloTablaProcesados.setRowCount(row + 1);
        modeloTablaProcesados.setValueAt(proceso, row, 0);
    }

    public void agregarNoProcesado(String proceso) {
        int row = this.modeloTablaNoProcesados.getRowCount();
        modeloTablaNoProcesados.setRowCount(row + 1);
        modeloTablaNoProcesados.setValueAt(proceso, row, 0);
    }

    public void agregarDespachado(InformacionTransicion it) {
        int row = this.modeloTablaDespachados.getRowCount();
        modeloTablaDespachados.setRowCount(row + 1);
        modeloTablaDespachados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }

    public void agregarExpirado(InformacionTransicion it) {
        int row = this.modeloTablaExpirados.getRowCount();
        modeloTablaExpirados.setRowCount(row + 1);
        modeloTablaExpirados.setValueAt(it.getIdentificadorProceso() + "-Tiempo:" + it.getTiempoLleva(), row, 0);
    }

    public void agregarEjecutado(String it) {
        int row = this.modeloTablaEjecutados.getRowCount();
        modeloTablaEjecutados.setRowCount(row + 1);
        modeloTablaEjecutados.setValueAt(it, row, 0);
    }

    public void agregarTerminado(String proceso) {
        int row = this.modeloTablaTerminados.getRowCount();
        modeloTablaTerminados.setRowCount(row + 1);
        modeloTablaTerminados.setValueAt(proceso, row, 0);
    }
}
