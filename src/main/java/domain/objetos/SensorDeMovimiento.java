package domain.objetos;

import domain.incidentes.AlertaFraude;
import domain.incidentes.Incidente;

public class SensorDeMovimiento {
    public void enviarAlerta(Heladera heladera){
        Incidente incidente = new AlertaFraude(heladera);
        incidente.reportar();
        }
    }

