package domain.objetos;

import domain.accesorios.Uso;
import domain.personas.Vulnerable;

import java.util.List;

public class Tarjeta {
    private int cantidadDeUsosDelDia;
    private long codigoTarjeta;

    private List<Uso> usos;
    private Vulnerable personaTitular;

}
