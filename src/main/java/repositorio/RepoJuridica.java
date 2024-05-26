package repositorio;

import domain.personas.Juridica;

import java.util.ArrayList;
import java.util.List;

public class RepoJuridica {
    private final List<Juridica> organizaciones = new ArrayList<>();

    public boolean agregarJuridica(Juridica organizacion) {

        return  organizaciones.add(organizacion);
    }

    public List<Juridica> retornarJuridicas() {
        return organizaciones;
    }

    public Juridica retornarJuridicaPor(Integer id) {
        return organizaciones.stream()
                .filter(org -> org.getId()==(id.intValue()))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarJuridicaPor(Integer id) {
        return    organizaciones.removeIf(o->o.getId()==id.intValue());
    }
}
