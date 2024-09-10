package domain.objetos.sensorTemp;

import domain.enums.TipoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteAlerta;
import domain.objetos.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "sensor_temperatura")
public class SensorTemperatura implements  Job {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "temperatura_maxima_grados")
    private double tempMaxima;
    @Column(name = "temperatura_minima_grados",scale = 5,precision = 3)
    private  double tempMinima;
    @Column(name = "temperatura_minima_grados",scale = 5,precision = 3)
    private  double ultimaTempRegistrada;
    @Column(name = "fecha_ultimo_registro",columnDefinition = "DATETIME")
    private Date fechaUltimoRegistro;
    @OneToOne(mappedBy = "sensorTemperatura")
    private Heladera heladera;
    public boolean superaRangoDeTemperatura(double temperatura){
        return this.tempMinima>temperatura && temperatura>this.tempMaxima;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        verificarFallaDeConexion();
    }

    public synchronized void  registrarTemperatura( String temperatura) {
            this.ultimaTempRegistrada=Double.parseDouble(temperatura);
            this.fechaUltimoRegistro=new Date();
            if(superaRangoDeTemperatura(Double.parseDouble(temperatura)) ){
                Incidente alertaTemp=new IncidenteAlerta(new Date(),this.heladera ,TipoIncidente.ALERTA_TEMPERATURA);
                alertaTemp.reportarIncidente("Alerta! Temperatura fuera de rango establecido en: "+heladera.getUbicacion().getDireccion());
        }
    }
    public synchronized void verificarFallaDeConexion(){
        Date ahora = new Date();
        long diferenciaTiempo = ahora.getTime() - fechaUltimoRegistro.getTime();
        if (diferenciaTiempo >= 5 * 60 * 1000) { // 5 minutos en milisegundos
            // Generar un incidente de fallo de conexión
            Incidente falla = new IncidenteAlerta(new Date(),this.heladera, TipoIncidente.ALERTA_CONEXION);
            falla.reportarIncidente("Alerta!! Error de conexion. No se puede recibir la temperatura de: "+heladera.getUbicacion().getDireccion());
        }
    }

}
