package domain.realizadorDeReporte;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDeInforme{
    private IMedioDeRegistro medioDeRegistro;
    private CalculadorDatosDeReporte datos;
    public GeneradorDeInforme() {
        this.medioDeRegistro=new MedioDeRegistroPDF();
        this.datos=new CalculadorDatosDeReporte();
    }
    public void generarInforme(String ruta) throws FileNotFoundException {
        this.medioDeRegistro.registrarReporte(ruta,new CalculadorDatosDeReporte(){
            //FIXME pasar atributo "datos" como parametro en lugar de crear un nuevo CalculadorDatosDeReporte. Lo hardcodie para probar la generacion de informe
            @Override
            public List<IncidentePorHeladera> contarIncidentesPorHeladera() {
                List<IncidentePorHeladera> incidentesList=new ArrayList<>();
                incidentesList.add(new IncidentePorHeladera("Direccion 1", "Ubicacion 1", 5));
                incidentesList.add(new IncidentePorHeladera("Direccion 2", "Ubicacion 2", 3));
                incidentesList.add(new IncidentePorHeladera("Direccion 3", "Ubicacion 3", 8));
                return  incidentesList;
            }

            @Override
            public List<ViandasPorColaborador> contarViandasPorColaborador() {
                List<ViandasPorColaborador> viandasColaboradorList = new ArrayList<>();
                viandasColaboradorList.add(new ViandasPorColaborador("Colaborador 1", 10.5));
                viandasColaboradorList.add(new ViandasPorColaborador("Colaborador 2", 8.0));
                viandasColaboradorList.add(new ViandasPorColaborador("Colaborador 3", 12.3));
                return viandasColaboradorList;
            }

            @Override
            public List<ViandasColocadasRetiradas> contarViandasColocadasRetiradas() {
                List<ViandasColocadasRetiradas> viandasCRList = new ArrayList<>();
                viandasCRList.add(new ViandasColocadasRetiradas(15, 7, "Ubicacion 1"));
                viandasCRList.add(new ViandasColocadasRetiradas(20, 10, "Ubicacion 2"));
                viandasCRList.add(new ViandasColocadasRetiradas(25, 13, "Ubicacion 3"));
                return viandasCRList;

            }
        });
    }

}
