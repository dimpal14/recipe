package com.projectsap.recipes.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.projectsap.recipes.domain.RecipeResponse;
import com.projectsap.recipes.entities.RecipeEntity;
import com.projectsap.recipes.repos.RecipeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

class RecipeServiceImplTest {

  @Mock private RecipeRepository recipeRepository;
  @Mock private RestTemplate restTemplate;

  @InjectMocks private RecipeServiceImpl recipeServiceImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void loadRecipes_savesNewRecipes() {
    String apiUrl = "http://example.com/recipes";
    RecipeResponse response = new RecipeResponse();
    RecipeEntity recipe = new RecipeEntity();
    recipe.setId(1L);
    response.setRecipeEntities(List.of(recipe));

    when(restTemplate.getForObject(apiUrl, RecipeResponse.class)).thenReturn(response);
    when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

    recipeServiceImpl.loadRecipes(apiUrl);

    verify(recipeRepository, times(1)).save(recipe);
  }

  @Test
  void loadRecipes_doesNotSaveExistingRecipes() {
    String apiUrl = "http://example.com/recipes";
    RecipeResponse response = new RecipeResponse();
    RecipeEntity recipe = new RecipeEntity();
    recipe.setId(1L);
    response.setRecipeEntities(List.of(recipe));

    when(restTemplate.getForObject(apiUrl, RecipeResponse.class)).thenReturn(response);
    when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

    recipeServiceImpl.loadRecipes(apiUrl);

    verify(recipeRepository, never()).save(recipe);
  }

  @Test
  void searchRecipes_returnsMatchingRecipes() {
    String keyword = "chocolate";
    List<RecipeEntity> recipes = List.of(new RecipeEntity());

    when(recipeRepository.searchByKeyword(keyword)).thenReturn(recipes);

    List<RecipeEntity> result = recipeServiceImpl.searchRecipes(keyword);

    assertEquals(recipes, result);
  }

  @Test
  void getRecipeById_returnsRecipeIfExists() {
    Long id = 1L;
    RecipeEntity recipe = new RecipeEntity();

    when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

    RecipeEntity result = recipeServiceImpl.getRecipeById(id);

    assertEquals(recipe, result);
  }

  @Test
  void getRecipeById_throwsExceptionIfNotFound() {
    Long id = 1L;

    when(recipeRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> recipeServiceImpl.getRecipeById(id));
  }

  @Test
  void getAllRecipes_returnsPagedRecipes() {
    PageRequest pageable = PageRequest.of(0, 10);
    List<RecipeEntity> recipes = List.of(new RecipeEntity());
    Page<RecipeEntity> page = new PageImpl<>(recipes);

    when(recipeRepository.findAll(pageable)).thenReturn(page);

    Page<RecipeEntity> result = recipeServiceImpl.getAllRecipes(pageable);

    assertEquals(page, result);
  }
}
