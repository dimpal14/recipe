import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../recipe.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-recipe-search',
  templateUrl: './recipe-search.component.html',
  styleUrls: ['./recipe-search.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
})
export class RecipeSearchComponent implements OnInit {
  keyword: string = '';
  recipes: any[] = [];
  errorMessage: string = '';
  allRecipes: any[] = [];
  currentPage: number = 0; // Page numbers start at 0
  totalPages: number = 0;
  pageSize: number = 8; // Default page size set to 10

  constructor(private recipeService: RecipeService) {}

  ngOnInit() {
    // Load all recipes (initial page)
    this.loadAllRecipes(this.currentPage);
  }

  loadAllRecipes(page: number) {
    this.recipeService.getAllRecipes(page, this.pageSize).subscribe({
      next: (data: any) => {
        this.allRecipes = data.content; // Recipes for the current page
        this.currentPage = data.pageable.pageNumber;
        this.totalPages = data.totalPages;
        this.errorMessage = ''; // Clear error messages on success
      },
      error: (error) => {
        console.error('Error:', error);
        this.allRecipes = [];
        this.errorMessage = 'Failed to fetch recipes. Please try again later.';
      },
    });
  }

  searchRecipes() {
    if (this.keyword.length >= 3) {
        this.recipeService.searchRecipes(this.keyword).subscribe({
            next: (data: any[]) => {
                
                this.recipes = data; // Search results
                if (this.recipes.length === 0) {
                    this.errorMessage = 'No recipes found. Try a different keyword, but Don\'t worry we have listed below all the recipes for you 😋';
                } else {
                    this.errorMessage = ''; // Clear error messages on success
                }
            },
            error: (error) => {
                console.error('Error:', error);
                this.recipes = [];
                this.errorMessage = 'Failed to fetch recipes. Please try again later.';
            },
        });
    } else {
        this.errorMessage = 'Please enter at least 3 characters to search.';
    }
}


  // Handle pagination navigation
  changePage(newPage: number) {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.loadAllRecipes(newPage);
    }
  }
}
