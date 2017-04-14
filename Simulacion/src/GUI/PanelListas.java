package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import main.Estado;
import main.InformacionTransicion;
import main.Procesador;
import main.Proceso;

public class PanelListas extends JPanel {

    // Tabla para listos
    private DefaultTableModel modeloTablaListos;
    private JTable tablaListos;
    private JScrollPane scrollListos;

    // Tabla para bloqueados
    private DefaultTableModel modeloTablaBloqueados;
    private JTable tablaBloqueados;
    private JScrollPane scrollBloqueados;

    // Tabla para suspendidosListos
    private DefaultTableModel modeloTablaSuspendidosListos;
    private JTable tablaSuspedidosListos;
    private JScrollPane scrollSuspendidosListos;
    
 // Tabla para suspendidosBloqueados
    private DefaultTableModel modeloTablaSuspendidosBloqueados;
    private JTable tablaSuspedidosBloqueados;
    private JScrollPane scrollSuspendidosBloqueados;

    // Tabla para destruidos
    private DefaultTableModel modeloTablaDestruidos;
    private JTable tablaDestruidos;
    private JScrollPane scrollDestruidos;

    // Tabla para destruidos
    private DefaultTableModel modeloTablaComunicados;
    private JTable tablaComunicados;
    private JScrollPane scrollComunicados;
    
    private DefaultTableModel modeloTablaEjecutados;
    private JTable tablaEjecutados;
    private JScrollPane scrollEjecutados;
    
    private DefaultTableModel modeloTablaExpirados;
    private JTable tablaExpirados;
    private JScrollPane scrollExpirados;

    private DefaultTableModel modeloTablaDespachados;
    private JTable tablaDespachados;
    private JScrollPane scrollDespachados;
    
    private DefaultTableModel modeloTablaTerminados;
    private JTable tablaTerminados;
    private JScrollPane scrollTerminados;
    
    Procesador procesador;
    
    public PanelListas(Procesador procesador){
    	
    	
        int width = 350;
        int heigth = 230;
        
        this.procesador = procesador;
        
        //La decoración
		try{
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}      
        
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
        this.add(scrollListos);
        
        // Despachados
         String[] identificadoresDespachados = {"Lista Procesos Despachados (Listo a Ejecutado)"};
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
        this.add(scrollDespachados);
        
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
        this.add(scrollEjecutados);
        
        // expirados
        String[] identificadoresExpirados = {"Lista Procesos expirados"};
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
        this.add(scrollExpirados);
        
        // Bloqueados
        String[] identificadoresBloqueado = {"Lista Procesos Bloqueados"};
        modeloTablaBloqueados = new DefaultTableModel(0, identificadoresBloqueado.length);
        modeloTablaBloqueados.setColumnIdentifiers(identificadoresBloqueado);
        tablaBloqueados = new JTable(modeloTablaBloqueados) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaBloqueados.getTableHeader().setReorderingAllowed(false);
        scrollBloqueados = new JScrollPane(tablaBloqueados);
        scrollBloqueados.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollBloqueados);

