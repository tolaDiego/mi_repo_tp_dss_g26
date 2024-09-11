package domain.personas;

import adapters.notificadores.Notificador;
import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Contacto;
import domain.accesorios.Documento;
import domain.calculadorPuntos.CalculadorPuntos;
import domain.colaboraciones.DistribucionVianda;
import domain.colaboraciones.DonacionDinero;
import domain.colaboraciones.DonacionVianda;
import domain.colaboraciones.ColabEntregaDeTarjeta;
import domain.enums.TipoIncidente;
import domain.incidentes.Incidente;
import domain.incidentes.IncidenteFalla;
import domain.objetos.Heladera;
import domain.objetos.Oferta;
import domain.objetos.TarjetaColaborador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "humano")
public class Humano  {
    @Id
    @GeneratedValue
    private long id;
    @Embedded
    private Documento documento;
    @Column(name = "nombre",columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(name = "apellido",columnDefinition = "VARCHAR(50)")
    private   String apellido;
    @Column(name = "direccion",columnDefinition = "VARCHAR(200)")
    private String direccion;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento",columnDefinition = "DATETIME")
    private Date fechaNacimiento;
    @OneToMany
    @JoinColumn(name = "id_humano")
    private List<Contacto> contactos;
    @OneToMany
    @JoinColumn(name="id_humano")
    private List<ColabEntregaDeTarjeta> tarjertaRepartidas;
    @OneToMany
    @JoinColumn(name="id_humano")
    private List<DistribucionVianda> distribucionViandas;
    @OneToMany
    @JoinColumn(name="id_humano")
    private List<DonacionVianda> donacionViandas;
    @OneToMany
    @JoinColumn(name="id_humano")
    private List<DonacionDinero> donacionDinero;
    @OneToOne
    @JoinColumn(name="id_humano")
    private TarjetaColaborador tarjetaColaborador;
    @ManyToMany
    private List<Oferta> ofertasCanjeadas;
    @Transient
    private CalculadorPuntos calculador;
    @OneToOne
    @JoinColumn(name = "id_notificador_colaborador")
    private Notificador notificador;
    public Humano(){
        this.tarjertaRepartidas=new ArrayList<>();
        this.distribucionViandas = new ArrayList<>();
        this.donacionDinero=new ArrayList<>();
        this.donacionViandas=new ArrayList<>();
        this.ofertasCanjeadas=new ArrayList<>() ;
        this.contactos=new ArrayList<>();
        this.calculador=CalculadorPuntos.getInstanceCalculadorPuntos();
    }
//    public Humano(CamposArchivo datos){
//        this.colaboraciones=new ArrayList<>();
//        this.contactos=new ArrayList<>();
//        puntosCanjeados=0;
//        this.documento = new Documento(TipoDocumento.valueOf(datos.getTipoDoc()),datos.getDocumento());
//
//        this.nombre = datos.getNombre();
//        this.apellido = datos.getApellido();
//        Contacto contacto=new Contacto("MAIL",datos.getMail());
//        this.contactos.add(contacto);
//    }

    public int cantTarjetasRepartidas(){

        return this.tarjertaRepartidas.size();
    }
    public int cantDonacionesDinero(){

        return this.donacionDinero.size();
    }
    public int cantDonacionVianda(){

        return this.donacionViandas.size();
    }
    public double cantDistribucionVianda(){

        return this.distribucionViandas.stream().mapToDouble(DistribucionVianda::getCantidadViandas).sum();
    }

    public void reportarFallaTecnica(Heladera heladera,String descripcion,String urlFoto){
        Incidente falla = new IncidenteFalla(heladera, TipoIncidente.FALLA_TECNICA,this,descripcion,urlFoto);
        falla.reportarIncidente("Falla Tecnica!!Hay un desperfecto en la heladera d ");
    }

    public boolean agregarColaboracion(DistribucionVianda contribucion) {
        return  this.distribucionViandas.add(contribucion);
    }
    public boolean agregarColaboracion(ColabEntregaDeTarjeta colab){
        return  this.tarjertaRepartidas.add(colab);
    }
    public double calcularPuntos() {
        return calculador.calcularPuntosDe(this)-this.puntosCanjeados();
    }
    public boolean canjearPuntos(Oferta oferta){
        if(oferta.tieneLosPuntosRequeridos(this)){
            this.ofertasCanjeadas.add(oferta);
            return   true;
        }
        return false;

    }
    public  double puntosCanjeados(){
        return this.ofertasCanjeadas.stream().mapToDouble(Oferta::getPuntosNecesarios).sum();
    }
    public double pesosDonados(){
        return  this.donacionDinero.stream().mapToDouble(DonacionDinero::getMonto).sum();
    }

    public void imprimir(){
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Documento:" + documento.getTipoDoc() + documento.getNumero());
    }
}

