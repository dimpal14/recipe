package com.projectsap.recipes.repos;

import com.projectsap.recipes.entities.RecipeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
  @Query(
      "SELECT r FROM RecipeEntity r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(r.cuisine) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<RecipeEntity> searchByKeyword(@Param("keyword") String keyword);
}
