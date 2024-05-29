package domain.objetos;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Heladera {
  private int capacidad;
    private Calendar fechaInicioFuncionamiento;
    private Calendar fechaFinalServicio;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date fechaInicioFuncionamiento;
    private Ubicacion ubicacion;
    private SensorDeMovimiento sensorMovimiento;
    private SensorTemperatura sensorTemperatura;
    private List<Vianda> viandas;

    private boolean estadoActivo;
    public void cambiarEstado (boolean estado) {
        estadoActivo = estado;
        if(!estado){ fechaFinalServicio= Calendar.getInstance();}

    }

    public long mesesActiva() {
      if(estadoActivo) {fechaFinalServicio= Calendar.getInstance();}
      int anioFinal=fechaFinalServicio.get(Calendar.YEAR);
      int mesFinal=fechaFinalServicio.get(Calendar.MONTH);
      int anioInicial= fechaInicioFuncionamiento.get(Calendar.YEAR);
      int mesInicio=fechaInicioFuncionamiento.get(Calendar.MONTH);


      return Math.abs((anioFinal-anioInicial)*12+mesFinal-mesInicio);
    public int mesesActiva() {
//      Calendar fechaFinalServicio= Calendar.getInstance();
//      int anioFinal=fechaFinalServicio.get(Calendar.YEAR);
//      int mesFinal=fechaFinalServicio.get(Calendar.MONTH);
//      int anioInicial= fechaInicioFuncionamiento.get(Calendar.YEAR);
//      int mesInicio=fechaInicioFuncionamiento.get(Calendar.MONTH);
//
//
//      return Math.abs((anioFinal-anioInicial)*12+mesFinal-mesInicio);
      return 1;
    }

    public void sacarVianda(int idVianda){
      viandas.remove(idVianda);
    }

    public void agregarViandas(List<Vianda> viandasNuevas){
      viandas.addAll(viandasNuevas);
    }
}
