package domain.colaboraciones;

import domain.accesorios.CamposArchivo;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
public class DonacionDinero implements Colaboracion {
    public Date fechaDonacion;
    public double monto;
    public  String frecuencia;//por dia,mensual,anual, quincenal,etc
    private double coefPorDinero;

    public DonacionDinero(CamposArchivo campos){
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.fechaDonacion=formato.parse(campos.getFechaColaboracion()) ;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.monto=1;
        this.coefPorDinero=0.5;
    }
    public DonacionDinero(){}
    @Override
    public double puntaje() {
        return coefPorDinero*monto;
    }
}
