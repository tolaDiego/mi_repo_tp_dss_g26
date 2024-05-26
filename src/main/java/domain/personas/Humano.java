package domain.personas;

import domain.accesorios.Contacto;
import domain.accesorios.Documento;
import domain.colaboraciones.Colaboracion;
import domain.colaboraciones.DistribucionVianda;
import domain.colaboraciones.DonacionVianda;
import domain.colaboraciones.TarjertaRepartida;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Humano  {
    private Documento documento;
    private int id=0;

    private String nombre;

    private   String apellido;

    private String direccion;

    private Date fechaNacimiento ;
    private List<Contacto> contactos;
    private List<Colaboracion> colaboraciones;

    public Humano(){

    }

    public int cantTarjetasRepartidas(){
                int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof TarjertaRepartida){
                    cantidad++;
                }
        }
        return cantidad;
    }
    public int cantDonacionVianda(){
        int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof DonacionVianda){
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantDistribucionVianda(){
        int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof DistribucionVianda)
                cantidad += ((DistribucionVianda) colaboracion).getCantidadViandas();
        }
        return cantidad;
    }

    public double puntaje() {
        return  colaboraciones.stream().mapToDouble(colab->colab.puntaje()).sum();
    }


}

