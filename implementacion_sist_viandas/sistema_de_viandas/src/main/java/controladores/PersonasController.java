package controladores;


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

    private PersonaServicio personaServicio=new PersonaServicio();
    String nosepudoCrearElUsuario="no se pudo crear el usuario";
    String elUsuarioSeCreoBien="El usuario se creo bien";
    public void registrarRutas(Javalin app) {
        app.post("/registrar/humano", this.crearUsuarioHumano);
        app.post("/registrar/juridica",this.crearUsuarioJuridico);
        app.post("/registrar/tecnico",this.crearUsuarioTecnico);
        app.post("/registrar/vulnerable",this.crearUsuarioVulnerable);
        app.delete("/eliminar/humano/{id}",eliminarUsuarioHumano);
    }
    int codigoRegistroOk=200;
    int getCodigoRegistroNoOk=400;
    private Handler crearUsuarioHumano = ctx -> {
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
    private Handler crearUsuarioJuridico=ctx->{
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

    private Handler crearUsuarioTecnico=ctx->{
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

    private Handler crearUsuarioVulnerable=ctx->{

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
    private Handler eliminarUsuarioHumano =ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarHumano(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private Handler eliminarUsuarioJuridico =ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarJuridico(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private Handler eliminarUsuarioTecnico =ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarTecnico(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);

    };
    private Handler eliminarUsuarioVulnerable=ctx->{
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        if(personaServicio.eliminarVulnerable(idUsuario)    )
            ctx.status(200).result(okEliminacion);
        else
            ctx.status(400).result(errorEliminacion);
    };

String okColbAgregada="Colaboración agregada";
String noSeAgregoColab="Humano no encontrado";
    private Handler agregarColaboracionHumano = ctx -> {
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Colaboracion colaboracion = ctx.bodyAsClass(Colaboracion.class);

        if ( personaServicio.agregarColaboracionHumano(idUsuario, colaboracion)) {
            ctx.status(201).result(okColbAgregada);
        } else {
            ctx.status(404).result(noSeAgregoColab);
        }
    };
    private Handler agregarColaboracionJuridico = ctx -> {
        Integer idUsuario = Integer.parseInt(ctx.pathParam("id"));
        Colaboracion colaboracion = ctx.bodyAsClass(Colaboracion.class);

        if ( personaServicio.agregarColaboracionJuridico(idUsuario, colaboracion)) {
            ctx.status(201).result(okColbAgregada);
        } else {
            ctx.status(404).result(noSeAgregoColab);
        }
    };

}

