package org.API.Grupp4;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
   

    @ElementCollection(fetch = FetchType.EAGER)
     @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
     @Column(name = "ingredient")
     private List<String> ingredients = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
     @CollectionTable(name = "steps", joinColumns = @JoinColumn(name = "recipe_id"))
     @Column(name = "step")
     private List<String> steps = new ArrayList<>();
    
    private String chef;
    private String imgUrl;
    private String owner;

    public Recipe(String name, String chef, String imgUrl, String owner) {
        this.name = name;
        this.chef = chef;
        this.imgUrl = imgUrl;
        this.owner = owner;
    }
    public Recipe() {
    }
  
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public List<String> getIngredients() {
         return ingredients;
     }

     public void setIngredients(List<String> list) {
         this.ingredients = list;
     }

     public List<String> getSteps() {
         return steps;
     }

     public void setSteps(List<String> list) {
         this.steps = list;
     }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    

}
