package domain.objetos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private String nombre;
    private Date fechaCaducidad;
    private Date fechaDonacion;
    private   float calorias;
    private   float peso;
    public  Vianda(String nom){
        this.nombre=nom;
    }

}
