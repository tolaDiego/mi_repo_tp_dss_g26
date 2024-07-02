package domain.accesorios;

import domain.objetos.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
@Setter
@Getter
public class AperturaColab {
    private Heladera heladeraUsada;
    private Calendar fechaDeUso;
}
