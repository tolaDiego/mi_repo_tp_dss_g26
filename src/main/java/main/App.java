package main;


import domain.accesorios.Ubicacion;
import domain.enums.TipoIncidente;
import domain.incidentes.IncidenteAlerta;
import domain.objetos.Heladera;
import domain.objetos.SensorDeMovimiento;
import domain.objetos.Vianda;
import domain.objetos.sensorTemp.SensorTemperatura;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import repositorio.IRepoHeladera;
import repositorio.RepoHeladera;
import suscripciones.ISuscripcionObservable;
import suscripciones.SuscripcionAIncidentesObservable;
import suscripciones.SuscripcionAStockMaxObservable;
import suscripciones.SuscripcionAStockMinObservable;

import java.io.IOException;

import java.util.Calendar;



public class App implements WithSimplePersistenceUnit {
    private RepoHeladera repoHeladera=new RepoHeladera();
    public static void main(String[] args) throws IOException {
//        Javalin app;
//        app = Javalin.create().start(8082);
//        new PersonasController().registrarRutas(app);
//        System.out.println("sistema ejecutando");

//
//        // prueba consumo de api recomendador de ubicaciones(necesita postman).
//        AdapterRecomendadorRetrofit recomendador= AdapterRecomendadorRetrofit.getInstanciaRecomendador();
//        System.out.println(recomendador.getUrl());
//        PuntoUbicacion punto=new PuntoUbicacion(-34.616747665,-58.4396);
//       List<PuntoUbicacion> lista=recomendador.recomendarUbicaciones(punto,12);
//
//       //prueba envio de emails
//        Contacto contacto=new Contacto("mail","dddjtola@gmail.com");
//        Mensaje mensaje=new Mensaje("un mensaje para MI",null);
//        AdapterMedioNotifMailSender mailSender=new AdapterMedioNotifMailSender();
//        mailSender.notificar(contacto,mensaje);
//        System.out.println("mail enviado");
//        //prueba wp
//        contacto.setContacto("1125572659");
//        AdapterMedioNotificacion wp=new AdapterMedioNotifwhatsapp();
//        wp.notificar(contacto,mensaje);
        //telegram
//        Contacto contacto=new Contacto("mail","5118817082");
//        Mensaje mensaje=new Mensaje("un mensaje para MI",null);
//        AdapterMedioNotificacion telegram=new AdapterMedioNotifTelegram();
//        telegram.notificar(contacto,mensaje);
//        ConfigCronoInforme.ejecutarCronoInforme("miInformeCrono");

//        --------------------------------------PRUEBA SUSCRIPCION

//        Heladera heladera=new Heladera();
//        heladera.setCapacidad(6);
//        heladera.setUbicacion(new Ubicacion(){
//            @Override
//            public String getDireccion() {
//                return "Av. Siempre Viva";
//            }
//        });
//        heladera.setEscucharStockMax(new SuscripcionAStockMaxObservable());
//        PersonaObserver observadorStok1=new PersonaObserver();
//        observadorStok1.setSuscriptor(new Humano(){
//            @Override
//            public String getNombre() {
//                return "carl";
//            }
//        });
//        Notificador notif1=new Notificador();
//        notif1.setMedioDeNotificacion(new AdapterMedioNotifwhatsapp() );
//        observadorStok1.getSuscriptor().getContactos().add(new Contacto("WP","1125572659"));
//        observadorStok1.setTipoContacto("WP");
//        observadorStok1.setNotificador(notif1);
//
//        observadorStok1.setStock(5);
//
//        PersonaObserver observadorStok2=new PersonaObserver();
//        observadorStok2.setSuscriptor(new Humano(){
//            @Override
//            public String getNombre() {
//                return "lenny";
//            }
//        });
//        Notificador notif2=new Notificador();
//        notif2.setMedioDeNotificacion(new AdapterMedioNotifwhatsapp() );
//        observadorStok2.getSuscriptor().getContactos().add(new Contacto("WP","1125572659"));
//        observadorStok2.setTipoContacto("WP");
//        observadorStok2.setNotificador(notif2);
//
//        observadorStok2.setStock(5);
//        heladera.getEscucharStockMax().agregarSuscriptor(observadorStok2);
//        heladera.getEscucharStockMax().agregarSuscriptor(observadorStok1);
//        System.out.println("se ingreso unas viandas\n");
//        heladera.agregarViandas(List.of(new Vianda("primera"),new Vianda("segunda")));
//        System.out.println("se ingreso otra vianda\n");
//        heladera.agregarViandas(List.of(new Vianda("tercera")));

//        ---------------Prueba broker
//            Thread hilo2P = new Thread(new MiRunnable());
//            Thread hilo1R = new Thread(new MiRunnable(){
//                @Override
//                public void run() {
//                    ComunicadorMqtt brokerReceptor=new ComunicadorMqtt();
//                    brokerReceptor.recibirDatosDeTopic("algo-prueba","mi-receptor");
//                }
//            });
//
//        hilo1R.start(); // Inicia el segundo hilo
//        hilo2P.start(); // Inicia el primer hilo
//
        App instancia=new App();

            //instancia.guardarHeladera();
            Heladera unaHeladera=instancia.obtenerHeladera();
            System.out.println("heladera recuperada id=1: \n"+unaHeladera.toString());
        }

        public void guardarHeladera(){
            Heladera heladera=new Heladera();
            // Inicializar atributos de Heladera
            heladera.setCapacidad(10); // Capacidad de la heladera

            // Inicializar fechas
            Calendar fechaInicio = Calendar.getInstance(); // Fecha actual para el inicio de funcionamiento
            fechaInicio.add(Calendar.YEAR,-2);
            heladera.setFechaInicioFuncionamiento(fechaInicio);

            // Opcional: Inicializar fecha final

            heladera.setFechaFinalServicio(Calendar.getInstance());

            // Inicializar ubicación (supongamos que ya tienes una instancia de Ubicacion)
            Ubicacion ubicacion = new Ubicacion(); // Crear ubicación o obtener una existente
            ubicacion.setDireccion("Calle TODISIMA 123");
            heladera.setUbicacion(ubicacion);

            SensorDeMovimiento sensorMovimiento = new SensorDeMovimiento();
            heladera.setSensorMovimiento(sensorMovimiento);

            SensorTemperatura sensorTemperatura = new SensorTemperatura();
            sensorTemperatura.setTempMinima(2);
            sensorTemperatura.setTempMaxima(8);
            heladera.setSensorTemperatura(sensorTemperatura);

            heladera.setEstadoActivo(true);

            Vianda vianda = new Vianda();
            vianda.setNombre("Alfajor");
            heladera.getViandas().add(vianda);
            heladera.getIncidentes().add(new IncidenteAlerta(TipoIncidente.ALERTA_TEMPERATURA));
            ISuscripcionObservable stockMax=new SuscripcionAStockMaxObservable();
            ISuscripcionObservable stockMin = new SuscripcionAStockMinObservable();
            ISuscripcionObservable stockIncidente = new SuscripcionAIncidentesObservable();

            heladera.setEscucharStockMin(stockMin);
            heladera.setEscucharStockMax(stockMax);
            heladera.setEscucharEstado(stockIncidente);
            withTransaction(()->{ repoHeladera.insert(heladera);});

        }
        public Heladera obtenerHeladera(){
              return repoHeladera.getById(1);
        }


}
