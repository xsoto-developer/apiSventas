package factoryRequest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestPOST implements  IRequest{
    @Override
    public Response send(RequestInfo info) {
        // Configuracion
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(info.getBody())
                .log().all()
                // Metodo de peticion
                .when()
                .post(info.getUrl());
        response.then().log().all();
        return response;
    }
}
