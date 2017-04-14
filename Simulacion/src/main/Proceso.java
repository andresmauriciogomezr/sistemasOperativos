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
    boolean suspedido;
    boolean destruido;
    String cambioPrioridad;
    String transiciones;
    private ArrayList<String> procesosComunica;
    private String seComunica;

    //Nombre				priodidad		tiempo 				bloqueo			suspendido				destruido		seComunica
    public Proceso(String identificador, int prioridad, int tiempoEjecucion, boolean bloqueado, boolean suspendido, boolean destruido, String seComunica, String cambioPrioridad) {
        this.transicion = "";
        this.identificador = identificador;
        this.procesosComunica = new ArrayList<>();
        this.prioridad = prioridad;
        this.tiempoEjecucion = tiempoEjecucion;
        this.bloqueado = bloqueado;
        this.suspedido = suspendido;
        this.destruido = destruido;
        transiciones = identificador + " ";
        this.cambioPrioridad = cambioPrioridad;
        this.seComunica = seComunica;
        this.estadoActual = Estado.listo;
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

    public boolean isSuspendido() {
        return suspedido;
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
    
    

}
