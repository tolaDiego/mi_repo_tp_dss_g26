package domain.realizadorDeReporte;

import java.io.FileNotFoundException;

public interface IMedioDeRegistro {
    void registrarReporte(String rutaArchivo,CalculadorDatosDeReporte datos)throws FileNotFoundException;
}