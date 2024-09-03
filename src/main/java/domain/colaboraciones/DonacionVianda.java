package domain.colaboraciones;

import domain.objetos.Vianda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonacionVianda implements Colaboracion{
    private Vianda vianda;
    private double coefPorViandas;
    private  boolean entregada;
    public DonacionVianda(){

    }
    @Override
    public double puntaje() {
        return coefPorViandas;
    }
}
