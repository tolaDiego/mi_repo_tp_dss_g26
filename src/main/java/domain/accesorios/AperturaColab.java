package domain.accesorios;

import domain.objetos.Heladera;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
@Setter
@Getter
@Entity
@Table(name = "apertura_colaborador")
public class AperturaColab {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_heladera")
    private Heladera heladeraUsada;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_de_uso")
    private Calendar fechaDeUso;
}
