package domain.objetos;

import domain.accesorios.AperturaColab;
import domain.personas.Humano;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class TarjetaColaborador {
    private long codigo;
    private Humano colaborador;
    private List<AperturaColab> aperturas;

    public void agregarApertura(AperturaColab nuevaApertura){
            aperturas.add(nuevaApertura);
    }
}
