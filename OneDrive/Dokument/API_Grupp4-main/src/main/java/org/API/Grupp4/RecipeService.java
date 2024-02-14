package org.API.Grupp4;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@ApplicationScoped
@Transactional(Transactional.TxType.SUPPORTS)
public class RecipeService {

    @Inject
    EntityManager em;

    public Recipe find(Long id) {
        return em.find(Recipe.class, id);
    }

    public List<Recipe> getAllRecipes() {
        return em.createQuery("SELECT r FROM Recipe r", Recipe.class).getResultList();
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Recipe patch(Long id, Recipe updatedRecipe, HttpServletRequest request) {

        if (checkIfItsOwner(request, id)) {
            Recipe existingRecipe = em.find(Recipe.class, id);
            if (existingRecipe == null) {
                return null;
            }
            if (updatedRecipe != null) {
                if (updatedRecipe.getName() != null) {
                    existingRecipe.setName(updatedRecipe.getName());
                }
                if (updatedRecipe.getIngredients() != null && !updatedRecipe.getIngredients().isEmpty()) {
                    existingRecipe.setIngredients(updatedRecipe.getIngredients());
                }
                if (updatedRecipe.getSteps() != null && !updatedRecipe.getSteps().isEmpty()) {
                    existingRecipe.setSteps(updatedRecipe.getSteps());
                }
                
                if (updatedRecipe.getChef() != null) {
                    existingRecipe.setChef(updatedRecipe.getChef());
                }
            }
            
            return em.merge(existingRecipe);
            
        } else {
            return null;
        }

    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public Recipe create(Recipe newRecipe, HttpServletRequest request) {
        String keyValue = request.getHeader("group_4_is_the_best");
        UUID ownerUuid = em.createQuery("SELECT k.owner FROM Key k WHERE k.key = :keyValue", UUID.class)
            .setParameter("keyValue", keyValue)
            .getSingleResult();
        newRecipe.setOwner(ownerUuid.toString());
        try {
            em.persist(newRecipe);
            return newRecipe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Recipe save(Recipe recipe) {
        em.persist(recipe);
        return recipe;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Recipe delete(Long id, HttpServletRequest request) {
        if (checkIfItsOwner(request, id)) {
            Recipe deletedRecipe = find(id);
            em.remove(em.getReference(Recipe.class, id));
            return deletedRecipe;
        } else {
            return null;
        }
    }

    public Boolean checkIfItsOwner(HttpServletRequest request, Long id) {
        String keyValue = request.getHeader("group_4_is_the_best");
        UUID userUuid = em.createQuery("SELECT k.owner FROM Key k WHERE k.key = :keyValue", UUID.class)
            .setParameter("keyValue", keyValue)
            .getSingleResult();
        String userId = userUuid.toString();
        
        Recipe recipe = find(id);
        String recipeOwner = recipe.getOwner();

        if (userId.equals(recipeOwner)) {
            return true;
        } 
        return false;

    }

}
