package domain.objetos;

import domain.personas.Humano;
import domain.personas.Juridica;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Oferta {
    private  String nombre;
    private double puntosNecesarios;
    private String imagen;

    public Oferta(){}

    public boolean tieneLosPuntosRequeridos(Humano unaPersona){
        return unaPersona.calcularPuntos()>=this.puntosNecesarios;
    }
    public boolean tieneLosPuntosRequeridos(Juridica entidad){
        return entidad.calcularPuntos()>=this.puntosNecesarios;
    }
}
