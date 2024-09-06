package domain.colaboraciones;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="servicio_administracion")
public class ServicioAdministracion {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn (name = "id_heladera")
    private Heladera heladera;
    @Column(name="fecha_registro_heladera",columnDefinition = "DATETIME")
    private Date fechaRegistroHeladera;
}
