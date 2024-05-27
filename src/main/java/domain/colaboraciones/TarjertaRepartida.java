package domain.colaboraciones;

import domain.objetos.Tarjeta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarjertaRepartida implements   Colaboracion{
    private Tarjeta tarjeta;
   private double coefPorTarjeta;
   public TarjertaRepartida(){
       this.coefPorTarjeta=2;
       this.tarjeta=new Tarjeta();
   }
   @Override
   public double puntaje(){
       return coefPorTarjeta;
   }
}
