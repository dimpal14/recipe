package com.projectsap.recipes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectsap.recipes.entities.RecipeEntity;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RecipeResponse {
  @JsonProperty("recipes")
  private List<RecipeEntity> recipeEntities;

  private int total;
  private int skip;
  private int limit;
}
