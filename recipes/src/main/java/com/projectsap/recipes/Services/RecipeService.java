package com.projectsap.recipes.Services;

import com.projectsap.recipes.entities.RecipeEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeService {
  void loadRecipes(String recipesApiUrl);

  List<RecipeEntity> searchRecipes(String keyword);

  RecipeEntity getRecipeById(Long id);

  Page<RecipeEntity> getAllRecipes(Pageable pageable);
}
