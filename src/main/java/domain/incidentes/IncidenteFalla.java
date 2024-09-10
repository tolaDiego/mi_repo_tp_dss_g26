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
@DiscriminatorValue("FALLA")
public class IncidenteFalla extends Incidente{

    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Humano colaborador;
    @Column(name = "descripcion",columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "url_foto",columnDefinition = "VARCHAR(255)")
    private String urlFoto;
    public IncidenteFalla(Heladera heladeraAfectada, TipoIncidente tipo, Humano colaborador, String descripcion, String urlFoto) {
        this.heladeraAfectada = heladeraAfectada;
        this.tipoIncidente = tipo;
        this.colaborador = colaborador;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
    }


}
