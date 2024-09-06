package domain.objetos;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Ubicacion;
import domain.incidentes.Incidente;
import domain.objetos.sensorTemp.SensorTemperatura;
import lombok.Getter;
import lombok.Setter;
import suscripciones.ISuscripcionObservable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "heladera")
public class Heladera {
    @Id
    @GeneratedValue
    private long id;
    private int capacidad;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Calendar fechaInicioFuncionamiento;
    private Calendar fechaFinalServicio;
    private Ubicacion ubicacion;
    private SensorDeMovimiento sensorMovimiento;
    private SensorTemperatura sensorTemperatura;
    private List<Vianda> viandas;
    private List<Vianda> viandasRetiradas;
    private List<Vianda> viandasColocadas;
    private List<Incidente> incidentes;
    private ISuscripcionObservable escucharStockMax;
    private ISuscripcionObservable escucharStockMin;
    private ISuscripcionObservable escucharEstado;
    private boolean estadoActivo;
    public Heladera(){
        viandasRetiradas=new ArrayList<>();
        viandasColocadas=new ArrayList<>();
        incidentes=new ArrayList<>();

    }

    public void setEstadoActivo (boolean estado) {
        estadoActivo = estado;
        if(!estado){
            fechaFinalServicio= Calendar.getInstance();
        }
        else{fechaInicioFuncionamiento = Calendar.getInstance();}
        escucharEstado.notificar(this);
    }

    public long mesesActiva() {
        if (estadoActivo) {
            fechaFinalServicio = Calendar.getInstance();
        }
        int anioFinal = fechaFinalServicio.get(Calendar.YEAR);
        int mesFinal = fechaFinalServicio.get(Calendar.MONTH);
        int anioInicial = fechaInicioFuncionamiento.get(Calendar.YEAR);
        int mesInicio = fechaInicioFuncionamiento.get(Calendar.MONTH);


        return Math.abs((anioFinal - anioInicial) * 12 + mesFinal - mesInicio);
    }
//    public void sacarVianda(int idVianda){
//      viandasRetiradas.remove(idVianda);
//    }
    public void sacarViandas(List<Vianda> viandaRetiradas){
        viandasRetiradas.addAll(viandaRetiradas);
        escucharStockMin.notificar(this);
    }
    public void agregarViandas(List<Vianda> viandasNuevas){

      viandasColocadas.addAll(viandasNuevas);
      escucharStockMax.notificar(this);
    }
    public int cantidadViandasActuales(){
        return   viandasColocadas.size()-viandasRetiradas.size();
    }
    public int cantidadIncidentes() {return incidentes.size();}
    public int cantidadViandasColocadas() {
        return viandasColocadas.size();

    }
    public int cantidadViandasRetiradas() {
        return viandasRetiradas.size();
    }
}
