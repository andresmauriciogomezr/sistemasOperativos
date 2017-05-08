/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Usuario
 */
public class Particion {

    private int tamanio;
    private int index; // Index en la lista de particiones
    private int tiempo; // Tiempo en total de procesamiento de la particion
    private Proceso procesoProcesando;
    private ArrayList<Proceso> procesos;
    private ArrayList<InformacionTransicion> procesosListos;
    private ArrayList<InformacionTransicion> procesosEjecutados;
    private ArrayList<InformacionTransicion> procesosExpirados;
    private ArrayList<InformacionTransicion> procesosDespachados;
    private ArrayList<String> procesosProcesados;
    private ArrayList<String> procesosNoProcesados;
    private ArrayList<String> procesosTerminados;

    public Particion(int tamanio) {
        this.tamanio = tamanio;
        this.procesos = new ArrayList<>();
        this.index = 0;
    }
    
    public Particion(int tamanio, int index) {
        this.tamanio = tamanio;
        this.index= index;
        this.procesos = new ArrayList<>();
        this.procesosProcesados = new ArrayList<>();
        this.procesosNoProcesados = new ArrayList<>();
        this.procesoProcesando = null;
        this.tiempo = 0;
    }
    
    public void empezar(){
        if (procesosDespachados == null) this.procesosDespachados = new ArrayList<>();
        if (procesosListos == null) this.procesosListos = new ArrayList<>();
        if (procesosEjecutados == null) this.procesosEjecutados = new ArrayList<>();
        if (procesosExpirados == null) this.procesosExpirados = new ArrayList<>();
        if (procesosTerminados == null) this.procesosTerminados = new ArrayList<>();
    }

    public int getTamanio() {
        return tamanio;
    }

    public ArrayList<Proceso> getProcesses() {
        return procesos;
    }
    
    public void addProcess(Proceso process){
        this.procesos.add(process);
        this.tiempo += process.getTiempoEjecucion();
    }
    
    public void agregarProcesado(String proceso){
        this.procesosProcesados.add(proceso);
    }
    
    public void agregarNoProcesado(String proceso){
        this.procesosNoProcesados.add(proceso);
    }
    
    public void agregarListo(InformacionTransicion it){
        this.procesosListos.add(it);
    }
    
    public void agregarDespachado(InformacionTransicion it){
        this.procesosDespachados.add(it);
    }
    
    public void agregarEjecutado(InformacionTransicion it){
        this.procesosEjecutados.add(it);
    }
    
    public void agregarExpirado(InformacionTransicion it){
        this.procesosExpirados.add(it);
    }
    
    public void agregarTerminado(String  proceso){
        this.procesosTerminados.add(proceso);
    }
    
    public boolean procesosProcesados(){
    	System.out.println(this.procesos.size());
        return this.procesos.isEmpty();
    }
    
    public void removerProceso(Proceso proceso){
       this.procesos.remove(proceso);
    }
    
    public Proceso obtenerProceso(int posicion){
        return procesos.get(posicion);
    }
    
    public int obternerTotalProcesos(){
        return procesos.size();
    }

    public boolean estaEnProcesados(Proceso proceso){
        return procesosProcesados.contains(proceso.getIdentificador());
    }
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(ArrayList<Proceso> procesos) {
		this.procesos = procesos;
	}

	public ArrayList<InformacionTransicion> getProcesosListos() {
		return procesosListos;
	}

	public void setProcesosListos(ArrayList<InformacionTransicion> procesosListos) {
		this.procesosListos = procesosListos;
	}

	public ArrayList<InformacionTransicion> getProcesosEjecutados() {
		return procesosEjecutados;
	}

	public void setProcesosEjecutados(
			ArrayList<InformacionTransicion> procesosEjecutados) {
		this.procesosEjecutados = procesosEjecutados;
	}

	public ArrayList<InformacionTransicion> getProcesosExpirados() {
		return procesosExpirados;
	}

	public void setProcesosExpirados(
			ArrayList<InformacionTransicion> procesosExpirados) {
		this.procesosExpirados = procesosExpirados;
	}

	public ArrayList<InformacionTransicion> getProcesosDespachados() {
		return procesosDespachados;
	}

	public void setProcesosDespachados(
			ArrayList<InformacionTransicion> procesosDespachados) {
		this.procesosDespachados = procesosDespachados;
	}

	public ArrayList<String> getProcesosProcesados() {
		return procesosProcesados;
	}

	public void setProcesosProcesados(ArrayList<String> procesosProcesados) {
		this.procesosProcesados = procesosProcesados;
	}

	public ArrayList<String> getProcesosNoProcesados() {
		return procesosNoProcesados;
	}

	public void setProcesosNoProcesados(ArrayList<String> procesosNoProcesados) {
		this.procesosNoProcesados = procesosNoProcesados;
	}

	public ArrayList<String> getProcesosTerminados() {
		return procesosTerminados;
	}

	public void setProcesosTerminados(ArrayList<String> procesosTerminados) {
		this.procesosTerminados = procesosTerminados;
	}

    Comparator<Particion> compareTiempo = new Comparator<Particion>() {

        @Override
        public int compare(Particion o1, Particion o2) {
            return o1.tiempo - o2.tiempo;
        }
    };

    public void setProcesoProcesando(Proceso procesoProcesando) {
        this.procesoProcesando = procesoProcesando;
    }

    public Proceso getProcesoProcesando() {
        return procesoProcesando;
    }
}
