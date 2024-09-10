package domain.objetos;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Ubicacion;
import domain.enums.EstadoVianda;
import domain.incidentes.Incidente;
import domain.objetos.sensorTemp.SensorTemperatura;
import lombok.Getter;
import lombok.Setter;
import suscripciones.ISuscripcionObservable;

import javax.persistence.*;
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
    @Column(name = "capacidad",columnDefinition = "INTEGER")
    private int capacidad;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio_funcionamiento", columnDefinition = "DATETIME")
    private Calendar fechaInicioFuncionamiento;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_final_funcionamiento", columnDefinition = "DATETIME")
    private Calendar fechaFinalServicio;
    @ManyToOne
    @Column(name = "id_ubicacion")
    private Ubicacion ubicacion;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sensor_movimiento")
    private SensorDeMovimiento sensorMovimiento;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sensor_temperatura")
    private SensorTemperatura sensorTemperatura;
    @OneToMany
    @JoinColumn(name = "id_heladera")  // Clave foránea en la tabla vianda
    private List<Vianda> viandas;
//    @OneToMany
//    @JoinColumn(name = "id_heladera")  // Clave foránea en la tabla vianda
//    private List<Vianda> viandasRetiradas;
//    @OneToMany
//    @JoinColumn(name = "id_heladera")  // Clave foránea en la tabla vianda
//    private List<Vianda> viandasColocadas;
    @OneToMany(mappedBy = "heladeraAfectada")
    private List<Incidente> incidentes;
    @OneToOne
    @JoinColumn(name = "stock_max_id")
    private ISuscripcionObservable escucharStockMax;

    @OneToOne
    @JoinColumn(name = "stock_min_id")
    private ISuscripcionObservable escucharStockMin;

    @OneToOne
    @JoinColumn(name = "estado_id")
    private ISuscripcionObservable escucharEstado;

    @Column(name = "estado_activo")
    private boolean estadoActivo;
    public Heladera(){
        viandas=new ArrayList<>();
//        viandasColocadas=new ArrayList<>();
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
    public void sacarViandas(List<Long> viandaRetiradas){
        for (Long viandaRetirada : viandaRetiradas) {
            // Buscar la vianda en la lista principal (viandas)
            for (Vianda vianda : viandas) {
                if (vianda.getId() == viandaRetirada) {

                    vianda.setEstadoVianda(EstadoVianda.RETIRADA);
                    break; // Salimos del loop interno una vez encontrada
                }
            }
        }
        escucharStockMin.notificar(this);
    }
    public void agregarViandas(List<Vianda> viandasNuevas){

      viandas.addAll(viandasNuevas);
      escucharStockMax.notificar(this);
    }
    public int cantidadViandasActuales(){
        return  viandas.stream().filter(v->v.getEstadoVianda().equals(EstadoVianda.DISPONIBLE)).toList().size();
    }
    public int cantidadIncidentes() {return incidentes.size();}
    public int cantidadViandasColocadas() {
        return viandas.size();

    }
    public int cantidadViandasRetiradas() {
        return viandas.stream().filter(v->v.getEstadoVianda().equals(EstadoVianda.RETIRADA)).toList().size();
    }
}