        // SuspendidosListos
        String[] identificadoresSuspendidos = {"Lista Procesos Suspendidos/Listos"};
        modeloTablaSuspendidosListos = new DefaultTableModel(0, identificadoresSuspendidos.length);
        modeloTablaSuspendidosListos.setColumnIdentifiers(identificadoresSuspendidos);
        tablaSuspedidosListos = new JTable(modeloTablaSuspendidosListos) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaSuspedidosListos.getTableHeader().setReorderingAllowed(false);
        scrollSuspendidosListos = new JScrollPane(tablaSuspedidosListos);
        scrollSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollSuspendidosListos);
        
     // SuspendidosBLoqueados
        String[] identificadoresSuspendidosBloqueados = {"Lista Suspendidos/bloqueados"};
        modeloTablaSuspendidosBloqueados = new DefaultTableModel(0, identificadoresSuspendidosBloqueados.length);
        modeloTablaSuspendidosBloqueados.setColumnIdentifiers(identificadoresSuspendidosBloqueados);
        tablaSuspedidosBloqueados= new JTable(modeloTablaSuspendidosBloqueados) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaSuspedidosBloqueados.getTableHeader().setReorderingAllowed(false);
        scrollSuspendidosBloqueados= new JScrollPane(tablaSuspedidosBloqueados);
        scrollSuspendidosBloqueados.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollSuspendidosBloqueados);

        // Destruidos
        String[] identificadoresDestruidos = {"Lista Procesos Destruidos"};
        modeloTablaDestruidos = new DefaultTableModel(0, identificadoresDestruidos.length);
        modeloTablaDestruidos.setColumnIdentifiers(identificadoresDestruidos);
        tablaDestruidos = new JTable(modeloTablaDestruidos) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaDestruidos.getTableHeader().setReorderingAllowed(false);
        scrollDestruidos = new JScrollPane(tablaDestruidos);
        scrollDestruidos.setPreferredSize(new Dimension(width, heigth));
        //this.add(scrollDestruidos);

        // Comunicados
        String[] identificadoresComunicados = {"Lista Procesos Comunicados"};
        modeloTablaComunicados = new DefaultTableModel(0, identificadoresComunicados.length);
        modeloTablaComunicados.setColumnIdentifiers(identificadoresComunicados);
        tablaComunicados = new JTable(modeloTablaComunicados) {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        tablaComunicados.getTableHeader().setReorderingAllowed(false);
        scrollComunicados = new JScrollPane(tablaComunicados);
        scrollComunicados.setPreferredSize(new Dimension(width, heigth));
        this.add(scrollComunicados);

             // Transiciones
        
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
        this.add(scrollTerminados);

    }

    public void listarProcesos() {
        ArrayList<String> procesosListos = this.procesador.getProcesosListos();
        ArrayList<InformacionTransicion> procesosDespachados = this.procesador.getProcesosDespachados();
        ArrayList<InformacionTransicion> procesosExpirados = this.procesador.getProcesosExpirados();
        ArrayList<InformacionTransicion> procesosBloqueados = this.procesador.getProcesosBloqueados();
        ArrayList<InformacionTransicion> procesosSuspendidosListos = this.procesador.getProcesosSuspendidos();
        ArrayList<String> procesosSuspendidosBloqueados= this.procesador.getListaSuspendidosBloqueados();
        ArrayList<String> procesosDestruidos = this.procesador.getProcesosDestruidos();
        ArrayList<InformacionTransicion> procesosComunicados = this.procesador.getProcesosComunicados();
        ArrayList<String> procesosTerminados = this.procesador.getProcesosTerminados();
        ArrayList<Proceso> procesos = this.procesador.getProcesosCargados();
        ArrayList<String> ejecutados = this.procesador.getListaEjecutados();
        ArrayList<Proceso> listaComunicaciones = this.procesador.getListaComunicaciones();

        for (int i = 0; i < procesosListos.size(); i++) {
            //Proceso proceso = ;
            agregarListo(procesosListos.get(i));
        }
        for (int i = 0; i < procesosDespachados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosDespachados.get(i);
            agregarDespachado(informacionTransicion);
        }
        for (int i = 0; i < procesosBloqueados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosBloqueados.get(i);
            agregarBloqueado(informacionTransicion);
        }
        for (int i = 0; i < procesosSuspendidosListos.size(); i++) {
            InformacionTransicion informacionTransicion = procesosSuspendidosListos.get(i);
            agregarSuspendido(informacionTransicion);
        }
        for (int i = 0; i < procesosSuspendidosBloqueados.size(); i++) {
            agregarSuspendidoBloqueado(procesosSuspendidosBloqueados.get(i));
        }
        for (int i = 0; i < procesosDestruidos.size(); i++) {
            String proceso = procesosDestruidos.get(i);
            agregarDestruido(proceso);
        }
        for (int i = 0; i < listaComunicaciones.size(); i++) {
            agregarComunicado(listaComunicaciones.get(i));
        }

        for (int i = 0; i < ejecutados.size(); i++) {
            agregarEjecutado(ejecutados.get(i));
        }

        for (int i = 0; i < procesosExpirados.size(); i++) {
            InformacionTransicion informacionTransicion = procesosExpirados.get(i);
            agregarExpirado(informacionTransicion);
        }

        for (int i = 0; i < procesosTerminados.size(); i++) {
            agregarTerminado(procesosTerminados.get(i));
        }
        //tabla.setFont(new FontUIResource("Verdana", Font.PLAIN, 20));
        tablaListos.setModel(modeloTablaListos);
        tablaDespachados.setModel(modeloTablaDespachados);
        tablaBloqueados.setModel(modeloTablaBloqueados);
        tablaSuspedidosListos.setModel(modeloTablaSuspendidosListos);
        tablaDestruidos.setModel(modeloTablaDestruidos);
        tablaComunicados.setModel(modeloTablaComunicados);
        tablaEjecutados.setModel(modeloTablaEjecutados);
        tablaExpirados.setModel(modeloTablaExpirados);
    }

    public void agregarListo(String proceso) {
        int row = this.modeloTablaListos.getRowCount();
        modeloTablaListos.setRowCount(row + 1);
        modeloTablaListos.setValueAt(proceso, row, 0);

    }

    public void agregarDespachado(InformacionTransicion it) {
        int row = this.modeloTablaDespachados.getRowCount();
        modeloTablaDespachados.setRowCount(row + 1);
        modeloTablaDespachados.setValueAt(it.getIdentificadorProceso() + " con un tiempo de " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarBloqueado(InformacionTransicion it) {
        int row = this.modeloTablaBloqueados.getRowCount();
        modeloTablaBloqueados.setRowCount(row + 1);
        modeloTablaBloqueados.setValueAt(it.getIdentificadorProceso() + " con un tiempo de " + it.getTiempoLleva(), row, 0);
    }

    public void agregarSuspendido(InformacionTransicion it) {
        int row = this.modeloTablaSuspendidosListos.getRowCount();
        modeloTablaSuspendidosListos.setRowCount(row + 1);
        modeloTablaSuspendidosListos.setValueAt(it.getIdentificadorProceso() + " con un tiempo de " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarSuspendidoBloqueado(String proceso) {
        int row = this.modeloTablaSuspendidosBloqueados.getRowCount();
        modeloTablaSuspendidosBloqueados.setRowCount(row + 1);
        modeloTablaSuspendidosBloqueados.setValueAt(proceso, row, 0);
    }

    public void agregarDestruido(String proceso) {
        int row = this.modeloTablaDestruidos.getRowCount();
        modeloTablaDestruidos.setRowCount(row + 1);
        modeloTablaDestruidos.setValueAt(proceso, row, 0);
    }

    public void agregarComunicado(Proceso proceso) {
        int row = this.modeloTablaComunicados.getRowCount();
        modeloTablaComunicados.setRowCount(row + 1);
        modeloTablaComunicados.setValueAt(proceso.getIdentificador() + " con el proceso " + proceso.getSeComunica(), row, 0);
    }

    public void agregarExpirado(InformacionTransicion it) {
        int row = this.modeloTablaExpirados.getRowCount();
        modeloTablaExpirados.setRowCount(row + 1);
        modeloTablaExpirados.setValueAt(it.getIdentificadorProceso() + " con un tiempo de " + it.getTiempoLleva(), row, 0);
    }

    public void agregarEjecutado(String proceso) {
        int row = this.modeloTablaEjecutados.getRowCount();
        modeloTablaEjecutados.setRowCount(row + 1);
        modeloTablaEjecutados.setValueAt(proceso, row, 0);
    }
    
    public void agregarTerminado(String proceso) {
        int row = this.modeloTablaTerminados.getRowCount();
        modeloTablaTerminados.setRowCount(row + 1);
        modeloTablaTerminados.setValueAt(proceso, row, 0);
    }
}
