package domain.accesorios;

import domain.objetos.Heladera;

import java.util.Date;

public class Uso {
    private Heladera heladeraUsada;

    public Date getFechaDeUso() {
        return fechaDeUso;
    }

    public void setFechaDeUso(Date fechaDeUso) {
        this.fechaDeUso = fechaDeUso;
    }

    private Date fechaDeUso;

    public Heladera getHeladeraUsada() {
        return heladeraUsada;
    }

    public void setHeladeraUsada(Heladera heladeraUsada) {
        this.heladeraUsada = heladeraUsada;
    }
}
