package domain.accesorios;

import domain.objetos.Heladera;

import java.util.Date;

public class Apertura {
    private Heladera heladeraUsada;
    private Date fechaDeUso;
    public Date getFechaDeUso() {
        return fechaDeUso;
    }

    public void setFechaDeUso(Date fechaDeUso) {
        this.fechaDeUso = fechaDeUso;
    }



    public Heladera getHeladeraUsada() {
        return heladeraUsada;
    }

    public void setHeladeraUsada(Heladera heladeraUsada) {
        this.heladeraUsada = heladeraUsada;
    }
}
