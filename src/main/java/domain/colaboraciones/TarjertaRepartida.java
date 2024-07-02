package domain.colaboraciones;


import domain.objetos.TarjetaVulnerable;
import domain.personas.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TarjertaRepartida {
    private TarjetaVulnerable tarjeta;
    private Vulnerable personaTitular;
    private Date fechaContribucion;
   public TarjertaRepartida(){

   }

}
