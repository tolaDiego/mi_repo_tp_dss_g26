package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class PuntoUbicacion {
    private double latitud;
    private  double longitud;
    public PuntoUbicacion(){}
}
