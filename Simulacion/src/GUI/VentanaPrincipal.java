package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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

    private DialogoResultados dialogoResultados;
    JMenuItem menuItemManual;

    public VentanaPrincipal() {

        Dimension dimension = this.getToolkit().getScreenSize();

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItemManual = new JMenuItem("Ver Manual");
        menuItemManual.addActionListener(this);
        menu.add(menuItemManual);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        this.procesador = new Procesador();
        this.panelListas = new PanelListas(this.procesador);
        dialogoResultados = new DialogoResultados(this.procesador, this.panelListas);

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

        ingresarParticiones();

    }

    public void probar() {

        //		this.procesador.agregarProceso("Proceso1", 34, 6);
        //		this.procesador.agregarProceso("Proceso2", 34, 8);
        //		this.procesador.agregarProceso("Proceso3", 34, 4);
        //		this.procesador.agregarProceso("Proceso4", 34, 7);
        //							Nombre	priodidad	tiempo 	 bloqueo suspendido/Listo SuspendidoBloq destruido	seComunica	cambioPrioridad	
//    	this.procesador.agregarProceso("P1",	5 ,	 		4	, false, 	"No",			"No", 					false,	 "", 		"");
//    	this.procesador.agregarProceso("P2",	5 ,	 		9	, true, 	"No",			"No", 					false,	 "", 		"");
//    	this.procesador.agregarProceso("P3",	5 ,	 		14	, false, 	"Ejecucion",			"No", 					false, 	 "", 		"");
//    	this.procesador.agregarProceso("P4",	5 ,	 		8	, true, 	"No",			"No", 					false,    "", 		"");
//    	this.procesador.agregarProceso("P5",	5, 			7	, true, 	"No",			"Bloqueado", 			false,   "p1", 		"");
//    	this.procesador.agregarProceso("P6",	5 ,	 		11	, false, 	"Ejecucion",			"No", 					false, 	 "", 		"");
//    	this.procesador.agregarProceso("P7",	5 ,			8	, true, 	"No",			"Suspendido/Listo",	false, 	 "", 		"1");
//    	this.procesador.agregarProceso("P8",	5 ,			10	, false, 	"No",			"No", 					false, 	 "", 		"");
//
//    	for (int i = 0; i < 100; i++) {
//    		//Nombre				priodidad					tiempo 							bloqueo							suspendido				destruido					seComunica
//    		//this.procesador.agregarProceso("Proceso" +i,(int)(Math.random() * 20) , (int)(Math.random() * 10), (int)(Math.random() * 10) == 3, (int)(Math.random() * 5) == 2, (int)(Math.random() * 5) == 2, (int)(Math.random() * 5) == 1);
//    	}
//    	//this.panelTabla.listarProcesos();
        this.panelTabla.listarComunes();
    }

    public void ingresarParticiones() {
        String totalParticiones = JOptionPane.showInputDialog(this, "¿Cuantas particiones son en total?");
        int total;
        if (totalParticiones != null) {
            if (validarNumeros(totalParticiones)) {
                total = Integer.parseInt(totalParticiones);
                for (int i = 0; i < total; i++) {
                    String tamanioParticion = JOptionPane.showInputDialog(this, "¿Cual es el tamaño de la particion numero: " + (i + 1) + "?");
                    if (validarNumeros(tamanioParticion)) {
                        procesador.agregarParticion(Integer.parseInt(tamanioParticion));
                    } else {
                        JOptionPane.showMessageDialog(this, "Debe ser un numero entero positivo");
                        i--;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ser un numero entero positivo");
                ingresarParticiones();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe existir por los menos 1 particion");
            ingresarParticiones();
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
            // Se actualiza la selección de comunicacion
            String[] opciones = new String[procesador.getProcesosCargados().size() + 1];
            opciones[0] = "";
            for (int i = 1; i < opciones.length; i++) {
                opciones[i] = procesador.getProcesosCargados().get(i - 1).getIdentificador();
            }
            panelProceso.getPanelComunicacion().setOpciones(opciones);
            this.panelTabla.listarComunes();
        }
        if (evento.getActionCommand().equals("Procesar")) {
            if (this.procesador.getProcesosCargados().isEmpty() == false) {
                this.panelTabla.listarComunes();
                this.procesador.procesar();
                this.panelListas.listarProcesos(); // Muestra como qued� la tabla de procesos cargados 
                this.dialogoResultados.setVisible(true); // Muestra la ventana resultados
            } else {
                JOptionPane.showMessageDialog(this, "Tiene que haber por lo menos 1 proceso");
            }
        }
        if (evento.getActionCommand().equals("Ver Manual")) {
            Manual manual = new Manual();
            manual.abrir();
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

        // Validamos y agregamos el campo prioridd
        int prioridadProceso = 5;
        if (!this.validarNumeros(auxTamanio)) {
            JOptionPane.showMessageDialog(null, "El campo prioridad debe contener unicamente numeros enteros");
            return;
        }
        if (!auxTamanio.equals("")) { // Se asign� ua prioridad			
            prioridadProceso = Integer.parseInt(auxTamanio);
        }

        // Validamos y agregamos el campo tiempo
        int tiempo = 0;
        if (!this.validarNumeros(auxTiempo) || auxTiempo.equals("") || auxTiempo.equals(" ")) {
            JOptionPane.showMessageDialog(null, "El campo tiempo debe contener unicamente numeros enteros");
            return;
        } else {
            tiempo = Integer.parseInt(auxTiempo);
        }

        boolean bloqueo = this.panelProceso.getPanelBloqueo().getComboBox().getSelectedItem().equals("Si"); // se evala que el campo seleccionado sea Si
        boolean destruido = this.panelProceso.getPanelDestruir().getComboBox().getSelectedItem().equals("Si"); // se evala que el campo seleccionado sea Si
        String seComunica = (String) (this.panelProceso.getPanelComunicacion().getComboBox().getSelectedItem()); // se evala que el campo seleccionado sea Si
        String suspendidoListo = (String) this.panelProceso.getPanelSuspender().getComboBox().getSelectedItem(); // se evala que el campo seleccionado sea Si
        String suspendidoBloqueado = (String) this.panelProceso.getPanelSuspenderBloqueado().getComboBox().getSelectedItem(); // se evala que el campo seleccionado sea Si;
        if (suspendidoListo.equals("No") == false && suspendidoBloqueado.equals("No") == false) {
            JOptionPane.showMessageDialog(null, "El proceso ya va a pasar a estado de Suspendido listo");
            return;
        }
        if (suspendidoBloqueado.equals("No") == false) {
            bloqueo = true;
        }
        if (suspendidoBloqueado.equals("Suspendido/Listo")) {
            suspendidoListo = "Si";
        }
        System.out.println("SL: " + suspendidoListo);
        //									Nombre		priodidad		tiempo	bloqueo	suspendido destruido seComunica
//        this.procesador.agregarProceso(nombreProceso, tiempo, tamanio, particion);
        this.panelTabla.listarComunes();
        limpiarProceso();
    }

    public void limpiarProceso() {
        panelProceso.limpiarTexto();
    }

}
