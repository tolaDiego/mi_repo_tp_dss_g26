package domain.colaboraciones;

import domain.accesorios.CamposArchivo;
import domain.objetos.Vianda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonacionVianda implements Colaboracion{
    private Vianda vianda;
    private double coefPorViandas;
    private  boolean entregada;
    public DonacionVianda(CamposArchivo tipo){
        this.vianda=new Vianda();
        this.coefPorViandas=1.5;

    }
    public DonacionVianda(){

    }
    @Override
    public double puntaje() {
        return coefPorViandas;
    }
}
