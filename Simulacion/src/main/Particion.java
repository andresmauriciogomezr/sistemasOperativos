/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Particion {

    private int tamanio;
    private int index; // Index en la lista de particiones
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
    }
    
    public void empezar(){
        this.procesosDespachados = new ArrayList<>();
        this.procesosNoProcesados = new ArrayList<>();
        this.procesosProcesados = new ArrayList<>();
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

    
    
    
}
