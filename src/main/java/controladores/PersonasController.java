package controladores;



import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import domain.accesorios.CamposArchivo;
import domain.accesorios.Documento;
import domain.accesorios.TipoDocumento;
import domain.colaboraciones.*;
import domain.personas.Humano;
import domain.personas.Juridica;
import domain.personas.Tecnico;
import domain.personas.Vulnerable;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import javassist.tools.reflect.Reflection;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import servicios.PersonaServicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PersonasController {

    private final PersonaServicio personaServicio=new PersonaServicio();
    String nosepudoCrearElUsuario="no se pudo crear el usuario";
    String elUsuarioSeCreoBien="El usuario se creo bien";
    public void registrarRutas(Javalin app) {
        app.post("/registrar/humano", this.crearUsuarioHumano);
        app.post("/registrar/juridica",this.crearUsuarioJuridico);
        app.post("/registrar/tecnico",this.crearUsuarioTecnico);
        app.post("/registrar/vulnerable",this.crearUsuarioVulnerable);
        app.delete("/eliminar/humano/{id}",eliminarUsuarioHumano);
        app.delete("/eliminar/juridica/{id}",eliminarUsuarioJuridico);
        app.delete("/eliminar/tecnico/{id}",eliminarUsuarioTecnico);
        app.delete("/eliminar/vulnerable/{id}",eliminarUsuarioVulnerable);
        app.get("/retornar/humano/{id}",retornarHumanoPorId);
        app.get("/retornar/juridica/{id}",retornarJuridicaPorId);
        app.get("/retornar/tecnico/{id}",retornarTecnicoPorId);
        app.get("/retornar/vulnerable/{id}",retornarVulnerablePorId);
        app.get("/retornar/humano/{tipoDoc}/{numero}",retornarHumanoPorDoc);
        app.put("/actualizar/humano/{tipoDoc}/{numero}",actualizarHumano);
        app.post("/importar",cargarColaboradores   );
    }
    int codigoRegistroOk=200;
    int getCodigoRegistroNoOk=400;
    private final Handler crearUsuarioHumano = ctx -> {
        Humano humano=personaServicio.agregarhumano(ctx.bodyAsClass(Humano.class));
        Map<String, Object> response = new HashMap<>();
        if(humano!=null) {

            response.put("usuario", humano);
            response.put("mensaje", elUsuarioSeCreoBien);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {

            response.put("mensaje", nosepudoCrearElUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };
    private final Handler crearUsuarioJuridico= ctx->{
        Map<String, Object> response = new HashMap<>();
        Juridica juridica=personaServicio.agregarOrganisacion(ctx.bodyAsClass(Juridica.class));
       if(juridica!=null){

           response.put("usuario", juridica);
           response.put("mensaje", elUsuarioSeCreoBien);
           response.put("codigo", codigoRegistroOk);
           ctx.status(200);
           ctx.json(response);
       } else {
           response.put("mensaje", nosepudoCrearElUsuario);
           response.put("codigo", getCodigoRegistroNoOk);
           ctx.status(400);
           ctx.json(response);
       }
       };

    private final Handler crearUsuarioTecnico= ctx->{
        Map<String, Object> response = new HashMap<>();
        Tecnico tecnico=personaServicio.agregarTecnico(ctx.bodyAsClass(Tecnico.class));
        if(tecnico!=null){

            response.put("usuario", tecnico);
            response.put("mensaje", elUsuarioSeCreoBien);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", nosepudoCrearElUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };

    private final Handler crearUsuarioVulnerable= ctx->{

        Map<String, Object> response = new HashMap<>();
        Vulnerable vulnerable=personaServicio.agregarVulnerable(ctx.bodyAsClass(Vulnerable.class));
        if(vulnerable!=null){

            response.put("usuario", vulnerable);
            response.put("mensaje", elUsuarioSeCreoBien);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", nosepudoCrearElUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }

    };

String okEliminacion=" usuario eliminado";
String errorEliminacion=" error al eliminar";
    private final Handler eliminarUsuarioHumano = ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarHumano(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private final Handler eliminarUsuarioJuridico = ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarJuridico(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private final Handler eliminarUsuarioTecnico = ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarTecnico(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private final Handler eliminarUsuarioVulnerable= ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarVulnerable(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);
    };
    String okRetornoUsuario="se encontro el usuario buscado";
    String noOkRetornoUsuario="no se encontro el usuario buscado";
    private final Handler retornarHumanoPorId= ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> response = new HashMap<>();
        Humano humano=personaServicio.retornarHumanoPorId(idUsuario);
        if(humano!=null){

            response.put("usuario", humano);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };
    private final Handler retornarJuridicaPorId= ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> response = new HashMap<>();
        Juridica juridica=personaServicio.retornarJuridicaPorId(idUsuario);
        if(juridica!=null){

            response.put("usuario", juridica);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };
    private final Handler retornarTecnicoPorId= ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> response = new HashMap<>();
        Tecnico tecnico=personaServicio.retornarTecnicoPorId(idUsuario);
        if(tecnico!=null){

            response.put("usuario", tecnico);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };
    private final Handler retornarVulnerablePorId= ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> response = new HashMap<>();
        Vulnerable vulnerable=personaServicio.retornarVulnerablePorId(idUsuario);
        if(vulnerable!=null){

            response.put("usuario", vulnerable);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };
    private final Handler retornarHumanoPorDoc= ctx->{
        Documento doc=new Documento(
                TipoDocumento.valueOf(ctx.pathParam("tipoDoc")),
                ctx.pathParam("numero")
        );
        Humano persona=personaServicio.retornarHumanoPorDoc(doc);
        Map<String, Object> response = new HashMap<>();
        if(persona!=null){

            response.put("usuario", persona);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);
        }
    };

    private final Handler actualizarHumano= ctx->{

        Documento doc=new Documento(
                TipoDocumento.valueOf(ctx.pathParam("tipoDoc")),
                ctx.pathParam("numero")
        );
        Humano futuro = ctx.bodyAsClass(Humano.class);
       Humano viejo=personaServicio.retornarHumanoPorDoc(doc);
        Map<String, Object> response = new HashMap<>();

        if(viejo!=null){
            Humano humanoActializado  = personaServicio.actualizarHumano(futuro);
            response.put("usuario", humanoActializado);
            response.put("mensaje", okRetornoUsuario);
            response.put("codigo", codigoRegistroOk);
            ctx.status(200);
            ctx.json(response);
        } else {
            response.put("mensaje", noOkRetornoUsuario);
            response.put("codigo", getCodigoRegistroNoOk);
            ctx.status(400);
            ctx.json(response);

        }
    };
    private Handler cargarColaboradores=ctx->{
        InputStream archivo = ctx.uploadedFile("file").content();
        List<String> lines = new BufferedReader(new InputStreamReader(archivo))
                .lines().toList();
        List<String> errores = new ArrayList<>();
        for (String line : lines) {
            String[] columns = line.split(",");
            if (columns.length != 8) {
                errores.add("Formato incorrecto en la línea: " + line);
                continue;
            }

            // Validar los datos según las especificaciones
            CamposArchivo datos = new CamposArchivo(
                    columns[0], columns[1], columns[2], columns[3],
                    columns[4], columns[5], columns[6], columns[7]
            );

            if (!validarDatos(datos)) {
                errores.add("Datos inválidos en la línea: " + line);
                continue;
            }

            // Buscar o crear usuario
            Documento doc=new Documento(TipoDocumento.valueOf(datos.getTipoDoc()), datos.getDocumento());

            Humano humano;
            humano = personaServicio.retornarHumanoPorDoc(doc);
            if (humano == null) {
                humano = new Humano(datos);
                personaServicio.agregarhumano(humano);

                //envio de email
                enviarEmail(datos);
            }
            // Registrar colaboración
            Integer cantidad= Integer.parseInt(datos.getCantidad());
            for(int i=0;i<cantidad;i++){
                Colaboracion colab=instanciarColaboracion(datos);

                personaServicio.agregarColaboracionHumano( personaServicio.retornarHumanoPorDoc(doc).getId(), colab);
            }


        }

    };
    public void enviarEmail(CamposArchivo datos){
        Resend resend = new Resend("re_frsxaaWH_Gb7F15z6WYxm2U3VaAgeyTfb");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(""+datos.getMail())
                .subject("sistema_viandas")
                .html("<strong>hello world "+ datos.getNombre()+"</strong>")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
    public Colaboracion instanciarColaboracion(CamposArchivo tipo){
        Colaboracion colaboracion = null;
        if("DINERO".equals(tipo.getFormaColaboracion())) colaboracion = new DonacionDinero(tipo);
        if("DONACION_VIANDAS".equals(tipo.getFormaColaboracion())) colaboracion = new DonacionVianda(tipo);
        if("ENTREGA_TARJETAS".equals(tipo.getFormaColaboracion())) colaboracion = new TarjertaRepartida();
        if("REDISTRIBUCION_VIANDAS".equals(tipo.getFormaColaboracion())) colaboracion=new DistribucionVianda(tipo);

        return colaboracion;
    }

    public boolean validarDatos(CamposArchivo datos) {
        //TODO Implementar la validación de cada campo según las especificaciones
        return true;
    }
String okColbAgregada="Colaboración agregada";
String noSeAgregoColab="Humano no encontrado";
    private final Handler agregarColaboracionHumano = ctx -> {
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Colaboracion colaboracion = ctx.bodyAsClass(Colaboracion.class);

        if ( personaServicio.agregarColaboracionHumano(idUsuario, colaboracion)) {
            ctx.status(201).result(okColbAgregada);
        } else {
            ctx.status(404).result(noSeAgregoColab);
        }
    };
    private final Handler agregarColaboracionJuridico = ctx -> {
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Colaboracion colaboracion = ctx.bodyAsClass(Colaboracion.class);

        if ( personaServicio.agregarColaboracionJuridico(idUsuario, colaboracion)) {
            ctx.status(201).result(okColbAgregada);
        } else {
            ctx.status(404).result(noSeAgregoColab);
        }
    };

}


