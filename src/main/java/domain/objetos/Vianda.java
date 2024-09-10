package domain.objetos;

import domain.enums.EstadoVianda;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vianda")
public class Vianda {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "nombre",columnDefinition = "VARCHAR(100)")
    private String nombre;
    @Column(name = "fecha_caducidad",columnDefinition = "DATETIME")
    private Date fechaCaducidad;
    @Column(name = "fecha_donacion",columnDefinition = "DATETIME")
    private Date fechaDonacion;
    @Column(name = "calorias")
    private   float calorias;
    @Column(name = "peso")
    private   float peso;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_vianda")
    private EstadoVianda estadoVianda;
    public  Vianda(String nom){
        this.nombre=nom;
    }

}
