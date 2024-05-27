package main;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import controladores.PersonasController;
import io.javalin.Javalin;


import java.awt.desktop.SystemEventListener;

public class App {
    public static void main(String[] args) {
        Javalin app=Javalin.create().start(8082);
        new PersonasController().registrarRutas(app);
        System.out.println("sistema ejecutando");

    }
}
