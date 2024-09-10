package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
public class Documento {

    @Column(name = "tipo_doc")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDoc;
    @Column(name = "numero",columnDefinition = "VARCHAR(10)")
    private String numero;
    public Documento(){}

    public Documento(TipoDocumento tipoDoc, String numero) {
    }
}
