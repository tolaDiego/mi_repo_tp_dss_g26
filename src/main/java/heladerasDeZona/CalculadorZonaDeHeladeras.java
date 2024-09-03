package heladerasDeZona;

import domain.accesorios.AperturaColab;
import domain.accesorios.AreaCobertura;
import domain.accesorios.Ubicacion;
import domain.objetos.Heladera;
import domain.objetos.TarjetaColaborador;
import domain.personas.Humano;
import domain.personas.Tecnico;
import repositorio.IRepoTecnico;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class CalculadorZonaDeHeladeras {
    private static final int RADIO_TIERRA_KM = 6371;
   private IRepoTecnico repoTecnico;
    public static List<Heladera> heladerasFrecuentadasPara(Humano humano){
        Calendar fechaHaceDosMeses= Calendar.getInstance();
        fechaHaceDosMeses.add(Calendar.DAY_OF_MONTH, -60);
        TarjetaColaborador unaTarjeta=humano.getTarjetaColaborador();
        List<AperturaColab> aperturasUltimoMes= unaTarjeta.getAperturas().stream().filter(apertura ->
                                                        esAperturaReciente(apertura,fechaHaceDosMeses)
                                                        ). toList();
        return aperturasUltimoMes.stream().map(AperturaColab::getHeladeraUsada).toList();
    }
    public static List<Heladera> heladerasRecomendadasParaDistribucion(Humano humano){
       return heladerasFrecuentadasPara(humano).stream().filter(CalculadorZonaDeHeladeras::tieneCapacidadParaViandas).toList();
    }
    public static boolean esAperturaReciente(AperturaColab apertura,Calendar fechaBase){
        return apertura.getFechaDeUso().after(fechaBase);
    }
    public static boolean tieneCapacidadParaViandas(Heladera heladera){
        return heladera.cantidadViandasActuales()<heladera.getCapacidad();
    }
    public  Tecnico tecnicoMasCercanoAHeladera(Heladera heladera){
        return repoTecnico.getAll().stream()
                .filter(tecnico -> cubreAreaTecnico(tecnico, heladera))
                .min(Comparator.comparing(tecnico -> calcularDistancia(tecnico, heladera)))
                .orElse(null);    }
    public static boolean cubreAreaTecnico(Tecnico tecnico, Heladera healHeladera){
        AreaCobertura areaCubierta=tecnico.getAreaCobertura();
        Ubicacion puntoHeladera=healHeladera.getUbicacion();
        double distanciaHeladeraTecnico=calcularDistancia(areaCubierta.getLatitud(), areaCubierta.getLongitud(),puntoHeladera.getLatitud(),puntoHeladera.getLongitud());
        return distanciaHeladeraTecnico<= areaCubierta.getRadio();
    }
    public static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIO_TIERRA_KM * c;
    }
    private double calcularDistancia(Tecnico tecnico, Heladera heladera) {
        double lat1 = Math.toRadians(tecnico.getAreaCobertura().getLatitud());
        double lon1 = Math.toRadians(tecnico.getAreaCobertura().getLongitud());
        double lat2 = Math.toRadians(heladera.getUbicacion().getLatitud());
        double lon2 = Math.toRadians(heladera.getUbicacion().getLongitud());

        // Diferencias entre las coordenadas
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Aplicación de la fórmula del haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distancia en kilómetros
        return RADIO_TIERRA_KM * c;
    }

}
