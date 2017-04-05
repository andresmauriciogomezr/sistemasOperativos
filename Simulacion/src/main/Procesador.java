package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import GUI.VentanaPrincipal;

public class Procesador {

	public static int TIEMPO_PROCESAMIENTO = 5;
	public static int TIEMPO_BLOQUEO = 3;

	private int count = 0;
	private ArrayList<Proceso> procesosListos;
	private ArrayList<Proceso> procesosBloqueados;
	private ArrayList<Proceso> procesosSuspendidos;
	private ArrayList<Proceso> procesosDestruidos;
	private ArrayList<Proceso> procesosComunicados;
	private ArrayList<Proceso> procesosTerminados;
	private ArrayList<Proceso> ListaComun;
	private Proceso procesoEjecucion;
	private VentanaPrincipal ventana;
	boolean procesando; // Determina si existen procesos listos para ejecutarse

	public Procesador(VentanaPrincipal ventana){
		this.procesando = false;
		this.procesosListos = new ArrayList<Proceso>();
		this.procesosBloqueados = new ArrayList<Proceso>();
		this.procesosSuspendidos = new ArrayList<Proceso>();
		this.procesosDestruidos = new ArrayList<Proceso>();
		this.procesosComunicados = new ArrayList<Proceso>();
		this.procesosTerminados = new ArrayList<Proceso>();
		this.ListaComun = new ArrayList<Proceso>();
		this.ventana = ventana;
	}

	public void ordernarPorPrioridad(){
		Collections.sort(procesosListos,procesosListos.get(0).comparatorPrioridad.reversed());
	}
	//								Nombre				priodidad		tiempo 				bloqueo				suspendido		destruido		seComunica
	public void agregarProceso(String identificador, int prioridad, int tiempoEjecucion, boolean bloqueado, boolean suspendido, boolean destruido, boolean seComunica){
		Proceso proceso = new Proceso(identificador, 	prioridad , 	tiempoEjecucion, 	bloqueado, 				suspendido, 	destruido, seComunica);
		ListaComun.add(proceso);
//		if (bloqueado) {
//			this.procesosBloqueados.add(proceso);
//		}else {
//			this.procesosListos.add(proceso);
//			ordernarPorPrioridad();
//		}	
		
	}
	
	
	public void asignar() {
		for (int i = 0; i < ListaComun.size(); i++) {
			Proceso proceso = ListaComun.get(i);
			if (proceso.getEstadoActual() == Estado.bloqueado) {
				procesosBloqueados.add(proceso);
				
			}
			else if (proceso.getEstadoActual() == Estado.suspendido) {
				procesosSuspendidos.add(proceso);
			}
			else if (proceso.getEstadoActual() == Estado.destruido) {
				procesosDestruidos.add(proceso);
			}
			else {
				procesosListos.add(proceso);
			}
			
			if (proceso.SeComunica()) {
				procesosComunicados.add(proceso);
			}
		}		
	}

	public void procesar(){
		if (procesosListos.isEmpty() == false) { // Listos No está vacío
			this.procesando = true;
			ejecutarProceso();
		} else if (procesosBloqueados.isEmpty() == false) {// Listos está vacío y bloqueados no esta vacio
			this.desbloquear();
		}

	}

	public void desbloquear(){
		if (this.procesosBloqueados.size() > 0) {
			for (int i = 0; i < this.procesosBloqueados.size(); i++) {
				this.procesosListos.add(this.procesosBloqueados.get(i));
			}
			this.procesosBloqueados.clear();
		}
		this.ordernarPorPrioridad();
	}

	public boolean tieneBloqueos(){
		return this.procesosBloqueados.size() > 0;
	}

//	public void ejecutarProceso(){        	
//		if (procesoEjecucion == null) {//Creamos un proceso nuevo
//			despacharProceso();
//		} else if (count>=TIEMPO_PROCESAMIENTO){ 
//			expirarTiempo();
//			despacharProceso();
//			count = 0;
//		} else {// Proceso en ejecución
//			if (procesoEjecucion.getTiempoFaltante() == 0) { // Se terminó de ejecutar el proceso
//				this.terminarProceso();
//				return;
//			}
//			count++;
//			procesoEjecucion.setTiempoFaltante();
//		}
//	}
	
