package com.Protecht.utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Authenticator {
    public static String getToken(){
        String publicKey = System.getenv("public_key");
        String privateKey = System.getenv("private_key");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
        .body("{\"public_key\":\""+publicKey+"\",\"secret_key\":\""+privateKey+"\"}")
                .post(ConfigurationReader.getProperty("apiUrl") +"auth/token/");
        JsonPath jsonPath = new JsonPath(response.body().asString());
        return jsonPath.getString("token");
    }

}
