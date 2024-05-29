package domain.personas;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.accesorios.Documento;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter


public class Vulnerable {
    private int id;
    public  boolean estaEnSituacionDeCalle;
    public int menoresACargo;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    public Date fechaDeRegistro;
    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    public Date fechaDeNacimiento;
    public  String nombre;
    public String domicilio;
    public Documento documento;

 public Vulnerable(){

 }
}
