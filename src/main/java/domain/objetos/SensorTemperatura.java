package domain.objetos;

import domain.incidentes.AlertaFraude;
import domain.incidentes.AlertaTemperatura;
import domain.incidentes.Incidente;

public class SensorTemperatura {
    private double tempMaxima;
    private  double tempMinima;
    private  double ultimaTempRegistrada;

    public double getTempMaxima() {
        return tempMaxima;
    }

    public void setTempMaxima(double tempMaxima) {
        this.tempMaxima = tempMaxima;
    }

    public double getTempMinima() {
        return tempMinima;
    }

    public void setTempMinima(double tempMinima) {
        this.tempMinima = tempMinima;
    }

    public void enviarAlerta(Heladera heladera){
        Incidente incidente = new AlertaTemperatura(heladera);
        incidente.reportar();
    }


    public double getUltimaTempRegistrada() {
        return ultimaTempRegistrada;
    }

    public void setUltimaTempRegistrada(double ultimaTempRegistrada) {
        this.ultimaTempRegistrada = ultimaTempRegistrada;
    }



}
