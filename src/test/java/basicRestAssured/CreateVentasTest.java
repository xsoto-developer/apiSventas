package basicRestAssured;

import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class CreateVentasTest {
    @Test
    public void verifyCreateVentas(){
        // Configuracion
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(" {\n" +
                        "\"idCliente\": 1,\n" +
                        "\"detalle\":\t\"detalle001\",\n" +
                        "\"cantidad\":\t50,\n" +
                        "\"subTotal\":\t5.0,\n" +
                        "\"iva\": 0.15,\n" +
                        "\"total\": 500\n" +
                        "}")
                .log().all()
        // Metodo de peticion
        .when().post("http://localhost:8080/ventas")
        // verificacion
        .then()
                .log().all();
    }

    @Test
    public void verifyCreateVentasJsonObject(){

        JSONObject  body = new JSONObject();
        body.put("idCliente", 1);
        body.put("detalle", "detallleJSON");
        body.put("cantidad", 1000);
        body.put("subTotal", 1001.0);
        body.put("iva", 0.13);
        body.put("total", 1131.0);
        // Configuracion
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .log().all()
                // Metodo de peticion
                .when().post("http://localhost:8080/ventas")
                // verificacion
                .then()
                .log().all();
    }

    @Test
    public void verifyCreateVentasFile(){

        String jsonfile = new File("").getAbsolutePath()+ "/src/test/resources/CreateVentas.json";

        // Configuracion
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new File(jsonfile))
                .log().all()
                // Metodo de peticion
                .when().post("http://localhost:8080/ventas")
                // verificacion
                .then()
                .log().all();
    }
}
