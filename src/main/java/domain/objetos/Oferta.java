package domain.objetos;

import domain.personas.Humano;
import domain.personas.Juridica;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter

@Entity
@Table(name = "oferta")
public class Oferta {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "nombre",columnDefinition = "VARCHAR(100)")
    private  String nombre;
    @Column(name = "puntos_necesarios",scale = 9,precision = 2)
    private double puntosNecesarios;
    @Column(name = "nombre",columnDefinition = "VARCHAR(255)")
    private String imagen;

    public Oferta(){}

    public boolean tieneLosPuntosRequeridos(Humano unaPersona){
        return unaPersona.calcularPuntos()>=this.puntosNecesarios;
    }
    public boolean tieneLosPuntosRequeridos(Juridica entidad){
        return entidad.calcularPuntos()>=this.puntosNecesarios;
    }
}
