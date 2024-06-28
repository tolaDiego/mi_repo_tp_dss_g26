package domain.lectorCSV;

import domain.accesorios.CamposArchivo;
import domain.personas.Humano;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class CSVLector {

  private String archivoCSV;
  private String separador;

  public CSVLector(String archivoCSV, String separador) {
    this.archivoCSV = archivoCSV;
    this.separador = separador;
  }

  public  List<Humano> leerCSV() {
    List<Humano> personas = new ArrayList<>();
    String linea;
    boolean esPrimeraLinea = true;

    try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
      while ((linea = br.readLine()) != null) {
        if (esPrimeraLinea) {
          esPrimeraLinea = false;
          continue; // Saltar la primera línea (encabezados)
        }
        String[] datos = linea.split(separador);
        String tipoDoc = datos[0];
        String doc = datos[1];
        String nombre = datos[2];
        String apellido = datos[3];
        String mail = datos[4];
        String fechaColabo =datos[5];
        String formaColabo = datos[6];
        String cantidad = datos[7];

        CamposArchivo create = new CamposArchivo(tipoDoc,doc,nombre,apellido,mail,fechaColabo,formaColabo,cantidad);
        //Humano persona = new Humano(create);
        Humano persona  =new Humano();
        personas.add(persona);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return personas;
  }

}
