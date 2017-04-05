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
    private ArrayList<InformacionTransicion> procesosSuspendidos;
    private ArrayList<String> procesosDestruidos;
    private ArrayList<InformacionTransicion> procesosComunicados;
    private ArrayList<String> procesosTerminados;
    private ArrayList<Proceso> procesosCargados;
    private ArrayList<String> listaEjecutados;

    private int count;
    boolean procesando; // Determina si existen procesos listos para ejecutarse

    public Procesador() {
        this.procesando = false;
        this.procesosListos = new ArrayList<>();
        this.procesosDespachados = new ArrayList<>();
        this.procesosExpirados = new ArrayList<>();
        this.procesosBloqueados = new ArrayList<>();
        this.procesosSuspendidos = new ArrayList<>();
        this.procesosDestruidos = new ArrayList<>();
        this.procesosComunicados = new ArrayList<>();
        this.procesosTerminados = new ArrayList<>();
        this.procesosCargados = new ArrayList<>();
        this.listaEjecutados = new ArrayList<>();
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
        for (this.count = 0; count < procesosCargados.size(); count++) {
            Proceso proceso = procesosCargados.get(count);
            procesosListos.add(proceso);
            if (!proceso.isDestruido()) {
                this.despacharProceso(proceso);
                //se ejecuta
                this.ejecutarProceso(proceso);
                if (proceso.isSuspendido()) {
                    this.suspenderProceso(proceso);
                    this.bloquearProceso(proceso);
                    this.desbloquear(proceso);
                } else if (proceso.isBloqueado()) {
                    this.bloquearProceso(proceso);
                    this.desbloquear(proceso);
                } else {
                    this.expirarTiempo(proceso);
                }

            } else { // Se destruye
                destruirProceso(proceso);
                removerProceso(proceso);
            }
        }

    }

    public void removerProceso(Proceso proceso) {
        procesosCargados.remove(count);
        count--;
    }

    public void cambiarPrioridades() {
        for (int i = 0; i < this.procesosCargados.size(); i++) {
            Proceso proceso = this.procesosCargados.get(i);
            if (proceso.getCambioPrioridad().isEmpty() == false) {
                int nuevaPrioridad = Integer.parseInt(proceso.getCambioPrioridad());
                if (!this.existePrioridad(nuevaPrioridad)) {
                    proceso.setPrioridad(nuevaPrioridad);
                }
            }
        }
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

    public void desbloquear(Proceso proceso) {
        this.procesosListos.add(proceso);
    }

    public void ejecutarProceso(Proceso proceso) {
        int tiempo = proceso.getTiempoEjecucion();
        if (tiempo >= 5) {
            proceso.setTiempoEjecucion(tiempo - 5);
        } else {
            proceso.setTiempoEjecucion(0);
            terminarProceso(proceso);
        }
        proceso.setTransicion(Estado.enEjecucion);
        this.listaEjecutados.add(proceso.getIdentificador() + " Termio ejecucio con tiempo de : " + proceso.getTiempoEjecucion());
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
        this.procesosListos.add(proceso);
    }

    public void suspenderProceso(Proceso proceso) {
        proceso.setTransicion(Estado.suspendido);
        this.procesosSuspendidos.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
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
        return procesosSuspendidos;
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

}
