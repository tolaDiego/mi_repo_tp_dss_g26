package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter

public class PuntoUbicacion {

    private double latitud;

    private  double longitud;
    public PuntoUbicacion(){}
}
