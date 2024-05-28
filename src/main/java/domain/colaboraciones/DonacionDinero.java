package domain.colaboraciones;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.CamposArchivo;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Getter
@Setter
public class DonacionDinero implements Colaboracion {
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    public Date fechaDonacion;
    public double monto;
    public  String frecuencia;//por dia,mensual,anual, quincenal,etc
    private double coefPorDinero;
    private  boolean puntosUsados;
    public DonacionDinero(){

        this.coefPorDinero=0.5;
    }
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

    @Override
    public double puntaje() {
        return coefPorDinero*monto;
    }
}
