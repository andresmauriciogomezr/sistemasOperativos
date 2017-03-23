package main;

import java.util.ArrayList;

public class Proceso {
	private Estado estadoActual;
	private String identificador;
	private Proceso procesoPadre;
	private ArrayList<Proceso> procesosHijos;
	private int prioridad;
	
	public Proceso(String identificador, int prioridad, Proceso procesoPadre){
		this.estadoActual = Estado.listo;
		this.identificador = identificador;
		this.procesoPadre = procesoPadre;
		this.procesosHijos = new ArrayList<Proceso>();
		this.prioridad = prioridad;
	}
	
}
