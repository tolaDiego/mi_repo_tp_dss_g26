package adapters.notificadores;

import domain.accesorios.Contacto;

public interface AdapterMedioNotificacion {
    public void  notificar(Contacto contacto , Mensaje mensaje);
}
