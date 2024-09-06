package domain.accesorios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "punto_ubicacion")
public class PuntoUbicacion {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "latitud", precision = 9, scale = 6)
    private double latitud;
    @Column(name = "longitud", precision = 9, scale = 6)
    private  double longitud;
    public PuntoUbicacion(){}
}
