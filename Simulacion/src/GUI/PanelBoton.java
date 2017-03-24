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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelBoton extends JPanel{

	private static final long serialVersionUID = 735523904991087453L;

	private JLabel labelTitulo;
	private JButton botonAceptar;

	private GridBagLayout gridbag;
	private GridBagConstraints gbc;

	public static final String ICON = "/img/icoInfo.png";
	public static final int WIDTH = 400;
	public static final int HEIGHT = 100;
	

	public PanelBoton(ActionListener listener) {
		

		gridbag = new GridBagLayout();

		setLayout(gridbag);
		setBorder(BorderFactory.createEtchedBorder());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		labelTitulo = new JLabel("", SwingConstants.LEFT);
		
		gbc = new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);
		
		add(labelTitulo, gbc);

		botonAceptar = new JButton("Agregar Proceso");
		botonAceptar.addActionListener(listener);
		gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 0, 5, 5), 0, 0);
		add(botonAceptar, gbc);
		
	}
}
