package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Documento {
    private TipoDocumento tipoDoc;
    private String numero;
    public Documento(){}
}
