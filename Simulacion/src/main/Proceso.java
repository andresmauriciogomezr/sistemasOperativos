package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Proceso {
	private Estado estadoActual;
	private String identificador;
	private Proceso procesoPadre;
	private ArrayList<Proceso> procesosHijos;
	private int prioridad;
	private int tiempoEjecucion;
	private int tiempoFaltante;
	private int tiempoBloqueado;
	private String transicion;

	public Proceso(String identificador, int prioridad, int tiempoEjecucion, Proceso procesoPadre, boolean bloqueado){
		this.transicion = "";
		this.estadoActual = Estado.listo;
		this.identificador = identificador;
		this.procesoPadre = procesoPadre;
		this.procesosHijos = new ArrayList<Proceso>();
		this.prioridad = prioridad;
		this.tiempoEjecucion = tiempoEjecucion;
		this.tiempoFaltante = tiempoEjecucion;
		this.tiempoBloqueado = 0;
		
		if (bloqueado) { 
			this.estadoActual = Estado.bloqueado;
		}
	}

	public Estado getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(Estado estadoActual) {
		this.transicion = "De " + this.estadoActual + " a " + estadoActual;
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

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public int getTiempoFaltante() {
		return tiempoFaltante;
	}

	public void setTiempoFaltante() {
		this.tiempoFaltante--;
	}

	public void adicionarTiempoBloqueado(){
		this.tiempoBloqueado++;
	}

	public void setTiempoBloqueado(int tiempoBloqueado) {
		this.tiempoBloqueado = tiempoBloqueado;
	}

	public int getTiempoBloqueado() {
		return tiempoBloqueado;
	}



	public String getTransicion() {
		return transicion;
	}

	public void setTransicion(String transicion) {
		this.transicion = transicion;
	}



	Comparator<Proceso> comparatorPrioridad = new Comparator<Proceso>() {

		@Override
		public int compare(Proceso o1, Proceso o2) {
			return o1.prioridad-o2.prioridad;
		}
	};
}
