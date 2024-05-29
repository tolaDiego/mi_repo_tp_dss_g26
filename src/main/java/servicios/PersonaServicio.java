package servicios;

import domain.accesorios.Documento;
import domain.colaboraciones.Colaboracion;
import domain.personas.Humano;
import domain.personas.Juridica;
import domain.personas.Tecnico;
import domain.personas.Vulnerable;
import repositorio.RepoHumano;
import repositorio.RepoJuridica;
import repositorio.RepoTecnicos;
import repositorio.RepoVulnerables;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonaServicio {

    private final AtomicInteger id = new AtomicInteger();
    private final RepoHumano repoHumano = new RepoHumano();
    private final RepoJuridica repoJuridica = new RepoJuridica();
    private final RepoTecnicos repoTecnicos = new RepoTecnicos();
    private final RepoVulnerables repoVulnerables = new RepoVulnerables();

    public Humano agregarhumano(Humano humano) {
        humano.setId(id.incrementAndGet());
        if (repoHumano.agregarHumano(humano)) {
            System.out.println("se creo el usuario" + humano);
            return humano;
        } else
            return null;
    }

    public Juridica agregarOrganisacion(Juridica organizacion) {
        organizacion.setId(id.incrementAndGet());
        if (repoJuridica.agregarJuridica(organizacion)) {
            System.out.println("se creo el usuario" + organizacion);
            return organizacion;
        } else
            return null;

    }

    public Tecnico agregarTecnico(Tecnico tecnico) {
        tecnico.setId(id.incrementAndGet());
        if (repoTecnicos.agregarTecnico(tecnico)) {
            System.out.println("se creo el usuario" + tecnico);
            return tecnico;
        } else
            return null;

    }

    public Vulnerable agregarVulnerable(Vulnerable vulnerable) {
        vulnerable.setId(id.incrementAndGet());
        if (repoVulnerables.agregarVulnerable(vulnerable)) {
            System.out.println("se creo el usuario" + vulnerable);
            return vulnerable;
        } else
            return null;

    }


    public boolean agregarColaboracionHumano(Integer idUsuario, Colaboracion colaboracion) {
        Humano humano = repoHumano.retornarHumanoPor(idUsuario);

        return humano.agregarColaboracion(colaboracion);
    }

    public boolean agregarColaboracionJuridico(Integer idUsuario, Colaboracion colaboracion) {
        Juridica juridica = repoJuridica.retornarJuridicaPor(idUsuario);
        juridica.agregarColacoracion(colaboracion);
        return false;
    }

    public boolean eliminarHumano(Integer idUsuario) {

        return repoHumano.eliminarHumanoPor(idUsuario);
    }

    public boolean eliminarTecnico(Integer idUsuario) {
        return repoTecnicos.eliminarTecnicoPor(idUsuario);
    }

    public boolean eliminarVulnerable(Integer idUsuario) {
        return repoVulnerables.eliminarVulnerablesPor(idUsuario);

    }

    public boolean eliminarJuridico(Integer idUsuario) {
        return repoJuridica.eliminarJuridicaPor(idUsuario);
    }

    public Humano retornarHumanoPorId(Integer id) {
        return repoHumano.retornarHumanoPor(id);
    }

    public Juridica retornarJuridicaPorId(Integer id) {
        return repoJuridica.retornarJuridicaPor(id);
    }

    public Tecnico retornarTecnicoPorId(Integer id) {
        return repoTecnicos.retornarTecnicoPor(id);
    }

    public Vulnerable retornarVulnerablePorId(Integer id) {
        return repoVulnerables.retornarVulnerablePor(id);
    }

    public Humano retornarHumanoPorDoc(Documento doc) {
        return repoHumano.retornarHumanoPorDoc(doc);
    }

    public Humano actualizarHumano(Humano nuevo) {
        return repoHumano.actualizarHumano(nuevo);
    }

    public double retornarPuntos(String tipopersona,int id) {
        double puntos = -1;
        if (tipopersona.equals("HUMANO")) {
            puntos = retornarHumanoPorId(id).puntaje();

        }
        if (tipopersona.equals("JURIDICA")) {
            puntos = retornarJuridicaPorId(id).puntaje();
        }

        return puntos;
    }
    }