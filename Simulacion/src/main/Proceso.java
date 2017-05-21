package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Proceso {

    private Estado estadoActual;
    private String identificador;
    private int prioridad;
    private int tiempoEjecucion;
    private String transicion;
    boolean bloqueado;
    String suspedidoListo;
    String suspedidoBloqueado;
    boolean destruido;
    String cambioPrioridad;
    String transiciones;
    private ArrayList<String> procesosComunica;
    private String seComunica;
    private int tamanio;
    private int indexParticion; // Particion a la que pertenece
    
    int index;


    public Proceso(String identificador, int tiempoEjecucion, int tamanio, int index) {
        this.identificador = identificador;
        this.tiempoEjecucion = tiempoEjecucion;
        this.tamanio = tamanio;
        this.index = index ;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.transicion = "De " + this.estadoActual + " a " + estadoActual;
        this.estadoActual = estadoActual;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public String getTransicion() {
        return transicion;
    }

    public void setTransicion(Estado estado) {
        this.transicion += " - Cambio a " + estado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public String getSuspedidoListo() {
        return suspedidoListo;
    }

    public String getSuspedidoBloqueado() {
        return suspedidoBloqueado;
    }

    public void setSuspedidoBloqueado(String suspedidoBloqueado) {
        this.suspedidoBloqueado = suspedidoBloqueado;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public String getCambioPrioridad() {
        return cambioPrioridad;
    }

    Comparator<Proceso> comparatorPrioridad = new Comparator<Proceso>() {

        @Override
        public int compare(Proceso o1, Proceso o2) {
            return o1.prioridad - o2.prioridad;
        }
    };

    public ArrayList<String> getProcesosComunica() {
        return procesosComunica;
    }

    public void setProcesosComunica(ArrayList<String> procesosComunica) {
        this.procesosComunica = procesosComunica;
    }

    public String getSeComunica() {
        return seComunica;
    }

    public void setSeComunica(String seComunica) {
        this.seComunica = seComunica;
    }

    public int getSize() {
        return tamanio;
    }

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public int getIndexParticion() {
		return indexParticion;
	}

	public void setIndexParticion(int indexParticion) {
		this.indexParticion = indexParticion;
	}
    
	
    
}
