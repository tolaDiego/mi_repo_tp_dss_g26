package domain.accesorios;

import domain.objetos.Heladera;
import domain.objetos.TarjetaColaborador;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
@Setter
@Getter

public class SolicitudApertura {
    private Heladera heladeraSolicitada;
    private TarjetaColaborador tarjeta;
    private Calendar fechaDeSolicutud;
    private int horasLimite = 3;

}
