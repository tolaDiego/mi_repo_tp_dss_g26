package domain.personas;

import domain.enums.TipoEntidad;
import domain.calculadorPuntos.CalculadorPuntos;
import domain.colaboraciones.ColabOferta;
import domain.colaboraciones.DonacionDinero;
import domain.colaboraciones.ServicioAdministracion;
import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Juridica {
    private int id;
    private String razonSocial;
    private TipoEntidad tipo;
    private String rubro;
    private  String direccion;
    private List<DonacionDinero> donacionesDinero;
    private  List<ColabOferta> contribucionesOferta;
    private List<ServicioAdministracion> heladerasAdministradas;
    private List<Oferta> ofertasCanjeadas;
    private CalculadorPuntos calculador;
    public Juridica(){
        this.donacionesDinero=new ArrayList<>();
        this.contribucionesOferta = new ArrayList<>();
        this.heladerasAdministradas=new ArrayList<>();
        this.ofertasCanjeadas=new ArrayList<>();
        this.calculador=CalculadorPuntos.getInstanceCalculadorPuntos();
    }

    public double calcularPuntos(){
        return calculador.calcularPuntosDe(this)-this.puntosCanjeados();
    }
    private double puntosCanjeados() {
        return this.ofertasCanjeadas.stream().mapToDouble(oferta->oferta.getPuntosNecesarios()).sum();
    }

    public boolean agregarColacoracion(DonacionDinero donacion) {
        return this.donacionesDinero.add(donacion);
    }
    public boolean agregarColacoracion(ColabOferta oferton) {
        return this.contribucionesOferta.add(oferton);
    }
    public boolean agregarColacoracion(ServicioAdministracion administracion) {
        return this.heladerasAdministradas.add(administracion);
    }
    public double cantHeladerasActivas() {
        //TODO
        return 0;
    }

    public double totalMesesActivas() {
        //TODO
        return 0;
    }

    public double pesosDonados() {
        return  this.donacionesDinero.stream().mapToDouble(donacion->donacion.getMonto()).sum();
    }
}

