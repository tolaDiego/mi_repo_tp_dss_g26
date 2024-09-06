package suscripciones;

import adapters.notificadores.Mensaje;
import adapters.notificadores.Notificador;
import domain.accesorios.Contacto;
import domain.enums.TipoContacto;
import domain.personas.Humano;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonaObserver implements IPersonaObserver{
    private Date fechaSuscripcion;
    private int stock;
    private Humano suscriptor;
    private Notificador notificador;
    private TipoContacto tipoContacto;
    public PersonaObserver(){

    }
    @Override
    public void serNotificadoDeEvento(Mensaje mensaje ) {

        Contacto contacto = null;
         for(Contacto contac :suscriptor.getContactos()){
             if(contac.getTipoContacto().equals(tipoContacto)) {
                 contacto=contac;
                 break;
             }
         }
        notificador.enviarNotificacion(contacto,mensaje);
    }
}
