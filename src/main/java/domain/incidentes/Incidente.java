package domain.incidentes;

import domain.enums.TipoAlerta;
import domain.objetos.Heladera;

public abstract class Incidente {


  Heladera heladera;
  TipoAlerta alerta;

  public Incidente(Heladera heladera){
    this.heladera = heladera;
  }

  public void reportar(){

    heladera.setEstadoActivo(false);
    this.reportePersonalizado();
  }

  public abstract void reportePersonalizado();

}
