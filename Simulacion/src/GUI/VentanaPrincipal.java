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
    private PanelTablaParticiones panelTablaParticiones;

    private Procesador procesador;
    private DialogoParticiones dialogoParticiones;

    private DialogoResultados dialogoResultados;
    JMenuItem menuItemManual;
    
    private PanelBotonesResultados panelBotonResultados;
    
    private PanelProceso2 panelParticiones;
    
    

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
        
        this.panelParticiones = new PanelProceso2(procesador, this);
        //this.add(panelParticiones, BorderLayout.WEST);
        
        this.panelTablaParticiones = new PanelTablaParticiones(procesador, this);
        //this.add(panelTablaParticiones, BorderLayout.CENTER);
        
        panelBotonResultados = new PanelBotonesResultados(procesador);

        panelProceso = new PanelProceso(this.procesador, this);
        this.add(panelProceso, BorderLayout.WEST);

        //Agregamos los dos paneles a uno nuevo con otro layout
        panelTabla = new PanelTabla(this.procesador, this);
        this.add(panelTabla, BorderLayout.CENTER);

    }

    public void probar() {
    	
//    	ArrayList<Particion> lista = new ArrayList<>();
//    	lista.add(new Particion(30, 0));
//    	lista.add(new Particion(40, 1));
//    	lista.add(new Particion(60, 2));
//    	lista.add(new Particion(20, 3));
//    	lista.add(new Particion(10, 4));
//    	lista.add(new Particion(50, 5));
//    	this.procesador.setParticiones(lista);
        //								nombre	tiempo	  tamano indexParticion
    	
    	this.procesador.agregarProceso("P11", 		5, 		11, 0);
    	this.procesador.agregarProceso("P15", 		7, 		15, 1	);
    	this.procesador.agregarProceso("P18", 		8, 		18, 2	);
    	this.procesador.agregarProceso("P6", 		3, 		6, 3	);
    	this.procesador.agregarProceso("P9", 		4, 		9, 4	);
    	this.procesador.agregarProceso("P20", 		2,	 	20, 5	);
    	this.procesador.agregarProceso("P13", 		3,	 	13, 6	);
    	
        this.panelTabla.listarComunes();
        
        this.setVisible(true);
    }

    public void ingresarParticiones() {   	
    	this.setVisible(true);
        //String totalParticiones = JOptionPane.showInputDialog(this, "¿Cuantas particiones desea crear?");
                

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
    
    public void agregarPanelBotonesResultados(){
    	
    	panelBotonResultados.setPreferredSize(new Dimension(300, 300));
        this.add(panelBotonResultados, BorderLayout.SOUTH);
        
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

            	this.procesador.procesar();
               
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
        
//        if (evento.getActionCommand().equals("Agregar Particiones")) {
//        	ArrayList<Particion> lista = this.dialogoParticiones.getParticiones();
//        	if (lista != null) {
//        		this.procesador.setParticiones(lista);
//        		this.dialogoParticiones.setVisible(false);
//        		this.setVisible(true);
//        		// Actualiza la lista de particiones 
//        		this.panelProceso.setParticiones(lista);
//			}
//        	this.mostrarPanelesProcesos();
//        }
        if (evento.getActionCommand().equals("Agregar Particion")) {
        	String textoNombre = this.panelParticiones.getPanelNombre().getText();
        	int index = this.procesador.getParticiones().size();
        	String txtNombre = this.panelParticiones.getName();

        	String textoTamano = this.panelParticiones.getPanelTamanio().getText();
        	int tamanio = 0;
        	if (!this.validarNumeros(textoTamano)||textoTamano.equals("") || textoTamano.equals(" ")){
        		JOptionPane.showMessageDialog(null, "el tamano de la particion debe ser un numero positivo");
        	}else{
        		tamanio = Integer.parseInt(textoTamano);
        	}        	
        	procesador.agregarParticion(tamanio, index);
        	this.mostrarTablaParticiones();
        	this.panelParticiones.limpiar();
        }
        
        if (evento.getActionCommand().equals("Agregar particiones")) {
        	//System.out.println(this.procesador.getParticiones().size());
        	this.panelProceso.setParticiones(this.procesador.getParticiones());
        	this.mostrarPanelesProcesos();
        }
    }
    
    public void mostrarPanelesProcesos(){
    	this.remove(this.panelParticiones);
    	this.remove(this.panelTablaParticiones);
    	
    	this.add(panelProceso, BorderLayout.WEST);
        this.add(panelTabla, BorderLayout.CENTER);
    }
    
    public void mostrarTablaParticiones(){
    	this.panelTablaParticiones.listarParticiones();
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

        // Validamos y agregamos el campo tamanioo
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
        //								Nombre		tiempo 	tamanio			indexParticion
        this.procesador.agregarProceso(nombreProceso, tiempo, tamanoProceso, this.procesador.getProcesosCargados().size());
        this.panelTabla.listarComunes();
        limpiarProceso();
    }

    public void limpiarProceso() {
        panelProceso.limpiarTexto();
    }

}
