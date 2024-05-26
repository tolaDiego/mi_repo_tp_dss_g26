package org.example;

import controladores.PersonasController;
import io.javalin.Javalin;
import org.junit.jupiter.api.Test;

public class WebAppTest {

    @Test
    public void testApi(){
        Javalin app=Javalin.create().start(8082);
        new PersonasController().registrarRutas(app);

    }

}
