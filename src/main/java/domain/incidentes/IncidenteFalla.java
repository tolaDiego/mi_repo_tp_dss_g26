package domain.incidentes;


import adapters.notificadores.Mensaje;
import domain.enums.TipoIncidente;
import domain.objetos.Heladera;
import domain.personas.Humano;
import domain.personas.Tecnico;
import heladerasDeZona.CalculadorZonaDeHeladeras;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "incidente_falla")
public class IncidenteFalla implements Incidente{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "fecha_hora",columnDefinition = "DATETIME")
    private Date fechaHora;
    @ManyToOne
    @JoinColumn(name = "id_heladera")
    private Heladera heladeraAfectada;
    @Enumerated(EnumType.STRING)
    private TipoIncidente tipo;
    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Humano colaborador;
    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "url_foto",columnDefinition = "VARCHAR(255)")
    private String urlFoto;
    public IncidenteFalla(Heladera heladeraAfectada, TipoIncidente tipo, Humano colaborador, String descripcion, String urlFoto) {
        this.heladeraAfectada = heladeraAfectada;
        this.tipo = tipo;
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
    }

    @Override
    public void reportarIncidente(String mensajeParatecnico) {
        this.heladeraAfectada.setEstadoActivo(false);
        this.heladeraAfectada.getIncidentes().add(this);
        //informo a tecnico de falla
        CalculadorZonaDeHeladeras calculadorZonaDeHeladeras=new CalculadorZonaDeHeladeras();
        Tecnico tecnico=calculadorZonaDeHeladeras.tecnicoMasCercanoAHeladera(heladeraAfectada);
        tecnico.getNotificador().enviarNotificacion(
                tecnico.getContacto()
                ,new Mensaje(mensajeParatecnico,new Date()));
    }
}
