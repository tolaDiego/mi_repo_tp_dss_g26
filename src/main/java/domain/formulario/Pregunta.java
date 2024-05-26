package domain.formulario;

public class Pregunta {
    private Respuesta respuestaPosible;
    private int codigoPregunta;
    public Respuesta getRespuestaPosible() {
        return respuestaPosible;
    }

    public void setRespuestaPosible(Respuesta respuestaPosible) {
        this.respuestaPosible = respuestaPosible;
    }

    public int getCodigoPregunta() {
        return codigoPregunta;
    }

    public void setCodigoPregunta(int codigoPregunta) {
        this.codigoPregunta = codigoPregunta;
    }



}
