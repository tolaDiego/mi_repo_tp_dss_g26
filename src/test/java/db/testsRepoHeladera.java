package db;

import domain.objetos.Heladera;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositorio.RepoHeladera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;




public class testsRepoHeladera {
    private EntityManagerFactory emf;
    private EntityManager em;
    private RepoHeladera repoHeladera;

    @BeforeEach
    void setUp() {
        // Configurar EntityManagerFactory y EntityManager para H2
        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();

        // Inicializar RepoHeladera, que usa WithSimplePersistenceUnit
        repoHeladera = new RepoHeladera() {
            @Override
            public EntityManager entityManager() {
                return em; // Devolvemos el EntityManager de prueba
            }
        };
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void testGetById() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar una Heladera
        Heladera heladera = new Heladera();
        heladera.setCapacidad(100);
        em.persist(heladera);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Buscar la Heladera por ID usando el repositorio
        Heladera result = repoHeladera.getById(heladera.getId());
        assertNotNull(result);
        assertEquals(100, result.getCapacidad());
    }

    @Test
    void testGetAll() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar algunas Heladeras
        Heladera heladera1 = new Heladera();
        heladera1.setCapacidad(100);
        Heladera heladera2 = new Heladera();
        heladera2.setCapacidad(200);
        em.persist(heladera1);
        em.persist(heladera2);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Obtener todas las heladeras usando el repositorio
        List<Heladera> heladeras = repoHeladera.getAll();
        assertEquals(2, heladeras.size());
    }

    @Test
    void testInsert() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar una nueva Heladera
        Heladera heladera = new Heladera();
        heladera.setCapacidad(150);
        repoHeladera.insert(heladera);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Verificar que se ha guardado correctamente
        Heladera result = em.find(Heladera.class, heladera.getId());
        assertNotNull(result);
        assertEquals(150, result.getCapacidad());
    }
    @Test
    void testUpdate() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar una nueva Heladera
        Heladera heladera = new Heladera();
        heladera.setCapacidad(150);
        em.persist(heladera);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Modificar la capacidad de la heladera
        em.getTransaction().begin();
        Heladera heladeraToUpdate = repoHeladera.getById(heladera.getId());
        heladeraToUpdate.setCapacidad(300);
        repoHeladera.update(heladeraToUpdate);
        em.getTransaction().commit();

        // Verificar que los cambios se han guardado
        Heladera updatedHeladera = em.find(Heladera.class, heladera.getId());
        assertNotNull(updatedHeladera);
        assertEquals(300, updatedHeladera.getCapacidad());
    }
}