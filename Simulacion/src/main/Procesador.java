package main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesador {
	
	ArrayList<Proceso> procesosListos;
	ArrayList<Proceso> procesosBloqueados;
	
	public Procesador(){
		this.procesosListos = new ArrayList<Proceso>();
		this.procesosBloqueados = new ArrayList<Proceso>();
	}
	
	public void agregarProceso(String identificador, int prioridad, int tiempoEjecucion){
		Proceso proceso = new Proceso(identificador, prioridad , tiempoEjecucion, null);
		this.procesosListos.add(proceso);
		//JOptionPane.showMessageDialog(null, "Proceso " + proceso.getIdentificador()+ " añadido satisfactoriamente");
	}

	public ArrayList<Proceso> getProcesosListos() {
		return procesosListos;
	}

	public void setProcesosListos(ArrayList<Proceso> procesosListos) {
		this.procesosListos = procesosListos;
	}

	public ArrayList<Proceso> getProcesosBloqueados() {
		return procesosBloqueados;
	}

	public void setProcesosBloqueados(ArrayList<Proceso> procesosBloqueados) {
		this.procesosBloqueados = procesosBloqueados;
	}
	
	
	
}
