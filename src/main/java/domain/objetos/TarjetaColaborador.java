package domain.objetos;

import domain.accesorios.AperturaColab;
import domain.accesorios.SolicitudApertura;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "tarjeta_colaborador")
public class TarjetaColaborador {

   // private Humano colaborador;
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "codigo")
    private long codigo;
    @OneToMany
    @JoinColumn(name = "id_tarjeta_colaborador")
    private List<AperturaColab> aperturas;
    @OneToMany
    @JoinColumn(name = "id_tarjeta_colaborador")
    private List<SolicitudApertura> solicitudes;
    private int horasLimite = 3;

    public void agregarApertura(AperturaColab nuevaApertura){

        if(this.chequearSolicitud(nuevaApertura)){
            aperturas.add(nuevaApertura);
        }
    }
    public void agregarApertura(SolicitudApertura nuevaSolicitud){
        solicitudes.add(nuevaSolicitud);
    }

    public boolean chequearSolicitud(AperturaColab nuevaApertura){
//        List<SolicitudApertura> filtradas;
//        List<Calendar> horarios,horariosMaximos;
//
//        filtradas = solicitudes.stream().filter(solicitud -> solicitud.getHeladeraSolicitada().equals(nuevaApertura.getHeladeraUsada())).toList();
//        horarios = filtradas.stream().map(SolicitudApertura::getFechaDeSolicutud).toList();
//        horarios.forEach(a->a.add(Calendar.HOUR_OF_DAY,horasLimite));
//
//
//        return horarios.stream().anyMatch(horario -> (horario.compareTo(nuevaApertura.getFechaDeUso())<0));

            // Filtrar las solicitudes que coinciden con la heladera solicitada
            List<SolicitudApertura> filtradas = solicitudes.stream()
                    .filter(solicitud -> solicitud.getHeladeraSolicitada().equals(nuevaApertura.getHeladeraUsada()))
                    .toList();

            // Calcular el tiempo límite para cada solicitud filtrada
            List<Calendar> horariosLimite = filtradas.stream()
                    .map(solicitud -> {
                        Calendar limite = (Calendar) solicitud.getFechaDeSolicitud().clone();
                        limite.add(Calendar.HOUR_OF_DAY, horasLimite);
                        return limite;
                    })
                    .toList();

            // Verificar si alguna de las solicitudes supera el límite permitido
            return horariosLimite.stream()
                    .anyMatch(horarioLimite -> nuevaApertura.getFechaDeUso().before(horarioLimite));

    }
}
