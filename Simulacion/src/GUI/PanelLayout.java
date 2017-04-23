package GUI;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelLayout extends JPanel{ // Funciona como layout para los diferentes paneles que reciben datos

	private static final long serialVersionUID = 735523904991087453L;

	private JLabel labelTitulo;
	private JLabel icoInfo;
	private Icon icon;


	private JTextField campoTexto;
	private JButton botonAceptar;
	private JComboBox<String> comboBox;


	private GridBagLayout gridbag;
	private GridBagConstraints gbc;

	public static final String ICON = "/img/icoInfo.png";
	public static final int WIDTH = 400;
	public static final int HEIGHT = 100;


	public PanelLayout(ActionListener listener, String titulo, String ayuda, TipoPanel tipoPanel) {

		gridbag = new GridBagLayout();

		setLayout(gridbag);
		setBorder(BorderFactory.createEtchedBorder());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));


		labelTitulo = new JLabel(titulo, SwingConstants.LEFT);

		gbc = new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);

		add(labelTitulo, gbc);	


		icoInfo = new JLabel(new ImageIcon(getClass().getResource(ICON)));
		icoInfo.setSize(20, 20);
		gbc = new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);

		icon = new ImageIcon(new ImageIcon(getClass().getResource(ICON)).getImage()
				.getScaledInstance(icoInfo.getWidth(), icoInfo.getHeight(), Image.SCALE_DEFAULT));
		icoInfo.setIcon(icon);
		icoInfo.setToolTipText(ayuda);
		add(icoInfo, gbc);

		if (tipoPanel == TipoPanel.texto) { // Depende de la variable tipoPanel que indica el tipo de panel que es
			campoTexto = new JTextField(12);
			gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
					new Insets(5, 0, 5, 5), 0, 0);
			add(campoTexto, gbc);			
		}
		if (tipoPanel == TipoPanel.boton) {
			botonAceptar = new JButton("Agregar Proceso");
			botonAceptar.addActionListener(listener);
			gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
					new Insets(5, 0, 5, 5), 0, 0);
			add(botonAceptar, gbc);
		}
		if (tipoPanel == TipoPanel.select) {
			String[] opciones = { "No", "Si" };

			//Create the combo box, select item at index 4.
			//Indices start at 0, so 4 specifies the pig.
			comboBox = new JComboBox(opciones);
			comboBox.addActionListener(listener);
			gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
					new Insets(5, 0, 5, 5), 0, 0);
			add(comboBox, gbc);
		}


	}

	public String getText(){
		return this.campoTexto.getText();
	}

	public void limpiarTexto(){
		this.campoTexto.setText("");
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}
	
	public void setOpciones(String[] opciones) {
		this.remove(this.comboBox);
		this.comboBox = new JComboBox<>(opciones);
		gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 0, 5, 5), 0, 0);
		add(comboBox, gbc);
	}

	public JButton getBotonAceptar() {
		return botonAceptar;
	}

	public void setBoton(JButton boton) {
		this.remove(this.botonAceptar);
		this.botonAceptar = boton;
		gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 0, 5, 5), 0, 0);
		this.add(this.botonAceptar, gbc);
	}

	
}
