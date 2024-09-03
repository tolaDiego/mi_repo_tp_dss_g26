package suscripciones;

import adapters.notificadores.Mensaje;
import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class SuscripcionAStockMaxObservable implements  ISuscripcionObservable{
    private List<PersonaObserver> suscriptores;
    public SuscripcionAStockMaxObservable (){
        suscriptores=new ArrayList<>();
    }
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
    @Override
    public void agregarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.add(personaObserver);
    }

    @Override
    public void eliminarSuscriptor(PersonaObserver personaObserver) {
        suscriptores.remove(personaObserver);
    }
}
