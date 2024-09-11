package db;

import adapters.notificadores.Notificador;
import domain.accesorios.Contacto;
import domain.personas.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositorio.IRepoTecnico;
import repositorio.RepoTecnicos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestsRepoTecnico {
    private EntityManagerFactory emf;
    private EntityManager em;
    private IRepoTecnico repoTecnico;

    @BeforeEach
    void setUp() {
        // Configurar EntityManagerFactory y EntityManager para H2
        emf = Persistence.createEntityManagerFactory("simple-persistence-unit");
        em = emf.createEntityManager();

        // Inicializar RepoTecnico, que usa WithSimplePersistenceUnit
        repoTecnico = new RepoTecnicos() {
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

        // Insertar un Tecnico
        Notificador notificador = new Notificador();
        Tecnico tecnico = new Tecnico(notificador);
        tecnico.setNombre("Juan");
        tecnico.setApellido("Pérez");
        Contacto contacto = new Contacto();
        tecnico.setContacto(contacto);
        em.persist(contacto);
        em.persist(tecnico);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Buscar el Tecnico por ID usando el repositorio
        Tecnico result = repoTecnico.getById(tecnico.getId());
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        assertEquals("Pérez", result.getApellido());
    }

    @Test
    void testGetAll() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar algunos Tecnicos
        Notificador notificador1 = new Notificador();
        Notificador notificador2 = new Notificador();
        Tecnico tecnico1 = new Tecnico(notificador1);
        tecnico1.setNombre("Juan");
        tecnico1.setApellido("Pérez");
        Tecnico tecnico2 = new Tecnico(notificador2);
        tecnico2.setNombre("María");
        tecnico2.setApellido("Gómez");
        em.persist(tecnico1);
        em.persist(tecnico2);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Obtener todos los tecnicos usando el repositorio
        List<Tecnico> tecnicos = repoTecnico.getAll();
        assertEquals(2, tecnicos.size());
    }

    @Test
    void testInsert() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar un nuevo Tecnico
        Notificador notificador = new Notificador();
        Tecnico tecnico = new Tecnico(notificador);
        tecnico.setNombre("Carlos");
        tecnico.setApellido("López");
        Contacto contacto = new Contacto();
        tecnico.setContacto(contacto);
        em.persist(contacto);
        repoTecnico.insert(tecnico);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Verificar que se ha guardado correctamente
        Tecnico result = em.find(Tecnico.class, tecnico.getId());
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
        assertEquals("López", result.getApellido());
    }

    @Test
    void testUpdate() {
        // Iniciar transacción
        em.getTransaction().begin();

        // Insertar un nuevo Tecnico
        Notificador notificador = new Notificador();
        Tecnico tecnico = new Tecnico(notificador);
        tecnico.setNombre("Carlos");
        tecnico.setApellido("López");
        Contacto contacto = new Contacto();
        tecnico.setContacto(contacto);
        em.persist(contacto);
        em.persist(tecnico);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Modificar los datos del Tecnico
        em.getTransaction().begin();
        Tecnico tecnicoToUpdate = repoTecnico.getById(tecnico.getId());
        tecnicoToUpdate.setNombre("Juan Carlos");
        tecnicoToUpdate.setApellido("López Pérez");
        repoTecnico.update(tecnicoToUpdate);
        em.getTransaction().commit();

        // Verificar que los cambios se han guardado
        Tecnico updatedTecnico = em.find(Tecnico.class, tecnico.getId());
        assertNotNull(updatedTecnico);
        assertEquals("Juan Carlos", updatedTecnico.getNombre());
        assertEquals("López Pérez", updatedTecnico.getApellido());
    }
}