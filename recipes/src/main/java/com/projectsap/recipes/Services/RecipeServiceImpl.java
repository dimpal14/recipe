package com.projectsap.recipes.Services;

import com.projectsap.recipes.domain.RecipeResponse;
import com.projectsap.recipes.entities.RecipeEntity;
import com.projectsap.recipes.repos.RecipeRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;
  private final RestTemplate restTemplate;

  @Autowired
  public RecipeServiceImpl(RecipeRepository recipeRepository, RestTemplate restTemplate) {
    this.recipeRepository = recipeRepository;
    this.restTemplate = restTemplate;
  }

  @Override
  public void loadRecipes(String recipesApiUrl) {
    RecipeResponse response = restTemplate.getForObject(recipesApiUrl, RecipeResponse.class);

    String responses = restTemplate.getForObject(recipesApiUrl, String.class);

    log.info("Recipes response: {}", response);
    if (response != null && response.getRecipeEntities() != null) {
      response
          .getRecipeEntities()
          .forEach(
              recipe -> {
                Optional<RecipeEntity> existingRecipe = recipeRepository.findById(recipe.getId());
                if (existingRecipe.isEmpty()) {
                  log.info("Saving recipe: {}", recipe);
                  recipeRepository.save(recipe);
                }
              });
    }
  }

  @Override
  public List<RecipeEntity> searchRecipes(String keyword) {
    return recipeRepository.searchByKeyword(keyword);
  }

  @Override
  public RecipeEntity getRecipeById(Long id) {
    return recipeRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Recipe not found"));
  }

  @Override
  public Page<RecipeEntity> getAllRecipes(Pageable pageable) {
    return recipeRepository.findAll(pageable);
  }
}
