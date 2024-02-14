package org.API.Grupp4;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/recipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @Inject
    RecipeService recipeService;

    @Context
    HttpServletRequest request;

    @GET
    @Operation(summary = "Skaffar Recept med ID", description = "Receptet hittas genom att skicka in ett unikt ID.")
    @APIResponse(responseCode = "200", description = "Successful operation")
    @APIResponse(responseCode = "400", description = "Hittade inte det du sökte. Försök med ett annat id")
    @APIResponse(responseCode = "404", description = "Hittar inte servern, kontrollera stavningen")

    @Path("/{id}")
    public Response getRecipeById(@PathParam("id") @Min(1) Long id) {
        Recipe recipe = recipeService.find(id);
        return Response.ok(recipe).build();
    }

    @GET
    @Operation(summary = "Vissar alla recept", description = "Vissar alla recept som vi har kodat in i databasen. med maträtts namn och vem som har tilagat den / lagt up den.")
    @APIResponse(responseCode = "200", description = "Alt gick bra receptet är Postad")
    @APIResponse(responseCode = "400", description = "Kunde inte posta ")

    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Ändra recept", description = "Ändra sparade recept")
    @APIResponse(responseCode = "200", description = "Recept ändrades.")
    @APIResponse(responseCode = "400", description = "Hittade inte 'id' till receptet. Försök med ett annat id")
    @APIResponse(responseCode = "500", description = "Kunde inte ändra receptet.")

    public Response patchRecipe(@PathParam("id") @Min(1) Long id, Recipe updatedRecipe) {

        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id not found").build();
        }

        Recipe patchedRecipe = recipeService.patch(id, updatedRecipe, request);
        if (patchedRecipe == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Recipe not patched").build();
        }
        return Response.ok(patchedRecipe).build();

    }

    @POST
    @Operation(summary = "Skapa nytt recept", description = "Skapa helt nytt recept. För att skapa recept måste 'id' tas bort eftersom den autoskapas av programmet.")
    @APIResponse(responseCode = "200", description = "Nytt recept skapad.")
    @APIResponse(responseCode = "400", description = "Fanns ingen data för att skapa recept.")
    @APIResponse(responseCode = "500", description = "Skapandet av nytt recept misslyckades.")

    public Response createRecipe(Recipe newRecipe) {
        if (newRecipe == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Data for recipe is required").build();
        }
        Recipe createdRecipe = recipeService.create(newRecipe, request);

        if (createdRecipe == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Creation of recipe failed").build();
        }
        return Response.status(Response.Status.CREATED).entity(createdRecipe).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Recipe", description = "Delete a recipe based on its ID.")
    @APIResponse(responseCode = "200", description = "Receptet har tagits bort")
    @APIResponse(responseCode = "404", description = "Receptet hitades inte")
    @APIResponse(responseCode = "500", description = "Mislyckades att ta bort recept")

    public Response deleteRecipe(@PathParam("id") @Min(1) Long id) {

        return Response.ok(recipeService.delete(id, request)).build();
    }

}
