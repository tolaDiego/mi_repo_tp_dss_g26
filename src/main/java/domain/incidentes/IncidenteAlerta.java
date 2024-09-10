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
@DiscriminatorValue("ALERTA")
public class IncidenteAlerta extends   Incidente{
    @Enumerated(EnumType.STRING)
    private TipoIncidente tipoAlerta;
    public IncidenteAlerta(Date fechaHora, Heladera heladeraAfectada, TipoIncidente tipo) {
        this.fechaHora = fechaHora;
        this.heladeraAfectada = heladeraAfectada;
        this.tipoIncidente = tipo;
    }


}