	public void ejecutarProceso(){        	
		if (procesoEjecucion == null) {//El proceso actual es nulo .Creamos un proceso nuevo
			despacharProceso();
//		} else if (count>=TIEMPO_PROCESAMIENTO){ 
//			expirarTiempo();
//			despacharProceso();
//			count = 0;
		} else {// Proceso en ejecución
			if (procesoEjecucion.getTiempoFaltante() == 0) { // Se terminó de ejecutar el proceso
				this.terminarProceso();
				return;
			}
//			count++; //Aca se procesa
			procesoEjecucion.setTiempoFaltante();
		}
	}

	public void terminarProceso(){
		procesoEjecucion.setEstadoActual(Estado.terminado);
		this.procesosTerminados.add(procesoEjecucion);
		this.procesoEjecucion = null;
		this.count = 0;
		this.procesando = false;			
	}

	public void despacharProceso(){
		if (procesoEjecucion == null && procesosListos.size() > 0){ // Proceso en ejecución nulo y listos no esta vacio
			procesoEjecucion = procesosListos.get(0);
			procesosListos.remove(0);
			
			this.procesando = true;
			procesoEjecucion.setEstadoActual(Estado.enEjecucion);

		}
	}

	public void expirarTiempo(){
		procesoEjecucion.setEstadoActual(Estado.bloqueado);
		procesosBloqueados.add(procesoEjecucion);
		procesoEjecucion = null;
		this.procesando = false;
	}

	public void bloquearProceso(){
		for (int i = 0; i < procesosBloqueados.size(); i++) {
			Proceso proceso = procesosBloqueados.get(i);
			if (proceso.getTiempoBloqueado()<TIEMPO_PROCESAMIENTO){
				proceso.adicionarTiempoBloqueado();
			} else {
				proceso.setTiempoBloqueado(0);
				proceso.setEstadoActual(Estado.listo);
				procesosListos.add(proceso);
				ordernarPorPrioridad();
				procesosBloqueados.remove(i);
				i = i-1;
			}
		}
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

	public void setProcesoEjecucion(Proceso procesoEjecucion) {
		this.procesoEjecucion = procesoEjecucion;
	}

	public Proceso getProcesoEjecucion() {
		return procesoEjecucion;
	}

	public boolean tieneProcesos(){
		return !procesosListos.isEmpty() || !procesosBloqueados.isEmpty();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isProcesando() {
		return procesando;
	}

	public void setProcesando(boolean procesando) {
		this.procesando = procesando;
	}

	public ArrayList<Proceso> getProcesosTerminados() {
		return procesosTerminados;
	}

	public void setProcesosTerminados(ArrayList<Proceso> procesosTerminados) {
		this.procesosTerminados = procesosTerminados;
	}

	public ArrayList<Proceso> getListaComun() {
		return ListaComun;
	}

	public void setListaComun(ArrayList<Proceso> listaComun) {
		ListaComun = listaComun;
	}

	public ArrayList<Proceso> getProcesosSuspendidos() {
		return procesosSuspendidos;
	}

	public void setProcesosSuspendidos(ArrayList<Proceso> procesosSuspendidos) {
		this.procesosSuspendidos = procesosSuspendidos;
	}

	public ArrayList<Proceso> getProcesosDestruidos() {
		return procesosDestruidos;
	}

	public void setProcesosDestruidos(ArrayList<Proceso> procesosDestruidos) {
		this.procesosDestruidos = procesosDestruidos;
	}

	public ArrayList<Proceso> getProcesosComunicados() {
		return procesosComunicados;
	}

	public void setProcesosComunicados(ArrayList<Proceso> procesosComunicados) {
		this.procesosComunicados = procesosComunicados;
	}

	

}
