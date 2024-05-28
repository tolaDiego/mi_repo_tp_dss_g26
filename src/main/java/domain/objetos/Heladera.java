package domain.objetos;

import domain.accesorios.Ubicacion;

import java.util.Calendar;
import java.util.List;


public class Heladera {
  private int capacidad;
    private Calendar fechaInicioFuncionamiento;
    private Ubicacion ubicacion;
    private SensorDeMovimiento sensorMovimiento;
    private SensorTemperatura sensorTemperatura;
    private List<Vianda> viandas;
    public boolean estaActiva() {
        return true;
    }

    public long mesesActiva() {
      Calendar fechaFinalServicio= Calendar.getInstance();
      int anioFinal=fechaFinalServicio.get(Calendar.YEAR);
      int mesFinal=fechaFinalServicio.get(Calendar.MONTH);
      int anioInicial= fechaInicioFuncionamiento.get(Calendar.YEAR);
      int mesInicio=fechaInicioFuncionamiento.get(Calendar.MONTH);


      return Math.abs((anioFinal-anioInicial)*12+mesFinal-mesInicio);
    }

    public void sacarVianda(int idVianda){
      viandas.remove(idVianda);
    }

    public void agregarViandas(List<Vianda> viandasNuevas){
      viandas.addAll(viandasNuevas);
    }
}
