package domain.personas;

import domain.accesorios.TipoEntidad;
import domain.colaboraciones.Colaboracion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Juridica {
    private int id;
    private String razonSocial;
    private TipoEntidad tipo;
    private String rubro;
    private  String direccion;
    private List<Colaboracion> colaboraciones;
    public Juridica(){
        this.colaboraciones=new ArrayList<>();
    }

    public double puntaje(){

    return colaboraciones.stream().mapToDouble(colab->colab.puntaje()).sum();
    }

    public boolean agregarColacoracion(Colaboracion colaboracion) {
        return this.colaboraciones.add(colaboracion);
    }
}

