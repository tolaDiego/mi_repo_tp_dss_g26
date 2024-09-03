package humanoTest;

import domain.calculadorPuntos.CalculadorPuntos;
import domain.colaboraciones.DonacionDinero;
import domain.objetos.Oferta;
import domain.personas.Humano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HumanoTest {
    @Mock
    private CalculadorPuntos calculadorMock;

    @InjectMocks
    private Humano humano;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        humano = new Humano();
        humano.setCalculador(calculadorMock);
    }

    @Test
    public void testCalcularPuntos() {
        // Mockear el cálculo de puntos
        when(calculadorMock.calcularPuntosDe(humano)).thenReturn(100.0);

        // Sin puntos canjeados
        assertEquals(100.0, humano.calcularPuntos(), 0.01);

        // Con puntos canjeados
        Oferta oferta = new Oferta();
        oferta.setPuntosNecesarios(30.0);
        humano.getOfertasCanjeadas().add(oferta);
        assertEquals(70.0, humano.calcularPuntos(), 0.01);

        // Verificar interacción con el mock
        verify(calculadorMock, times(2)).calcularPuntosDe(humano);
    }

    @Test
    public void testCanjearPuntos() {
        Oferta oferta = mock(Oferta.class);

        // Caso: No tiene suficientes puntos
        when(oferta.tieneLosPuntosRequeridos(humano)).thenReturn(false);
        assertFalse(humano.canjearPuntos(oferta));

        // Caso: Tiene suficientes puntos
        when(oferta.tieneLosPuntosRequeridos(humano)).thenReturn(true);
        assertTrue(humano.canjearPuntos(oferta));
        assertEquals(1, humano.getOfertasCanjeadas().size());
    }

    @Test
    public void testPuntosCanjeados() {
        // Sin puntos canjeados
        assertEquals(0.0, humano.puntosCanjeados(), 0.01);

        // Con puntos canjeados
        Oferta oferta1 = new Oferta();
        oferta1.setPuntosNecesarios(50.0);
        humano.getOfertasCanjeadas().add(oferta1);

        Oferta oferta2 = new Oferta();
        oferta2.setPuntosNecesarios(30.0);
        humano.getOfertasCanjeadas().add(oferta2);

        assertEquals(80.0, humano.puntosCanjeados(), 0.01);
    }

    @Test
    public void testPesosDonados() {
        // Sin donaciones
        assertEquals(0.0, humano.pesosDonados(), 0.01);

        // Con donaciones
        DonacionDinero donacion1 = new DonacionDinero();
        donacion1.setMonto(100.0);
        humano.getDonacionDinero().add(donacion1);

        DonacionDinero donacion2 = new DonacionDinero();
        donacion2.setMonto(150.0);
        humano.getDonacionDinero().add(donacion2);

        assertEquals(250.0, humano.pesosDonados(), 0.01);
    }
}