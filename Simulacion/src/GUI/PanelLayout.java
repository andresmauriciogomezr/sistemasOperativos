package GUI;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelLayout extends JPanel{

	private static final long serialVersionUID = 735523904991087453L;

	private JLabel labelTitulo;
	private JLabel icoInfo;
	private Icon icon;
	private JTextField campoTexto;

	private GridBagLayout gridbag;
	private GridBagConstraints gbc;

	public static final String ICON = "/img/icoInfo.png";
	public static final int WIDTH = 400;
	public static final int HEIGHT = 100;
	

	public PanelLayout(String titulo, String ayuda) {

		gridbag = new GridBagLayout();

		setLayout(gridbag);
		setBorder(BorderFactory.createEtchedBorder());
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		

		labelTitulo = new JLabel(titulo, SwingConstants.LEFT);
		
		gbc = new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);
		
		add(labelTitulo, gbc);

		campoTexto = new JTextField(12);
		gbc = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 0, 5, 5), 0, 0);
		add(campoTexto, gbc);

		
		icoInfo = new JLabel(new ImageIcon(getClass().getResource(ICON)));
		icoInfo.setSize(20, 20);
		gbc = new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0);
		
		icon = new ImageIcon(new ImageIcon(getClass().getResource(ICON)).getImage()
				.getScaledInstance(icoInfo.getWidth(), icoInfo.getHeight(), Image.SCALE_DEFAULT));
		icoInfo.setIcon(icon);
		icoInfo.setToolTipText(ayuda);
		add(icoInfo, gbc);
		
		
	}
	
	public String getText(){
		return this.campoTexto.getText();
	}
}
