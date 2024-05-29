package adapters;

import domain.accesorios.PuntoUbicacion;
import java.util.List;

public interface RAdapter {

  public List<PuntoUbicacion> recomendarUbicacion(PuntoUbicacion ubicacion, Integer radio);


}
