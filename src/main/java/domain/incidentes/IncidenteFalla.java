package domain.incidentes;


import adapters.notificadores.Mensaje;
import domain.enums.TipoIncidente;
import domain.objetos.Heladera;
import domain.personas.Humano;
import domain.personas.Tecnico;
import heladerasDeZona.CalculadorZonaDeHeladeras;
import lombok.AllArgsConstructor;

import java.util.Date;
@AllArgsConstructor
public class IncidenteFalla implements Incidente{
    private Date fechaHora;
    private Heladera heladeraAfectada;
    private TipoIncidente tipo;
    private Humano colaborador;
    private String descripcion;
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
