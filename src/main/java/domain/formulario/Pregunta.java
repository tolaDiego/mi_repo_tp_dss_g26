package domain.formulario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pregunta {
    private Respuesta respuestaPosible;
    private int codigoPregunta;

}
