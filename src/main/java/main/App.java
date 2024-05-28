package main;


import controladores.PersonasController;
import io.javalin.Javalin;


public class App {
    public static void main(String[] args) {
        Javalin app;
        app = Javalin.create().start(8082);
        new PersonasController().registrarRutas(app);
        System.out.println("sistema ejecutando");

    }
}
