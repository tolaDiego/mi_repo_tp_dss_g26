package repositorio;


import domain.accesorios.Documento;
import domain.personas.Humano;

import java.util.ArrayList;
import java.util.List;

public class RepoHumano implements  IRepoHumano {
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
    public Humano retornarHumanoPorDoc(Documento documento) {
        return humanos.stream()
                .filter(humano -> humano.getDocumento().getTipoDoc() == documento.getTipoDoc() &&
                        humano.getDocumento().getNumero().equals(documento.getNumero()))
                .findFirst().
                orElse(null);
    }

    public Humano actualizarHumano(Humano nuevo) {

       Humano humano=    retornarHumanoPorDoc(nuevo.getDocumento());

                humano.setNombre(nuevo.getNombre());
                humano.setApellido(nuevo.getApellido());
                humano.setDireccion(nuevo.getDireccion());

       return humano;
        }

    @Override
    public List<Humano> getAll() {
        return null;
    }
}

