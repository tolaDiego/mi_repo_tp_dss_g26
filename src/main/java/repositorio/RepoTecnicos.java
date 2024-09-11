package repositorio;

import domain.personas.Tecnico;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepoTecnicos implements IRepoTecnico, WithSimplePersistenceUnit {
    private final List<Tecnico> tecnicos = new ArrayList<>();
    public void agregarTecnico(Tecnico tecnico) {
        entityManager().persist(tecnico);
    }
    public List<Tecnico> retornarTecnicos() {
        return tecnicos;
    }
    public Tecnico retornarTecnicoPorId(Integer id) {
        return tecnicos.stream()
                .filter(t -> t.getId()== id.intValue())
                .findFirst()
                .orElse(null);
    }
    public Tecnico retornarTecnicoPor(Integer id) {
        return tecnicos.stream()
                .filter(t -> t.getId()== id.intValue())
                .findFirst()
                .orElse(null);
    }
    public boolean eliminarTecnicoPor(Integer id) {
        return    tecnicos.removeIf(t->t.getId()==id.intValue());
    }


    @Override
    public List<Tecnico> getAll() {
        return null;
    }

    @Override
    public Tecnico getByid() {
        return null;
    }
}
