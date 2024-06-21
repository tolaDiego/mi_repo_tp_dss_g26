package domain.accesorios;

import domain.objetos.Heladera;

import java.util.Calendar;
import java.util.Date;

public class Apertura {
    private Heladera heladeraUsada;

    public Calendar getFechaDeUso() {
        return fechaDeUso;
    }

    public void setFechaDeUso(Calendar fechaDeUso) {
        this.fechaDeUso = fechaDeUso;
    }

    private Calendar fechaDeUso;

    public Heladera getHeladeraUsada() {
        return heladeraUsada;
    }

    public void setHeladeraUsada(Heladera heladeraUsada) {
        this.heladeraUsada = heladeraUsada;
    }
}
