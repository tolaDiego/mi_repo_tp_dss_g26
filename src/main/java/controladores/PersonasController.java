package controladores;


import domain.accesorios.Documento;
import domain.accesorios.TipoDocumento;
import domain.colaboraciones.Colaboracion;
import domain.personas.Humano;
import domain.personas.Juridica;
import domain.personas.Tecnico;
import domain.personas.Vulnerable;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import servicios.PersonaServicio;

import java.util.HashMap;
import java.util.Map;


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
        app.get("/actualizar/humano/{tipoDoc}/{numero}",retornarHumanoPorDoc);
        app.put("/actualizar/humano/{tipoDoc}/{numero}",actualizarHumano);
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

