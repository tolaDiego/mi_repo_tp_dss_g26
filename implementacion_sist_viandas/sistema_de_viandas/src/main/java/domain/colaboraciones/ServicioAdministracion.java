package domain.colaboraciones;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioAdministracion implements   Colaboracion{
    private Heladera heladera;
    private  double coefPorHeladeras=2;
    public ServicioAdministracion() {

    }
    @Override
    public double puntaje(){
            if(heladera.estaActiva())
              return coefPorHeladeras*heladera.mesesActiva()+1;
            return coefPorHeladeras*heladera.mesesActiva();
          }
}
