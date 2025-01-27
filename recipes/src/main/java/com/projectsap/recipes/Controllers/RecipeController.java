package com.projectsap.recipes.Controllers;

import com.projectsap.recipes.Services.RecipeService;
import com.projectsap.recipes.entities.RecipeEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {

  private final RecipeService recipeService;

  @Autowired
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @PostMapping("/load")
  public String loadRecipes(@RequestParam String recipesApiUrl) {
    recipeService.loadRecipes(recipesApiUrl);
    return "Recipes loaded successfully";
  }

  @GetMapping("/search")
  public List<RecipeEntity> searchRecipes(@RequestParam String keyword) {
    return recipeService.searchRecipes(keyword);
  }

  @GetMapping("/{id}")
  public RecipeEntity getRecipeById(@PathVariable Long id) {
    return recipeService.getRecipeById(id);
  }

  @GetMapping()
  public Page<RecipeEntity> getAllRecipes(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return recipeService.getAllRecipes(pageable);
  }
}
