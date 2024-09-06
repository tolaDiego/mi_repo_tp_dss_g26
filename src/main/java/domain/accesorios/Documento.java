package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "documento")
public class Documento {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "tipo_doc")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDoc;
    @Column(name = "numero",columnDefinition = "INTEGER")
    private Integer numero;
    public Documento(){}

    public Documento(TipoDocumento tipoDoc, String numero) {
    }
}
