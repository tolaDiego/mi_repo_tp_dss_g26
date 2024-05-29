package domain.lectorCSV;

import domain.personas.Humano;
import java.util.List;

public class Lectura {


  public static void main(String[] args) {
    CSVLector csvReader = new CSVLector("Recursos/ejemplo_colaboradores.csv", ",");
    List<Humano> personas = csvReader.leerCSV();

    for (Humano persona : personas) {
      persona.imprimir();
    }
  }

}
