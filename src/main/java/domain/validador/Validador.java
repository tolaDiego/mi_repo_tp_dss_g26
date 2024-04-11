package domain.validador;

import domain.validador.validaciones.Validacion;
import java.util.ArrayList;
import java.util.List;

public class Validador {

  public Validador(){}
  private List<Validacion> Validaciones = new ArrayList<>();
  public boolean validarPassword(String contrasenia){
    return Validaciones.stream().allMatch(v -> v.validar(contrasenia));
  }

  public void agregarValidacion(Validacion validacion){
    Validaciones.add(validacion);
  }
}
