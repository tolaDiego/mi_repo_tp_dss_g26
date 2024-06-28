package domain.objetos;

import domain.personas.Humano;
import domain.personas.Juridica;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Setter
@Getter
public class Oferta {
    private  String nombre;
    private double puntosNecesarios;
    private BufferedImage imagen;

    public Oferta(){}

    public boolean tieneLosPuntosRequeridos(Humano unaPersona){
        return unaPersona.calcularPuntos()>=this.puntosNecesarios;
    }
    public boolean tieneLosPuntosRequeridos(Juridica entidad){
        return entidad.calcularPuntos()>=this.puntosNecesarios;
    }
}
