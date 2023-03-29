package basicRestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProjectTest {
    @Test
    public void verifyCreateVentasJsonObject() {

        JSONObject body = new JSONObject();
        body.put("idCliente", 1);
        body.put("detalle", "detallleCRUD");
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
                .body("detalle", equalTo("detallleCRUD"))
                .body("idCliente", equalTo(1));

        //read
        int idProject = response.then().extract().path("id");
        response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .log().all()
        // Metodo de peticion
        .when()
                .get("http://localhost:8080/ventas/"+idProject);
        // Impresion y verificacion de respuesta
        response.then()
                .log().all()
                .statusCode(200)
                .body("detalle", equalTo("detallleCRUD"))
                .body("idCliente", equalTo(1));

        //update
        body.put("id", 1);
//        body.put("idCliente", 1);
        body.put("detalle", "detallleCRUDUpdate");
//        body.put("cantidad", 1000);
//        body.put("subTotal", 1001.0);
//        body.put("iva", 0.13);
//        body.put("total", 1131.0);
        // Configuracion
        response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body.toString())
                .log().all()
        // Metodo de peticion
        .when()
                .put("http://localhost:8080/ventas");

        // Impresion y verificacion de respuesta
        response.then()
                .log().all()
                .statusCode(200)
                .body("detalle", equalTo("detallleCRUDUpdate"))
                .body("idCliente", equalTo(1));

        //delete
        response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .log().all()
        // Metodo de peticion
        .when()
                .delete("http://localhost:8080/ventas/"+idProject);
        // Impresion y verificacion de respuesta
        response.then()
                .log().all()
                .statusCode(200);
//                .body("detalle", equalTo("detallleCRUDUpdate"))
//                .body("idCliente", equalTo(1));
    }
}
