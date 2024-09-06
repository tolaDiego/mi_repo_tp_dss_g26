package domain.incidentes;

import adapters.notificadores.Mensaje;
import domain.enums.TipoIncidente;
import domain.objetos.Heladera;
import domain.personas.Tecnico;
import heladerasDeZona.CalculadorZonaDeHeladeras;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incidente_alerta")
public class IncidenteAlerta implements  Incidente{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "fecha_hoa",columnDefinition = "DATETIME")
    private Date fechaHora;
    @ManyToOne
    @JoinColumn(name = "id_heladera")
    private Heladera heladeraAfectada;
    @Enumerated(EnumType.STRING)
    private TipoIncidente tipo;

    public IncidenteAlerta(Date fechaHora, Heladera heladeraAfectada, TipoIncidente tipo) {
        this.fechaHora = fechaHora;
        this.heladeraAfectada = heladeraAfectada;
        this.tipo = tipo;
    }

    @Override
    public void reportarIncidente(String descripcion) {
        heladeraAfectada.setEstadoActivo(false);
        heladeraAfectada.getIncidentes().add(this);
        //busacar tecnico mas cercano y enviar mensaje a tecnico
        CalculadorZonaDeHeladeras calculadorZona=new CalculadorZonaDeHeladeras();
        Tecnico tecnico=calculadorZona.tecnicoMasCercanoAHeladera(heladeraAfectada);
                tecnico.getNotificador().enviarNotificacion(
                tecnico.getContacto()
                ,new Mensaje(descripcion,new Date()));

    }

}
