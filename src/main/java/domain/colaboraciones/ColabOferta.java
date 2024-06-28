package domain.colaboraciones;

import domain.objetos.Oferta;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.Date;

@Getter
@Setter
public class ColabOferta {
    private Oferta oferta;
    private Date fechaContribucion;

}
