package domain.colaboraciones;

import domain.accesorios.CamposArchivo;
import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
public class DistribucionVianda implements Colaboracion {
    private Heladera origen;
    private Heladera destino;
    private int cantidadViandas;
    private  String motivo;
    private Date fechaDistribucion;

    private  double coefPorDistribuir;

    public DistribucionVianda(){}
    public DistribucionVianda(CamposArchivo datos){
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        this.coefPorDistribuir=1;
        try {
            this.fechaDistribucion=formato.parse(datos.getFechaColaboracion());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.cantidadViandas=1;
    }
    public  double puntaje(){
        return coefPorDistribuir*cantidadViandas;
    }


}
