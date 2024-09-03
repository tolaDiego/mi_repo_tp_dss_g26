package repositorio;

import domain.personas.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class RepoTecnicos implements IRepoTecnico{
    private final List<Tecnico> tecnicos = new ArrayList<>();
    public boolean agregarTecnico(Tecnico tecnico) {
        return  tecnicos.add(tecnico);
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
