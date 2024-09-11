package repositorio;


import domain.accesorios.Documento;
import domain.objetos.Heladera;
import domain.personas.Humano;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepoHumano implements  IRepoHumano, WithSimplePersistenceUnit {
        private final List<Humano> humanos = new ArrayList<>();

        @Override
        @SuppressWarnings("unchecked")
        public List<Humano> getAll(){
            return  entityManager().createQuery("from "+Humano.class.getName())
                    .getResultList();
        }

    public Humano getById(long humanoId) {

        return entityManager().find(Humano.class,humanoId);

    }
    public void insert(Humano humano){
        entityManager().persist(humano);
    }
    public void update(Humano humano){
        entityManager().merge(humano);
    }
}

