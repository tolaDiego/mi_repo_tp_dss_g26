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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sensor_movimiento")
    private SensorDeMovimiento sensorMovimiento;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sensor_temperatura")
    private SensorTemperatura sensorTemperatura;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_heladera")  // Clave foránea en la tabla vianda
    private List<Vianda> viandas;

    @OneToMany(mappedBy = "heladeraAfectada",cascade = CascadeType.PERSIST)
    private List<Incidente> incidentes;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_max_id")
    private ISuscripcionObservable escucharStockMax;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_min_id")
    private ISuscripcionObservable escucharStockMin;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estado_id")
    private ISuscripcionObservable escucharEstado;

    @Column(name = "estado_activo")
    private boolean estadoActivo;
    public Heladera(){
        viandas=new ArrayList<>();

        incidentes=new ArrayList<>();

    }

    public void setEstadoActivo (boolean estado) {
        estadoActivo = estado;
        if(!estado){
            fechaFinalServicio= Calendar.getInstance();
        }
        else{fechaInicioFuncionamiento = Calendar.getInstance();}
        if(escucharEstado!=null) {
            escucharEstado.notificar(this);
        }
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

    public void setSensorMovimiento(SensorDeMovimiento sensorMovimiento) {
        this.sensorMovimiento = sensorMovimiento;
        sensorMovimiento.setHeladera(this);
    }

    public void setSensorTemperatura(SensorTemperatura sensorTemperatura) {
        this.sensorTemperatura = sensorTemperatura;
        sensorTemperatura.setHeladera(this);
    }
    @Override
    public String toString() {
        return "Heladera{" +
                "\nid=" + id +
                "\n, capacidad=" + capacidad +
                "\n, fechaInicioFuncionamiento=" + (fechaInicioFuncionamiento != null ? fechaInicioFuncionamiento.getTime() : "N/A") +
                "\n, fechaFinalServicio=" + (fechaFinalServicio != null ? fechaFinalServicio.getTime() : "N/A") +
                "\n, ubicacion=" + (ubicacion != null ? ubicacion.toString() : "N/A") +
                "\n, sensorMovimiento=" + (sensorMovimiento != null ? sensorMovimiento.toString() : "N/A") +
                "\n, sensorTemperatura=" + (sensorTemperatura != null ? sensorTemperatura.toString() : "N/A") +
                "\n, viandas=" + (viandas != null ? viandas.size() : 0) +
                "\n, incidentes=" + (incidentes != null ? incidentes.size() : 0) +
                "\n, escucharStockMax=" + (escucharStockMax != null ? escucharStockMax.toString() : "N/A") +
                "\n, escucharStockMin=" + (escucharStockMin != null ? escucharStockMin.toString() : "N/A") +
                "\n, escucharEstado=" + (escucharEstado != null ? escucharEstado.toString() : "N/A") +
                "\n, estadoActivo=" + estadoActivo +
                '}';
    }
}
