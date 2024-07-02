package domain.incidentes;


import domain.objetos.Heladera;
import domain.personas.Humano;

public class FallaTecnica extends Incidente {

  private Humano colaborador;

  private String descripcion;

  public FallaTecnica(Heladera heladera, Humano colaborador , String descripcion) {
    super(heladera);
    this.colaborador=colaborador;
    this.descripcion = descripcion;
  }

  @Override
  public void reportePersonalizado() {

  }

}
