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

    private ArrayList<Proceso> procesosListos;
    private ArrayList<InformacionTransicion> procesosDespachados;
    private ArrayList<InformacionTransicion> procesosExpirados;
    private ArrayList<InformacionTransicion> procesosBloqueados;
    private ArrayList<InformacionTransicion> procesosSuspendidosBloqueados;
    private ArrayList<InformacionTransicion> procesosSuspendidosListos;
    private ArrayList<String> procesosDestruidos;
    private ArrayList<InformacionTransicion> procesosComunicados;
    private ArrayList<String> procesosTerminados;
    private ArrayList<Proceso> procesosCargados;
    private ArrayList<String> listaEjecutados;
    private ArrayList<Proceso> listaComunicaciones;

    private int count;
    boolean procesando; // Determina si existen procesos listos para ejecutarse

     public Procesador() {
        this.procesando = false;
        this.procesosListos = new ArrayList<>();
        this.procesosDespachados = new ArrayList<>();
        this.procesosExpirados = new ArrayList<>();
        this.procesosBloqueados = new ArrayList<>();
        this.procesosSuspendidosListos = new ArrayList<>();
        this.procesosSuspendidosBloqueados= new ArrayList<>();
        this.procesosDestruidos = new ArrayList<>();
        this.procesosComunicados = new ArrayList<>();
        this.procesosTerminados = new ArrayList<>();
        this.procesosCargados = new ArrayList<>();
        this.listaEjecutados = new ArrayList<>();
        this.listaComunicaciones = new ArrayList<>();
        this.count = 0;
    }
    
    public void ordernarComunPorPrioridad() {
        Collections.sort(procesosCargados, procesosCargados.get(0).comparatorPrioridad);
    }

    public void agregarProceso(String identificador, int prioridad, int tiempoEjecucion, boolean bloqueado, boolean suspendido, boolean destruido, String seComunica, String cambioPrioridad) {
        Proceso proceso = new Proceso(identificador, prioridad, tiempoEjecucion, bloqueado, suspendido, destruido, seComunica, cambioPrioridad);
        procesosCargados.add(proceso);
    }

    public void procesar() {
        this.ordernarComunPorPrioridad();
        while (procesosCargados.isEmpty() == false) {
            for (this.count = 0; count < procesosCargados.size(); count++) {
                Proceso proceso = procesosCargados.get(count);
                procesosListos.add(proceso);
                if (!proceso.isDestruido()) { // No se destruye
                    this.ejecutarProceso(proceso);
                } else { // Se destruye                	
                	if (proceso.isSuspendido() || proceso.isBloqueado()) { // Se debe procesar antes de destruirse
                		this.ejecutarProceso(proceso);
					}                	
                    destruirProceso(proceso);
                    removerProceso(proceso);
                }
            }
        }
    }
    
    
    public void ejecutarProceso(Proceso proceso){ 
    	this.despacharProceso(proceso); // Se agrega a la lista despachados
        //se ejecuta
        int tiempo = proceso.getTiempoEjecucion();
        
        if (tiempo > 5) { // debe expirarse y luego volver a procesar        	
            proceso.setTiempoEjecucion(tiempo - 5);
            if (proceso.isSuspendido()) {
                this.suspenderProceso(proceso);
                this.bloquearProceso(proceso);
            } else if (proceso.isBloqueado()) {
                this.bloquearProceso(proceso);
            } else {
                this.expirarTiempo(proceso);
            }
        } else {
            proceso.setTiempoEjecucion(0);
            terminarProceso(proceso);
        }
        if(!proceso.getSeComunica().equals("")){
        	this.listaComunicaciones.add(proceso);
        	//proceso.setSeComunica("");
        }
        
        proceso.setTransicion(Estado.enEjecucion);
        this.listaEjecutados.add(proceso.getIdentificador() + " Termio ejecucio con tiempo de : " + proceso.getTiempoEjecucion());
    }

    public void removerProceso(Proceso proceso) {
        procesosCargados.remove(count);
        count--;
    }

    public void cambiarPrioridades() {
        for (int i = 0; i < this.procesosCargados.size(); i++) {
            Proceso proceso = this.procesosCargados.get(i);
            if (!proceso.getCambioPrioridad().equals("") && this.validarNumeros(proceso.getCambioPrioridad())) {
                int nuevaPrioridad = Integer.parseInt(proceso.getCambioPrioridad());
                if (!this.existePrioridad(nuevaPrioridad)) {
                    proceso.setPrioridad(nuevaPrioridad);
                }
            }

        }
    }
   

    public boolean validarNumeros(String cadena) {
        char[] permitios = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < permitios.length; j++) {
                if (cadena.charAt(i) == permitios[j]) {
                    break;
                } else if (j == permitios.length) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean existePrioridad(int prioridad) { // Verifica si existe una prioridad
        ArrayList<Proceso> procesos = this.procesosCargados;
        for (int i = 0; i < procesos.size(); i++) {
            if (procesos.get(i).getPrioridad() == prioridad) {
                return true;
            }
        }
        return false;
    }

    public void terminarProceso(Proceso proceso) {
        procesosTerminados.add(proceso.getIdentificador());
        this.removerProceso(proceso);
    }

    public void despacharProceso(Proceso proceso) {
        this.procesosDespachados.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void expirarTiempo(Proceso proceso) {
        proceso.setTransicion(Estado.listo);
        this.procesosExpirados.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void suspenderProceso(Proceso proceso) {
        proceso.setTransicion(Estado.suspendido);
        this.procesosSuspendidosListos.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void bloquearProceso(Proceso proceso) {
        proceso.setTransicion(Estado.bloqueado);
        this.procesosBloqueados.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void destruirProceso(Proceso proceso) {
        proceso.setTransicion(Estado.destruido);
        this.procesosDestruidos.add(proceso.getIdentificador());
    }


    public boolean existeProceso(String nombre) {
        for (Iterator<Proceso> iterator = procesosCargados.iterator(); iterator.hasNext();) {
            Proceso proceso = iterator.next();
            if (proceso.getIdentificador().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProcesando() {
        return procesando;
    }

    public ArrayList<Proceso> getProcesosListos() {
        return procesosListos;
    }

    public ArrayList<Proceso> getProcesosCargados() {
        return procesosCargados;
    }

    public ArrayList<String> getListaEjecutados() {
        return listaEjecutados;
    }

    public ArrayList<InformacionTransicion> getProcesosDespachados() {
        return procesosDespachados;
    }

    public ArrayList<InformacionTransicion> getProcesosExpirados() {
        return procesosExpirados;
    }

    public ArrayList<InformacionTransicion> getProcesosBloqueados() {
        return procesosBloqueados;
    }

    public ArrayList<InformacionTransicion> getProcesosSuspendidos() {
        return procesosSuspendidosListos;
    }

    public ArrayList<String> getProcesosDestruidos() {
        return procesosDestruidos;
    }

    public ArrayList<InformacionTransicion> getProcesosComunicados() {
        return procesosComunicados;
    }

    public ArrayList<String> getProcesosTerminados() {
        return procesosTerminados;
    }

	public ArrayList<Proceso> getListaComunicaciones() {
		return listaComunicaciones;
	}

	public void setListaComunicaciones(ArrayList<Proceso> listaComunicaciones) {
		this.listaComunicaciones = listaComunicaciones;
	}

    
    
}
	
