package domain.incidentes;

import domain.enums.TipoAlerta;
import domain.objetos.Heladera;

public class AlertaConexion extends Incidente{

  public AlertaConexion(Heladera heladera) {
    super(heladera);
  }

  public void reportePersonalizado(){
    this.alerta = TipoAlerta.CONEXION;
  };
}
