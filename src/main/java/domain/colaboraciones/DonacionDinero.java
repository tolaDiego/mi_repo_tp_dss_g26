package domain.colaboraciones;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DonacionDinero implements Colaboracion {
    public Date fechaDonacion;
    public double monto;
    public  String frecuencia;//por dia,mensual,anual, quincenal,etc
    private double coefPorDinero;

    public DonacionDinero(String tipo){

        this.monto=1;
        this.coefPorDinero=0.5;
    }
    public DonacionDinero(){}
    @Override
    public double puntaje() {
        return coefPorDinero*monto;
    }
}
