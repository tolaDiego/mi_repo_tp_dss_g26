package domain.personas;

import adapters.notificadores.Notificador;
import domain.accesorios.AreaCobertura;
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
    private AreaCobertura areaCobertura;
    private Notificador notificador;
    public  Tecnico(Notificador notif){
    this.notificador=notif ;
    }
}
