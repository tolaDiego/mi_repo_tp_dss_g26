package db;

import domain.personas.Humano;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositorio.RepoHumano;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestsRepoHumanoColaborador {
    private EntityManagerFactory emf;
    private EntityManager em;
    private RepoHumano repoHumano;

    @BeforeEach
    void setUp() {
        // Configurar EntityManagerFactory y EntityManager para H2
        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();

        // Inicializar RepoHumano, que usa WithSimplePersistenceUnit
        repoHumano = new RepoHumano() {
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

        // Insertar un Humano
        Humano humano = new Humano();
        humano.setNombre("Juan");
        humano.setApellido("Pérez");
        humano.setDireccion("Calle Falsa 123");
        humano.setFechaNacimiento(new Date());
        em.persist(humano);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Buscar el Humano por ID usando el repositorio
        Humano result = repoHumano.getById(humano.getId());
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        assertEquals("Pérez", result.getApellido());
    }

    @Test
    void testGetAll() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar algunos Humanos
        Humano humano1 = new Humano();
        humano1.setNombre("Juan");
        humano1.setApellido("Pérez");
        Humano humano2 = new Humano();
        humano2.setNombre("María");
        humano2.setApellido("Gómez");
        em.persist(humano1);
        em.persist(humano2);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Obtener todos los Humanos usando el repositorio
        List<Humano> humanos = repoHumano.getAll();
        assertEquals(2, humanos.size());
    }

    @Test
    void testInsert() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar un nuevo Humano
        Humano humano = new Humano();
        humano.setNombre("Carlos");
        humano.setApellido("López");
        humano.setDireccion("Avenida Siempre Viva 742");
        humano.setFechaNacimiento(new Date());
        repoHumano.insert(humano);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Verificar que se ha guardado correctamente
        Humano result = em.find(Humano.class, humano.getId());
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
        assertEquals("López", result.getApellido());
    }

    @Test
    void testUpdate() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar un nuevo Humano
        Humano humano = new Humano();
        humano.setNombre("Carlos");
        humano.setApellido("López");
        humano.setDireccion("Avenida Siempre Viva 742");
        humano.setFechaNacimiento(new Date());
        em.persist(humano);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Modificar los datos del Humano
        em.getTransaction().begin();
        Humano humanoToUpdate = repoHumano.getById(humano.getId());
        humanoToUpdate.setNombre("Juan Carlos");
        humanoToUpdate.setDireccion("Calle Nueva 456");
        repoHumano.update(humanoToUpdate);
        em.getTransaction().commit();

        // Verificar que los cambios se han guardado
        Humano updatedHumano = em.find(Humano.class, humano.getId());
        assertNotNull(updatedHumano);
        assertEquals("Juan Carlos", updatedHumano.getNombre());
        assertEquals("Calle Nueva 456", updatedHumano.getDireccion());
    }
}