package domain.objetos;

import domain.personas.Humano;
import domain.personas.Juridica;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "oferta")
public class Oferta {
    @Id
    @GeneratedValue
    private long id;

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
