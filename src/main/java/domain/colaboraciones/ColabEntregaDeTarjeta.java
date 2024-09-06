package domain.colaboraciones;


import domain.objetos.TarjetaVulnerable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "colaboracion_entrega_tarjeta")
public class ColabEntregaDeTarjeta {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "tarjeta_vulnerable")
    private TarjetaVulnerable tarjeta;

   // private Vulnerable personaTitular;
    @Column(name = "fecha_contribucion",columnDefinition = "DATETIME")
    private Date fechaContribucion;

}
