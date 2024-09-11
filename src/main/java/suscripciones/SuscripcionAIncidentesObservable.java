package suscripciones;

import adapters.notificadores.Mensaje;
import domain.objetos.Heladera;
import heladerasDeZona.CalculadorZonaDeHeladeras;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@DiscriminatorValue("SUSCRIPCION_A_INCIDENTES")
public class SuscripcionAIncidentesObservable extends ISuscripcionObservable{

    public SuscripcionAIncidentesObservable(){
        this.suscriptores=new ArrayList<>();
    }
       @Override
    public void notificar(Heladera heladera) {
         for (PersonaObserver suscriptor : suscriptores) {
            Mensaje mensaje;
            mensaje = new Mensaje("Hola %s "+suscriptor.getSuscriptor().getNombre()+"!!!"
                    +"\nLa heladera de %s"+heladera.getUbicacion().getDireccion()+" se encuentra fuera de servicio."
                    , new Date());
            mensaje.setDescripcion(mensaje.getDescripcion()+"\n Heladeras recomendadas para llevar las viandas:");
             CalculadorZonaDeHeladeras heladerasRecomendadas=new CalculadorZonaDeHeladeras();
            List<String> direccionHeladeras= heladerasRecomendadas
                            .heladerasRecomendadasParaDistribucion(suscriptor.getSuscriptor())
                            .stream().map(heladera1 ->heladera1.getUbicacion().getDireccion())
                            .toList();
                int contador=1;
            for (String direccionHeladera : direccionHeladeras) {
                 mensaje.setDescripcion(mensaje.getDescripcion()+"\nopcion %i:"+contador+" %s"+direccionHeladera);
                 contador++;
             }
            suscriptor.serNotificadoDeEvento(mensaje);
        }
    }

}
