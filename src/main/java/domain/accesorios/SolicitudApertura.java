package domain.accesorios;

import domain.objetos.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class SolicitudApertura {
    private Heladera heladeraSolicitada;
    private Date fechaDeSolicutud;

}
