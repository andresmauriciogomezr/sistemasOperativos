package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import com.sun.istack.internal.FragmentContentHandler;

import GUI.VentanaPrincipal;

public class Procesador {

    public static int TIEMPO_PROCESAMIENTO = 5;

    private ArrayList<InformacionTransicion> procesosDespachados;

    private ArrayList<InformacionTransicion> procesosExpirados;
    private ArrayList<Proceso> procesosCargados;

    // Cosas utilizadas
    private ArrayList<Condensacion> condensaciones;

    private ArrayList<Particion> particiones;
    private ArrayList<Particion> historialParticiones;

    private ArrayList<String> listaProcesados;
    private ArrayList<String> listaNoProcesados;
    private ArrayList<String> listaTerminados;
    private ArrayList<String> listaParticionesTerminadas;

    boolean procesando; // Determina si existen procesos listos para ejecutarse

    int inicioBusquedaParticion;
    int inicioBusquedaProceso;

    int tamanoMemoria;

    int paraPillar = 1;

    public Procesador() {
//        this.procesosListos = new ArrayList<>();
//        this.procesosTerminados = new ArrayList<>();
//        this.listaEjecutados = new ArrayList<>();
        this.condensaciones = new ArrayList<>();
        this.particiones = new ArrayList<>();
        this.procesando = true;
        this.procesosCargados = new ArrayList<>();

        this.listaProcesados = new ArrayList<>();
        this.listaNoProcesados = new ArrayList<>();
        this.listaTerminados = new ArrayList<>();
        this.listaParticionesTerminadas = new ArrayList<>();

        this.inicioBusquedaParticion = 0;
        this.inicioBusquedaProceso = 0;

        this.historialParticiones = new ArrayList<>();

    }

    public void ordenarTotalParticion() {
        Collections.sort(historialParticiones, historialParticiones.get(0).comparatorTotal);
    }

    public void ordenarIndexParticion() {
        Collections.sort(historialParticiones, historialParticiones.get(0).comparatorIndex);
    }

    public void agregarParticion(int tamanio, int index) {
        Particion particion = new Particion(tamanio, index);
        this.particiones.add(particion);
    }

    //	nombre				tiempo				tamano			indexParticion
    public void agregarProceso(String identificador, int tiempoEjecucion, int tamanio, int index) {
        Proceso process = new Proceso(identificador, tiempoEjecucion, tamanio, index);
        this.procesosCargados.add(process);
    }

    public void activarParticiones() {// Activa las particiones que deben esperar la siguiente iteracion
        for (int i = 0; i < this.particiones.size(); i++) {
            this.particiones.get(i).esperarSiguienteIteracion = false;
        }
    }

    public void ejecutarParticiones() {
        //this.llenarListas();
        for (int i = 0; i < particiones.size(); i++) {
            Particion particion = particiones.get(i);
            if (particion.getProcesoProcesando() != null && particion.esperarSiguienteIteracion == false) { // tiene procesos                	
                Proceso proceso = particion.obtenerProceso(0);
                System.out.println("Procesando " + proceso.getIdentificador() + " - tiempo: " + proceso.getTiempoEjecucion() + " de ....." + (particion.getIndex() + 1));
                ejecutarProceso(proceso, i);
            } else {
                //ejecutarProceso(proceso, i);
            }
        }
        this.activarParticiones(); // para el proceso 9 que ten�a que esperar hasta que se procese la lista entera
        this.condensar();
        this.ubicarSiguiente(-1);
        //this.ubicarProcesos();
    }

    public void procesar() {

        ubicarProcesos();
        while (procesando) {
            this.ejecutarParticiones();
            verificarProcesando();
        }
        terminarParticiones();
    }

