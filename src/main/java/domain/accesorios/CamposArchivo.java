package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CamposArchivo {
    private String tipoDoc;
    private String documento;
    private String nombre;
    private String apellido;
    private String mail;
    private String fechaColaboracion;
    private String formaColaboracion;
    private String cantidad;
}
