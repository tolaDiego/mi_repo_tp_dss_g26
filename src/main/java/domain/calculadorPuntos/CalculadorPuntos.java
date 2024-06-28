package domain.calculadorPuntos;

import domain.personas.Humano;
import domain.personas.Juridica;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculadorPuntos {

    private  double coefPorTarjetaRepartida;
    private  double coefPorDonarViandas;
    private  double coefPorDistribucion;
    private  double coefPorDinero;
    private  double coefPorCantHeladerasActiva;
    private static CalculadorPuntos instanciaCalculador=null ;
    private  CalculadorPuntos(){
        coefPorDinero =0.5;
        coefPorCantHeladerasActiva =5;
        coefPorDistribucion=1;
        coefPorDonarViandas=1.5;
        coefPorTarjetaRepartida=2;
    }
    public static CalculadorPuntos getInstanceCalculadorPuntos(){
        if(instanciaCalculador==null    ) instanciaCalculador = new CalculadorPuntos();
        return  instanciaCalculador;
    }

    public   double calcularPuntosDe(Humano humano){

        return humano.pesosDonados()* coefPorDinero
                + humano.cantDistribucionVianda()* coefPorDistribucion
                +humano.cantDonacionVianda()* coefPorDonarViandas
                +humano.cantTarjetasRepartidas()*coefPorTarjetaRepartida;
    }
    public  double calcularPuntosDe(Juridica entidadJuridica){
        return  entidadJuridica.cantHeladerasActivas()* coefPorCantHeladerasActiva
                *entidadJuridica.totalMesesActivas()
                +entidadJuridica.pesosDonados()* coefPorDinero;
    }
}
