package main;

import java.util.ArrayList;

public class Proceso {
	private Estado estadoActual;
	private String identificador;
	private Proceso procesoPadre;
	private ArrayList<Proceso> procesosHijos;
	private int prioridad;
	private int tiempoEjecucion;
	
	public Proceso(String identificador, int prioridad, int tiempoEjecucion, Proceso procesoPadre){
		this.estadoActual = Estado.listo;
		this.identificador = identificador;
		this.procesoPadre = procesoPadre;
		this.procesosHijos = new ArrayList<Proceso>();
		this.prioridad = prioridad;
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Proceso getProcesoPadre() {
		return procesoPadre;
	}

	public void setProcesoPadre(Proceso procesoPadre) {
		this.procesoPadre = procesoPadre;
	}

	public ArrayList<Proceso> getProcesosHijos() {
		return procesosHijos;
	}

	public void setProcesosHijos(ArrayList<Proceso> procesosHijos) {
		this.procesosHijos = procesosHijos;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	
	
}
