package domain.colaboraciones;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioAdministracion implements   Colaboracion{
    private Heladera heladera;
    private  double coefPorHeladeras;
    public ServicioAdministracion() {
        this.coefPorHeladeras=5;
    }
    @Override
    public double puntaje(){
            if(heladera.estaActiva())
              return coefPorHeladeras*heladera.mesesActiva()+1;
            return coefPorHeladeras*heladera.mesesActiva();
          }
}
