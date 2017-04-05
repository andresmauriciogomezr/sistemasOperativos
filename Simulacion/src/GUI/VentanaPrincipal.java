package GUI;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.PanelPeer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Procesador;
import main.Proceso;

public class VentanaPrincipal extends JFrame implements ActionListener, Runnable{



	public static final String TITLE = "";

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;

	int tiempoProcesador;
	private PanelProceso panelProceso;
	private PanelTabla panelTabla;
	private PanelListas panelListas;
	
	private Procesador procesador;
	private Panel panelContendio; // Va a contener tanto la tabla como las listas
	
	private DialogoResultados dialogoResultados;

	public VentanaPrincipal() {
		tiempoProcesador = 6;
		int WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;;
		int HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;


		menuBar = new JMenuBar();
		menu = new JMenu("Menu");

		menuItem = new JMenuItem("Salir");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		this.procesador = new Procesador(this);
		this.panelListas = new PanelListas(this.procesador);
		dialogoResultados = new DialogoResultados(this.procesador, this.panelListas);

		this.setUndecorated(true);
		try{
			JFrame.setDefaultLookAndFeelDecorated(true);
			//JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			setTitle(TITLE);
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

		panelProceso = new PanelProceso(this.procesador, this);
		this.add(panelProceso, BorderLayout.WEST);

		//Agregamos los dos paneles a uno nuevo con otro layout
		panelTabla = new PanelTabla(this.procesador, this);
		this.add(panelTabla, BorderLayout.CENTER);
		
	}

	public void probar(){

		//		this.procesador.agregarProceso("Proceso1", 34, 6);
		//		this.procesador.agregarProceso("Proceso2", 34, 8);
		//		this.procesador.agregarProceso("Proceso3", 34, 4);
		//		this.procesador.agregarProceso("Proceso4", 34, 7);
//									Nombre	priodidad	tiempo 	bloqueo	suspendido	destruido	seComunica		
		this.procesador.agregarProceso("P1",	3 ,	 		8	, false, 	false, 		false, "", "");
		this.procesador.agregarProceso("P2",	2 ,	 		11	, true, 	true, 		false, "", "");
		this.procesador.agregarProceso("P3",	4 ,	 		7	, false, 	false, 		true, "", "");
		this.procesador.agregarProceso("P4",	6 ,	 		9	, true, 	false, 		true, "", "");
		this.procesador.agregarProceso("P5",	8 ,	 		6	, false, 	false, 		false, "p1", "");
		this.procesador.agregarProceso("P6",	9 ,	 		7	, false, 	false, 		false, "", "5");
		this.procesador.agregarProceso("P7",	10 ,	 	14	, true, 	false, 		false, "", "");
		
		for (int i = 0; i < 100; i++) {
//											Nombre				priodidad					tiempo 							bloqueo							suspendido				destruido					seComunica
			//this.procesador.agregarProceso("Proceso" +i,(int)(Math.random() * 20) , (int)(Math.random() * 10), (int)(Math.random() * 10) == 3, (int)(Math.random() * 5) == 2, (int)(Math.random() * 5) == 2, (int)(Math.random() * 5) == 1);
		}
		//this.panelTabla.listarProcesos();
		this.panelTabla.listarComunes();
	}

	public boolean validarNumeros(String cadena){
		char[] permitios = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

		int cantidadValidos = 0; //Determina la cantidad de caracteres validos hay en la cadena
		for (int i = 0; i < cadena.length(); i++) {
			for (int j = 0; j < permitios.length; j++) {
				//ystem.out.println(cadena.charAt(i));
				if (cadena.charAt(i) == (permitios[j]) ) {
					cantidadValidos ++;
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
		Thread hilo = new Thread(this);

		if (evento.getActionCommand().equals("Salir")) {
			System.exit(0);
		}
		if (evento.getActionCommand().equals("Agregar Proceso")) {
			this.agregarProceso();
			// Se actualiza la selección de comunicacion
			String[] opciones = new String[procesador.getListaComun().size()];
			for (int i = 0; i < opciones.length; i++) {
				opciones[i] = procesador.getListaComun().get(i).getIdentificador();
			}
			panelProceso.getPanelComunicacion().setOpciones(opciones);
		}
		if (evento.getActionCommand().equals("Procesar")){			
			//this.procesador.asignar();// Distribuye los procesos en las diferentes listas
			this.procesador.cambiarPrioridades();
			this.procesador.ejecutar();			
			this.panelListas.listarProcesos();
			this.panelTabla.listarComunes();
			this.dialogoResultados.setVisible(true);
//			if (hilo.isAlive()==false){
//				//hilo.start();
//			}
		}
	}

	public void actualizarTiempo(Proceso proceso){
		this.panelTabla.actualizarTiempo(proceso);
	}

	public void agregarProceso(){
		String nombreProceso = this.panelProceso.getPanelNombre().getText();
		String auxPriodad = this.panelProceso.getPanelPrioridad().getText(); //usa axiliar porque luego hay que convertir a entero
		String auxTiempo = this.panelProceso.getPanelTiempo().getText();
		String auxCambioPrioridad = this.panelProceso.getPanelCambioPrioridad().getText();

		// Validamos y agregamos el campo nombre
		if (nombreProceso.equals("") || nombreProceso.equals(" ") || auxPriodad.equals("") || auxPriodad.equals(" ")) { 
			JOptionPane.showMessageDialog(null, "El campo nombre debe tener al menos una caracter alfanumerico");
			return;
		} else if (procesador.existeProceso(nombreProceso)) {
                    JOptionPane.showMessageDialog(null, "El proceso ya existe");
			return;
                }

		// Validamos y agregamos el campo prioridd
		if (!this.validarNumeros(auxPriodad)) { 
			JOptionPane.showMessageDialog(null, "El campo prioridad debe contener unicamente numeros enteros");
			return;
		} 
		int prioridadProceso = Integer.parseInt(auxPriodad);
		System.out.println("Prioridad: " + prioridadProceso);
		if (procesador.existePrioridad(prioridadProceso)){
			JOptionPane.showMessageDialog(null, "La prioridad ya esta asignada a un proceso");
			return;
		}

		// Validamos y agregamos el campo tiempo
		int tiempo = 0;
		if (!this.validarNumeros(auxTiempo) && !auxTiempo.equals("") && !auxTiempo.equals(" ")) {			
			JOptionPane.showMessageDialog(null, "El campo tiempo debe contener unicamente numeros enteros");
			return;
		} else {
			tiempo = Integer.parseInt(auxTiempo);			
		}		
		
		boolean bloqueo = this.panelProceso.getPanelBloqueo().getComboBox().getSelectedItem().equals("Si"); // se evala que el campo seleccionado sea Si
		boolean suspendido = this.panelProceso.getPanelSuspender().getComboBox().getSelectedItem().equals("Si"); // se evala que el campo seleccionado sea Si
		boolean destruido = this.panelProceso.getPanelDestruir().getComboBox().getSelectedItem().equals("Si"); // se evala que el campo seleccionado sea Si
		String seComunica = (String)(this.panelProceso.getPanelComunicacion().getComboBox().getSelectedItem()); // se evala que el campo seleccionado sea Si

		//									Nombre		priodidad		tiempo	bloqueo	suspendido destruido seComunica
		this.procesador.agregarProceso(nombreProceso, prioridadProceso, tiempo, bloqueo, suspendido, destruido, seComunica, auxCambioPrioridad);
		//this.panelTabla.listarProcesos();
		//this.panelListas.listarProcesos();
		this.panelTabla.listarComunes();
		limpiarProceso();
	}
	
	

	public void limpiarProceso(){
		panelProceso.limpiarTexto();
	}

//	@Override
//	public void run() {
//		while (procesador.tieneProcesos() || procesador.tieneBloqueos()){
//			this.procesador.procesar();
//			while(this.procesador.isProcesando()){ //Existen procesos listos y pueden haber bloqueados
//				this.procesador.ejecutarProceso();
//				this.panelTabla.listarProcesos();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException ex) {
//					Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//				}
//			}
//
//		} 
//
//	}
	
	@Override
	public void run() {
		while (procesador.tieneProcesos() || procesador.tieneBloqueos()){
			this.procesador.procesar();
			while(this.procesador.isProcesando()){ //Existen procesos listos y pueden haber bloqueados
				this.procesador.ejecutarProceso();
				this.panelTabla.listarProcesos();
				try {
					Thread.sleep(400);
				} catch (InterruptedException ex) {
					Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				}
			}

		}
		System.out.println("salio del blu");
		dialogoResultados.setVisible(true);
	}
	


}
