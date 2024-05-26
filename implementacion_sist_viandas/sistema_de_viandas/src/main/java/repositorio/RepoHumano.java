package repositorio;


import domain.personas.Humano;

import java.util.ArrayList;
import java.util.List;

public class RepoHumano {
        private final List<Humano> humanos = new ArrayList<>();

        public boolean agregarHumano(Humano humano) {

            return humanos.add(humano);
        }

        public List<Humano> retornarHumanos() {
            return humanos;
        }

        public Humano retornarHumanoPor(Integer id) {
            return humanos.stream()
                    .filter(humano -> humano.getId()== id.intValue())
                    .findFirst()
                    .orElse(null);
        }
        public boolean eliminarHumanoPor(Integer id){

                return  humanos.removeIf(humano->humano.getId()==id.intValue());
        }
    }

