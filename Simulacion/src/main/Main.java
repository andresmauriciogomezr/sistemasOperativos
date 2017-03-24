package main;

import GUI.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		System.out.println(Estado.bloqueado);
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}

}
