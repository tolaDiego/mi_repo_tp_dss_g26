package passwordTest;


import domain.validador.Validador;
import domain.validador.validaciones.EsDebil;
import domain.validador.validaciones.RepetitivoOSecuencial;
import domain.validador.validaciones.TieneLongitudMinima;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidadorPolimorficoTest {
  private Validador validador;

  @BeforeEach
  void setUp() {
    validador = new Validador();

    validador.agregarValidacion(new RepetitivoOSecuencial());
    validador.agregarValidacion(new TieneLongitudMinima());
    validador.agregarValidacion(new EsDebil());
  }

  @Test
  public void contraseniaInvalidaCuandoTieneCaracteresRepetidos() {
    String contrasenia = "holahola123";
    Assertions.assertFalse(validador.validarPassword(contrasenia));
  }

  @Test
  public void contraseñaInvalidaCuandoTieneCaracteresNumericosRepetidos() {
    String contrasenia = "11112222";
    Assertions.assertFalse(validador.validarPassword(contrasenia));
  }

  @Test
  public void contraseniaInvalidaCuandoEsDebil() {
    String contrasenia = "starwars";
    Assertions.assertFalse(validador.validarPassword(contrasenia));
  }

  @Test
  public void contraseniaInvalidaCuandoEsCorta() {
    String contrasenia = "corta";
    Assertions.assertFalse(validador.validarPassword(contrasenia));
  }


}