    public void ubicarProcesos() {
        //for (int i = 0; i < this.procesosCargados.size(); i++) {
        System.out.println("***** Ubicando procesos *********");
        for (int i = 0; i < this.procesosCargados.size(); i++) {
            Proceso proceso = this.procesosCargados.get(i);
            if (proceso.getTamanio() <= this.tamanoMemoria) {
                //System.out.println((this.getSumaParticiones()+ proceso.getTamanio()));
                System.out.println("Tama�o particioenes " + this.getSumaParticiones());
                if ((this.getSumaParticiones() + proceso.getTamanio()) <= this.tamanoMemoria) { // No sobrepasa la memoria
                    Particion particion = new Particion(proceso.getTamanio(), this.historialParticiones.size());
                    particion.setProcesoProcesando(proceso);
                    particion.addProcess(proceso);
                    this.particiones.add(particion);
                    this.historialParticiones.add(particion);
                    this.procesosCargados.remove(i);
                    i -= 1;
                    //System.out.println(this.paraPillar);
                    System.out.println("Agregando Proceso : " + proceso.getIdentificador() + " - Tama�o: " + proceso.getTamanio() + " - Tiempo: " + proceso.getTiempoEjecucion() + " - Particion : " + (particion.getIndex() + 1));
                    listaProcesados.add(proceso.getIdentificador());
                } else {
                    boolean fueAgregado = this.crearSobreParticionVacia(proceso);
                    if (fueAgregado) {
                        this.procesosCargados.remove(i);
                        i -= 1;
                    } else {
                    }
                }
            } else {
                listaNoProcesados.add(proceso.getIdentificador());
            }
        }

        if (this.getSumaParticiones() < this.tamanoMemoria) { // Se debe crear hueco
            int tamanoHueco = this.tamanoMemoria - this.getSumaParticiones();
            Particion particionVacia = new Particion(tamanoHueco, this.historialParticiones.size());
            //this.particiones.add(particionVacia);
            this.particiones.add(particionVacia); // Se agrega al inicio
            this.historialParticiones.add(particionVacia);
            System.out.println("Agregando hueco porque no hay m�s proceoss con tama�o " + particionVacia.getTamanio() + " desde " + (particionVacia.getIndex() + 1));
        }

        System.out.println("***** / Ubicando procesos *********");
    }

    public void ubicarSiguiente(int i) {
        System.out.println("***** Ubicando siguiente *********");
        if (this.procesosCargados.size() > 0) {
            Proceso proceso = this.procesosCargados.get(0);
            if ((this.getSumaParticiones() + proceso.getTamanio()) <= this.tamanoMemoria) { // No sobrepasa la memoria
                System.out.println("************+++*+++++++++++++++");
                Particion particion = new Particion(proceso.getTamanio(), this.historialParticiones.size());
                particion.setProcesoProcesando(proceso);
                particion.addProcess(proceso);
                particion.esperarSiguienteIteracion = true;
                this.particiones.add(particion);
                this.historialParticiones.add(particion);
                this.procesosCargados.remove(0);
                i -= 1;
                //System.out.println(this.paraPillar);
                System.out.println("Agregando Proceso : " + proceso.getIdentificador() + " - Tama�o: " + proceso.getTamanio() + " - Tiempo: " + proceso.getTiempoEjecucion() + " - Particion : " + (particion.getIndex() + 1));
            } else {
                boolean fueAgregado = this.crearSobreParticionVacia(proceso);
                if (fueAgregado) {
                    this.procesosCargados.remove(0);
                    i -= 1;
                } else {
                }
            }
        }
        System.out.println("***** / Ubicando siguiente *********");
    }

    public int getSumaParticiones() {
        int tamano = 0;
        for (int i = 0; i < this.particiones.size(); i++) {
            Particion particion = this.particiones.get(i);

            tamano += particion.getTamanio();
        }
        return tamano;
    }

    public void terminarProceso(Proceso proceso, int posicion) {
        Particion particion = particiones.get(posicion);
        particion.setProcesoProcesando(null);
        listaTerminados.add(proceso.getIdentificador());
        System.out.println("Terminado proceso " + proceso.getIdentificador());
        //this.condensar();
    }

