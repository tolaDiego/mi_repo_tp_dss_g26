package domain.objetos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Vianda {
    private String nombre;
    private Date fechaCaducidad;
    private Date fechaDonacion;
    private   float calorias;
    private   float peso;
    public  Vianda(String nom){
        this.nombre=nom;
    }

}
