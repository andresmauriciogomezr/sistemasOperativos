package main;

/**
 *
 * @author Usuario
 */
public class InformacionTransicion {
    
    private String identificadorProceso;
    private String identificadorProcesadorComunicado;
    private int tiempoLleva;

    public InformacionTransicion(String identificadorProceso, int tiempoLleva) {
        this.identificadorProceso = identificadorProceso;
        this.tiempoLleva = tiempoLleva;
    }

    public InformacionTransicion(String identificadorProceso, String identificadorProcesadorComunicado) {
        this.identificadorProceso = identificadorProceso;
        this.identificadorProcesadorComunicado = identificadorProcesadorComunicado;
    }

    public String getIdentificadorProceso() {
        return identificadorProceso;
    }

    public int getTiempoLleva() {
        return tiempoLleva;
    }

    public String getIdentificadorProcesadorComunicado() {
        return identificadorProcesadorComunicado;
    }   
}