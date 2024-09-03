package repositorio;

import domain.personas.Tecnico;

import java.util.List;

public interface IRepoTecnico {
    List<Tecnico> getAll();
    Tecnico getByid();
}
