package domain.objetos;

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

    public double getUltimaTempRegistrada() {
        return ultimaTempRegistrada;
    }

    public void setUltimaTempRegistrada(double ultimaTempRegistrada) {
        this.ultimaTempRegistrada = ultimaTempRegistrada;
    }



}
