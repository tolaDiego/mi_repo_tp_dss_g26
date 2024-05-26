package servicios;

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
    private final RepoTecnicos repoTecnicos=new RepoTecnicos();
    private final RepoVulnerables repoVulnerables=new RepoVulnerables();
    public Humano agregarhumano(Humano humano){
        humano.setId((int) id.incrementAndGet());
        if(repoHumano.agregarHumano(humano))
            return humano;
       else
           return null;
    }
    public Juridica agregarOrganisacion(Juridica organizacion){
        organizacion.setId((int) id.incrementAndGet());
        if(repoJuridica.agregarJuridica(organizacion))
            return organizacion;
        else
            return null;

    }

    public Tecnico agregarTecnico(Tecnico tecnico) {
        tecnico.setId((int) id.incrementAndGet());
        if(repoTecnicos.agregarTecnico(tecnico))
            return tecnico;
        else
            return null;

    }

    public Vulnerable agregarVulnerable(Vulnerable vulnerable) {
        vulnerable.setId((int) id.incrementAndGet());
        if(repoVulnerables.agregarVulnerable(vulnerable))
            return vulnerable;
        else
            return null;

    }


    public boolean agregarColaboracionHumano(Integer idUsuario, Colaboracion colaboracion) {
        return false;
    }

    public boolean agregarColaboracionJuridico(Integer idUsuario, Colaboracion colaboracion) {
    return false;
    }

    public boolean eliminarHumano(Integer idUsuario) {

        return   repoHumano.eliminarHumanoPor(idUsuario);
    }

    public boolean eliminarTecnico(Integer idUsuario) {
        return repoTecnicos.eliminarTecnicoPor(idUsuario);
    }

    public boolean eliminarVulnerable(Integer idUsuario) {
        return  repoVulnerables.eliminarVulnerablesPor(idUsuario);

    }

    public boolean eliminarJuridico(Integer idUsuario) {
        return repoJuridica.eliminarJuridicaPor(idUsuario);
    }
}
