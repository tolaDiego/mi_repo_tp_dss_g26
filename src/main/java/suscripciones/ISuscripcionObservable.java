package suscripciones;

import domain.objetos.Heladera;

public interface ISuscripcionObservable {
    void notificar(Heladera heladera);
    void agregarSuscriptor(PersonaObserver personaObserver);

    void eliminarSuscriptor(PersonaObserver personaObserver);
}
