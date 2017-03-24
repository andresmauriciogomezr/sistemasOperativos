package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.PanelPeer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import main.Procesador;

public class VentanaPrincipal extends JFrame implements ActionListener{
	
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 500;
	public static final String TITLE = "";
	
	
	private PanelProceso panelProceso;
	private PanelTabla panelTabla;
	private Procesador procesador;
		
	public VentanaPrincipal() {
		
		this.procesador = new Procesador();
		
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
		
		panelTabla = new PanelTabla(this.procesador);
		this.add(panelTabla, BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String nombreProceso = this.panelProceso.getPanelNombre().getText();
		int prioridadProceso = Integer.parseInt(this.panelProceso.getPanelPrioridad().getText());
		int tiempo = Integer.parseInt(this.panelProceso.getPanelTiempo().getText());
		
		this.procesador.agregarProceso(nombreProceso, prioridadProceso, tiempo);
		this.panelTabla.listarProcesos();
		
	}

}
