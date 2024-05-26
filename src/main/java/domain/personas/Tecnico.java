package domain.personas;

import domain.accesorios.Contacto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tecnico {
    private  int id;
    private String nombre;
    private String apellido;
    private  long cuil;
    private Contacto contacto;
    private long documento;
    public  void Tecnico(){

    }
}
