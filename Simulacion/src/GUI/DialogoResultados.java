package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import main.Particion;
import main.Procesador;

public class DialogoResultados extends JFrame implements ActionListener{
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	private Procesador procesador;	
        private PanelListasProcesos panelListasProcesos;
        private PanelListasParticiones panelListasParticiones;
        private JScrollPane scrollPane;
	
	public DialogoResultados(Procesador procesador) {
		this.procesador = procesador;
		Dimension dimension = this.getToolkit().getScreenSize();
	

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");

		menuItem = new JMenuItem("Salir");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		
		this.setUndecorated(true);
		try{
			JFrame.setDefaultLookAndFeelDecorated(true);
			//JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			setTitle("");
			setSize(dimension);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			//setLayout(new BorderLayout());			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
                
                panelListasProcesos = new PanelListasProcesos(procesador);
                panelListasProcesos.listarProcesos();
                this.add(panelListasProcesos, BorderLayout.PAGE_START);
                
                panelListasParticiones = new PanelListasParticiones(procesador);
                panelListasParticiones.listarProcesos();
                this.add(panelListasParticiones, BorderLayout.CENTER);
		//this.inicializarPaneles(particiones);	
		//this.add(this.panelListas, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		// TODO Auto-generated method stub
		if (evento.getActionCommand().equals("Salir")) {
			System.exit(0);
		}
	}
	
}
