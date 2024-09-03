package domain.objetos;

import domain.enums.TipoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteAlerta;

import java.util.Date;

public class SensorDeMovimiento   {
    private Heladera heladera;
    public void procesarAlerta(){
          Incidente aperturaNoRegistrada= new IncidenteAlerta(new Date(),this.heladera, TipoIncidente.ALERTA_FRAUDE);
           aperturaNoRegistrada.reportarIncidente("Alerta!!Se intento realizar un movimiento no programado en: "+this.heladera.getUbicacion().getDireccion());
        }
    }

