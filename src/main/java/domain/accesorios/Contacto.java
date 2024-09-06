package domain.accesorios;

import domain.enums.TipoContacto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacto")
public  class Contacto {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "tipo_contacto")
    @Enumerated(EnumType.STRING)
    private TipoContacto tipoContacto;
    @Column(name = "contacto",columnDefinition = "VARCHAR(30)")
    private String contacto;

}
