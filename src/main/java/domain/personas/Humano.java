package domain.personas;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.CamposArchivo;
import domain.accesorios.Contacto;
import domain.accesorios.Documento;
import domain.accesorios.TipoDocumento;
import domain.colaboraciones.Colaboracion;
import domain.colaboraciones.DistribucionVianda;
import domain.colaboraciones.DonacionVianda;
import domain.colaboraciones.TarjertaRepartida;
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
    private List<Colaboracion> colaboraciones;
    private double puntosCanjeados;
    public Humano(){
        puntosCanjeados=0;
        this.colaboraciones=new ArrayList<>();
        this.contactos=new ArrayList<>();
    }
    public Humano(CamposArchivo datos){
        this.colaboraciones=new ArrayList<>();
        this.contactos=new ArrayList<>();
        puntosCanjeados=0;
        this.documento = new Documento(TipoDocumento.valueOf(datos.getTipoDoc()),datos.getDocumento());

        this.nombre = datos.getNombre();
        this.apellido = datos.getApellido();
        Contacto contacto=new Contacto("MAIL",datos.getMail());
        this.contactos.add(contacto);
    }

    public int cantTarjetasRepartidas(){
                int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof TarjertaRepartida){
                    cantidad++;
                }
        }
        return cantidad;
    }
    public int cantDonacionVianda(){
        int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof DonacionVianda){
                cantidad++;
            }
        }
        return cantidad;
    }
    public int cantDistribucionVianda(){
        int cantidad=0;
        for (Colaboracion colaboracion : colaboraciones) {
            if(colaboracion instanceof DistribucionVianda)
                cantidad += ((DistribucionVianda) colaboracion).getCantidadViandas();
        }
        return cantidad;
    }
    public boolean agregarColaboracion(Colaboracion colab){
        return  this.colaboraciones.add(colab);
    }
    public double puntaje() {
        return  colaboraciones.stream().mapToDouble(colab->colab.puntaje()).sum()-puntosCanjeados;
    }
    public void canjearPuntos(double puntosNecesarios){
        puntosCanjeados+=puntosNecesarios;
    }

    public void imprimir(){
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Documento:" + documento.getTipoDoc() + documento.getNumero());
    }
}

