package adapters.recomendadorUbicaciones;

import domain.accesorios.PuntoUbicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ListaPuntosReferenciados {
    private List<PuntoUbicacion> puntos;
    private  double radioRef;
    private  double latRef;
    private  double lonRef;
}
