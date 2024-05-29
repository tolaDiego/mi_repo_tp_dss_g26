package domain.colaboraciones;

import domain.objetos.Tarjeta;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarjertaRepartida implements   Colaboracion{
    private Tarjeta tarjeta;
   private double coefPorTarjeta;
    private Vulnerable personaTitular;
   public TarjertaRepartida(){
       this.coefPorTarjeta=2;

   }
   @Override
   public double puntaje(){
       return coefPorTarjeta;
   }
}
