package main;

import java.util.ArrayList;

public class Proceso {
	private Estado estadoActual;
	private String identificador;
	private Proceso procesoPadre;
	private ArrayList<Proceso> procesosHijos;
	private int prioridad;
	private int tiempoEjecucion;
        private int tiempoFaltante;
        private boolean esBloqueado; 
	
	public Proceso(String identificador, int prioridad, int tiempoEjecucion, Proceso procesoPadre, boolean esBloqueado){
		this.estadoActual = Estado.listo;
		this.identificador = identificador;
		this.procesoPadre = procesoPadre;
		this.procesosHijos = new ArrayList<Proceso>();
		this.prioridad = prioridad;
		this.tiempoEjecucion = tiempoEjecucion;
                this.tiempoFaltante = tiempoEjecucion;
                this.esBloqueado = esBloqueado;
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
	
    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public int getTiempoRestante() {
        return tiempoFaltante;
    }

    public void setTiempoRestante() {
        this.tiempoFaltante--;
    }

    public boolean esBloqueado() {
        return esBloqueado;
    }       
}
