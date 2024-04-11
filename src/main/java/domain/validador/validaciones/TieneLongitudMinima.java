package domain.validador.validaciones;

public class TieneLongitudMinima implements Validacion {
  public boolean validar(String contrasenia){
    return contrasenia.length() >= 8;
  }
}
