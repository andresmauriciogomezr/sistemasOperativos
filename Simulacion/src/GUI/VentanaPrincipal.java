package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import main.Particion;
import main.Procesador;
import main.Proceso;
import manual.Manual;

public class VentanaPrincipal extends JFrame implements ActionListener {

    public static final String TITLE = "";

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;

    private PanelProceso panelProceso;
    private PanelTabla panelTabla;
    private PanelListas panelListas;

    private Procesador procesador;
    private Panel panelContendio; // Va a contener tanto la tabla como las listas
    private DialogoParticiones dialogoParticiones;

    private DialogoResultados dialogoResultados;
    JMenuItem menuItemManual;

    public VentanaPrincipal() {

        Dimension dimension = this.getToolkit().getScreenSize();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        menuItemManual = new JMenuItem("Ver Manual");
        menuItemManual.addActionListener(this);
        menu.add(menuItemManual);

        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.procesador = new Procesador();
        this.dialogoResultados = new DialogoResultados(this.procesador);

        this.setUndecorated(true);
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

            setTitle(TITLE);
            setSize(dimension);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
        } catch (Exception e) {
            e.printStackTrace();
        }

        panelProceso = new PanelProceso(this.procesador, this);
        this.add(panelProceso, BorderLayout.WEST);

        //Agregamos los dos paneles a uno nuevo con otro layout
        panelTabla = new PanelTabla(this.procesador, this);
        this.add(panelTabla, BorderLayout.CENTER);

    }

    public void probar() {
    	
    	ArrayList<Particion> lista = new ArrayList<>();
    	lista.add(new Particion(20, 0));
    	lista.add(new Particion(15, 1));
    	lista.add(new Particion(25, 2));
    	this.procesador.setParticiones(lista);
        //								nombre	tiempo	  tamano indexParticion
    	this.procesador.agregarProceso("P1", 		5, 		27	, 0);
    	this.procesador.agregarProceso("P2", 		8, 		4	, 0);
    	this.procesador.agregarProceso("P3", 		4, 		10	, 0);
    	this.procesador.agregarProceso("P4", 		5, 		6	, 1);
    	this.procesador.agregarProceso("P5", 		8, 		4	, 1);
    	this.procesador.agregarProceso("P6", 		9, 		8	, 1);
    	this.procesador.agregarProceso("P7", 		11,		10	, 2);
    	this.procesador.agregarProceso("P8", 		6, 		20	, 2);
    	
        this.panelTabla.listarComunes();
        this.setVisible(true);
    }

    public void ingresarParticiones() {
        String totalParticiones = JOptionPane.showInputDialog(this, "Â¿Cuantas particiones son en total?");
        int total;
        if (totalParticiones != null) {
            if (validarNumeros(totalParticiones) && !totalParticiones.equals("") && !totalParticiones.equals(" ") ) { // No es vacío y es un numero
                total = Integer.parseInt(totalParticiones);
                this.dialogoParticiones = new DialogoParticiones(this.procesador, total, this);
                this.dialogoParticiones.setVisible(true);
                
//                for (int i = 0; i < total; i++) {
//                    String tamanioParticion = JOptionPane.showInputDialog(this, "Â¿Cual es el tamaÃ±o de la particion numero: " + (i + 1) + "?");
//                    if (validarNumeros(tamanioParticion)) {
//                        procesador.agregarParticion(Integer.parseInt(tamanioParticion));
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Debe ser un numero entero positivo");
//                        i--;
//                    }
//                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ser un numero entero positivo");
                ingresarParticiones();
            }
        } else { // Preciona el boton cancelar
            //JOptionPane.showMessageDialog(this, "Debe existir por los menos 1 particion");
            //ingresarParticiones();
        	System.exit(0); 
        }
    }

    public boolean validarNumeros(String cadena) {
        char[] permitios = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int cantidadValidos = 0; //Determina la cantidad de caracteres validos hay en la cadena
        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < permitios.length; j++) {
                //ystem.out.println(cadena.charAt(i));
                if (cadena.charAt(i) == (permitios[j])) {
                    cantidadValidos++;
                    break;
                }
            }
        }
        if (cantidadValidos == cadena.length()) { //todos los caracteres son validos
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals("Salir")) {
            System.exit(0);
        }
        if (evento.getActionCommand().equals("Agregar Proceso")) {
            this.agregarProceso();
            this.panelTabla.listarComunes();
        }
        if (evento.getActionCommand().equals("Procesar")) {
            if (this.procesador.getProcesosCargados().isEmpty() == false) {
                //this.panelTabla.listarComunes();
                this.procesador.procesar();
                //this.panelListas.listarProcesos(); // Muestra como quedï¿½ la tabla de procesos cargados 
                this.dialogoResultados.inicializarPaneles(this.procesador.getParticiones());
                this.dialogoResultados.setVisible(true); // Muestra la ventana resultados
            } else {
                JOptionPane.showMessageDialog(this, "Tiene que haber por lo menos 1 proceso");
            }
        }
        if (evento.getActionCommand().equals("Ver Manual")) {
            Manual manual = new Manual();
            manual.abrir();
        }
        
        if (evento.getActionCommand().equals("Volver")) {
        	this.dialogoParticiones.setVisible(false);
        	this.ingresarParticiones();
        }
        
        if (evento.getActionCommand().equals("Agregar Particiones")) {
        	ArrayList<Particion> lista = this.dialogoParticiones.getParticiones();
        	if (lista != null) {
        		this.procesador.setParticiones(lista);
        		this.dialogoParticiones.setVisible(false);
        		this.setVisible(true);
        		// Actualiza la lista de particiones 
        		this.panelProceso.setParticiones(lista);
			}
        }
    }

    public void actualizarTiempo(Proceso proceso) {
        this.panelTabla.actualizarTiempo(proceso);
    }

    public void agregarProceso() {
        String nombreProceso = this.panelProceso.getPanelNombre().getText();
        String auxTamanio = this.panelProceso.getPanelTamanio().getText(); //usa axiliar porque luego hay que convertir a entero
        String auxTiempo = this.panelProceso.getPanelTiempo().getText();

        // Validamos y agregamos el campo nombre
        if (nombreProceso.equals("") || nombreProceso.equals(" ")) {
            JOptionPane.showMessageDialog(null, "El campo nombre debe tener al menos una caracter alfanumerico");
            return;
        } else if (procesador.existeProceso(nombreProceso)) {
            JOptionPane.showMessageDialog(null, "El proceso ya existe");
            return;
        }

        // Validamos y agregamos el campo tamaño
        int tamanoProceso = -1;
        if (!this.validarNumeros(auxTamanio)||auxTamanio.equals("") || auxTamanio.equals(" ")) {
            JOptionPane.showMessageDialog(null, "El campo Tamano del proceso debe contener unicamente numeros enteros");
            return;
        }else { // Se asignï¿½ ua prioridad			
            tamanoProceso = Integer.parseInt(auxTamanio);
        }

        // Validamos y agregamos el campo tiempo
        int tiempo = 0;
        if (!this.validarNumeros(auxTiempo) || auxTiempo.equals("") || auxTiempo.equals(" ")) {
            JOptionPane.showMessageDialog(null, "El campo tiempo debe contener unicamente numeros enteros");
            return;
        } else {
            tiempo = Integer.parseInt(auxTiempo);
        }
                
        int indexParticion = this.panelProceso.getPanelParticion().getComboBox().getSelectedIndex();
        //								Nombre		tiempo 	tamaño			indexParticion
        this.procesador.agregarProceso(nombreProceso, tiempo, tamanoProceso, indexParticion);
        this.panelTabla.listarComunes();
        limpiarProceso();
    }

    public void limpiarProceso() {
        panelProceso.limpiarTexto();
    }

}
