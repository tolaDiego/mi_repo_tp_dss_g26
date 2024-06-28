package adapters.recomendadorUbicaciones;

import domain.accesorios.PuntoUbicacion;

import java.io.IOException;
import java.util.List;

public interface AdapterRecomendadorUbicaciones {
    public List<PuntoUbicacion> recomendarUbicaciones(PuntoUbicacion punto, double radio) throws IOException;
}
