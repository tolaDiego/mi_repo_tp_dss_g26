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
@Getter
@Setter
@Entity
@DiscriminatorValue("SUSCRIPCION_A_STOCK_MAXIMO")
public class SuscripcionAStockMaxObservable extends   ISuscripcionObservable{

    @Override
    public void notificar(Heladera heladera) {
        for (PersonaObserver suscriptor : suscriptores) {

            if( suscriptor.getStock()>=heladera.getCapacidad()- heladera.cantidadViandasActuales()) {
                System.out.println("sucriptor: "+suscriptor.getSuscriptor().getNombre()+" stock: "+suscriptor.getStock()+"capacidad faltante: "+(heladera.getCapacidad()- heladera.cantidadViandasActuales())+"actuales:"+heladera.cantidadViandasActuales());
                Mensaje mensaje;
                mensaje = new Mensaje("Hola "+suscriptor.getSuscriptor().getNombre()
                        +"!!! Faltan "+suscriptor.getStock()+" viandas para que se llene la heladera de "+heladera.getUbicacion().getDireccion()
                        , new Date());
                suscriptor.serNotificadoDeEvento(mensaje);
            }
        }
    }

}
