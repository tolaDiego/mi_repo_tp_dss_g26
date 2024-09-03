package repositorio;

import domain.objetos.Heladera;

import java.util.List;

public interface IRepoHeladera {
    List<Heladera> getAll();

    Heladera getById(String heladeraId);
}
