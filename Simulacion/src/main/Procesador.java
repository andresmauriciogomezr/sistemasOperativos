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

    private ArrayList<String> procesosListos;
    private ArrayList<InformacionTransicion> procesosDespachados;
    private ArrayList<InformacionTransicion> procesosExpirados;
    private ArrayList<InformacionTransicion> procesosBloqueados;
    private ArrayList<InformacionTransicion> procesosEsperaEjeABloq;
    private ArrayList<InformacionTransicion> procesosTerminaBloqALis;
    private ArrayList<InformacionTransicion> procesosSuspenderBloqASuspBloq;
    private ArrayList<InformacionTransicion> procesosReanudarSBloqABloq;
    private ArrayList<String> listaSuspendidosBloqueados;
    private ArrayList<InformacionTransicion> procesosSuspendidosListos;
    private ArrayList<InformacionTransicion> procesosSuspenderEjeASuspL;
    private ArrayList<InformacionTransicion> procesosSuspenderListosASuspL;
    private ArrayList<InformacionTransicion> procesosReanudarSuspLAListo;
    private ArrayList<InformacionTransicion> procesosTerminaSuspBASuspL;
    private ArrayList<String> procesosDestruidos;
    private ArrayList<InformacionTransicion> procesosComunicados;
    private ArrayList<String> procesosTerminados;
    private ArrayList<Proceso> procesosCargados;
    private ArrayList<String> listaEjecutados;
    private ArrayList<Proceso> listaComunicaciones;
    private ArrayList<Particion> particiones;
    
    private ArrayList<String> listaProcesados;
    private ArrayList<String> listaNoProcesados;

    private int count; // Indica en que posicion de las listas de procesos se encuentra ejecutando
    boolean procesando; // Determina si existen procesos listos para ejecutarse

    public Procesador() {
        this.procesosListos = new ArrayList<>();
        this.procesosDespachados = new ArrayList<>();
        this.procesosExpirados = new ArrayList<>();
        this.procesosTerminados = new ArrayList<>();
        this.listaEjecutados = new ArrayList<>();
        this.particiones = new ArrayList<>();
        this.count = 0;
        this.procesando = true;
        this.procesosCargados = new ArrayList<>();
        
        this.listaProcesados = new ArrayList<>();
        this.listaNoProcesados = new ArrayList<>();
    }

    public void agregarParticion(int tamanio) {
        Particion particion = new Particion(tamanio);
        this.particiones.add(particion);
    }
    //								nombre				tiempo				tamano			indexParticion
    public void agregarProceso(String identificador, int tiempoEjecucion, int tamanio, int particion) {
        Proceso process = new Proceso(identificador, tiempoEjecucion, tamanio, particion);
        this.procesosCargados.add(process);
        particiones.get(particion).addProcess(process);
    }

    public void procesar() {
        verificarProcesando();
        empezar(); // Inicializa las listas
        System.out.println(procesando);
        while (procesando) {
            for (int i = 0; i < particiones.size(); i++) {
                Particion particion = particiones.get(i);
                if (particion.obternerTotalProcesos() > 0) {
                    Proceso proceso = particion.obtenerProceso(0);
                    if (proceso.getSize() <= particion.getTamanio()) {
                        particion.agregarProcesado(proceso.getIdentificador() + "-- tiempo : " + proceso.getTiempoEjecucion());
                        ejecutarProceso(proceso, i);
                    } else {
                        listarProceso(proceso, i);
                        particion.agregarNoProcesado(proceso.getIdentificador());
                        removerProceso(proceso, i);
                    }
                }
            }
            verificarProcesando();
            count++;
        }
    }

    public void verificarProcesando() {
        boolean procesar = false;
        for (Iterator<Particion> iterator = particiones.iterator(); iterator.hasNext();) {
            Particion next = iterator.next();
            procesar = procesar || !next.procesosProcesados();
        }
        this.procesando = procesar;
    }

    public void empezar() {
        for (Iterator<Particion> iterator = particiones.iterator(); iterator.hasNext();) {
            Particion next = iterator.next();
            if (!next.procesosProcesados()) {
                next.empezar();
            }
        }
    }

    public void ejecutarProceso(Proceso proceso, int posicion) {
        //se ejecuta
        int tiempo = proceso.getTiempoEjecucion();
        this.listarProceso(proceso, posicion); //Se agrega a las listas de listos
        this.despacharProceso(proceso, posicion); // Se agrega a la listas despachados
        if (tiempo > 5) { // debe expirarse y luego volver a procesar        	
            proceso.setTiempoEjecucion(tiempo - 5);
            expirarTiempo(proceso, posicion);
        } else {
            proceso.setTiempoEjecucion(0);
            terminarProceso(proceso, posicion);
        }
        this.listaEjecutados.add(proceso.getIdentificador() + " - Tiempo: " + proceso.getTiempoEjecucion());
        particiones.get(posicion).agregarEjecutado(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));

    }

    public void listarProceso(Proceso proceso, int posicion) {
        procesosListos.add(proceso.getIdentificador() + " - Tiempo: " + proceso.getTiempoEjecucion());
        particiones.get(posicion).agregarListo(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void removerProceso(Proceso proceso, int posicion) {
        particiones.get(posicion).removerProceso(proceso);
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

    public void terminarProceso(Proceso proceso, int posicion) {
        procesosTerminados.add(proceso.getIdentificador());
        particiones.get(posicion).agregarTerminado(proceso.getIdentificador());
        this.removerProceso(proceso, posicion);
    }
//
//    public void suspenderBloqueado(Proceso proceso) {
//        this.listaSuspendidosBloqueados.add(proceso.getIdentificador() + " -Tiempo : " + proceso.getTiempoEjecucion());
//        this.procesosSuspenderBloqASuspBloq.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        this.bloquearProceso(proceso);
//        if (proceso.getSuspedidoBloqueado().equals("Suspendido/Listo")) {
//            this.terminarESuspendidoB(proceso);
//            this.suspenderListo(proceso);
//        } else if (proceso.getSuspedidoBloqueado().equals("Bloqueado")) {
//            this.reanudarSuspendidoBloqueado(proceso);
//        }
//    }
//
//    public void terminarESuspendidoB(Proceso proceso) {
//        proceso.setTransicion(Estado.suspendidoListo);
//        this.procesosTerminaSuspBASuspL.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//    }
//
//    public void reanudarSuspendidoBloqueado(Proceso proceso) {
//        this.procesosReanudarSBloqABloq.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        this.procesosBloqueados.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        this.terminarEBloqueados(proceso);
//    }

    public void despacharProceso(Proceso proceso, int posicion) {
        InformacionTransicion it = new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion());
        this.procesosDespachados.add(it);
        this.particiones.get(posicion).agregarDespachado(it);
    }

    public void expirarTiempo(Proceso proceso, int posicion) {
        proceso.setTransicion(Estado.listo);
        InformacionTransicion it = new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion());
        this.procesosExpirados.add(it);
        this.particiones.get(posicion).agregarExpirado(it);
    }
//
//    public void suspenderListo(Proceso proceso) {
//        this.procesosSuspendidosListos.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        if (proceso.getSuspedidoListo().equals("Listo")) {
//            System.out.println("Entro A");
//            this.expirarTiempo(proceso);
//            this.procesosListos.add(proceso.getIdentificador() + " - Tiempo : " + proceso.getTiempoEjecucion());
//            this.suspenderListoDeListo(proceso);
//        } else if (proceso.getSuspedidoListo().equals("Ejecucion")) {
//            System.out.println("Entro B");
//            this.suspenderListoDeEjecucion(proceso);
//        }
//        this.reanudarSuspendidoListo(proceso);
//    }
//
//    public void reanudarSuspendidoListo(Proceso proceso) {
//        proceso.setTransicion(Estado.listo);
//        this.procesosReanudarSuspLAListo.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//
//    }
//
//    public void suspenderListoDeEjecucion(Proceso proceso) {
//        proceso.setTransicion(Estado.suspendidoListo);
//        this.procesosSuspenderEjeASuspL.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//    }
//
//    public void terminarEBloqueados(Proceso proceso) {
//        proceso.setTransicion(Estado.listo);
//        this.procesosTerminaBloqALis.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//    }
//
//    public void suspenderListoDeListo(Proceso proceso) {
//        proceso.setTransicion(Estado.suspendidoListo);
//        this.procesosSuspenderListosASuspL.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//    }
//
//    public void bloquearProceso(Proceso proceso) {
//        proceso.setTransicion(Estado.bloqueado);
//        this.procesosEsperaEjeABloq.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        this.procesosBloqueados.add(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
//        if (proceso.getSuspedidoBloqueado().equals("No")) {
//            this.terminarEBloqueados(proceso);
//        }
//    }
//
//    public void destruirProceso(Proceso proceso) {
//        proceso.setTransicion(Estado.destruido);
//        this.procesosDestruidos.add(proceso.getIdentificador());
//    }

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

    public ArrayList<String> getProcesosListos() {
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

    public ArrayList<String> getListaSuspendidosBloqueados() {
        return listaSuspendidosBloqueados;
    }

    public void setListaSuspendidosBloqueados(
            ArrayList<String> listaSuspendidosBloqueados) {
        this.listaSuspendidosBloqueados = listaSuspendidosBloqueados;
    }

    public ArrayList<InformacionTransicion> getProcesosEsperaEjeABloq() {
        return procesosEsperaEjeABloq;
    }

    public ArrayList<InformacionTransicion> getProcesosTerminaBloqALis() {
        return procesosTerminaBloqALis;
    }

    public ArrayList<InformacionTransicion> getProcesosSuspenderBloqASuspBloq() {
        return procesosSuspenderBloqASuspBloq;
    }

    public ArrayList<InformacionTransicion> getProcesosReanudarSBloqABloq() {
        return procesosReanudarSBloqABloq;
    }

    public ArrayList<InformacionTransicion> getProcesosSuspendidosListos() {
        return procesosSuspendidosListos;
    }

    public ArrayList<InformacionTransicion> getProcesosSuspenderEjeASuspL() {
        return procesosSuspenderEjeASuspL;
    }

    public ArrayList<InformacionTransicion> getProcesosSuspenderListosASuspL() {
        return procesosSuspenderListosASuspL;
    }

    public ArrayList<InformacionTransicion> getProcesosReanudarSuspLAListo() {
        return procesosReanudarSuspLAListo;
    }

    public ArrayList<InformacionTransicion> getProcesosTerminaSuspBASuspL() {
        return procesosTerminaSuspBASuspL;
    }

	public ArrayList<Particion> getParticiones() {
		return particiones;
	}

	public void setParticiones(ArrayList<Particion> particiones) {
		this.particiones = particiones;
	}

	public ArrayList<String> getListaNoProcesados() {
		return listaNoProcesados;
	}

	public void setListaNoProcesados(ArrayList<String> listaNoProcesados) {
		this.listaNoProcesados = listaNoProcesados;
	}

	public void setListaEjecutados(ArrayList<String> listaEjecutados) {
		this.listaEjecutados = listaEjecutados;
	}

	public ArrayList<String> getListaProcesados() {
		return listaProcesados;
	}

	public void setListaProcesados(ArrayList<String> listaProcesados) {
		this.listaProcesados = listaProcesados;
	}
    
    
}
