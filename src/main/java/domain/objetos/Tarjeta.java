package domain.objetos;

import domain.accesorios.Apertura;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
@Setter
@Getter
public class Tarjeta {
    private int cantidadDeUsosDelDia;
    private long codigoTarjeta;
    private Vulnerable personaVul;

    private List<Apertura> aperturas;

    public Tarjeta(){}

    public void agregarApertura(Apertura nuevaApertura){
        aperturas.add(nuevaApertura);
    }

    public int aperturasRestantes(){
        Calendar fechaActual = Calendar.getInstance();

        List<Apertura> aperturasdeHoy = aperturas.stream().filter(a->a.getFechaDeUso() == fechaActual).toList() ;

        return Math.max((int) (4 + personaVul.getMenoresACargo()*2 - (long) aperturasdeHoy.size()), 0);
    }

}
