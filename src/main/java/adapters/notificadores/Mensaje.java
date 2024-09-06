package adapters.notificadores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor

@Entity
@Table(name = "mensaje")
public class Mensaje {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "fechaNotificacion", columnDefinition = "DATETIME")
    private Date fechaNotificacion;

    public Mensaje(String descripcion, Date fechaNotificacion) {
        this.descripcion = descripcion;
        this.fechaNotificacion = fechaNotificacion;
    }

    public Mensaje() {

    }
}
