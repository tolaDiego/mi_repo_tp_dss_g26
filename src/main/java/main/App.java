package main;



import adapters.notificadores.*;
import adapters.recomendadorUbicaciones.AdapterRecomendadorRetrofit;
import domain.accesorios.Contacto;
import domain.accesorios.PuntoUbicacion;
import domain.calculadorPuntos.CalculadorPuntos;

import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {
//        Javalin app;
//        app = Javalin.create().start(8082);
//        new PersonasController().registrarRutas(app);
//        System.out.println("sistema ejecutando");


        // prueba consumo de api recomendador de ubicaciones(necesita postman).
        AdapterRecomendadorRetrofit recomendador= AdapterRecomendadorRetrofit.getInstanciaRecomendador();
        System.out.println(recomendador.getUrl());
        PuntoUbicacion punto=new PuntoUbicacion(-34.616747665,-58.4396);
       List<PuntoUbicacion> lista=recomendador.recomendarUbicaciones(punto,12);

       //prueba envio de emails
        Contacto contacto=new Contacto("mail","dddjtola@gmail.com");
        Mensaje mensaje=new Mensaje("un mensaje para MI",null);
        AdapterMedioNotifMailSender mailSender=new AdapterMedioNotifMailSender();
        mailSender.notificar(contacto,mensaje);
        System.out.println("mail enviado");
        //prueba wp
        contacto.setContacto("1125572659");
        AdapterMedioNotificacion wp=new AdapterMedioNotifwhatsapp();
        wp.notificar(contacto,mensaje);



        CalculadorPuntos calculadorPuntos=CalculadorPuntos.getInstanceCalculadorPuntos();
        System.out.println("aaaa"+calculadorPuntos.getCoefPorDinero() );
    }
}
