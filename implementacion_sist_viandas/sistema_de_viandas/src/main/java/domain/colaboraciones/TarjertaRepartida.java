package domain.colaboraciones;

import domain.objetos.Tarjeta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarjertaRepartida implements   Colaboracion{
    private Tarjeta Tarjeta;
   private double coefPorTarjeta =2;
   public TarjertaRepartida(){}
   @Override
   public double puntaje(){
       return coefPorTarjeta;
   }
}
