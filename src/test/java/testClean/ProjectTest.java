package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import util.ApiConfiguration;
import util.Constants;

import static org.hamcrest.Matchers.equalTo;

public class ProjectTest {
    Response response;
    JSONObject body = new JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    @Test
    public void verifyCRUDProject(){
        body.put("idCliente", 1);
        body.put("detalle", "detallleFactoryCRUD");
        body.put("cantidad", 3000);
        body.put("subTotal", 3001.0);
        body.put("iva", 0.13);
        body.put("total", 3131.0);
        requestInfo.setUrl(ApiConfiguration.CREATE_PROJECT);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make(Constants.POST).send(requestInfo);
        response.then().body("detalle", equalTo(body.get("detalle"))).statusCode(201);

        body.put("detalle", "detallleFactoryUPDATE");
        requestInfo.setUrl(ApiConfiguration.UPDATE_PROJECT);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make(Constants.PUT).send(requestInfo);
        response.then().body("detalle", equalTo(body.get("detalle"))).statusCode(200);

        int idProject = response.then().extract().path("id");

        requestInfo.setUrl(String.format(ApiConfiguration.READ_PROJECT, idProject));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make(Constants.GET).send(requestInfo);
        response.then().body("detalle", equalTo(body.get("detalle"))).statusCode(200);

        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_PROJECT, idProject));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make(Constants.DELETE).send(requestInfo);
        response.then().statusCode(200);
    }
}
