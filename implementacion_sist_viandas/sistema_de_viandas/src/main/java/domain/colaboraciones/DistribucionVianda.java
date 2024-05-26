package domain.colaboraciones;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DistribucionVianda implements Colaboracion {
    private Heladera origen;
    private Heladera destino;
    private int cantidadViandas;
    private  String motivo;
    private Date fechaDistribucion;

    private  double coefPorDistribuir=1;

    public DistribucionVianda(){

    }
    public  double puntaje(){
        return coefPorDistribuir*cantidadViandas;
    }


}
