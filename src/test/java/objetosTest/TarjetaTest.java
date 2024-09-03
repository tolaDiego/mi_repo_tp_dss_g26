package objetosTest;

import domain.accesorios.Apertura;
import domain.objetos.TarjetaVulnerable;
import domain.personas.Vulnerable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TarjetaTest {
    private Apertura apertura1, apertura2, apertura3;
    private TarjetaVulnerable tarjeta1;
    private Vulnerable vulnerable;

    @BeforeEach
    void setUp() {
        apertura1 = new Apertura();
        apertura2 = new Apertura();
        apertura3 = new Apertura();
        tarjeta1 = new TarjetaVulnerable();
        vulnerable = new Vulnerable();

        vulnerable.setMenoresACargo(2);
        tarjeta1.setPersonaVul(vulnerable);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse("2024-05-14");

            apertura1.setFechaDeUso(fecha);
            apertura2.setFechaDeUso(fecha);
            apertura3.setFechaDeUso(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCargaDeApertura() {
        tarjeta1.agregarApertura(apertura1);
        Assertions.assertTrue(tarjeta1.getAperturas().contains(apertura1),
                "La apertura debería haber sido agregada a la tarjeta.");
    }

    @Test
    public void testAperturasRestantesSinUsarTarjeta() {
        tarjeta1.agregarApertura(apertura1);
        tarjeta1.agregarApertura(apertura2);
        tarjeta1.agregarApertura(apertura3);

        // El número máximo de aperturas es 8 (4 + 2 * 2) , ya hemos agregado 3 de otro dia por lo que no se restan aperturas
        Assertions.assertEquals(8, tarjeta1.aperturasRestantes(),
                "La cantidad de aperturas de un persona con 2 hijos sin haber utilizado la tarjeta en el dia es 8");
    }
    @Test
    public void testUsarTarjetaRestaAperturasDiariasRestantes() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaHoyStr = sdf.format(new Date());
        Date fechaHoy = sdf.parse(fechaHoyStr);  // Formatea la fecha actual en el formato deseado
        apertura1.setFechaDeUso(new Date() );
        tarjeta1.agregarApertura(apertura1);
        tarjeta1.agregarApertura(apertura2);
        tarjeta1.agregarApertura(apertura3);

        // El número máximo de aperturas es 8 (4 + 2 * 2) -1=7
        Assertions.assertEquals(7, tarjeta1.aperturasRestantes(),
                "La cantidad de aperturas de un persona con 2 hijos utilizado la tarjeta 1 en el dia es 7");
    }
}