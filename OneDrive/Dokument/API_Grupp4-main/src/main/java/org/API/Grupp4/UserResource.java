package org.API.Grupp4;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.API.Grupp4.Security.ApiKey.Key;
import org.API.Grupp4.Security.ApiKey.KeyService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;
    @Inject
    KeyService keyService;

    @GET
    @Operation(summary = "Hämtar alla Användare", description = "Skriver ut alla användare som har registrerat sig och använder sidan")
    @APIResponse(responseCode = "200", description = "Hämtade alla användare")
    @APIResponse(responseCode = "400", description = "Fel förfrågan")
    @APIResponse(responseCode = "500", description = "Internal server fel")
    public Response getUsers() {
        List<User> users = userService.findAllUsers();
        return Response.ok(users).build();

    }

    @POST
    @Operation(summary = "Skapa ny användare", description = "Gratis du har blivet en del av Grupp 4 API vi lovar att inte stjäla dina informationer.. (Än)...")
    @APIResponse(responseCode = "200", description = "Ny användare har skapats utan problem.")
    @APIResponse(responseCode = "400", description = "Fanns ingen data för att skapa den nya användaren")
    @APIResponse(responseCode = "500", description = "Mislyckades med att skapa den nya användaren")
    public Response creatUser(@Valid User user) throws URISyntaxException {

        user = userService.createUser(user);
        if (user != null) {

            URI uri = new URI(user.getUuid().toString());
            return Response.created(uri).entity(user).build();

        } else {
            return Response.status(Response.Status.CONFLICT).entity("Användarnamnet är upptaget").build();
        }

    }

    @POST
    @Path("/key")
    public Response createKey(@HeaderParam("Authorization") String auth) {
        String[] creds = decodeBasicAuthHeader(auth);
        boolean authenticated = userService.authenticateUser(creds[0], creds[1]);
        if (authenticated) {

            return Response.status(Response.Status.CREATED)
                    .entity(keyService.createkey(userService.findByUsername(creds[0]))).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("fel lösenord eller användarnamn").build();
        }
    }

    @GET
    @Path("/key")
    public Response getKeys(@HeaderParam("Authorization") String auth) {
        String[] creds = decodeBasicAuthHeader(auth);
        boolean authenticated = userService.authenticateUser(creds[0], creds[1]);
        if (authenticated) {

            UUID owner = userService.findByUsername(creds[0]).getUuid();
            List<Key> keys = keyService.getYouKeys(owner);
            if (keys.size() <= 0) {
                return Response.status(Response.Status.LENGTH_REQUIRED)
                        .entity("Ditt konto har inga nycklar knutna till det.").build();
            }
            return Response.ok(keys).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Ta bort användare", description = "Ta bort en användare baserat på dess ID.")
    @APIResponse(responseCode = "200", description = "Användaren har tagits bort")
    @APIResponse(responseCode = "404", description = "Användaren hittades inte")
    @APIResponse(responseCode = "500", description = "Misslyckades att ta bort användaren")

    public Response deleteUser(@HeaderParam("Authorization") String authHeader) {
        String[] credentials = decodeBasicAuthHeader(authHeader);
        String username = credentials[0];
        String password = credentials[1];

        boolean authenticated = userService.authenticateUser(username, password);
        if (!authenticated) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Authentication failed").build();
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }

        userService.deleteUser(user);
        return Response.ok("User successfully deleted").build();
    }

    private String[] decodeBasicAuthHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Invalid Basic Authentication header");
        }
        String base64Credentials = authHeader.substring("Basic".length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);

        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        return credentials.split(":", 2);
    }
}
