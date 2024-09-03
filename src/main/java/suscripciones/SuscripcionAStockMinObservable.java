package suscripciones;

import adapters.notificadores.Mensaje;
import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
public class SuscripcionAStockMinObservable implements ISuscripcionObservable{

    private List<PersonaObserver> suscriptores;
    public SuscripcionAStockMinObservable(){
        this.suscriptores=new ArrayList<>();
    }
    @Override
    public void notificar(Heladera heladera) {
        for (PersonaObserver suscriptor : suscriptores) {
            if(heladera.cantidadViandasActuales()<= suscriptor.getStock()) {
                Mensaje mensaje;
                mensaje = new Mensaje("Hola %s " + suscriptor.getSuscriptor().getNombre()
                        + "!!! Faltan %i"+ suscriptor.getStock()+" viandas para que se vacie la heladera de %s"+heladera.getUbicacion().getDireccion()
                        , new Date());
                suscriptor.serNotificadoDeEvento(mensaje);
            }
        }
    }
    @Override
    public void agregarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.add(personaObserver);

    }

    @Override
    public void eliminarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.remove(personaObserver);
    }
}
