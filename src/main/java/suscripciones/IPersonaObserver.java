package suscripciones;

import adapters.notificadores.Mensaje;

public interface IPersonaObserver {
    void serNotificadoDeEvento(Mensaje mensaje);
}
