package adapters.notificadores;

import domain.accesorios.Contacto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    @Setter
    @Getter
    private AdapterMedioNotificacion medioDeNotificacion;
    private List<Mensaje> mensajes;
    public  void cambiarMedioDeNotificacion(AdapterMedioNotificacion nuevoMedio ){
        this.medioDeNotificacion=nuevoMedio;
        this.mensajes=new ArrayList<>();
    }
    public  void enviarNotificacion(Contacto contacto , Mensaje mensaje){
        medioDeNotificacion.notificar(contacto,mensaje);
        mensajes.add(mensaje);
    }

}
