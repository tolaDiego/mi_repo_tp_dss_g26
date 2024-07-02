package domain.incidentes;

import domain.enums.TipoAlerta;
import domain.objetos.Heladera;

public class AlertaTemperatura extends Incidente{

  public AlertaTemperatura(Heladera heladera) {
    super(heladera);
  }

  public void reportePersonalizado(){
    this.alerta = TipoAlerta.TEMPERATURA;
  }

}
