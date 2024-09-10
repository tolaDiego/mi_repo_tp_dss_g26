package domain.objetos;

import domain.enums.TipoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteAlerta;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "sensor_movimiento")
public class SensorDeMovimiento   {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(mappedBy = "sensorMovimiento")
    private Heladera heladera;
    public void procesarAlerta(){
          Incidente aperturaNoRegistrada= new IncidenteAlerta(new Date(),this.heladera, TipoIncidente.ALERTA_FRAUDE);
           aperturaNoRegistrada.reportarIncidente("Alerta!!Se intento realizar un movimiento no programado en: "+this.heladera.getUbicacion().getDireccion());
        }
    }

