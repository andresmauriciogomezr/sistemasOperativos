package GUI;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
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

import com.sun.javafx.tk.Toolkit;

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
	private Procesador procesador;
		
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
		
		this.setUndecorated(true);
		try{
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		panelProceso = new PanelProceso(this.procesador, this);
		this.add(panelProceso, BorderLayout.WEST);
		
		panelTabla = new PanelTabla(this.procesador, this);
		this.add(panelTabla, BorderLayout.CENTER);
		
	}
	
	public void probar(){
		
//		this.procesador.agregarProceso("Proceso1", 34, 6);
//		this.procesador.agregarProceso("Proceso2", 34, 8);
//		this.procesador.agregarProceso("Proceso3", 34, 4);
//		this.procesador.agregarProceso("Proceso4", 34, 7);
		for (int i = 0; i < 100; i++) {
			this.procesador.agregarProceso("Proceso" +i,(int)(Math.random() * 6) , (int)(Math.random() * 60));
		}
		this.panelTabla.listarProcesos();
		
	}
	
	public boolean validarNumeros(String cadena){
		char[] prohibidos = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		
		int cantidadValidos = 0; //Determina la cantidad de caracteres validos hay en la cadena
		for (int i = 0; i < cadena.length(); i++) {
			for (int j = 0; j < prohibidos.length; j++) {
				//ystem.out.println(cadena.charAt(i));
				if (cadena.charAt(i) == (prohibidos[j]) ) {
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
		}
		if (evento.getActionCommand().equals("Procesar")){
			if (hilo.isAlive()==false){
				hilo.start();
			}
		}
	}
	
	public void actualizarTiempo(Proceso proceso){
		this.panelTabla.actualizarTiempo(proceso);
	}
	
	public void agregarProceso(){
		String nombreProceso = this.panelProceso.getPanelNombre().getText();
		String auxPriodad = this.panelProceso.getPanelPrioridad().getText();
		String auxTiempo = this.panelProceso.getPanelTiempo().getText();
		
		// Validamos y agregamos el campo prioridd
		if (nombreProceso.equals("") || nombreProceso.equals(" ") || auxPriodad.equals("") || auxPriodad.equals(" ")) { 
			JOptionPane.showMessageDialog(null, "Solamente el campo 'Tiempo de ejecuci�n' puede estar vac�o");
			return;
		}
		
		// Validamos y agregamos el campo prioridd
		if (!this.validarNumeros(auxPriodad)) { 
			JOptionPane.showMessageDialog(null, "El campo prioridad debe contener unicamente n�meros");
			return;
		} 
		int prioridadProceso = Integer.parseInt(auxPriodad);
                System.out.println("Prioridad: " + prioridadProceso);
                if (prioridadProceso<1 || prioridadProceso>10){
                    JOptionPane.showMessageDialog(null, "El campo prioridad debe estar entre 1 y 10");
			return;
                }
		
		// Validamos y agregamos el campo tiempo
		int tiempo = (int)(Math.random() * 60);
		if (!this.validarNumeros(auxTiempo) && !auxTiempo.equals("") && !auxTiempo.equals(" ")) {			
			JOptionPane.showMessageDialog(null, "El campo tiempo debe contener unicamente n�meros, o estar vac�o");
			return;
		}
		if (!auxTiempo.equals("") && !auxTiempo.equals(" ")) {
			tiempo = Integer.parseInt(auxTiempo);			
		}
		
		// Agregamos
		this.procesador.agregarProceso(nombreProceso, prioridadProceso, tiempo);
		this.panelTabla.listarProcesos();
                limpiarProceso();
	}
        
        public void limpiarProceso(){
            panelProceso.limpiarTexto();
        }

    @Override
    public void run() {
        while (procesador.tieneProcesos() || procesador.tieneBloqueos()){
        	this.procesador.procesar();
        	while(this.procesador.isProcesando()){
        	//for (int i = 0; i < tiempoProcesador; i++) {
        		this.procesador.ejecutarProceso();
			
	            this.panelTabla.listarProcesos();
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException ex) {
	                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
	            }
        	}
        	
        } 
        
    }
	
	

}
