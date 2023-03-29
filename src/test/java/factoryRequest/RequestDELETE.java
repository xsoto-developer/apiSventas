package factoryRequest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements  IRequest{
    @Override
    public Response send(RequestInfo info) {
        // Configuracion
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                
                .log().all()
        // Metodo de peticion
        .when()
                .delete(info.getUrl());
        response.then().log().all();
        return response;
    }
}
