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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Incidente {
  @Id
  @GeneratedValue
  private long id;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_hora",columnDefinition = "DATETIME")
  protected Date fechaHora;
  @ManyToOne
  @JoinColumn(name = "id_heladera")
  protected Heladera heladeraAfectada;
  @Enumerated(EnumType.STRING)
  protected TipoIncidente tipoIncidente;
  public  void reportarIncidente(String mensaje){
    this.heladeraAfectada.setEstadoActivo(false);
    this.heladeraAfectada.getIncidentes().add(this);
    //informo a tecnico de falla
    CalculadorZonaDeHeladeras calculadorZonaDeHeladeras=new CalculadorZonaDeHeladeras();
    Tecnico tecnico=calculadorZonaDeHeladeras.tecnicoMasCercanoAHeladera(heladeraAfectada);
    tecnico.getNotificador().enviarNotificacion(
            tecnico.getContacto()
            ,new Mensaje(mensaje,new Date()));
  }

}
