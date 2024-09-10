package suscripciones;

import adapters.notificadores.Mensaje;
import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity
@DiscriminatorValue("SUSCRIPCION_A_STOCK_MINIMO")
public class SuscripcionAStockMinObservable extends ISuscripcionObservable{

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
}
