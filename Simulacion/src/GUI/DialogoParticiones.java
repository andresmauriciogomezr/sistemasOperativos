package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import main.Particion;
import main.Procesador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DialogoParticiones extends JFrame {

    private ArrayList<PanelLayout> listaPanelesParticiones;    
    private Procesador procesador;
    private JPanel panelPrincipal;
    private ActionListener listener;
    private PanelLayout panelAceptar;
    private PanelLayout panelCancelar;

    public DialogoParticiones(Procesador procesador, int cantidadParticiones, ActionListener listener) {
        this.procesador = procesador;
        this.listaPanelesParticiones = new ArrayList<>();
      
        this.listener = listener;
        
        Dimension dimension = this.getToolkit().getScreenSize();
		this.setUndecorated(true);
		try{
			JFrame.setDefaultLookAndFeelDecorated(true);
			//JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			setTitle("");
			setSize(dimension);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new GridLayout(1, 3));
			//setLayout(new BorderLayout());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		// panel primero vacio
		JPanel panel = new JPanel();
    	//panel.setSize(new Dimension(300, dimension.height));
    	this.add(panel);
		
		this.panelPrincipal = new JPanel();   
		this.panelPrincipal.setSize(new Dimension(700, dimension.height));
		this.panelPrincipal.setLayout(new GridLayout(cantidadParticiones + 2, 1)); // El numero de filas, el numero de columnas
		this.inicializarPaneles(cantidadParticiones);
		
        this.panelAceptar = new PanelLayout(listener, "Aceptar", "Precione el boton para agregar las particiones", TipoPanel.boton);
        JButton botonAceptar = new JButton("Agregar Particiones");
        botonAceptar.addActionListener(listener);	
        this.panelAceptar.setBoton(botonAceptar);
        this.panelPrincipal.add(this.panelAceptar);
		
        this.panelCancelar= new PanelLayout(listener, "Volver", "Precione el boton para Volver", TipoPanel.boton);
        JButton botonCancelar = new JButton("Volver");
        botonCancelar.addActionListener(listener);	
        panelCancelar.setBoton(botonCancelar);        
        this.panelPrincipal.add(panelCancelar);

        // panel tercer vacio
		panel = new JPanel();
    	//panel.setSize(new Dimension(300, dimension.height));
    	this.add(panel);
    }
    
    public void inicializarPaneles(int cantidadParticiones){
    	for (int i = 0; i < cantidadParticiones; i++) {
        	PanelLayout panel = new PanelLayout(null, "Particion # " + String.valueOf(i+1), "Escriba el tamano de memoria de la particion # " + String.valueOf(i +1), TipoPanel.texto);
        	this.listaPanelesParticiones.add(panel);
            this.panelPrincipal.add(panel);
		}
    	this.add(this.panelPrincipal);
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
    
    public ArrayList<Particion> getParticiones(){
    	ArrayList<Particion> lista = new ArrayList<>();
    	for (int i = 0; i < this.listaPanelesParticiones.size(); i++) {
			String stringTamano = listaPanelesParticiones.get(i).getText();
			if (validarNumeros(stringTamano) && !stringTamano.equals("") && !stringTamano.equals(" ")) {
				lista.add(new Particion(Integer.parseInt(stringTamano), i));
			}else{
				JOptionPane.showMessageDialog(null, "Debe ingresar numeros enteros positivos");
				return null;
			}
		}
    	return lista;
    }
}
