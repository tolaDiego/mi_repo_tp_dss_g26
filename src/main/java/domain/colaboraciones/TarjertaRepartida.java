package domain.colaboraciones;

import domain.objetos.Tarjeta;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TarjertaRepartida {
    private Tarjeta tarjeta;
    private Vulnerable personaTitular;
    private Date fechaContribucion;
   public TarjertaRepartida(){

   }

}
