package adapters.notificadores;

import domain.accesorios.Contacto;
import domain.converters.MedioDeNotificacionConverter;
import domain.enums.TipoContacto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "notificador")
public class Notificador {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    @JoinColumn(name = "id_notificador")
    private List<Mensaje> mensajes;
    @Convert(converter = MedioDeNotificacionConverter.class)
    @Column(name = "medio_de_notificacion")
    private AdapterMedioNotificacion medioDeNotificacion;
    public Notificador(AdapterMedioNotificacion medio){
        this.mensajes=new ArrayList<>();
        this.medioDeNotificacion=medio;
    }
    public  void enviarNotificacion(Contacto contacto , Mensaje mensaje){

        medioDeNotificacion.notificar(contacto,mensaje);
        mensajes.add(mensaje);
    }

}
