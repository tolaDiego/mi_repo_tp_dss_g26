package domain.colaboraciones;

import domain.objetos.Vianda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "donacion_vianda")
public class DonacionVianda {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "id_vianda")
    private Vianda vianda;

    @Column(name = "entregada", columnDefinition = "BOOLEAN")
    private  boolean entregada;

}
