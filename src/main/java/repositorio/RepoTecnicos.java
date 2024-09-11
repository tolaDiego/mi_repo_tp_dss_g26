package repositorio;

import domain.objetos.Heladera;
import domain.personas.Tecnico;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.ArrayList;
import java.util.List;

public class RepoTecnicos implements IRepoTecnico, WithSimplePersistenceUnit {
        @Override
        @SuppressWarnings("unchecked")
        public List <Tecnico> getAll(){
            return  entityManager().createQuery("from "+Tecnico.class.getName())
                    .getResultList();
        }
        @Override
        public Tecnico getById(long id) {

            return entityManager().find(Tecnico.class,id);

        }
        @Override
        public void insert(Tecnico tecnico){
            entityManager().persist(tecnico);

        }
        @Override
        public void update(Tecnico tecnico){
            entityManager().merge(tecnico);
        }


}
