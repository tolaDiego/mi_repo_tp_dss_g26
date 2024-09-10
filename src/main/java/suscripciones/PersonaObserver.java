package suscripciones;

import adapters.notificadores.Mensaje;
import adapters.notificadores.Notificador;
import domain.accesorios.Contacto;
import domain.enums.TipoContacto;
import domain.personas.Humano;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona_suscriptor")
public class PersonaObserver implements IPersonaObserver{
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_suscripcion",columnDefinition = "DATETIME")
    private Date fechaSuscripcion;
    @Column(name = "stock")
    private int stock;
    @OneToOne
    @JoinColumn(name = "id_suscriptor")
    private Humano suscriptor;
    @Enumerated(EnumType.STRING)
    private TipoContacto tipoContacto;

    @Override
    public void serNotificadoDeEvento(Mensaje mensaje ) {
        Contacto contacto = null;
         for(Contacto contac :suscriptor.getContactos()){
             if(contac.getTipoContacto().equals(tipoContacto)) {
                 contacto=contac;
                 break;
             }
         }
       suscriptor.getNotificador().enviarNotificacion(contacto,mensaje);
    }
}
