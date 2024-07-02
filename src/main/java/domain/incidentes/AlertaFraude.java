package domain.incidentes;

import domain.enums.TipoAlerta;
import domain.objetos.Heladera;

public class AlertaFraude extends Incidente{

  public AlertaFraude(Heladera heladera){
    super(heladera);
  }

  public void reportePersonalizado(){
    this.alerta = TipoAlerta.FRAUDE;
  };

}
