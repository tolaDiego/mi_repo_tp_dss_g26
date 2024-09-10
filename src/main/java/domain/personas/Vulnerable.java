package domain.personas;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Documento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter

@Entity
@Table(name = "vulnerable")
public class Vulnerable {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "en_situacion_de_calle")
    public  boolean estaEnSituacionDeCalle;
    @Column(name = "menores_a_cargo")
    public int menoresACargo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro",columnDefinition = "DATETIME")
    public Date fechaDeRegistro;
    @Column(name = "fecha_nacimiento",columnDefinition = "DATETIME")
    public Date fechaDeNacimiento;
    @Column(name = "nombre",columnDefinition = "VARCHAR(100)")
    public  String nombre;
    @Column(name = "domicilio",columnDefinition = "VARCHAR(200)")
    public String domicilio;
    @Embedded
    public Documento documento;

 public Vulnerable(){

 }
}
