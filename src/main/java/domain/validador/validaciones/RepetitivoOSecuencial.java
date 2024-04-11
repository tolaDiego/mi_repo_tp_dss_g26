package domain.validador.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepetitivoOSecuencial implements Validacion {
  final String repetitivo = "(\\w)\\1+";
  final String secuenciaNumerica = "123|234|345|456|567|678|789";
  final String secuenciaAlfabetica = "abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz\"";

  public boolean validar(String contrasenia) {
    Pattern pattern = Pattern.compile(repetitivo + "|" + secuenciaNumerica + "|" + secuenciaAlfabetica);
    Matcher matcher = pattern.matcher(contrasenia);
    return !(matcher.find());
  }
}
