package domain.accesorios;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@Entity
@Table(name = "apertura")
public class Apertura {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_heladera")
    private Heladera heladeraUsada;
    @Column(name = "fecha_de_uso",columnDefinition = "DATETIME")
    private Date fechaDeUso;
}
