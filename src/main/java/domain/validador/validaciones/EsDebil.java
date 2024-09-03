package domain.validador.validaciones;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

public class EsDebil implements Validacion {
  public boolean validar(String nuevaContrasenia) {
    final String nombreDeArchivo = "10-million-password-list-top-10000.txt";
    Stream<String> contrasenias = null;
    try {
      contrasenias = lines(Paths.get(nombreDeArchivo));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return contrasenias.noneMatch(contrasenia -> contrasenia.equals(nuevaContrasenia));
  }
}
