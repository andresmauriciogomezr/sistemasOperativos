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

public class PanelListasProcesos extends JPanel {

    // Tabla para listos
    private DefaultTableModel modeloTablaListos;
    private JTable tablaListos;
    private JScrollPane scrollListos;

    private DefaultTableModel modeloTablaEspESBloqueados;
    private JTable tablaEspESBloqueados;
    private JScrollPane scrollEspESBloqueados;

// Tabla para bloqueados
    private DefaultTableModel modeloTablaBloqueados;
    private JTable tablaBloqueados;
    private JScrollPane scrollBloqueados;

    private DefaultTableModel modeloTablaTermESBloqueados;
    private JTable tablaTermESBloqueados;
    private JScrollPane scrollTermESBloqueados;

    // Tabla para suspendidosListos
    private DefaultTableModel modeloTablaSuspendidosListos;
    private JTable tablaSuspedidosListos;
    private JScrollPane scrollSuspendidosListos;

    private DefaultTableModel modeloTablaReaSuspendidosListos;
    private JTable tablaReaSuspedidosListos;
    private JScrollPane scrollReaSuspendidosListos;

    private DefaultTableModel modeloTablaSuspESuspendidosListos;
    private JTable tablaSuspESuspedidosListos;
    private JScrollPane scrollSuspSuspendidosListos;

    private DefaultTableModel modeloTablaSuspLSuspendidosListos;
    private JTable tablaSuspLSuspedidosListos;
    private JScrollPane scrollSuspLSuspendidosListos;

    private DefaultTableModel modeloTablaTermESSuspendidosListos;
    private JTable tablaTermESSuspedidosListos;
    private JScrollPane scrollTermESSuspendidosListos;

    // Tabla para suspendidosBloqueados
    private DefaultTableModel modeloTablaSuspendidosBloqueados;
    private JTable tablaSuspedidosBloqueados;
    private JScrollPane scrollSuspendidosBloqueados;

    private DefaultTableModel modeloTablaReaSuspendidosBloqueados;
    private JTable tablaReaSuspedidosBloqueados;
    private JScrollPane scrollReaSuspendidosBloqueados;

    private DefaultTableModel modeloTablaSusSuspendidosBloqueados;
    private JTable tablaSusSuspedidosBloqueados;
    private JScrollPane scrollSusSuspendidosBloqueados;

    // Tabla para destruidos
    private DefaultTableModel modeloTablaDestruidos;
    private JTable tablaDestruidos;
    private JScrollPane scrollDestruidos;

    // Tabla para comunicados
    private DefaultTableModel modeloTablaComunicados;
    private JTable tablaComunicados;
    private JScrollPane scrollComunicados;

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

    //Tabla terminados
    private DefaultTableModel modeloTablaTerminados;
    private JTable tablaTerminados;
    private JScrollPane scrollTerminados;

    Procesador procesador;

