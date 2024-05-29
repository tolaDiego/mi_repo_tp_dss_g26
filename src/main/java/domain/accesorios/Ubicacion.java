package domain.accesorios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ubicacion {
    private double latitud;

    private double longitud;
    private String direccion;
    private String nombre;
    public Ubicacion(){}

}
