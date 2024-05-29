package domain.objetos;

import domain.accesorios.Uso;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Tarjeta {
    private int cantidadDeUsosDelDia;
    private long codigoTarjeta;

    private List<Uso> usos;

    public Tarjeta(){}
}
