package domain.accesorios;

import domain.objetos.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitud_apertura")
public class SolicitudApertura {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_heladera")
    private Heladera heladeraSolicitada;
    @Column(name = "fecha_de_solicitud", columnDefinition = "DATETIME")
    private Date fechaDeSolicutud;

    public SolicitudApertura(Heladera heladeraSolicitada, Date fechaDeSolicutud) {
        this.heladeraSolicitada = heladeraSolicitada;
        this.fechaDeSolicutud = fechaDeSolicutud;
    }
}
