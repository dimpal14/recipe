package com.projectsap.recipes.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class RecipeEntity {
  @Id private Long id;

  @Column(nullable = false)
  private String name;

  @ElementCollection
  @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "ingredient")
  private List<String> ingredients;

  @ElementCollection
  @CollectionTable(name = "recipe_instructions", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "instruction")
  private List<String> instructions;

  private int prepTimeMinutes;
  private int cookTimeMinutes;
  private int servings;
  private String difficulty;

  @Column(nullable = false)
  private String cuisine;

  private int caloriesPerServing;

  @ElementCollection
  @CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "tag")
  private List<String> tags;

  private Long userId;

  private String image;

  private double rating;

  private int reviewCount;

  @ElementCollection
  @CollectionTable(name = "recipe_meal_types", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "meal_type")
  private List<String> mealType;
}
