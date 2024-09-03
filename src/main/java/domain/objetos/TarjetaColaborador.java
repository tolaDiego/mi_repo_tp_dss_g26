package domain.objetos;

import domain.accesorios.AperturaColab;
import domain.accesorios.SolicitudApertura;
import domain.personas.Humano;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;
@Setter
@Getter

public class TarjetaColaborador {
    private long codigo;
    private Humano colaborador;
    private List<AperturaColab> aperturas;
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
                        Calendar limite = (Calendar) solicitud.getFechaDeSolicutud().clone();
                        limite.add(Calendar.HOUR_OF_DAY, horasLimite);
                        return limite;
                    })
                    .toList();

            // Verificar si alguna de las solicitudes supera el límite permitido
            return horariosLimite.stream()
                    .anyMatch(horarioLimite -> nuevaApertura.getFechaDeUso().before(horarioLimite));

    }
}
