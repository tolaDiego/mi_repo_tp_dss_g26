package domain.personas;

import domain.enums.TipoEntidad;
import domain.calculadorPuntos.CalculadorPuntos;
import domain.colaboraciones.ColabOferta;
import domain.colaboraciones.DonacionDinero;
import domain.colaboraciones.ServicioAdministracion;
import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "juridica")
public class Juridica {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "razon_social",columnDefinition = "VARCHAR(100)")
    private String razonSocial;
    @Enumerated(EnumType.STRING)
    private TipoEntidad tipo;
    @Column(name = "rubro",columnDefinition = "VARCHAR(50)")
    private String rubro;
    @Column(name = "direccion",columnDefinition = "VARCHAR(200)")
    private  String direccion;
    @OneToMany
    @JoinColumn(name = "id_juridica")
    private List<DonacionDinero> donacionesDinero;
    @OneToMany
    @JoinColumn(name = "id_juridica")
    private  List<ColabOferta> contribucionesOferta;
    @OneToMany
    @JoinColumn(name = "id_juridica")
    private List<ServicioAdministracion> heladerasAdministradas;
    @ManyToMany
    private List<Oferta> ofertasCanjeadas;

    @Transient
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

