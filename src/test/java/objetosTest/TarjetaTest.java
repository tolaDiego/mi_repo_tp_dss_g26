package objetosTest;

import java.util.Calendar;
import domain.accesorios.Apertura;
import domain.objetos.Heladera;
import domain.objetos.TarjetaVulnerable;
import domain.personas.Vulnerable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class TarjetaTest {
    private Apertura apertura1,apertura2,apertura3;
    private Heladera heladera1;
    private TarjetaVulnerable tarjeta1;
    private Vulnerable vulnerable;

    void setUp() {
        apertura1 = new Apertura();
        apertura2 = new Apertura();
        apertura3 = new Apertura();
        heladera1 = new Heladera();
        tarjeta1 = new TarjetaVulnerable();
        vulnerable = new Vulnerable();

        Calendar calendar = Calendar.getInstance();

        calendar.set(2024, Calendar.MAY, 14);
        apertura1.setFechaDeUso(calendar);
        apertura2.setFechaDeUso(calendar);
        apertura3.setFechaDeUso(calendar);

    }

    public void testCargaDeApertura(){
        tarjeta1.agregarApertura(apertura1);
        Assertions.assertEquals(apertura1,tarjeta1.getAperturas());
    }

    public void testAperturasRestantes(){
        tarjeta1.agregarApertura(apertura1);
        tarjeta1.agregarApertura(apertura2);
        tarjeta1.agregarApertura(apertura3);
        Assertions.assertEquals(tarjeta1.aperturasRestantes(),1);
    }
}