    public boolean sePuedeCondensar() {
        for (int i = 0; i < this.particiones.size() - 1; i++) {
            Particion particionActual = this.particiones.get(i);
            Particion particionSiguiente = this.particiones.get(i + 1);
            if (particionActual.getProcesoProcesando() == null && particionSiguiente.getProcesoProcesando() == null) {
                return true;
            }
        }
        return false;
    }

    public void condensar() {
        System.out.println("****************condensando **************************************");
        for (int i = 0; i < this.particiones.size() - 1; i++) {
//    		System.out.println(i);
//    		System.out.println(this.particiones.size());
            Particion particionActual = this.particiones.get(i);
            Particion particionSiguiente = this.particiones.get(i + 1);
            if (particionActual.getProcesoProcesando() == null && particionSiguiente.getProcesoProcesando() == null) {
                this.unirParticiones(i, (i + 1));
            }
        }

        if (this.sePuedeCondensar()) {
            this.condensar();
            this.ejecutarParticiones();
        }
        System.out.println("****************/ condensando **************************************");
    }

    public void unirParticiones(int indexParticion1, int indexParticion2) {
        Particion particion1 = this.particiones.get(indexParticion1);
        Particion particion2 = this.particiones.get(indexParticion2);
        System.out.println("eliminando particiones " + (particion1.getIndex() + 1) + " y " + (particion2.getIndex() + 1));
        this.eliminarparticiones(indexParticion1, indexParticion2);

        // Creando nueva particion
        int tamano = particion1.getTamanio() + particion2.getTamanio();
        Particion nuevaParticion = new Particion(tamano, this.historialParticiones.size());
        this.particiones.add(indexParticion1, nuevaParticion);
        this.condensaciones.add(new Condensacion(particion1, particion2, nuevaParticion));
        this.historialParticiones.add(nuevaParticion);
        System.out.println("Agregando desde unir hueco con tama�o " + nuevaParticion.getTamanio() + " desde " + (nuevaParticion.getIndex() + 1));
    }

    public void eliminarparticiones(int indexParticion1, int indexParticion2) {
        ArrayList<Particion> auxiliar = new ArrayList<>();
        for (int i = 0; i < this.particiones.size(); i++) {
            if (i != indexParticion1 && i != indexParticion2) {
                auxiliar.add(this.particiones.get(i));
            }
        }
        this.particiones = auxiliar;
    }

    public boolean crearSobreParticionVacia(Proceso proceso) {
        boolean resultado = false; // es true, si se logra ubicar el proceso
        for (int j = 0; j < this.particiones.size(); j++) {
            Particion particion = this.particiones.get(j);
            if (particion.getProcesoProcesando() == null && particion.getTamanio() >= proceso.getTamanio()) {
                if (particion.getTamanio() == this.tamanoMemoria) { // es la unica particion vacia y ocupa toda la memoria
                    this.particiones.clear();
                    this.ubicarProcesos();
                    return false;
                }

                System.out.println("Eliminando particion " + (particion.getIndex() + 1));
                this.particiones.remove(j);

                Particion nuevaParticion = new Particion(proceso.getTamanio(), this.historialParticiones.size());
                nuevaParticion.setProcesoProcesando(proceso);
                nuevaParticion.addProcess(proceso);
                nuevaParticion.esperarSiguienteIteracion = true;
                //this.particiones.add(nuevaParticion);
                this.particiones.add(j, nuevaParticion); // Se agrega al inicio
                this.historialParticiones.add(nuevaParticion);
                resultado = true;

                System.out.println("Agregando Proceso : " + proceso.getIdentificador() + " - Tama�o: " + proceso.getTamanio() + " - Tiempo: " + proceso.getTiempoEjecucion() + " - Particion : " + (nuevaParticion.getIndex() + 1));

                //  Si queda espacio despu�s de agregar el proceso, se crea un hueco
                if (particion.getTamanio() > proceso.getTamanio()) { // Qeuda un hueco
                    Particion particionVacia = new Particion((particion.getTamanio() - proceso.getTamanio()), this.historialParticiones.size());
                    //this.particiones.add(particionVacia);
                    this.particiones.add(j + 1, particionVacia); // Se agrega al inicio
                    this.historialParticiones.add(particionVacia);
                    System.out.println("Agregando desde vacias hueco con tama�o " + particionVacia.getTamanio() + " desde " + (particionVacia.getIndex() + 1));
                }
                break;
            }
        }
        return resultado;
    }

