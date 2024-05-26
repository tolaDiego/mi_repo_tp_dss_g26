package domain.personas;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter


public class Vulnerable {
    private int id;
    public  boolean estaEnSituacionDeCalle;
    public int menoresACargo;
    public Date fechaDeRegistro;
    public Date fechaDeNacimiento;
    public  String nombre;
    public String domicilio;
    public String tipoDeDocumento;
    public int numeroDeDocumento;

 public void Vulnerable(){

 }
}
