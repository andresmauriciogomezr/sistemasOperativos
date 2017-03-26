package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

public class Procesador {
	
        public static int TIEMPO_PROCESAMIENTO = 5;
        public static int TIEMPO_BLOQUEO = 3;
        
        private int count = 0;
	private ArrayList<Proceso> procesosListos;
	private ArrayList<Proceso> procesosBloqueados;
        private Proceso procesoEjecucion;
	
	public Procesador(){
		this.procesosListos = new ArrayList<Proceso>();
		this.procesosBloqueados = new ArrayList<Proceso>();
	}
        
        public void ordernarPorPrioridad(){
            Collections.sort(procesosListos,procesosListos.get(0).comparatorPrioridad.reversed());
        }
	
	public void agregarProceso(String identificador, int prioridad, int tiempoEjecucion){
		Proceso proceso = new Proceso(identificador, prioridad , tiempoEjecucion, null);
		this.procesosListos.add(proceso);
                ordernarPorPrioridad();
		//JOptionPane.showMessageDialog(null, "Proceso " + proceso.getIdentificador()+ " a�adido satisfactoriamente");
	}
        
        public void procesar(){
            if (procesosListos.isEmpty() == false) { 
                ejecutarProceso();
            } else if (procesosBloqueados.isEmpty() == false) {
                bloquearProceso();
            }
        }
        
        public void ejecutarProceso(){
            if (procesoEjecucion == null) {
                despacharProceso();
            } else if (count>TIEMPO_PROCESAMIENTO){
                expirarTiempo();
                despacharProceso();
                count = 0;
            } else {
                count++;
                procesoEjecucion.setTiempoFaltante();
            }
        }
        
        public void despacharProceso(){
            if (procesoEjecucion == null && procesosListos.get(0) != null){
                procesoEjecucion = procesosListos.get(0);
                procesosListos.remove(0);
                for (Iterator<Proceso> iterator = procesosListos.iterator(); iterator.hasNext();) {
                    Proceso next = iterator.next();
                    System.out.println("Proceso: " + next.getIdentificador() + " " + next.getEstadoActual() );
                }
                procesoEjecucion.setEstadoActual(Estado.enEjecucion);
            }
        }
        
        public void expirarTiempo(){
            procesoEjecucion.setEstadoActual(Estado.bloqueado);
            procesosBloqueados.add(procesoEjecucion);
            procesoEjecucion = null;
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
}
