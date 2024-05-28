package domain.colaboraciones;

import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColabOferta implements Colaboracion{
    private Oferta oferta;
    @Override
    public double puntaje() {
        return 0;
    }
}
