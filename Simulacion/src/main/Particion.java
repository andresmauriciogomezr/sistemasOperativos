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
    private int total;
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
    
    
    boolean esperarSiguienteIteracion = false; // No se procesa durante esa iteración

    public Particion(int tamanio) {
        this.tamanio = tamanio;
        this.procesos = new ArrayList<>();
        this.index = 0;
        this.total = 0;
    }
    
    public Particion(int tamanio, int index) {
        this.tamanio = tamanio;
        this.index= index;
        this.procesos = new ArrayList<>();
        this.procesosProcesados = new ArrayList<>();
        this.procesosNoProcesados = new ArrayList<>();
        this.procesoProcesando = null;
        this.procesosEjecutados = new ArrayList<>();
        this.procesosListos = new ArrayList<>();
        this.tiempo = 0;
        
        
        this.procesosDespachados = new ArrayList<>();
        this.procesosExpirados = new ArrayList<>();
        this.procesosTerminados = new ArrayList<>();
    }
    
    public void empezar(){ // Creo que no sirve
        this.procesosDespachados = new ArrayList<>();
        this.procesosListos = new ArrayList<>();
        this.procesosEjecutados = new ArrayList<>();
        this.procesosExpirados = new ArrayList<>();
        this.procesosTerminados = new ArrayList<>();
    }

    public int getTamanio() {
        return tamanio;
    }

    public ArrayList<Proceso> getProcesses() {
        return procesos;
    }
    
    public void addProcess(Proceso process){
        this.procesos.add(process);
//        this.total += process.getTiempoEjecucion();
//        this.tamanio -= process.getTamanio();
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
    
    public void agregarTerminado(Proceso proceso){
        this.procesosTerminados.add(proceso.getIdentificador());
        this.tamanio += proceso.getTamanio();
    }
    
    public boolean procesosProcesados(){
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

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	Comparator<Particion> comparatorTotal = new Comparator<Particion>() {
		
		@Override
		public int compare(Particion p0, Particion p1) {
			// TODO Auto-generated method stub
			return p0.total - p1.total;
		}
	};
    
    
    
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
