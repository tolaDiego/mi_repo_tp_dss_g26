package domain.objetos;

import domain.incidentes.AlertaFraude;
import domain.incidentes.Incidente;

public class SensorDeMovimiento implements Sensor {
    public void enviarAlerta(Heladera heladera){
        Incidente incidente = new AlertaFraude(heladera);
        incidente.reportar();
        }
    }