    public int puedeSerUbicado(int tamanio) {
        for (int i = inicioBusquedaParticion; i < particiones.size(); i++) {
            Particion particion = particiones.get(i);
            if (particion.getTamanio() >= tamanio) {
                return i;
            }
        }
        return -1;
    }

    public boolean puedeSerUbicado(int posicion, int tamanio) {
        for (int i = 0; i < particiones.size(); i++) {
            if (i != posicion) {
                if (particiones.get(i).getTamanio() <= tamanio) {
                    return true;
                }
            }
        }
        return false;
    }

    public void verificarProcesando() {
        boolean procesar = false;
        for (Iterator<Particion> iterator = particiones.iterator(); iterator.hasNext();) {
            Particion next = iterator.next();
            procesar = procesar || !next.procesosProcesados();
        }
        this.procesando = procesar || (procesosCargados.isEmpty() == false);
        //System.out.println("El resultado es: " + procesando);
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
        //this.listaEjecutados.add(proceso.getIdentificador() + " - Tiempo: " + proceso.getTiempoEjecucion());

    }

    public void listarProceso(Proceso proceso, int posicion) {
        // procesosListos.add(proceso.getIdentificador() + " - Tiempo: " + proceso.getTiempoEjecucion());
        Particion particion = particiones.get(posicion);
        particion.agregarListo(new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion()));
    }

    public void removerProceso(Proceso proceso, int posicion) {
        particiones.get(posicion).removerProceso(proceso);
    }

    public void eliminarParticionesVacias() {
        for (int i = 0; i < this.particiones.size(); i++) {
            Particion particion = this.particiones.get(i);
            if (particion.getProcesoProcesando() == null) {
                this.particiones.remove(i);
                System.out.println("Partici�n " + (particion.getIndex() + 1) + " Eliminada");
                i -= 1;
            }
        }
    }
    
    public void terminarParticiones(){
        this.ordenarTotalParticion();
        for (int i = 0; i < historialParticiones.size(); i++) {
            Particion p = historialParticiones.get(i);
            if (p.getProcesos().isEmpty() == false) {
                this.listaParticionesTerminadas.add("Particion " + (p.getIndex()+1));
            }
        }
        this.ordenarIndexParticion();
    }

    public void despacharProceso(Proceso proceso, int posicion) {
        InformacionTransicion it = new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion());
        this.particiones.get(posicion).agregarDespachado(it);
    }

    public void expirarTiempo(Proceso proceso, int posicion) {
        proceso.setTransicion(Estado.listo);
        InformacionTransicion it = new InformacionTransicion(proceso.getIdentificador(), proceso.getTiempoEjecucion());
        this.particiones.get(posicion).agregarExpirado(it);
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

    public ArrayList<Proceso> getProcesosCargados() {
        return procesosCargados;
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

//    public void setListaEjecutados(ArrayList<String> listaEjecutados) {
//        this.listaEjecutados = listaEjecutados;
//    }
    public ArrayList<String> getListaProcesados() {
        return listaProcesados;
    }

    public void setListaProcesados(ArrayList<String> listaProcesados) {
        this.listaProcesados = listaProcesados;
    }

    public ArrayList<Condensacion> getCondensaciones() {
        return condensaciones;
    }

    public ArrayList<Particion> getHistorialParticiones() {
        return historialParticiones;
    }

    public ArrayList<String> getListaTerminados() {
        return listaTerminados;
    }

    public void setTamanoMemoria(int tamanoMemoria) {
        this.tamanoMemoria = tamanoMemoria;
    }

    public ArrayList<String> getListaParticionesTerminadas() {
        return listaParticionesTerminadas;
    }

}
