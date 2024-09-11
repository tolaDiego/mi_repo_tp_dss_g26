package repositorio;

import domain.objetos.Heladera;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class RepoHeladera implements IRepoHeladera , WithSimplePersistenceUnit {

    @Override
    @SuppressWarnings("unchecked")
    public List <Heladera> getAll(){
        return  entityManager().createQuery("from "+Heladera.class.getName())
                .getResultList();
    }

    @Override
    public Heladera getById(long heladeraId) {

        return entityManager().find(Heladera.class,heladeraId);

    }
    public void insert(Heladera heladera){
            entityManager().persist(heladera);

    }
    public void update(Heladera heladera){
        entityManager().merge(heladera);
    }

}
