package main;

import java.util.ArrayList;

public class Procesador {
	
	ArrayList<Proceso> procesosListos;
	ArrayList<Proceso> procesosBloqueados;
	
	public Procesador(){
		this.procesosListos = new ArrayList<Proceso>();
		this.procesosBloqueados = new ArrayList<Proceso>();
	}
	
	public void agregarProceso(String nombreProceso){
		Proceso proceso = new Proceso("proceso1", 5, null);
		this.procesosListos.add(proceso);
	}
	
}