    public PanelListasProcesos(Procesador procesador) {

        int width = 250;
        int heigth = 250;

        this.procesador = procesador;

        //La decoraci�n
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // Listos
//        String[] identificadoresListos = {"Lista Procesos listos"};
//        modeloTablaListos = new DefaultTableModel(0, identificadoresListos.length);
//        modeloTablaListos.setColumnIdentifiers(identificadoresListos);
//        tablaListos = new JTable(modeloTablaListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaListos.getTableHeader().setReorderingAllowed(false);
//        scrollListos = new JScrollPane(tablaListos);
//        scrollListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollListos);
//
//        // Despachados
//        String[] identificadoresDespachados = {"Lista Despachados (Listo a Ejecutado)"};
//        modeloTablaDespachados = new DefaultTableModel(0, identificadoresDespachados.length);
//        modeloTablaDespachados.setColumnIdentifiers(identificadoresDespachados);
//        tablaDespachados = new JTable(modeloTablaDespachados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaDespachados.getTableHeader().setReorderingAllowed(false);
//        scrollDespachados = new JScrollPane(tablaDespachados);
//        scrollDespachados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollDespachados);

        // Ejecutados
        String[] identificadoresEjecutados = {"Lista Procesos Procesados"};
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
        String[] identificadoresExpirados = {"Lista Procesos No Procesados"};
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

//        String[] identificadoresEspESBloq = {"Lista Espera E/S(Ejecucion - bloqueo)"};
//        modeloTablaEspESBloqueados = new DefaultTableModel(0, identificadoresEspESBloq.length);
//        modeloTablaEspESBloqueados.setColumnIdentifiers(identificadoresEspESBloq);
//        tablaEspESBloqueados = new JTable(modeloTablaEspESBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaEspESBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollEspESBloqueados = new JScrollPane(tablaEspESBloqueados);
//        scrollEspESBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollEspESBloqueados);
//
//        // Bloqueados
//        String[] identificadoresBloqueado = {"Lista Procesos Bloqueados"};
//        modeloTablaBloqueados = new DefaultTableModel(0, identificadoresBloqueado.length);
//        modeloTablaBloqueados.setColumnIdentifiers(identificadoresBloqueado);
//        tablaBloqueados = new JTable(modeloTablaBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollBloqueados = new JScrollPane(tablaBloqueados);
//        scrollBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollBloqueados);
//
//        String[] identificadoresTermESBloqueado = {"Termina E/S o Evento(Bloqueados - Listo)"};
//        modeloTablaTermESBloqueados = new DefaultTableModel(0, identificadoresTermESBloqueado.length);
//        modeloTablaTermESBloqueados.setColumnIdentifiers(identificadoresTermESBloqueado);
//        tablaTermESBloqueados = new JTable(modeloTablaTermESBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaTermESBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollTermESBloqueados = new JScrollPane(tablaTermESBloqueados);
//        scrollTermESBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollTermESBloqueados);
//        
//        String[] identificadoresSusSuspendidosBloqueados = {"Lista Suspender a Suspendidos B."};
//        modeloTablaSusSuspendidosBloqueados= new DefaultTableModel(0, identificadoresSusSuspendidosBloqueados.length);
//        modeloTablaSusSuspendidosBloqueados.setColumnIdentifiers(identificadoresSusSuspendidosBloqueados);
//        tablaSusSuspedidosBloqueados = new JTable(modeloTablaSusSuspendidosBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaSusSuspedidosBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollSusSuspendidosBloqueados = new JScrollPane(tablaSusSuspedidosBloqueados);
//        scrollSusSuspendidosBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollSusSuspendidosBloqueados);
//        
//        String[] identificadoresReaSuspendidosBloqueados = {"Lista Reanudar Suspendidos B."};
//        modeloTablaReaSuspendidosBloqueados= new DefaultTableModel(0, identificadoresReaSuspendidosBloqueados.length);
//        modeloTablaReaSuspendidosBloqueados.setColumnIdentifiers(identificadoresReaSuspendidosBloqueados);
//        tablaReaSuspedidosBloqueados = new JTable(modeloTablaReaSuspendidosBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaReaSuspedidosBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollReaSuspendidosBloqueados = new JScrollPane(tablaReaSuspedidosBloqueados);
//        scrollReaSuspendidosBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollReaSuspendidosBloqueados);
//        
//        String[] identificadoresSuspendidosBloqueados = {"Lista Procesos Suspendidos/bloqueados"};
//        modeloTablaSuspendidosBloqueados = new DefaultTableModel(0, identificadoresSuspendidosBloqueados.length);
//        modeloTablaSuspendidosBloqueados.setColumnIdentifiers(identificadoresSuspendidosBloqueados);
//        tablaSuspedidosBloqueados = new JTable(modeloTablaSuspendidosBloqueados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaSuspedidosBloqueados.getTableHeader().setReorderingAllowed(false);
//        scrollSuspendidosBloqueados = new JScrollPane(tablaSuspedidosBloqueados);
//        scrollSuspendidosBloqueados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollSuspendidosBloqueados);
//        
//        // SuspendidosBLoqueados
//        String[] identificadoresTermESSuspendidosListos = {"Termina E/S o Evento(Susp.B - Susp.L)"};
//        modeloTablaTermESSuspendidosListos = new DefaultTableModel(0, identificadoresTermESSuspendidosListos.length);
//        modeloTablaTermESSuspendidosListos.setColumnIdentifiers(identificadoresTermESSuspendidosListos);
//        tablaTermESSuspedidosListos = new JTable(modeloTablaTermESSuspendidosListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaTermESSuspedidosListos.getTableHeader().setReorderingAllowed(false);
//        scrollTermESSuspendidosListos = new JScrollPane(tablaTermESSuspedidosListos);
//        scrollTermESSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollTermESSuspendidosListos);
//
//        String[] identificadoresSusESuspendidosListos = {"Lista Suspender(Ejec.-Susp.L.)"};
//        modeloTablaSuspESuspendidosListos = new DefaultTableModel(0, identificadoresSusESuspendidosListos.length);
//        modeloTablaSuspESuspendidosListos.setColumnIdentifiers(identificadoresSusESuspendidosListos);
//        tablaSuspESuspedidosListos = new JTable(modeloTablaSuspESuspendidosListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaSuspESuspedidosListos.getTableHeader().setReorderingAllowed(false);
//        scrollSuspSuspendidosListos = new JScrollPane(tablaSuspESuspedidosListos);
//        scrollSuspSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollSuspSuspendidosListos);
//        
//        String[] identificadoresSusLSuspendidosListos = {"Lista Suspender(Listo-Susp.L)"};
//        modeloTablaSuspLSuspendidosListos = new DefaultTableModel(0, identificadoresSusLSuspendidosListos.length);
//        modeloTablaSuspLSuspendidosListos.setColumnIdentifiers(identificadoresSusLSuspendidosListos);
//        tablaSuspLSuspedidosListos = new JTable(modeloTablaSuspLSuspendidosListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaSuspLSuspedidosListos.getTableHeader().setReorderingAllowed(false);
//        scrollSuspLSuspendidosListos = new JScrollPane(tablaSuspLSuspedidosListos);
//        scrollSuspLSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollSuspLSuspendidosListos);
//
//        // SuspendidosListos
//        String[] identificadoresSuspendidos = {"Lista Procesos Suspendidos/Listos"};
//        modeloTablaSuspendidosListos = new DefaultTableModel(0, identificadoresSuspendidos.length);
//        modeloTablaSuspendidosListos.setColumnIdentifiers(identificadoresSuspendidos);
//        tablaSuspedidosListos = new JTable(modeloTablaSuspendidosListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaSuspedidosListos.getTableHeader().setReorderingAllowed(false);
//        scrollSuspendidosListos = new JScrollPane(tablaSuspedidosListos);
//        scrollSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollSuspendidosListos);
//        
//        //Reanudar Suspendidos Listos
//        String[] identificadoresReaSuspendidosListos = {"Lista Reanudar Suspendidos/Listos"};
//        modeloTablaReaSuspendidosListos = new DefaultTableModel(0, identificadoresReaSuspendidosListos.length);
//        modeloTablaReaSuspendidosListos.setColumnIdentifiers(identificadoresReaSuspendidosListos);
//        tablaReaSuspedidosListos = new JTable(modeloTablaReaSuspendidosListos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaReaSuspedidosListos.getTableHeader().setReorderingAllowed(false);
//        scrollReaSuspendidosListos = new JScrollPane(tablaReaSuspedidosListos);
//        scrollReaSuspendidosListos.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollReaSuspendidosListos);
//        
//        // Destruidos
//        String[] identificadoresDestruidos = {"Lista Procesos Destruidos"};
//        modeloTablaDestruidos = new DefaultTableModel(0, identificadoresDestruidos.length);
//        modeloTablaDestruidos.setColumnIdentifiers(identificadoresDestruidos);
//        tablaDestruidos = new JTable(modeloTablaDestruidos) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaDestruidos.getTableHeader().setReorderingAllowed(false);
//        scrollDestruidos = new JScrollPane(tablaDestruidos);
//        scrollDestruidos.setPreferredSize(new Dimension(width, heigth));
//        //this.add(scrollDestruidos);
//
//        // Comunicados
//        String[] identificadoresComunicados = {"Lista Procesos Comunicados"};
//        modeloTablaComunicados = new DefaultTableModel(0, identificadoresComunicados.length);
//        modeloTablaComunicados.setColumnIdentifiers(identificadoresComunicados);
//        tablaComunicados = new JTable(modeloTablaComunicados) {
//            public boolean isCellEditable(int rowIndex, int vColIndex) {
//                return false;
//            }
//        };
//        tablaComunicados.getTableHeader().setReorderingAllowed(false);
//        scrollComunicados = new JScrollPane(tablaComunicados);
//        scrollComunicados.setPreferredSize(new Dimension(width, heigth));
//        this.add(scrollComunicados);

             // Transiciones
        String[] identificadoresTerminados = {"Lista Procesos Terminados"};
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
        ArrayList<String> procesosProcesados = this.procesador.getListaProcesados();
        ArrayList<String> procesosNoProcesados = this.procesador.getListaNoProcesados();
        ArrayList<String> procesosTerminados = this.procesador.getListaTerminados();
        
        for (int i = 0; i < procesosProcesados.size(); i++) {
            agregarEjecutado(procesosProcesados.get(i));
            System.out.println("..");
            System.out.println("Proceso: " + procesosProcesados.get(i));
            System.out.println("..");
        }
        
        for (int i = 0; i < procesosNoProcesados.size(); i++) {
            agregarExpirado(procesosNoProcesados.get(i));
        }
        
        for (int i = 0; i < procesosTerminados.size(); i++) {
            agregarTerminado(procesosTerminados.get(i));
        }
       
        tablaEjecutados.setModel(modeloTablaEjecutados);
        tablaExpirados.setModel(modeloTablaExpirados);
        tablaTerminados.setModel(modeloTablaTerminados);
    }

