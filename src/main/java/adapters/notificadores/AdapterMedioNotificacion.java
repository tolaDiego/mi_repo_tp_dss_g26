package adapters.notificadores;

import adapters.notificadores.Mensaje;
import domain.accesorios.Contacto;

public interface AdapterMedioNotificacion {
    public void  notificar(Contacto contacto , Mensaje mensaje);
}
