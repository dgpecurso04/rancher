package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    public static String pba;

    public void start(Future<Void> fut) {
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/primero").handler(this::primero);
        router.get("/api/suma").handler(this::suma);
        router.get("/api/resta").handler(this::resta);
        router.get("/api/mult").handler(this::mult);
        router.get("/api/div").handler(this::div);
                
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        pba = System.getenv("PBA");
        logger.info("Vertical iniciada !!!");
    }  
    private void primero(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String mode = request.getParam("mode");
        String jsonResponse = procesa(mode);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void suma(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int numA = Integer.parseInt(request.getParam("numA"));
        int numB = Integer.parseInt(request.getParam("numB"));
        int numC = numA + numB;
        String operacion = "SUMA";
        String jsonResponse = procesa(operacion,numA,numB,numC,request);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void resta(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int numA = Integer.parseInt(request.getParam("numA"));
        int numB = Integer.parseInt(request.getParam("numB"));
        int numC = numA - numB;
        String operacion = "RESTA";
        String jsonResponse = procesa(operacion,numA,numB,numC,request);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void mult(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        int numA = Integer.parseInt(request.getParam("numA"));
        int numB = Integer.parseInt(request.getParam("numB"));
        int numC = numA * numB;
        String operacion = "MULTIPLICACION";
        String jsonResponse = procesa(operacion,numA,numB,numC,request);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void div(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        float numA = Integer.parseInt(request.getParam("numA"));
        float numB = Integer.parseInt(request.getParam("numB"));
        float numC = numA / numB;
        String operacion = "DIVISION";
        String jsonResponse = procesafloat(operacion,numA,numB,numC);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private String procesa(String operacion, int numA, int numB, int numC, HttpServerRequest req) {
        Map<Object, Object> info = new HashMap<>();
        info.put("servidor", req.localAddress().host());
        info.put("operacion", operacion);
        info.put("numA", numA);
        info.put("numB", numB);
        info.put("resultado", numC);
        return Json.encodePrettily(info);
    }
    
    private String procesafloat(String operacion, float numA, float numB, float numC) {
        Map<Object, Object> info = new HashMap<>();
        info.put("servidor", pba);
        info.put("operacion", operacion);
        info.put("numA", numA);
        info.put("numB", numB);
        info.put("resultado", numC);
        return Json.encodePrettily(info);
    }

    private String procesa(String decoded) {
        Map<String, String> autos = new HashMap<>();
        autos.put("primero", "Ferrari");
        autos.put("segundo", "Lamborgini");
        autos.put("tercero", "Bugatti");
        
        Map<Object, Object> info = new HashMap<>();
        info.put("decoded", decoded);
        info.put("nombre", "gustavo");
        info.put("edad", "21");
        info.put("autos", autos);
	info.put("variable", pba);
        return Json.encodePrettily(info);
    }

    public String construye(String fuente) {
        String tmp = fuente + fuente;
        return String.valueOf(tmp.substring(1, tmp.length()-1).contains(fuente)); 
    }

}