    public void agregarListo(String proceso) {
        int row = this.modeloTablaListos.getRowCount();
        modeloTablaListos.setRowCount(row + 1);
        modeloTablaListos.setValueAt(proceso, row, 0);
    }

    public void agregarDespachado(InformacionTransicion it) {
        int row = this.modeloTablaDespachados.getRowCount();
        modeloTablaDespachados.setRowCount(row + 1);
        modeloTablaDespachados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }

    public void agregarEnEsperaES(InformacionTransicion it) {
        int row = this.modeloTablaEspESBloqueados.getRowCount();
        modeloTablaEspESBloqueados.setRowCount(row + 1);
        modeloTablaEspESBloqueados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarBloqueado(InformacionTransicion it) {
        int row = this.modeloTablaBloqueados.getRowCount();
        modeloTablaBloqueados.setRowCount(row + 1);
        modeloTablaBloqueados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }

    public void agregarTermESBloqueado(InformacionTransicion it) {
        int row = this.modeloTablaTermESBloqueados.getRowCount();
        modeloTablaTermESBloqueados.setRowCount(row + 1);
        modeloTablaTermESBloqueados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }

    public void agregarTermESSListos(InformacionTransicion it) {
        int row = this.modeloTablaTermESSuspendidosListos.getRowCount();
        modeloTablaTermESSuspendidosListos.setRowCount(row + 1);
        modeloTablaTermESSuspendidosListos.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }

    public void agregarReaSuspendidoB(InformacionTransicion it) {
        int row = this.modeloTablaReaSuspendidosBloqueados.getRowCount();
        modeloTablaReaSuspendidosBloqueados.setRowCount(row + 1);
        modeloTablaReaSuspendidosBloqueados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarReaSuspendidoL(InformacionTransicion it) {
        int row = this.modeloTablaReaSuspendidosListos.getRowCount();
        modeloTablaReaSuspendidosListos.setRowCount(row + 1);
        modeloTablaReaSuspendidosListos.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarSusESuspendidoL(InformacionTransicion it) {
        int row = this.modeloTablaSuspESuspendidosListos.getRowCount();
        modeloTablaSuspESuspendidosListos.setRowCount(row + 1);
        modeloTablaSuspESuspendidosListos.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarSusLSuspendidoL(InformacionTransicion it) {
        int row = this.modeloTablaSuspLSuspendidosListos.getRowCount();
        modeloTablaSuspLSuspendidosListos.setRowCount(row + 1);
        modeloTablaSuspLSuspendidosListos.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarSusSuspendidoB(InformacionTransicion it) {
        int row = this.modeloTablaSusSuspendidosBloqueados.getRowCount();
        modeloTablaSusSuspendidosBloqueados.setRowCount(row + 1);
        modeloTablaSusSuspendidosBloqueados.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
    }
    
    public void agregarSuspendido(InformacionTransicion it) {
        int row = this.modeloTablaSuspendidosListos.getRowCount();
        modeloTablaSuspendidosListos.setRowCount(row + 1);
        modeloTablaSuspendidosListos.setValueAt(it.getIdentificadorProceso() + " -Tiempo: " + it.getTiempoLleva(), row, 0);
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
        modeloTablaComunicados.setValueAt(proceso.getIdentificador() + "-Proceso:" + proceso.getSeComunica(), row, 0);
    }

    public void agregarExpirado(String proceso) {
        int row = this.modeloTablaExpirados.getRowCount();
        modeloTablaExpirados.setRowCount(row + 1);
        modeloTablaExpirados.setValueAt(proceso, row, 0);
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
