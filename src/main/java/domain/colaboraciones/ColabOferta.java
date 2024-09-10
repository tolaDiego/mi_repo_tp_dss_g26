package domain.colaboraciones;

import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "colaboracion_oferta")
public class ColabOferta {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "id_oferta")
    private Oferta oferta;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_contribucion",columnDefinition = "DATETIME")
    private Date fechaContribucion;

}
