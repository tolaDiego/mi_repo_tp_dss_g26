package domain.incidentes;

import adapters.notificadores.Mensaje;
import domain.enums.TipoIncidente;
import domain.objetos.Heladera;
import domain.personas.Tecnico;
import heladerasDeZona.CalculadorZonaDeHeladeras;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
public class IncidenteAlerta implements  Incidente{
    private Date fechaHora;
    private Heladera heladeraAfectada;
    private TipoIncidente tipo;
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
