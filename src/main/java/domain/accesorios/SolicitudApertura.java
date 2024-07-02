package domain.accesorios;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
@Setter
@Getter

public class SolicitudApertura {
    private Heladera heladeraSolicitada;
    private Calendar fechaDeSolicutud;

}
