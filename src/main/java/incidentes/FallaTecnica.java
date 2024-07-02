package incidentes;


import domain.personas.Humano;

public class FallaTecnica extends Incidente {

  private Humano colaborador;

  public void reportePersonalizado(Humano colaborador){
    this.colaborador = colaborador;
  };

}
