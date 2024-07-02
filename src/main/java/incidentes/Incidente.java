package incidentes;

import domain.objetos.Heladera;

public abstract class Incidente {


  private Heladera heladera;


  public void reportar(){

    heladera.setEstadoActivo(false);
    this.reportePersonalizado();
  }

  public abstract void reportePersonalizado();

}
