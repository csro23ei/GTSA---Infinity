package org.API.Grupp4.Security.Jwt;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class JwtGenerator {

    public void generateJwt(String username, String Password) {
        JsonObject userName = Json.createObjectBuilder().add("username", username).build();
        JsonObject password = Json.createObjectBuilder().add("password", Password).build();
        JsonObject jwt = Json.createObjectBuilder(userName).add("password", password).build();
        JwtClaimsBuilder builder = Jwt.claims(jwt);
        System.out.println(builder);

    }
}
