package domain.realizadorDeReporte;

import repositorio.IRepoHeladera;
import repositorio.IRepoHumano;
import repositorio.RepoHeladera;
import repositorio.RepoHumano;

import java.util.List;
import java.util.stream.Collectors;

public class CalculadorDatosDeReporte {
    private IRepoHumano repoHumano;
    private IRepoHeladera repoHeladera;
    public CalculadorDatosDeReporte(){
        this.repoHumano=new RepoHumano();
        this.repoHeladera=new RepoHeladera();
    }
    public List<IncidentePorHeladera> contarIncidentesPorHeladera(){

        return repoHeladera.getAll().stream().map(h->
                new IncidentePorHeladera(
                        h.getUbicacion().getDireccion()
                        , h.getUbicacion().getNombre()
                        ,h.cantidadIncidentes())
        ).collect(Collectors.toList());
    }
    public List<ViandasPorColaborador  > contarViandasPorColaborador(){
        return repoHumano.getAll().stream().map(h->
                new ViandasPorColaborador(
                        h.getNombre()
                        ,h.cantDonacionVianda()+h.cantDistribucionVianda()
                )).collect(Collectors.toList());
    }
    public  List<ViandasColocadasRetiradas> contarViandasColocadasRetiradas(){
        return repoHeladera.getAll().stream().map(h->
                new ViandasColocadasRetiradas(
                        h.cantidadViandasColocadas()
                        ,h.cantidadViandasRetiradas()
                        , h.getUbicacion().getNombre())
        ).collect(Collectors.toList());
    }
}
