package adapters.notificadores;

import adapters.notificadores.AdapterMedioNotificacion;
import adapters.notificadores.Mensaje;
import domain.accesorios.Contacto;


public class AdapterMedioNotifTelegram implements AdapterMedioNotificacion {
        private final  String tokenTelegram="6830980434:AAGV8IASlyv8g36voT2daYYLDAOWSM3w5vE";
        private final String chat_bot="5118817082";
    @Override
    public  void notificar(Contacto contacto, Mensaje mensaje){

    }
}
