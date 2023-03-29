package basicRestAssured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateVentasCheckTest {
    @Test
    public void verifyCreateVentasJsonObject() {

        JSONObject body = new JSONObject();
        body.put("idCliente", 1);
        body.put("detalle", "detallleCheck");
        body.put("cantidad", 1000);
        body.put("subTotal", 1001.0);
        body.put("iva", 0.13);
        body.put("total", 1131.0);
        // Configuracion
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .log().all()
                // Metodo de peticion
        .when()
                .post("http://localhost:8080/ventas");

        // Impresion y verificacion de respuesta
        response.then()
                .log().all()
                .statusCode(201)
                .body("detalle", equalTo("detallleCheck"))
                .body("idCliente", equalTo(1));
    }

    @Test
    public void verifyCreateVentasSchema() {

        JSONObject body = new JSONObject();
        body.put("idCliente", 1);
        body.put("detalle", "detallleCheck");
        body.put("cantidad", 1000);
        body.put("subTotal", 1001.0);
        body.put("iva", 0.13);
        body.put("total", 1131.0);
        // Configuracion
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .log().all()
                // Metodo de peticion
                .when()
                .post("http://localhost:8080/ventas");

        // Impresion y verificacion de respuesta
        response.then()
                .log().all()
                .statusCode(201)
                .body("detalle", equalTo("detallleCheck"))
                .body("idCliente", equalTo(1));

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(
                        SchemaVersion.DRAFTV4
                ).freeze()).freeze();
        response.then()
                .body(matchesJsonSchemaInClasspath("schemaCreateResponseGenerated.json")
                .using(jsonSchemaFactory));

        //Extraccion de valores
        int id = response.then().extract().path("idCliente");
        System.out.println("ID: " + id);
        String detalle = response.then().extract().path("detalle");
        System.out.println("Detalle: " + detalle);


    }
}
