package domain.accesorios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "latitud", precision = 9, scale = 6)
    private double latitud;
    @Column(name = "longitud", precision = 9, scale = 6)
    private double longitud;
    @Column(name = "direccion",columnDefinition = "VARCHAR(170)")
    private String direccion;
    @Column(name="nombre",columnDefinition = "VARCHAR(100)")
    private String nombre;
    public Ubicacion(){}

}
