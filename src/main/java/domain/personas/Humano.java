package domain.personas;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.CamposArchivo;
import domain.accesorios.Contacto;
import domain.accesorios.Documento;
import domain.accesorios.TipoDocumento;
import domain.calculadorPuntos.CalculadorPuntos;
import domain.colaboraciones.*;
import domain.incidentes.FallaTecnica;
import domain.objetos.Heladera;
import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Humano  {
    private Documento documento;
    private int id=0;

    private String nombre;

    private   String apellido;
    private String direccion;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    private Date fechaNacimiento;
    private List<Contacto> contactos;
    private List<TarjertaRepartida> tarjertaRepartidas;
    private List<DistribucionVianda> distribucionViandas;
    private List<DonacionVianda> donacionViandas;
    private List<DonacionDinero> donacionDinero;

    private List<Oferta> ofertasCanjeadas;
    private CalculadorPuntos calculador;
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

        return this.distribucionViandas.stream().mapToDouble(d->d.getCantidadViandas()).sum();
    }

    public void reportarFallaTecnica(Heladera heladera,String descripcion){
        FallaTecnica falla = new FallaTecnica(heladera,this, descripcion);
        falla.reportar();
    }

    public boolean agregarColaboracion(DistribucionVianda contribucion) {
        return  this.distribucionViandas.add(contribucion);
    }
    public boolean agregarColaboracion(TarjertaRepartida colab){
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
        return this.ofertasCanjeadas.stream().mapToDouble(oferta->oferta.getPuntosNecesarios()).sum();
    }
    public double pesosDonados(){
        return  this.donacionDinero.stream().mapToDouble(donacion->donacion.getMonto()).sum();
    }

    public void imprimir(){
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Documento:" + documento.getTipoDoc() + documento.getNumero());
    }
}

